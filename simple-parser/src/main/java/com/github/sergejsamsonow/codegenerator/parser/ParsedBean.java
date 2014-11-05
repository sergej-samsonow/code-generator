package com.github.sergejsamsonow.codegenerator.parser;

import java.util.List;

public interface ParsedBean {

    public abstract String getNamespace();

    public abstract String getType();

    public abstract String getParentType();

    public abstract List<ParsedProperty> getProperties();

}