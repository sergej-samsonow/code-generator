package com.github.sergejsamsonow.codegenerator.api;

public interface ProducerEngine<P> {

    public void setRenderFormat(CodeFormat format);

    public Result process(P parsedItem);
}
