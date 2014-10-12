package com.github.sergejsamsonow.codegenerator.api;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PartialRendererEngineTest {

    private static final RendererData OUTPUT = new RendererData();
    private static final String CODE = "CODE";
    private static final String SUBPATH = "PATH";

    private static class ParserData {}
    private static class RendererData {}
    private static class Wrapper extends PartialRendererEngine<RendererData, ParserData> {

        @SafeVarargs
        public Wrapper(Renderer<RendererData>... renderers) {
            super(renderers);
        }

        @Override
        protected RendererData transform(ParserData parsed) {
            return OUTPUT;
        }

        @Override
        protected String subpath(RendererData data) {
            return SUBPATH;
        }

    }

    @Mock
    private Renderer<RendererData> renderer;

    @Mock
    private CodeFormat format;

    private Wrapper wrapper;

    @Before
    public void setUp() {
        wrapper = new Wrapper(renderer);
        Mockito.when(renderer.render(OUTPUT)).thenReturn(CODE);
    }

    @Test
    public void testSetRenderFormat() {
        wrapper.setRenderFormat(format);
        Mockito.verify(renderer).setFromat(format);
    }

    @Test
    public void testProcessPrepareState() throws Exception {
        wrapper.setRenderFormat(format);
        wrapper.process(new ParserData());
        Mockito.verify(renderer).prepare(OUTPUT);
    }

    @Test
    public void testProcessRenderState() throws Exception {
        wrapper.setRenderFormat(format);
        wrapper.process(new ParserData());
        Mockito.verify(renderer).render(OUTPUT);
    }

    @Test
    public void testProcessCheckResult() throws Exception {
        wrapper.setRenderFormat(format);
        wrapper.process(new ParserData());
        Result result = wrapper.process(new ParserData());
        MatcherAssert.assertThat(result.getSubPath(), CoreMatchers.equalTo(SUBPATH));
        MatcherAssert.assertThat(result.getCode(), CoreMatchers.equalTo(CODE));
    }
}
