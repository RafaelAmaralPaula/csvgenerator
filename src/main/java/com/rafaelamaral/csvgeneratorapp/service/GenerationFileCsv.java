package com.rafaelamaral.csvgeneratorapp.service;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.rafaelamaral.csvgeneratorapp.repository.ExampleJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class GenerationFileCsv {

    @Autowired
    private ExampleJdbcTemplate exampleJdbcTemplate;

    public void execute() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        generateCsvFile();
    }

    private void generateCsvFile() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        exampleJdbcTemplate.findAll();

        Writer writer = Files.newBufferedWriter(Paths.get("files-csv/products.csv"));
        StatefulBeanToCsv<Object> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        // beanToCsv.write(list);

        writer.flush();
        writer.close();

    }

}
