package com.github.sergejsamsonow.codegenerator.api.producer;

public interface Renderer<D> {

    default void modify(D data) {}

    public String render(D data);
}
