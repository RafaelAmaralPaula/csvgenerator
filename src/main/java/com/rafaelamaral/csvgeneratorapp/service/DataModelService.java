package com.rafaelamaral.csvgeneratorapp.service;

import com.rafaelamaral.csvgeneratorapp.config.AppConfig;
import com.rafaelamaral.csvgeneratorapp.config.AppConfig.Property;
import com.rafaelamaral.csvgeneratorapp.model.DataModel;
import com.rafaelamaral.csvgeneratorapp.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.rafaelamaral.csvgeneratorapp.util.FileUtils.getContentFile;

@Slf4j
@Service
public class DataModelService {

    private final JdbcTemplate jdbcTemplate;
    private final Property property;

    public DataModelService(JdbcTemplate jdbcTemplate, Property property) {
        this.jdbcTemplate = jdbcTemplate;
        this.property = property;
    }

    public List<DataModel> consult() {
        var dataModelList = new ArrayList<DataModel>();
        getContentFile(property.getQueriesPathName())
                .parallelStream()
                .forEach(obj -> {
                    try {
                        var lines = jdbcTemplate.queryForList(obj.getContent());
                        var dataModel = new DataModel();
                        dataModel.setFileName(obj.getName());
                        dataModel.setHeader(toStringConvertHeader(lines.get(0).keySet()));
                        lines.forEach(line -> dataModel.getRows().add(toStringConvertRow(line.values())));
                        dataModelList.add(dataModel);
                    } catch (Exception e) {
                        log.error("Error processing file '{}' {}", obj.getName(), e.toString());
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
