package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCMethodCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.pojo.renderer.javalang.BeanModifier;

public class JavaLangHashCode extends BeanModifier {

    public JavaLangHashCode(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void writeBeforePropertiesIteration() {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.annotation("@Override");
        writer.start("public int hashCode() {");
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        if (isSingleProperty()) {
            writeSingleProperty(property);
        }
        else {
            writeCurrentProperty(property);
        }
    }

    @Override
    protected void writeAfterPropertiesIteration() {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.end();
        writer.emptyNewLine();
    }

    private void writeSingleProperty(PojoProperty property) {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.code("return Objects.hash(");
        writer.indentedCode("%s());", property.getGetterName());
    }

    private void writeCurrentProperty(PojoProperty property) {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        String end = isLast() ? ");" : ",";
        if (isFirst()) {
            writer.code("return Objects.hash(");
        }
        writer.indentedCode("%s()%s", property.getGetterName(), end);
    }
}
