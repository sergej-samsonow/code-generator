package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.Content;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;

@RunWith(MockitoJUnitRunner.class)
public class ClassHeaderTest {

    private static final SCNewLineAndIndentationFormat FORMAT = SCNewLineAndIndentationFormat.unixWithSpaces(4);

    private ClassHeader renderer;

    @Mock
    private PojoBean bean;

    @Before
    public void setUp() {
        renderer = new ClassHeader(FORMAT);
        when(bean.getClassName()).thenReturn("Form");
        when(bean.getPackageName()).thenReturn("frontend");
        when(bean.getImports()).thenReturn(new HashSet<>(asList("java.util.Objects")));
    }

    @Test
    public void testRenderWitImports() throws Exception {
        assertThat(renderer.render(bean), equalTo(
            Content.of("/pojo-renderer/ClassHeader-WithImports.txt")));
    }

    @Test
    public void testRenderWitoutImports() throws Exception {
        when(bean.getImports()).thenReturn(Collections.emptySet());
        assertThat(renderer.render(bean), equalTo(
            Content.of("/pojo-renderer/ClassHeader-Simple.txt")));
    }

    @Test
    public void testRenderSortedImports() throws Exception {
        when(bean.getImports()).thenReturn(new HashSet<>(asList(
            "fragments.Attributes",
            "java.util.List",
            "java.util.ArrayList"
            )));
        assertThat(renderer.render(bean), equalTo(
            Content.of("/pojo-renderer/ClassHeader-SortedImports.txt")));
    }

}
