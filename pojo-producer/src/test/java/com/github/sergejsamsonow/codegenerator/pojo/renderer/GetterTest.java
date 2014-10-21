package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.Content;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

@RunWith(MockitoJUnitRunner.class)
public class GetterTest {

    private static final SCNewLineAndIndentationFormat FORMAT = SCNewLineAndIndentationFormat.unixWithSpaces(4);

    private Getter renderer;

    @Mock
    private PojoBean bean;

    @Mock
    private PojoProperty property;

    @Before
    public void setUp() {
        renderer = new Getter(FORMAT);
        when(property.getGetterName()).thenReturn("getField");
        when(property.getDeclarationType()).thenReturn("String");
        when(property.getFieldName()).thenReturn("field");
        when(bean.getProperties()).thenReturn(asList(property));
    }

    @Test
    public void testRender() {
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/Getter-Simple.txt")));
    }

}
