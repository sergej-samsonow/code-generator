package com.github.sergejsamsonow.codegenerator.api.producer.sc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SCRendererTest {

    private static final String N = "NL";
    private static final String S = "SW";
    private static final Data D = new Data();

    private static class Data {}

    private static class AccessWrapper extends SCRenderer<Data> {

        private boolean prepared = false;
        private boolean rendered = false;
        private Data data = null;
        private SCCodeConcatenator code = null;
        private SCMethodCodeConcatenator methodCode = null;

        public AccessWrapper(SCNewLineAndIndentationFormat format) {
            super(format);
        }

        @Override
        protected void modify() {
            data = getData();
            prepared = true;
        }

        @Override
        protected void render() {
            data = getData();
            code = getCodeWriter();
            methodCode = getMethodCodeWriter();
            rendered = true;
        }
    }

    private static class RenderWrapper extends SCRenderer<Data> {

        private static final String EXPECTED = "A" + N + S + "B" + N;

        public RenderWrapper(SCNewLineAndIndentationFormat format) {
            super(format);
        }

        @Override
        protected void render() {
            SCCodeConcatenator code = getCodeWriter();
            SCMethodCodeConcatenator methodCode = getMethodCodeWriter();
            code.line("A");
            methodCode.start("B");
        }
    }

    private AccessWrapper accessWrapper;

    @Mock
    private SCNewLineAndIndentationFormat format;

    @Before
    public void setUp() {
        Mockito.when(format.newLineSequence()).thenReturn(N);
        Mockito.when(format.shiftSequence()).thenReturn(S);
        accessWrapper = new AccessWrapper(format);
    }

    @Test
    public void testPrepareInvoced() throws Exception {
        accessWrapper.modify(D);
        assertThat(accessWrapper.prepared, equalTo(true));
    }

    @Test
    public void testPrepareDataPresent() throws Exception {
        accessWrapper.modify(D);
        assertThat(accessWrapper.data, equalTo(D));
    }

    @Test
    public void testRenderInvoced() throws Exception {
        accessWrapper.render(D);
        assertThat(accessWrapper.rendered, equalTo(true));
    }

    @Test
    public void testRenderDataPresent() throws Exception {
        accessWrapper.render(D);
        assertThat(accessWrapper.data, equalTo(D));
    }

    @Test
    public void testRenderCodeWriterPresent() throws Exception {
        accessWrapper.render(D);
        assertThat(accessWrapper.code, notNullValue());
    }

    @Test
    public void testRenderMethodCodeWriterPresent() throws Exception {
        accessWrapper.render(D);
        assertThat(accessWrapper.methodCode, notNullValue());
    }

    @Test
    public void testFlush() throws Exception {
        RenderWrapper renderWrapper = new RenderWrapper(format);
        assertThat(renderWrapper.render(D), equalTo(RenderWrapper.EXPECTED));
    }
}
