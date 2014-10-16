package com.github.sergejsamsonow.codegenerator.pojo.model;

import java.util.List;
import java.util.Set;

public interface PojoBean {

    public String getPackageName();

    public String getClassName();

    public List<PojoProperty> getProperties();

    public Set<String> getImports();

    public void addToImports(String item);

    public void removeFromImports(String item);
}
