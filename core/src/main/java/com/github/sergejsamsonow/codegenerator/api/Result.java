package com.github.sergejsamsonow.codegenerator.api;

public class Result {

    private String subPath;
    private String code;

    public Result(String subPath, String code) {
        this.subPath = subPath;
        this.code = code;
    }

    public String getSubPath() {
        return subPath;
    }

    public String getCode() {
        return code;
    }

}
