package com.github.sergejsamsonow.codegenerator.pojo.model;

import java.util.Set;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.PropertiesContainer;

public interface PojoBean extends PropertiesContainer<PojoProperty> {

    public String getPackageName();

    public String getClassName();

    public String getParentClass();

    public Set<String> getInterfaces();

    public void addToInterfaces(String item);

    public void removeFromInterfaces(String item);

    public Set<String> getImports();

    public void addToImports(String item);

    public void removeFromImports(String item);
}
