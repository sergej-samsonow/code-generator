package com.github.sergejsamsonow.codegenerator.pojo.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SimplePojoBean implements PojoBean {

    private String packageName;
    private String className;
    private List<PojoProperty> properties;
    private Set<String> imports;

    public SimplePojoBean(String packageName, String className, List<PojoProperty> properties) {
        this.packageName = packageName;
        this.className = className;
        this.properties = properties;
        this.imports = new HashSet<>();
        for (PojoProperty property : properties)
            imports.addAll(property.getImportedTypes());
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public List<PojoProperty> getProperties() {
        return properties;
    }

    @Override
    public Set<String> getImports() {
        return imports;
    }

    @Override
    public void addToImports(String item) {
        this.imports.add(item);
    }

    @Override
    public void removeFromImports(String item) {
        this.imports.remove(item);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PojoBean))
            return false;
        PojoBean casted = (PojoBean) object;
        return Objects.equals(getPackageName(), casted.getPackageName())
            && Objects.equals(getClassName(), casted.getClassName())
            && Objects.equals(getImports(), casted.getImports())
            && Objects.equals(getProperties(), casted.getProperties());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPackageName(), getClassName(), getImports(), getProperties());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("SimplePojoBean (");
        result.append("packageName: " + getPackageName());
        result.append(", ");
        result.append("className: " + getClassName());
        result.append(", ");
        result.append("imports: " + Objects.toString(getImports()));
        result.append(", ");
        result.append("properties: " + Objects.toString(getProperties()));
        result.append(")");
        return result.toString();
    }
}
