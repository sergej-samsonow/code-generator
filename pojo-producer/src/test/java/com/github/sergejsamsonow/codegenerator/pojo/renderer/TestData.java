package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.pojo.model.SimplePojoProperty;

public class TestData {

    public SCNewLineAndIndentationFormat format;
    public String className = "Example";
    public PojoProperty nameString;
    public PojoProperty numbersIntegerList;
    public PojoProperty addressComplex;
    public PojoProperty personsList;

    public TestData() {
        format = SCNewLineAndIndentationFormat.unixWithSpaces(4);
        nameString = new SimplePojoProperty("name", "String");
        numbersIntegerList = new SimplePojoProperty("numbers", "List<Integer>");
        addressComplex = new SimplePojoProperty("address", "Address");
        personsList = new SimplePojoProperty("persons", "List<Person>");
    }
}