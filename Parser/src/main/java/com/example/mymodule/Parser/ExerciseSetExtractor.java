package com.example.mymodule.Parser;




import org.dozer.DozerBeanMapper;

import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jwatral on 26.06.2014.
 */
public class ExerciseSetExtractor {
    public static <S, D> List<D> extractFromCsvFile(FileDescriptor csvFile, Class<S> beanClass, Class<D> entityClass) {
        List<S> csvBeans = CsvParser.importFromCsvFile(csvFile, beanClass);
//        JMapper<D, S> mapper = new JMapper<D, S>(entityClass, beanClass);
        return transformBeans(csvBeans, entityClass);
    }

    private static<S,D> List<D> transformBeans(List<S> sourceList, Class<D> entityClass) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<D> result = new ArrayList<D>();
        for(S element : sourceList) {
            result.add(mapper.map(element, entityClass));
        }
        return result;
    }
}
