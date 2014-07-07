package com.example.mymodule.Parser;

import com.googlecode.jcsv.annotations.ValueProcessor;
import com.googlecode.jcsv.annotations.internal.ValueProcessorProvider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Created by jwatral on 26.06.2014.
*/
class AdaptedValueProcessorProvider extends ValueProcessorProvider {
    @Override
    public <E> ValueProcessor<E> getValueProcessor(Class<E> clazz) {
        ValueProcessor<E> valueProcessor;
        if(Date.class.equals(clazz)) {
            valueProcessor = new ValueProcessor<E>() {
                @Override
                public E processValue(String value) {
                    E result = null;
                    try {
                        result = (E) (new SimpleDateFormat("dd.MM.yyyy")).parse(value);
                    } catch (ParseException pe) {
                        throw new IllegalArgumentException(value + " can not be parsed as a date", pe);
                    }

                    return result;
                }
            };
        } else {
            valueProcessor = super.getValueProcessor(clazz);
        }

        return (ValueProcessor<E>) ValueProviderProxy.newInstance(valueProcessor);
    }

    private static class ValueProviderProxy implements InvocationHandler {
        private Object obj;
        public static Object newInstance(Object obj) {
            return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
                    new ValueProviderProxy(obj));
        }
        public ValueProviderProxy(Object obj) {
            this.obj = obj;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(args[0] instanceof String && ((String)args[0]).isEmpty()) {
                return null;
            }
            return method.invoke(obj, args);
        }
    }
}
