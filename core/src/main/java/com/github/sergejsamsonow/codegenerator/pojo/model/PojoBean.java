package com.github.sergejsamsonow.codegenerator.pojo.model;

import java.util.Set;

public interface PojoBean extends ContainsProperties<PojoProperty> {

    public String getPackageName();

    public String getClassName();

    public Set<String> getImports();

    public void addToImports(String item);

    public void removeFromImports(String item);
}
