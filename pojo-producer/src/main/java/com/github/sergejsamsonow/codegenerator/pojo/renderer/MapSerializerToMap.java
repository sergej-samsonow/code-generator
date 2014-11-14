package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCMethodCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCRendererForPropertiesContainer;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

public class MapSerializerToMap extends SCRendererForPropertiesContainer<PojoProperty, PojoBean> {

    public MapSerializerToMap(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void modify() {
        PojoBean bean = getData();
        bean.addToInterfaces("com.github.sergejsamsonow.codegenerator.utilities.MapSerializer");
        bean.addToImports("java.util.Map");
        bean.addToImports("java.util.HashMap");
        for (PojoProperty property : bean.getProperties()) {
            if (property.isList()) {
                bean.addToImports("java.util.List");
                bean.addToImports("java.util.ArrayList");
                break;
            }
        }
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
