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
    protected void writeAfterPropertiesIteration() {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.end();
        writer.emptyNewLine();
    }

    @Override
    protected void writeSinglePropertyCode(PojoProperty property) {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.code("return Objects.hash(");
        writer.indentedCode("%s());", property.getGetterName());
    }

    @Override
    protected void writeCurrentPropertyCode(PojoProperty property) {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        String end = isLast() ? ");" : ",";
        if (isFirst()) {
            writer.code("return Objects.hash(");
        }
        writer.indentedCode("%s()%s", property.getGetterName(), end);
    }
}
