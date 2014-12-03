package com.github.sergejsamsonow.codegenerator.producer.pojo.renderer;

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
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.producer.pojo.renderer.Fields;

@RunWith(MockitoJUnitRunner.class)
public class FieldsTest {

    private static final SCNewLineAndIndentationFormat FORMAT = SCNewLineAndIndentationFormat.unixWithSpaces(4);

    private Fields renderer;

    @Mock
    private PojoBean bean;

    @Mock
    private PojoProperty field;

    @Mock
    private PojoProperty name;

    @Before
    public void setUp() {
        renderer = new Fields(FORMAT);
        when(field.getDeclarationType()).thenReturn("String");
        when(field.getFieldName()).thenReturn("field");
        when(name.getDeclarationType()).thenReturn("String");
        when(name.getFieldName()).thenReturn("name");
        when(bean.getProperties()).thenReturn(asList(field, name));
    }

    @Test
    public void testRender() {
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/Fields-Simple.txt")));
    }

}
