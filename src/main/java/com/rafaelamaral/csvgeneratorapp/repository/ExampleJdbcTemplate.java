package com.rafaelamaral.csvgeneratorapp.repository;

import com.rafaelamaral.csvgeneratorapp.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class ExampleJdbcTemplate {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void findAll(){
        var listColumnValue = new ArrayList<>();
        jdbcTemplate.query("select * from tb_product", resultSet -> {
            var listColumnName = new ArrayList<DataModel>();

            // String[][] dados = new String[resultSet.getMetaData().getColumnCount()][];

            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                var dataModel = new DataModel(resultSet.getMetaData().getColumnName(i));
                listColumnName.add(dataModel);
            }

            listColumnName.forEach(dataModel -> {
                try {
                    //System.out.println("Coluna: " + dataModel.getColumn() + " Valor: " + resultSet.getString(dataModel.getColumn()));
                    var data = resultSet.getString(dataModel.getColumn());
                    System.out.println(data);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });

        });
    }

}
