package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.BaseRenderer;
import com.github.sergejsamsonow.codegenerator.api.CodeFormat;
import com.github.sergejsamsonow.codegenerator.api.CodeWriter;

public class ClassFooter extends BaseRenderer<Object> {

    public ClassFooter(CodeFormat format) {
        super(format);
    }

    @Override
    protected void render() {
        CodeWriter codeWriter = getCodeWriter();
        codeWriter.line("}");
        codeWriter.emptyNewLine();
    };

}
