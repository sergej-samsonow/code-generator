package com.github.sergejsamsonow.codegenerator.api.producer.sc;

public abstract class SCRendererForPropertiesContainer<X, D extends PropertiesContainer<X>> extends SCRenderer<D> {

    public SCRendererForPropertiesContainer(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    protected void writeBeforePropertiesIteration() {}

    protected void writePropertyCode(X property) {}

    protected void writeAfterPropertiesIteration() {}

    @Override
    protected void render() {
        writeBeforePropertiesIteration();
        iterateProperties();
        writeAfterPropertiesIteration();
    }

    private void iterateProperties() {
        for (X property : getData().getProperties()) {
            writePropertyCode(property);
        }
    }

}