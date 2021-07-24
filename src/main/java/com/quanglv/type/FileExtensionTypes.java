package com.quanglv.type;

public enum FileExtensionTypes {
    XLSX("xlsx"),
    XLS("xls"),
    JPG("jpg"),
    JPED("jpeg"),
    PDF("pdf"),
    DOCX("docx"),
    DOC("doc");

    FileExtensionTypes(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return this.code;
    }
}
