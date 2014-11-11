package com.github.sergejsamsonow.codegenerator.parser;

import java.util.Objects;
import com.github.sergejsamsonow.codegenerator.api.parser.ParsedProperty;

public class SimpleParsedProperty implements ParsedProperty {

    private String name;
    private String type;

    public SimpleParsedProperty(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SimpleParsedProperty)) {
            return false;
        }
        ParsedProperty casted = (ParsedProperty) object;
        return Objects.equals(getName(), casted.getName())
            && Objects.equals(getType(), casted.getType());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ParsedPropery (");
        builder.append("name: " + getName());
        builder.append(", ");
        builder.append("type: " + getType());
        builder.append(")");
        return builder.toString();
    }
}
