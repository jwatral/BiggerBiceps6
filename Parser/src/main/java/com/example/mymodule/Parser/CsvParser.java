package com.example.mymodule.Parser;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.annotations.internal.ValueProcessorProvider;
import com.googlecode.jcsv.reader.CSVEntryParser;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.AnnotationEntryParser;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import com.googlecode.jcsv.reader.internal.DefaultCSVEntryParser;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.List;


//import com.googlecode.jmapper.JMapper;

/**
 * Created by jwatral on 24.06.2014.
 */
public class CsvParser {
    public static <T> List<T> importFromCsvFile(FileDescriptor f, Class<T> beanClass){
        try {
            return doImport(f, beanClass);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static <T> List<T> doImport(FileDescriptor f, Class<T> beanClass) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        Reader reader = new FileReader(f);

        ValueProcessorProvider provider = new AdaptedValueProcessorProvider();
        CSVEntryParser<T> entryParser = new AnnotationEntryParser<T>(beanClass, provider);
        CSVStrategy strategy = new CSVStrategy(';', '"', '#', true, true);
        CSVReader<T> csvPersonReader = new CSVReaderBuilder<T>(new FileReader(f)).strategy(strategy).entryParser(entryParser).build();
        return csvPersonReader.readAll();

//        return BeanMerger.mergeBeans(csvPersonReader.iterator(), beanClass);
    }

}
