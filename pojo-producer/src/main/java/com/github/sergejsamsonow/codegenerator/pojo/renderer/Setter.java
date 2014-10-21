package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCMethodCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCRendererForPropertiesContainer;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

public class Setter extends SCRendererForPropertiesContainer<PojoProperty, PojoBean> {

    public Setter(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        String type = property.getDeclarationType();
        String field = property.getFieldName();
        String name = property.getSetterName();
        SCMethodCodeConcatenator method = getMethodCodeWriter();
        method.start("public void %s(%s %s) {", name, type, field);
        method.code("this.%s = %s;", field, field);
        method.end();
        method.emptyNewLine();
    }
}
