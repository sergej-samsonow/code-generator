package com.github.sergejsamsonow.codegenerator.pojo.model;

import java.util.HashSet;
import java.util.List;
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

}
