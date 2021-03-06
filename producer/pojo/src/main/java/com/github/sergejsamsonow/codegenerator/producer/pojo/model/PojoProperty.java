package com.github.sergejsamsonow.codegenerator.producer.pojo.model;

import java.util.Set;

public interface PojoProperty {

    public String getFieldName();

    public boolean isList();

    public boolean isSimpleTypeContainer();

    public String getContainedType();

    public String getInitCode();

    public Set<String> getImportedTypes();

    public String getDeclarationType();

    public String getGetterName();

    public String getSetterName();

}