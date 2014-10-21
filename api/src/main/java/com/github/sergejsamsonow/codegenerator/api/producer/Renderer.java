package com.github.sergejsamsonow.codegenerator.producer;

public interface Renderer<D> {

    default void prepare(D data) {}

    public String render(D data);
}
