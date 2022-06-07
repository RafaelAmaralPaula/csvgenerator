package com.rafaelamaral.csvgeneratorapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryModel {

    private String name;
    private String query;
}
