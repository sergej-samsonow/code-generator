package com.github.sergejsamsonow.codegenerator.api;

public class Producer<P> implements ProducerAccess<P> {

    private WriterAccess writer;
    private ProducerEngine<P> engine;

    public Producer(WriterAccess writer, CodeFormat format, ProducerEngine<P> engine) {
        this.engine = engine;
        this.writer = writer;
        this.engine.setRenderFormat(format);
    }

    @Override
    public void newItem(P parsedItem) {
        Result result = engine.process(parsedItem);
        writer.write(result.getSubPath(), result.getCode());
    }

}
