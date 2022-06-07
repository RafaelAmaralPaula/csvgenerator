package com.rafaelamaral.csvgeneratorapp.repository;

import com.rafaelamaral.csvgeneratorapp.config.AppConfig;
import com.rafaelamaral.csvgeneratorapp.model.DataModel;
import com.rafaelamaral.csvgeneratorapp.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class ExampleJdbcTemplate {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AppConfig.Property property;

    public List<DataModel> generate() {
        var dataModelList = new ArrayList<DataModel>();
        var result = FileUtils.getContentFile(property.getQueriesPathName());

        result.forEach(obj -> {
            try {
                var lines = jdbcTemplate.queryForList(obj.getQuery());
                var dataModel = new DataModel();
                dataModel.setFileName(obj.getName());
                dataModel.setHeader(toStringConvertHeader(lines.get(0).keySet()));
                lines.forEach(line -> dataModel.getRows().add(toStringConvertRow(line.values())));
                dataModelList.add(dataModel);
            } catch (Exception e) {
                log.error("Error: " + e);
            }
        });
        return dataModelList;
    }

    // TODO: Tentar fazer esses 2 metodos privados virar 1
    private String[] toStringConvertRow(Collection<Object> values) {
        String[] lines = new String[values.size()];
        int i = 0;

        for (Object line : values) {
            lines[i] = line.toString();
            i++;
        }

        return lines;
    }

    private String[] toStringConvertHeader(Set<String> keySet) {
        String[] header = new String[keySet.size()];
        int i = 0;

        for (String key : keySet) {
            header[i] = key;
            i++;
        }
        return header;
    }
}
