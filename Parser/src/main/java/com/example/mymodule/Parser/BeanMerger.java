package com.example.mymodule.Parser;

import com.googlecode.openbeans.BeanInfo;
import com.googlecode.openbeans.IntrospectionException;
import com.googlecode.openbeans.Introspector;
import com.googlecode.openbeans.PropertyDescriptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jwatral on 26.06.2014.
 */
public class BeanMerger {
    static <T> List<T> mergeBeans(Iterator<T> iterator, Class<T> beanClass) throws IllegalAccessException, InstantiationException {
        List<T> result = new ArrayList<T>();
        T previousBean = iterator.next();
        while (iterator.hasNext()){
            T currentBean = iterator.next();
            T newBean = beanClass.newInstance();
            copyProperties(previousBean, newBean);
            merge(newBean, currentBean);
            result.add(newBean);
            previousBean = newBean;
        }
        return result;
    }

    public static void copyProperties(Object fromObj, Object toObj) {
        Class<? extends Object> fromClass = fromObj.getClass();
        Class<? extends Object> toClass = toObj.getClass();

        try {
            BeanInfo fromBean = Introspector.getBeanInfo(fromClass);
            BeanInfo toBean = Introspector.getBeanInfo(toClass);

            PropertyDescriptor[] toPd = toBean.getPropertyDescriptors();
            List<PropertyDescriptor> fromPd = Arrays.asList(fromBean
                    .getPropertyDescriptors());

            for (PropertyDescriptor propertyDescriptor : toPd) {
                propertyDescriptor.getDisplayName();
                PropertyDescriptor pd = fromPd.get(fromPd
                        .indexOf(propertyDescriptor));
                if (pd.getDisplayName().equals(
                        propertyDescriptor.getDisplayName())
                        && !pd.getDisplayName().equals("class")) {
                    if(propertyDescriptor.getWriteMethod() != null)
                        propertyDescriptor.getWriteMethod().invoke(toObj, pd.getReadMethod().invoke(fromObj, null));
                }

            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void merge(Object obj, Object update){
        if(obj == null || !obj.getClass().isAssignableFrom(update.getClass())){
            return;
        }

        Method[] methods = obj.getClass().getMethods();

        for(Method fromMethod: methods){
            if(fromMethod.getDeclaringClass().equals(obj.getClass())
                    && fromMethod.getName().startsWith("get")){

                String fromName = fromMethod.getName();
                String toName = fromName.replace("get", "set");

                try {
                    Method toMetod = obj.getClass().getMethod(toName, fromMethod.getReturnType());
                    Object value = fromMethod.invoke(update, (Object[])null);
                    if(value != null){
                        toMetod.invoke(obj, value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
