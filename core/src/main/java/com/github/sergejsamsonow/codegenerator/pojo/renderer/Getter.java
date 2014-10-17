package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.CodeFormat;
import com.github.sergejsamsonow.codegenerator.api.MethodCodeWriter;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

public class Getter extends AbstractPropertyRenderer<PojoProperty, PojoBean> {

    public Getter(CodeFormat format) {
        super(format);
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        String type = property.getDeclarationType();
        String name = property.getGetterName();
        String field = property.getFieldName();
        MethodCodeWriter method = getMethodCodeWriter();
        method.start("public %s %s() {", type, name);
        method.code("return %s;", field);
        method.end();
    }

}
