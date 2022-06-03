package com.rafaelamaral.csvgeneratorapp.service;

import com.opencsv.CSVWriter;
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
import java.util.Arrays;

@Service
public class GenerationFileCsv {

    @Autowired
    private ExampleJdbcTemplate exampleJdbcTemplate;

    public void execute() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        generateCsvFile();
    }

    private void generateCsvFile() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        var dataModel = exampleJdbcTemplate.findOne();

        Writer writer = Files.newBufferedWriter(Paths.get("files-csv/products.csv"));
        CSVWriter csvWriter = new CSVWriter(writer);
        csvWriter.writeNext(dataModel.getHeader());
        csvWriter.writeAll(dataModel.getRows());

        csvWriter.flush();
        writer.close();
    }

}
