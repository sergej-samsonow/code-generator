package com.github.sergejsamsonow.codegenerator.pojo.renderer.javalang;

import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCRendererForPropertiesContainer;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

public class BeanModifier extends SCRendererForPropertiesContainer<PojoProperty, PojoBean> {

    public BeanModifier(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void modify() {
        getData().addToImports("java.util.Objects");
    }

}
