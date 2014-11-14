package com.github.sergejsamsonow.codegenerator.pojo.renderer.mapserializer;

import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

public class BeanModifier {

    public static final String MODIFIER_INTERFACE = "com.github.sergejsamsonow.codegenerator.utilities.MapSerializer";

    public void modify(PojoBean bean) {
        bean.addToInterfaces(MODIFIER_INTERFACE);
        bean.addToImports("java.util.Map");
        bean.addToImports("java.util.HashMap");
        for (PojoProperty property : bean.getProperties()) {
            if (property.isList()) {
                bean.addToImports("java.util.List");
                bean.addToImports("java.util.ArrayList");
                break;
            }
        }
    }
}
