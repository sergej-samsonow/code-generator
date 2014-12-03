package com.github.sergejsamsonow.codegenerator.producer.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCMethodCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.producer.pojo.renderer.javalang.BeanModifier;

public class JavaLangToString extends BeanModifier {

    public JavaLangToString(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void writeBeforePropertiesIteration() {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.annotation("@Override");
        writer.start("public String toString() {");
        writer.code("StringBuilder builder = new StringBuilder();");
        writer.code("builder.append(\"%s (\");", getData().getClassName());
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        String end = isLast() ? ");" : " + \", \");";
        writer.code("builder.append(\"%s: \" + Objects.toString(%s())%s",
            property.getFieldName(), property.getGetterName(), end);
    }

    @Override
    protected void writeAfterPropertiesIteration() {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.code("builder.append(\")\");");
        writer.code("return builder.toString();");
        writer.end();
        writer.emptyNewLine();
    }

}
