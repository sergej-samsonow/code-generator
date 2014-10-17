package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import java.util.List;
import com.github.sergejsamsonow.codegenerator.api.BaseRenderer;
import com.github.sergejsamsonow.codegenerator.api.CodeFormat;
import com.github.sergejsamsonow.codegenerator.api.MethodCodeWriter;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

public class Setter extends BaseRenderer<PojoBean> {

    private List<PojoProperty> properties;
    private MethodCodeWriter writer;

    public Setter(CodeFormat format) {
        super(format);
    }

    @Override
    protected void render() {
        prepareForRendering();
        writeSettersCode();
        cleanUpAfterRendering();
    }

    private void prepareForRendering() {
        properties = getData().getProperties();
        writer = getMethodCodeWriter();
    }

    private void writeSettersCode() {
        for (PojoProperty current : properties)
            writeCurrent(current.getDeclarationType(), current.getFieldName(), current.getSetterName());
    }

    private void writeCurrent(String type, String field, String setter) {
        writer.start("public void %s(%s %s) {", setter, type, field);
        writer.code("this.%s = %s;", field, field);
        writer.end();
    }

    private void cleanUpAfterRendering() {
        properties = null;
        writer = null;
    }

}
