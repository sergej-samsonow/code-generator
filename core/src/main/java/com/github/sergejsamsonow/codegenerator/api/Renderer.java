package com.github.sergejsamsonow.codegenerator.api;

public interface Renderer<D> {

    public void setFormat(CodeFormat format);

    default void prepare(D data) {}

    public String render(D data);
}
