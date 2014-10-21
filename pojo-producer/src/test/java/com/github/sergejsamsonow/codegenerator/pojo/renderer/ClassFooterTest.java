package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;

@RunWith(MockitoJUnitRunner.class)
public class ClassFooterTest {

    private static final SCNewLineAndIndentationFormat FORMAT = SCNewLineAndIndentationFormat.unixWithSpaces(4);

    private ClassFooter<PojoBean> renderer;

    @Mock
    private PojoBean bean;

    @Before
    public void setUp() {
        renderer = new ClassFooter<>(FORMAT);
    }

    @Test
    public void testRender() {
        assertThat(renderer.render(bean), equalTo("}\n\n"));
    }

}
