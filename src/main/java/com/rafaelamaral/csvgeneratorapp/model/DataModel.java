package com.rafaelamaral.csvgeneratorapp.model;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

    private String[] header;
    private List<String[]> rows;

    public DataModel() {
        this.rows = new ArrayList<>();
    }

    public List<String[]> getRows() {
        return rows;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public String[] getHeader() {
        return header;
    }
}

