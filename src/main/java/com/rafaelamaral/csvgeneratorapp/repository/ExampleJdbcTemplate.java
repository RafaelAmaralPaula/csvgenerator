package com.rafaelamaral.csvgeneratorapp.repository;

import com.rafaelamaral.csvgeneratorapp.model.DataModel;
import com.rafaelamaral.csvgeneratorapp.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class ExampleJdbcTemplate {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DataModel findOne() throws IOException {
        var result = FileUtils.getContentFile();

        var lines = jdbcTemplate.queryForList("select * from tb_product");
        var dataModel = new DataModel();
        dataModel.setFileName("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        dataModel.setHeader(toStringConvertHeader(lines.get(0).keySet()));
        lines.forEach(line -> dataModel.getRows().add(toStringConvertRow(line.values())));
        return dataModel;
    }

    private String[] toStringConvertRow(Collection<Object> values) {
        String [] lines = new String[values.size()];
        int i = 0;

        for(Object line : values){
          lines[i] = line.toString();
          i++;
        }

        return lines;
    }

    private String[] toStringConvertHeader(Set<String> keySet){
        String[] header = new String[keySet.size()];
        int i = 0;

        for(String key : keySet ){
            header[i] = key;
            i++;
        }
        return header;
    }
}
