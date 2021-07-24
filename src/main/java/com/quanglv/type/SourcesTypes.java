package com.quanglv.type;

public enum SourcesTypes {

    PUBLIC("public"),
    PRIVATE("private");

    SourcesTypes(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return this.code;
    }
}
