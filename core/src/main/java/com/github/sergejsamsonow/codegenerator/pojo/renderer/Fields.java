package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.AbstractPropertyRenderer;
import com.github.sergejsamsonow.codegenerator.api.CodeFormat;
import com.github.sergejsamsonow.codegenerator.api.CodeWriter;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

public class Fields extends AbstractPropertyRenderer<PojoProperty, PojoBean> {

    public Fields(CodeFormat format) {
        super(format);
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        String name = property.getFieldName();
        String type = property.getDeclarationType();
        CodeWriter classCode = getCodeWriter().indent();
        classCode.line("private %s %s;", type, name);
    }

    @Override
    protected void writeAfterPropertyIteration() {
        getCodeWriter().emptyNewLine();
    }
}
