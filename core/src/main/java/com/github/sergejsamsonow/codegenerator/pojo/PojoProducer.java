package com.github.sergejsamsonow.codegenerator.pojo;

import com.github.sergejsamsonow.codegenerator.api.PartialRendererEngine;
import com.github.sergejsamsonow.codegenerator.api.ProducerAccess;
import com.github.sergejsamsonow.codegenerator.parser.ParsedBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;

public class PojoProducer extends PartialRendererEngine<PojoBean, ParsedBean> implements ProducerAccess<ParsedBean> {

    @Override
    protected PojoBean transform(ParsedBean parsed) {
        return null;
    }

    @Override
    protected String subpath(PojoBean data) {
        return null;
    }

    @Override
    public void newItem(ParsedBean parsedItem) {

    }

}
