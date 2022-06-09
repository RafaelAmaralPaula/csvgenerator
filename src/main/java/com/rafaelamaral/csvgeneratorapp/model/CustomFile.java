package com.rafaelamaral.csvgeneratorapp.model;

public class CustomFile {

    private final String fullName;

    public CustomFile(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return this.fullName.substring(0, this.fullName.lastIndexOf('.'));
    }

    public String getExtension() {
        return this.fullName.replace(getName(), "");
    }

}
