package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.pojo.model.SimplePojoProperty;

public class TestData {

    public String className = "Example";
    public PojoProperty nameString;
    public PojoProperty numbersIntegerList;
    public PojoProperty addressComplex;
    public PojoProperty personsList;

    public TestData() {
        this.nameString = new SimplePojoProperty("name", "String");
        this.numbersIntegerList = new SimplePojoProperty("numbers", "List<Integer>");
        this.addressComplex = new SimplePojoProperty("address", "Address");
        this.personsList = new SimplePojoProperty("persons", "List<Person>");
    }
}