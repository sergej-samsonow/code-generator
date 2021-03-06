package com.github.sergejsamsonow.codegenerator.producer.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCMethodCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.producer.pojo.renderer.javalang.BeanModifier;

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
    protected void writeAfterPropertiesIteration() {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.end();
        writer.emptyNewLine();
    }

    @Override
    protected void writeSinglePropertyCode(PojoProperty property) {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        String getter = property.getGetterName();
        writer.code("return Objects.equals(%s(), casted.%s());", getter, getter);
    }

    @Override
    protected void writeCurrentPropertyCode(PojoProperty property) {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        String getter = property.getGetterName();
        if (isFirst()) {
            writer.code("return Objects.equals(%s(), casted.%s())", getter, getter);
        }
        else {
            String end = isLast() ? ";" : "";
            writer.indentedCode("&& Objects.equals(%s(), casted.%s())%s", getter, getter, end);
        }
    }

}
