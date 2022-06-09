package com.rafaelamaral.csvgeneratorapp.engine;

import com.opencsv.CSVWriter;
import com.rafaelamaral.csvgeneratorapp.config.AppConfig;
import com.rafaelamaral.csvgeneratorapp.service.DataModelService;
import com.rafaelamaral.csvgeneratorapp.util.FileUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;

import static com.opencsv.ICSVWriter.DEFAULT_ESCAPE_CHARACTER;
import static com.opencsv.ICSVWriter.DEFAULT_LINE_END;
import static com.opencsv.ICSVWriter.DEFAULT_SEPARATOR;
import static com.opencsv.ICSVWriter.NO_QUOTE_CHARACTER;
import static com.rafaelamaral.csvgeneratorapp.util.FileUtils.createdDirectory;
import static com.rafaelamaral.csvgeneratorapp.util.FileUtils.makeFile;
import static java.nio.file.Files.newBufferedWriter;

@Service
public class CsvFileGenerator {

    private final DataModelService service;
    private final AppConfig.Property property;

    public CsvFileGenerator(DataModelService service, AppConfig.Property property) {
        this.service = service;
        this.property = property;
    }

    public void generate() {
        var path = createdDirectory(property.getResultsPathName());
        service.consult()
                .parallelStream()
                .forEach(dataModel -> {
                    try {
                        var writer = newBufferedWriter(Paths.get(makeFile(path, dataModel.getFileName())));
                        var csvWriter = new CSVWriter(writer, DEFAULT_SEPARATOR, NO_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_LINE_END);

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
