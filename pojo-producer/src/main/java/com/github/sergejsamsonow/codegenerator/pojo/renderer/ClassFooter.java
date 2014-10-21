package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCRenderer;

public class ClassFooter<D> extends SCRenderer<D> {

    public ClassFooter(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void render() {
        SCCodeConcatenator codeWriter = getCodeWriter();
        codeWriter.line("}");
        codeWriter.emptyNewLine();
    };

}
