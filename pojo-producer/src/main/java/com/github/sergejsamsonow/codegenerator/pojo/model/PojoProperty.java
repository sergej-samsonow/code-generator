package com.github.sergejsamsonow.codegenerator.pojo.model;

import java.util.Set;

public interface PojoProperty {

    public String getFieldName();

    public boolean isList();

    public boolean contiansSimpleType();

    public String getInitCode();

    public Set<String> getImportedTypes();

    public String getDeclarationType();

    public String getGetterName();

    public String getSetterName();

}