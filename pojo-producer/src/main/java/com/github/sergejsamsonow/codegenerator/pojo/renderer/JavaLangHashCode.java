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
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        if (isFirst() && isLast()) {
            writer.code("return Objects.hash(");
            writer.indentedCode("%s());", property.getGetterName());
            writer.end();
            writer.emptyNewLine();

        }
        else if (isLast()) {
            writer.indentedCode("%s());", property.getGetterName());
            writer.end();
            writer.emptyNewLine();
        }
        else {
            if (isFirst()) {
                writer.code("return Objects.hash(");
            }
            writer.indentedCode("%s(),", property.getGetterName());
        }
    }

}
