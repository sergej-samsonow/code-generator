package com.github.sergejsamsonow.codegenerator.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract public class PartialRendererEngine<D, P> implements ProducerEngine<P> {

    private List<Renderer<D>> renderers;

    @SafeVarargs
    public PartialRendererEngine(Renderer<D>... renderers) {
        this.renderers = Arrays.asList(renderers);
    }

    private List<Renderer<D>> getRenderers() {
        return renderers == null ? Collections.emptyList() : renderers;
    }

    @Override
    final public void setRenderFormat(CodeFormat format) {
        for (Renderer<D> renderer : getRenderers())
            renderer.setFromat(format);
    }

    @Override
    final public Result process(P parsed) {
        D data = transform(parsed);
        prepareTransformed(data);
        return new Result(subpath(data), renderData(data));
    }

    private String renderData(D data) {
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

    abstract protected String subpath(D data);
}
