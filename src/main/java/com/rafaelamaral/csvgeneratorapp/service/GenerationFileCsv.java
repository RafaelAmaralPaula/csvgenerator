package com.rafaelamaral.csvgeneratorapp.service;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.rafaelamaral.csvgeneratorapp.repository.ExampleJdbcTemplate;
import com.rafaelamaral.csvgeneratorapp.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class GenerationFileCsv {

    @Autowired
    private ExampleJdbcTemplate exampleJdbcTemplate;

    public void execute() {
        generateCsvFile();
    }

    private void generateCsvFile() {

        exampleJdbcTemplate.generate().forEach(dataModel -> {
            try {
                var writer = Files.newBufferedWriter(Paths.get(FileUtils.createdDirectory("results") + "/" + dataModel.getFileName()));
                var csvWriter = new CSVWriter(writer, ',',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);

                csvWriter.writeNext(dataModel.getHeader());
                csvWriter.writeAll(dataModel.getRows());

                csvWriter.flush();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
