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
public class ConstructorTest {

    private static final CodeFormat FORMAT = CodeFormat.unixWithSpaces(4);

    private Constructor renderer;

    @Mock
    private PojoBean bean;

    @Mock
    private PojoProperty property;

    @Before
    public void setUp() {
        renderer = new Constructor(FORMAT);
        when(property.getInitCode()).thenReturn("\"\"");
        when(property.getSetterName()).thenReturn("setField");
        when(bean.getClassName()).thenReturn("Form");
        when(bean.getProperties()).thenReturn(asList(property));
    }

    @Test
    public void testRender() {
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/Constructor-Simple.txt")));
    }

}
