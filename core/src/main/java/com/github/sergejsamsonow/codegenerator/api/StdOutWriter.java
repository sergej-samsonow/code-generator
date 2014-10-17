package com.github.sergejsamsonow.codegenerator.api;

public class StdOutWriter implements WriterAccess {

    @Override
    public void write(String subpath, String code) {
        System.out.println("Path: " + subpath);
        System.out.print(code);
    }

}
