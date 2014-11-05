package com.github.sergejsamsonow.codegenerator.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimpleParsedBean implements ParsedBean {

    private String namespace;
    private String type;
    private String parentType;
    private List<ParsedProperty> properties;

    public SimpleParsedBean(String namespace, String type, String parentType, List<ParsedProperty> properties) {
        this.namespace = namespace;
        this.type = type;
        this.parentType = parentType == null ? "" : parentType;
        this.properties = new ArrayList<>(properties);
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getParentType() {
        return parentType;
    }

    @Override
    public List<ParsedProperty> getProperties() {
        return new ArrayList<>(properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNamespace(), getType(), getProperties(), getParentType());
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SimpleParsedBean)) {
            return false;
        }
        ParsedBean casted = (ParsedBean) object;
        return Objects.equals(getNamespace(), casted.getNamespace())
            && Objects.equals(getType(), casted.getType())
            && Objects.equals(getParentType(), casted.getParentType())
            && Objects.equals(getProperties(), casted.getProperties());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ParsedBean (");
        builder.append("namespace: " + getNamespace());
        builder.append(", ");
        builder.append("type: " + getType());
        builder.append(", ");
        builder.append("parentType: " + getParentType());
        builder.append(", ");
        builder.append("properties: " + Objects.toString(getProperties()));
        builder.append(")");
        return builder.toString();
    }
}
