package com.github.sergejsamsonow.codegenerator.api.producer.sc;

import com.github.sergejsamsonow.codegenerator.api.producer.Renderer;

// TODO refactor rename to CodeWriterBasedRenderer
public abstract class BaseRenderer<D> implements Renderer<D> {

    private D data;
    private CodeWriter code;
    private MethodCodeWriter methodCode;

    public BaseRenderer(CodeFormat format) {
        final StringBuilder builder = new StringBuilder();
        code = new CodeWriter(format, 0, builder);
        methodCode = new MethodCodeWriter(format, builder);
    }

    private void setData(D data) {
        this.data = data;
    }

    private void cleanUp() {
        this.data = null;
    }

    private String flush() {
        return code.flush();
    }

    final protected D getData() {
        return data;
    }

    // TODO rename to modify
    protected void prepare() {}

    protected void render() {}

    final protected CodeWriter getCodeWriter() {
        return code;
    }

    final protected MethodCodeWriter getMethodCodeWriter() {
        return methodCode;
    }

    // TODO rename to modify
    @Override
    final public void modify(D data) {
        setData(data);
        prepare();
        cleanUp();
    }

    @Override
    final public String render(D data) {
        setData(data);
        render();
        cleanUp();
        return flush();
    }

}
