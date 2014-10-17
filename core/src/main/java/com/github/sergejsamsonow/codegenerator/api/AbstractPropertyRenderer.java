package com.github.sergejsamsonow.codegenerator.api;


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