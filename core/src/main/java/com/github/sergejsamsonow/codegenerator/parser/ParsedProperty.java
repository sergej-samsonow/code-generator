package com.github.sergejsamsonow.codegenerator.parser;

import java.util.Objects;

public class ParsedProperty {

    private String name;
    private String type;

    public ParsedProperty(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ParsedProperty)) return false;
        ParsedProperty casted = (ParsedProperty) object;
        return Objects.equals(getName(), casted.getName())
            && Objects.equals(getType(), casted.getType());
    }

}
