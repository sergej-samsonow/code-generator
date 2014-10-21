package com.github.sergejsamsonow.codegenerator.api.producer.sc;

// TODO Rename CodeWriterBasedRendererForPropertiesContainer
public abstract class AbstractPropertyRenderer<X, D extends ContainsProperties<X>> extends BaseRenderer<D> {

    public AbstractPropertyRenderer(CodeFormat format) {
        super(format);
    }

    // TODO refactor change to writeCodeBeforePropertiesIteration
    protected void writeBeforePropertyIteration() {}

    protected void writePropertyCode(X property) {}

    // TODO change to PropertiesIteration
    // TODO refactor change to writeCodeAfterPropertiesIteration
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