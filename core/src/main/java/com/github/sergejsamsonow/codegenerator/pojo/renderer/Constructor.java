package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.AbstractPropertyRenderer;
import com.github.sergejsamsonow.codegenerator.api.CodeFormat;
import com.github.sergejsamsonow.codegenerator.api.MethodCodeWriter;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

public class Constructor extends AbstractPropertyRenderer<PojoProperty, PojoBean> {

    public Constructor(CodeFormat format) {
        super(format);
    }

    @Override
    protected void writeBeforePropertyIteration() {
        MethodCodeWriter constructor = getMethodCodeWriter();
        constructor.start("public %s() {", getData().getClassName());
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        MethodCodeWriter constructor = getMethodCodeWriter();
        constructor.code("%s(%s);", property.getSetterName(), property.getInitCode());
    }

    @Override
    protected void writeAfterPropertyIteration() {
        MethodCodeWriter constructor = getMethodCodeWriter();
        constructor.end();
        constructor.emptyNewLine();
    }

}
