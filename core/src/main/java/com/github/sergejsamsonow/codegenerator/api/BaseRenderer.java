package com.github.sergejsamsonow.codegenerator.api;

public abstract class BaseRenderer<D> implements Renderer<D> {

    private D data;
    private CodeWriter code;
    private MethodCodeWriter methodCode;

    @Override
    final public void setFormat(CodeFormat format) {
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

    protected void prepare() {}

    protected void render() {}

    final protected CodeWriter getCodeWriter() {
        return code;
    }

    final protected MethodCodeWriter getMethodCodeWriter() {
        return methodCode;
    }

    @Override
    final public void prepare(D data) {
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
