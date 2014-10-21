package com.github.sergejsamsonow.codegenerator.api;

public interface ProducerAccess<P> {

    public void newItem(P parsedItem);
}
