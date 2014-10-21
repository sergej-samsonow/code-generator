package com.github.sergejsamsonow.codegenerator.api.producer.sc;

import com.github.sergejsamsonow.codegenerator.api.producer.Renderer;

public abstract class SCRenderer<D> implements Renderer<D> {

    private D data;
    private SCCodeConcatenator code;
    private SCMethodCodeConcatenator methodCode;

    public SCRenderer(SCNewLineAndIndentationFormat format) {
        final StringBuilder builder = new StringBuilder();
        code = new SCCodeConcatenator(format, 0, builder);
        methodCode = new SCMethodCodeConcatenator(format, builder);
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

    protected void modify() {}

    protected void render() {}

    final protected SCCodeConcatenator getCodeWriter() {
        return code;
    }

    final protected SCMethodCodeConcatenator getMethodCodeWriter() {
        return methodCode;
    }

    @Override
    final public void modify(D data) {
        setData(data);
        modify();
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
