package com.github.sergejsamsonow.codegenerator.producer.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCMethodCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCRendererForPropertiesContainer;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.producer.pojo.renderer.mapserializer.BeanModifier;

public class MapSerializerFromMap extends SCRendererForPropertiesContainer<PojoProperty, PojoBean> {

    private static final BeanModifier MODIFIER = new BeanModifier();
    public static final String MAP_ACCESS = "com.github.sergejsamsonow.codegenerator.utilities.MapAccess";

    public MapSerializerFromMap(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void modify() {
        PojoBean bean = getData();
        MODIFIER.modify(bean);
        bean.addToImports(MAP_ACCESS);
    }

    @Override
    protected void writeBeforePropertiesIteration() {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.annotation("@Override");
        writer.start("public void fromMap(Map<String, Object> map) {");
        writer.code("MapAccess mapAccess = new MapAccess(map);");
    }

    @Override
    protected void writePropertyCode(PojoProperty property) {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        boolean list = property.isList();
        String setter = property.getSetterName();
        String type = list ? property.getContainedType() : property.getDeclarationType();
        String field = property.getFieldName();
        if (property.isSimpleTypeContainer()) {
            String mapAccess = "mapAccess.get" + (list ? type + "List" : type);
            writer.code("%s(%s(\"%s\"));", setter, mapAccess, field);
        }
        else if (list) {
            String listVar = field + "List";
            String listItem = field + "Item";
            writer.code("List<%s> %s = new ArrayList<>();", type, listVar);
            writer.code("for (Map<String, Object> current : mapAccess.getMapList(\"%s\")) {", field);
            writer.indentedCode("%s %s = new %s();", type, listItem, type);
            writer.indentedCode("%s.fromMap(current);", listItem);
            writer.indentedCode("%s.add(%s);", listVar, listItem);
            writer.code("}");
            writer.code("%s(%s);", setter, listVar);
        }
        else {
            writer.code("%s %s = new %s();", type, field, type);
            writer.code("%s.fromMap(mapAccess.getMap(\"%s\"));", field, field);
            writer.code("%s(%s);", setter, field);
        }
    }

    @Override
    protected void writeAfterPropertiesIteration() {
        SCMethodCodeConcatenator writer = getMethodCodeWriter();
        writer.end();
        writer.emptyNewLine();
    }
}
