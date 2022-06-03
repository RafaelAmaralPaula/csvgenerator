package com.rafaelamaral.csvgeneratorapp.model;

import java.util.ArrayList;
import java.util.List;

public class DataModel {
    private String column;

    private List<String> rows;

    public DataModel(String column) {
        this.column = column;
        this.rows = new ArrayList<>();
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }
}

