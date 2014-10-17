package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.AbstractPropertyRenderer;
import com.github.sergejsamsonow.codegenerator.api.CodeFormat;
import com.github.sergejsamsonow.codegenerator.api.MethodCodeWriter;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

public class Setter extends AbstractPropertyRenderer<PojoProperty, PojoBean> {

    public Setter(CodeFormat format) {
        super(format);
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        String type = property.getDeclarationType();
        String field = property.getFieldName();
        String name = property.getSetterName();
        MethodCodeWriter method = getMethodCodeWriter();
        method.start("public void %s(%s %s) {", name, type, field);
        method.code("this.%s = %s;", field, field);
        method.end();
    }
}
