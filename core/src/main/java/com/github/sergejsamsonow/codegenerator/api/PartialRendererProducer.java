package com.github.sergejsamsonow.codegenerator.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract public class PartialRendererProducer<D, P> implements ProducerAccess<P> {

    private List<Renderer<D>> renderers;

    @SafeVarargs
    public PartialRendererProducer(Renderer<D>... renderers) {
        this.renderers = Arrays.asList(renderers);
    }

    private List<Renderer<D>> getRenderers() {
        return renderers == null ? Collections.emptyList() : renderers;
    }

    @Override
    public void newItem(P parsed) {
        D data = transform(parsed);
        prepareTransformed(data);
        write(data, code(data));
    }

    private String code(D data) {
        StringBuilder output = new StringBuilder();
        for (Renderer<D> renderer : getRenderers())
            output.append(renderer.render(data));
        return output.toString();
    }

    private void prepareTransformed(D data) {
        for (Renderer<D> renderer : getRenderers())
            renderer.prepare(data);

    }

    abstract protected D transform(P parsed);

    abstract protected void write(D data, String code);
}