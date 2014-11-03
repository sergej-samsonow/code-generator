package com.github.sergejsamsonow.codegenerator.api.producer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.api.WriterAccess;

@RunWith(MockitoJUnitRunner.class)
public class PartialRendererBasedProducerTest {

    private static final String SUBPATH = "SUBPATH";
    private static final String CODE_ONE = "ONE";
    private static final String CODE_TWO = "TWO";
    private static final ParserData INPUT_D = new ParserData();

    private static class ParserData {}

    private static class RendererData {

        public void transformInvoced() {}

        public void subPathInvoced() {}

        public void renderModifyInvoced() {}

        public void renderRenderInvoced() {}
    }

    private static class Wrapper extends PartialRendererBasedProducer<RendererData, ParserData> {

        private RendererData data;

        @SafeVarargs
        public Wrapper(WriterAccess writer, RendererData data, Renderer<RendererData>... renderers) {
            super(writer, renderers);

            this.data = data;
        }

        @Override
        protected RendererData transform(ParserData parsed) {
            data.transformInvoced();
            return data;
        }

        @Override
        protected String subpath(RendererData data) {
            data.subPathInvoced();
            return SUBPATH;
        }
    }

    @Mock
    private RendererData data;

    @Mock
    private WriterAccess writer;

    @Mock
    private Renderer<RendererData> one;

    @Mock
    private Renderer<RendererData> two;

    private Wrapper wraper;

    @Before
    public void setUp() {
        wraper = new Wrapper(writer, data, one, two);
        Mockito.doAnswer(invocation -> {
            RendererData data = (RendererData) invocation.getArguments()[0];
            data.renderModifyInvoced();
            return null;
        }).when(one).modify(data);
        Mockito.doAnswer(invocation -> {
            RendererData data = (RendererData) invocation.getArguments()[0];
            data.renderRenderInvoced();
            return CODE_ONE;
        }).when(one).render(data);
        when(two.render(data)).thenReturn(CODE_TWO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPartialRendererBasedProducerNullWriter() throws Exception {
        new Wrapper(null, data, one);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPartialRendererBasedProducerNullRender() throws Exception {
        new Wrapper(writer, data, (Renderer<RendererData>[]) null);
    }

    @Test
    public void testNewItemTransformInvoced() throws Exception {
        wraper.newItem(INPUT_D);
        verify(data).transformInvoced();
    }

    @Test
    public void testNewItemSubPathInvoced() throws Exception {
        wraper.newItem(INPUT_D);
        verify(data).subPathInvoced();
    }

    @Test
    public void testNewItemDataRendererModifyInvoced() throws Exception {
        wraper.newItem(INPUT_D);
        verify(data).renderModifyInvoced();
    }

    @Test
    public void testNewItemDataRendererRenderInvoced() throws Exception {
        wraper.newItem(INPUT_D);
        verify(data).renderRenderInvoced();
    }

    @Test
    public void testNewItemMethodInvocationOrder() throws Exception {
        InOrder order = Mockito.inOrder(data);
        wraper.newItem(INPUT_D);
        order.verify(data).transformInvoced();
        order.verify(data).subPathInvoced();
        order.verify(data).renderModifyInvoced();
        order.verify(data).renderRenderInvoced();
    }

    @Test
    public void testNewItemWriterAccessInvocation() throws Exception {
        wraper.newItem(INPUT_D);
        verify(writer).write(SUBPATH, CODE_ONE + CODE_TWO);
    }
}
