package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import java.util.List;
import com.github.sergejsamsonow.codegenerator.api.BaseRenderer;
import com.github.sergejsamsonow.codegenerator.api.CodeFormat;
import com.github.sergejsamsonow.codegenerator.api.CodeWriter;
import com.github.sergejsamsonow.codegenerator.api.MethodCodeWriter;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

public class FieldsDeclarationAndInitialization extends BaseRenderer<PojoBean> {

    private String name;
    private List<PojoProperty> properties;
    private CodeWriter classCode;
    private MethodCodeWriter constructor;

    public FieldsDeclarationAndInitialization(CodeFormat format) {
        super(format);
    }

    @Override
    protected void render() {
        prepareForRendering();
        writeDeclarationFields();
        writeConstructor();
        cleanUpAfterRendering();
    }

    private void prepareForRendering() {
        PojoBean bean = getData();
        name = bean.getClassName();
        properties = bean.getProperties();
        classCode = getCodeWriter().indent();
        constructor = getMethodCodeWriter();
    }

    private void writeDeclarationFields() {
        for (PojoProperty property : properties)
            classCode.line("private %s %s;", property.getDeclarationType(), property.getFieldName());
        classCode.emptyNewLine();
    }

    private void writeConstructor() {
        constructor.start("public %s() {", name);
        for (PojoProperty property : properties)
            constructor.code("%s(%s);", property.getSetterName(), property.getInitCode());
        constructor.end();
        constructor.emptyNewLine();
    }

    private void cleanUpAfterRendering() {
        name = null;
        properties = null;
        classCode = null;
        constructor = null;
    }
}
