package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCMethodCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.pojo.renderer.javalang.BeanModifier;

public class JavaLangEquals extends BeanModifier {

    public JavaLangEquals(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void writeBeforePropertiesIteration() {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        String type = getData().getClassName();
        writer.annotation("@Override");
        writer.start("public boolean equals(Object object) {");
        writer.code("if (!(object instanceof %s)) {", type);
        writer.indentedCode("return false;");
        writer.code("}");
        writer.code("%s casted = (%s) object;", type, type);
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        String getter = property.getGetterName();
        if (isFirst() && isLast()) {
            writer.code("return Objects.equals(%s(), casted.%s());", getter, getter);
            writer.end();
            writer.emptyNewLine();
        }
        else if (isFirst()) {
            writer.code("return Objects.equals(%s(), casted.%s())", getter, getter);
        }
        else if (isLast()) {
            writer.indentedCode("&& Objects.equals(%s(), casted.%s());", getter, getter);
            writer.end();
            writer.emptyNewLine();
        }
        else {
            writer.indentedCode("&& Objects.equals(%s(), casted.%s())", getter, getter);
        }
    }
}
