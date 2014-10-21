package com.github.sergejsamsonow.codegenerator.api.producer;

import java.util.Collections;
import com.github.sergejsamsonow.codegenerator.api.ProducerAccess;

// TODO Partial renderer based producer
abstract public class PartialRendererBasedProducer<D, P> implements ProducerAccess<P> {

    private Renderer<D>[] renderers;

    @SafeVarargs
    public PartialRendererBasedProducer(Renderer<D>... renderers) {
        this.renderers = renderers;
    }

    @SuppressWarnings("unchecked")
    private Renderer<D>[] getRenderers() {
        if (renderers == null)
            this.renderers = (Renderer<D>[]) Collections.emptyList().toArray();
        return renderers;
    }

    @Override
    public void newItem(P parsed) {
        D data = transform(parsed);
        modify(data);
        write(data, code(data));
    }

    private String code(D data) {
        StringBuilder output = new StringBuilder();
        for (Renderer<D> renderer : getRenderers())
            output.append(renderer.render(data));
        return output.toString();
    }

    private void modify(D data) {
        for (Renderer<D> renderer : getRenderers())
            renderer.modify(data);

    }

    abstract protected D transform(P parsed);

    abstract protected void write(D data, String code);
}