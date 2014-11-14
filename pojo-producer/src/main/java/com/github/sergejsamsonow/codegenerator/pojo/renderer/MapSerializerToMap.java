package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCMethodCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCRendererForPropertiesContainer;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.pojo.renderer.mapserializer.BeanModifier;

public class MapSerializerToMap extends SCRendererForPropertiesContainer<PojoProperty, PojoBean> {

    private static final BeanModifier MODIFIER = new BeanModifier();

    public MapSerializerToMap(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void modify() {
        MODIFIER.modify(getData());
    }

    @Override
    protected void writeBeforePropertiesIteration() {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.annotation("@Override");
        writer.start("public Map<String, Object> toMap() {");
        writer.code("Map<String, Object> resultMap = new HashMap<>();");
    };

    @Override
    protected void writePropertyCode(PojoProperty property) {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        boolean simple = property.isSimpleTypeContainer();
        String field = property.getFieldName();
        String getter = property.getGetterName();
        String template = simple ? "resultMap.put(\"%s\", %s());" : "resultMap.put(\"%s\", %s().toMap());";
        if ((!simple) && property.isList()) {
            String list = property.getFieldName() + "List";
            writer.code("List<Map<String, Object>> %s = new ArrayList<>();", list);
            writer.code("for (%s current : %s()) {", property.getContainedType(), getter);
            writer.indentedCode("%s.add(current.toMap());", list);
            writer.code("}");
            writer.code("resultMap.put(\"%s\", %s);", field, list);
        }
        else {
            writer.code(template, field, getter);
        }
    }

    @Override
    protected void writeAfterPropertiesIteration() {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.code("return resultMap;");
        writer.end();
        writer.emptyNewLine();
    }

}
