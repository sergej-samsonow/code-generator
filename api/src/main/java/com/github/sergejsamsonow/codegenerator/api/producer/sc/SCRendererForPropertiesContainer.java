package com.github.sergejsamsonow.codegenerator.api.producer.sc;

import java.util.List;

public abstract class SCRendererForPropertiesContainer<X, D extends PropertiesContainer<X>> extends SCRenderer<D> {

    private int index;
    private int last;

    public SCRendererForPropertiesContainer(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    private List<X> getProperties() {
        return getData().getProperties();
    }

    private void initIndex() {
        List<X> properties = getProperties();
        last = properties.size() > 0 ? properties.size() - 1 : 0;
        index = -1;
    }

    private void resetIndex() {
        last = -1;
        index = -1;
    }

    private void incrementIndex() {
        index++;
    }

    protected void writeBeforePropertiesIteration() {}

    protected void writePropertyCode(X property) {}

    protected void writeAfterPropertiesIteration() {}

    protected boolean isFirst() {
        return index == 0;
    }

    protected boolean isLast() {
        return index == last;
    }

    @Override
    protected void render() {
        writeBeforePropertiesIteration();
        iterateOverProperties();
        writeAfterPropertiesIteration();
    }

    private void iterateOverProperties() {
        initIndex();
        for (X property : getProperties()) {
            incrementIndex();
            writePropertyCode(property);
        }
        resetIndex();
    }

}