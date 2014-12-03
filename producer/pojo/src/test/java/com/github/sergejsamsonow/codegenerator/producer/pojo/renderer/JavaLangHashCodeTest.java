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
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.producer.pojo.renderer.JavaLangHashCode;

@RunWith(MockitoJUnitRunner.class)
public class JavaLangHashCodeTest {

    private JavaLangHashCode renderer;

    private TestData data;

    @Mock
    private PojoBean bean;

    @Before
    public void setUp() throws Exception {
        data = new TestData();
        renderer = new JavaLangHashCode(data.format);
        when(bean.getProperties()).thenReturn(asList(data.nameString, data.addressComplex, data.numbersIntegerList, data.personsList));
    }

    @Test
    public void testRenderMultipeFields() throws Exception {
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/HashCode-Complex.txt")));
    }

    @Test
    public void testRenderMultipeOneField() throws Exception {
        when(bean.getProperties()).thenReturn(asList(data.nameString));
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/HashCode-Simple.txt")));
    }

}
