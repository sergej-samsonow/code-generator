package com.github.sergejsamsonow.codegenerator.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProducerTest {

    private static final String SUB_PATH = "subPath";

    private static final String CODE = "code";

    @Mock
    private WriterAccess writer;

    @Mock
    private CodeFormat format;

    @Mock
    @SuppressWarnings("rawtypes")
    private ProducerEngine engine;

    @Mock
    private Result result;

    @Mock
    private Object parsedItem;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        Mockito.when(result.getCode()).thenReturn(CODE);
        Mockito.when(result.getSubPath()).thenReturn(SUB_PATH);
        Mockito.when(engine.process(parsedItem)).thenReturn(result);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSetupRendererFormat() throws Exception {
        @SuppressWarnings("rawtypes")
        Producer producer = new Producer(writer, format, engine);
        producer.newItem(parsedItem);
        Mockito.verify(engine).setRenderFormat(format);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCallEngine() throws Exception {
        @SuppressWarnings("rawtypes")
        Producer producer = new Producer(writer, format, engine);
        producer.newItem(parsedItem);
        Mockito.verify(engine).process(parsedItem);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCallWriter() throws Exception {
        @SuppressWarnings("rawtypes")
        Producer producer = new Producer(writer, format, engine);
        producer.newItem(parsedItem);
        Mockito.verify(writer).write(SUB_PATH, CODE);
    }
}
