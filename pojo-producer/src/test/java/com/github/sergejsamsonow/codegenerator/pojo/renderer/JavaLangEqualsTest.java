package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
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
import com.github.sergejsamsonow.codegenerator.pojo.model.SimplePojoProperty;

@RunWith(MockitoJUnitRunner.class)
public class JavaLangEqualsTest {

    private static final SCNewLineAndIndentationFormat FORMAT = SCNewLineAndIndentationFormat.unixWithSpaces(4);

    private JavaLangEquals renderer;

    private PojoProperty nameString = new SimplePojoProperty("name", "String");
    private PojoProperty numbersIntegerList = new SimplePojoProperty("numbers", "List<Integer>");
    private PojoProperty addressComplex = new SimplePojoProperty("address", "Address");
    private PojoProperty personsList = new SimplePojoProperty("persons", "List<Person>");

    @Mock
    private PojoBean bean;

    @Before
    public void setUp() throws Exception {
        renderer = new JavaLangEquals(FORMAT);
        when(bean.getClassName()).thenReturn("Example");
        when(bean.getProperties()).thenReturn(asList(nameString, addressComplex, numbersIntegerList, personsList));
    }

    @Test
    public void testModifyAddMapSerializerInterface() throws Exception {
        renderer.modify(bean);
        verify(bean).addToImports("java.util.Objects");
    }

    @Test
    public void testRenderMultipeFields() throws Exception {
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/Equals-Complex.txt")));
    }

    @Test
    public void testRenderOneField() throws Exception {
        when(bean.getProperties()).thenReturn(asList(nameString));
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/Equals-Simple.txt")));
    }

}
