package com.github.sergejsamsonow.codegenerator.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PartialRendererProducerTest {

    private static final String CODE_ONE = "ONE";
    private static final String CODE_TWO = "TWO";
    private static final ParserData INPUT_D = new ParserData();
    private static final RendererData RENDERER_D = new RendererData();

    private static class ParserData {}
    private static class RendererData {}
    private static class Wrapper extends PartialRendererProducer<RendererData, ParserData> {

        private ParserData transformData;
        private RendererData writeData;
        private String writeCode;

        @SafeVarargs
        public Wrapper(Renderer<RendererData>... renderers) {
            super(renderers);
        }

        @Override
        protected RendererData transform(ParserData parsed) {
            transformData = parsed;
            return RENDERER_D;
        }

        @Override
        protected void write(RendererData data, String code) {
            writeData = data;
            writeCode = code;
        }
    }

    @Mock
    private Renderer<RendererData> one;

    @Mock
    private Renderer<RendererData> two;

    private Wrapper wraper;

    @Before
    public void setUp() {
        wraper = new Wrapper(one, two);
        Mockito.when(one.render(RENDERER_D)).thenReturn(CODE_ONE);
        Mockito.when(two.render(RENDERER_D)).thenReturn(CODE_TWO);
        wraper.newItem(INPUT_D);
    }

    @Test
    public void testNewItemTransformInvoced() throws Exception {
        assertThat(wraper.transformData, equalTo(INPUT_D));
    }

    @Test
    public void testNewItemRendererPrepareInvoced() throws Exception {
        verify(one).prepare(RENDERER_D);
        verify(two).prepare(RENDERER_D);
    }

    @Test
    public void testNewItemRendererRenderInvoced() throws Exception {
        verify(one).render(RENDERER_D);
        verify(two).render(RENDERER_D);
    }

    @Test
    public void testNewItemWriteMethodeInvoced() throws Exception {
        assertThat(wraper.writeData, equalTo(RENDERER_D));
        assertThat(wraper.writeCode, equalTo(CODE_ONE + CODE_TWO));
    }
}
