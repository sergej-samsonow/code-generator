package com.github.sergejsamsonow.codegenerator.producer.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCMethodCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCRendererForPropertiesContainer;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoProperty;

public class Constructor extends SCRendererForPropertiesContainer<PojoProperty, PojoBean> {

    public Constructor(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void writeBeforePropertiesIteration() {
        SCMethodCodeConcatenator constructor = getMethodCodeWriter();
        constructor.start("public %s() {", getData().getClassName());
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        SCMethodCodeConcatenator constructor = getMethodCodeWriter();
        constructor.code("%s(%s);", property.getSetterName(), property.getInitCode());
    }

    @Override
    protected void writeAfterPropertiesIteration() {
        SCMethodCodeConcatenator constructor = getMethodCodeWriter();
        constructor.end();
        constructor.emptyNewLine();
    }

}
