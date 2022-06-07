package com.rafaelamaral.csvgeneratorapp.engine;

import com.opencsv.CSVWriter;
import com.rafaelamaral.csvgeneratorapp.model.DataModel;
import com.rafaelamaral.csvgeneratorapp.service.DataModelService;
import com.rafaelamaral.csvgeneratorapp.util.FileUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class CsvFileGenerator {

    private final DataModelService service;

    public CsvFileGenerator(DataModelService service) {
        this.service = service;
    }

    public void execute() {
        service.generate().forEach(dataModel -> {
            var executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
            executor.setKeepAliveTime(2, TimeUnit.MINUTES);
            executor.allowCoreThreadTimeOut(true);
            executor.execute(getResults(dataModel));
        });
    }

    private Runnable getResults(DataModel dataModel) {
        return () -> {
            try {
                var file = FileUtils.createdDirectory("results") + "/" + dataModel.getFileName();
                var writer = Files.newBufferedWriter(Paths.get(file));
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
        };
    }
}
