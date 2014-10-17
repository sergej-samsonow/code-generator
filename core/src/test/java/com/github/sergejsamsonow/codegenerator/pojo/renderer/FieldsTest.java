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
import com.github.sergejsamsonow.codegenerator.api.CodeFormat;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;

@RunWith(MockitoJUnitRunner.class)
public class FieldsTest {

    private static final CodeFormat FORMAT = CodeFormat.unixWithSpaces(4);

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
