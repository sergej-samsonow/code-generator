package com.github.sergejsamsonow.codegenerator.api.producer;

import com.github.sergejsamsonow.codegenerator.api.ProducerAccess;
import com.github.sergejsamsonow.codegenerator.api.WriterAccess;

abstract public class PartialRendererBasedProducer<D, P> implements ProducerAccess<P> {

    private WriterAccess writerAccess;
    private Renderer<D>[] renders;

    @SafeVarargs
    public PartialRendererBasedProducer(WriterAccess writerAccess, Renderer<D>... renders) {
        if (writerAccess == null) {
            throw new IllegalArgumentException("Writer access is null!");
        }
        if (renders == null) {
            throw new IllegalArgumentException("Renders list is null!");
        }
        this.writerAccess = writerAccess;
        this.renders = renders;
    }

    private Renderer<D>[] getRenderers() {
        return renders;
    }

    @Override
    public void newItem(P parsed) {
        D data = transform(parsed);
        writerAccess.write(subpath(data), render(data));
    }

    private String render(D data) {
        modify(data);
        StringBuilder output = new StringBuilder();
        for (Renderer<D> renderer : getRenderers()) {
            output.append(renderer.render(data));
        }
        return output.toString();
    }

    private void modify(D data) {
        for (Renderer<D> renderer : getRenderers()) {
            renderer.modify(data);
        }
    }

    abstract protected D transform(P parsed);

    abstract protected String subpath(D data);
}