package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCRendererForPropertiesContainer;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

public class Fields extends SCRendererForPropertiesContainer<PojoProperty, PojoBean> {

    public Fields(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        String name = property.getFieldName();
        String type = property.getDeclarationType();
        SCCodeConcatenator classCode = getCodeWriter().indent();
        classCode.line("private %s %s;", type, name);
    }

    @Override
    protected void writeAfterPropertiesIteration() {
        getCodeWriter().emptyNewLine();
    }
}
