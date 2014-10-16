package com.github.sergejsamsonow.codegenerator.pojo;

import com.github.sergejsamsonow.codegenerator.api.PartialRendererProducer;
import com.github.sergejsamsonow.codegenerator.parser.ParsedBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;

public class PojoProducer extends PartialRendererProducer<PojoBean, ParsedBean> {

    @Override
    protected PojoBean transform(ParsedBean parsed) {
        return null;
    }

    @Override
    public void newItem(ParsedBean parsedItem) {

    }

    @Override
    protected void write(PojoBean data, String code) {}

}
