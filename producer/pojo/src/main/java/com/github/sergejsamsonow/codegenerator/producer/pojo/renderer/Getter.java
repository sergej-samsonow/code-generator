package com.github.sergejsamsonow.codegenerator.producer.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCMethodCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCRendererForPropertiesContainer;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoProperty;

public class Getter extends SCRendererForPropertiesContainer<PojoProperty, PojoBean> {

    public Getter(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        String type = property.getDeclarationType();
        String name = property.getGetterName();
        String field = property.getFieldName();
        SCMethodCodeConcatenator method = getMethodCodeWriter();
        method.start("public %s %s() {", type, name);
        method.code("return %s;", field);
        method.end();
        method.emptyNewLine();
    }

}
