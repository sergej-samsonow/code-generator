package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import com.github.sergejsamsonow.codegenerator.api.BaseRenderer;
import com.github.sergejsamsonow.codegenerator.api.CodeFormat;
import com.github.sergejsamsonow.codegenerator.pojo.model.ContainsProperties;

public abstract class AbstractPropertyRenderer<X, D extends ContainsProperties<X>> extends BaseRenderer<D> {

    public AbstractPropertyRenderer(CodeFormat format) {
        super(format);
    }

    protected void writeBeforePropertyIteration() {}

    protected void writePropertyCode(X property) {}

    protected void writeAfterPropertyIteration() {}

    @Override
    protected void render() {
        writeBeforePropertyIteration();
        iterateProperties();
        writeAfterPropertyIteration();
    }

    private void iterateProperties() {
        for (X property : getData().getProperties())
            writePropertyCode(property);
    }

}