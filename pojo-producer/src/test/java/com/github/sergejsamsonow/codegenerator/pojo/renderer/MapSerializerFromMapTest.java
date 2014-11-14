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
import com.github.sergejsamsonow.codegenerator.pojo.renderer.mapserializer.BeanModifier;

@RunWith(MockitoJUnitRunner.class)
public class MapSerializerFromMapTest {

    private static final SCNewLineAndIndentationFormat FORMAT = SCNewLineAndIndentationFormat.unixWithSpaces(4);

    private MapSerializerFromMap renderer;

    @Mock
    private PojoBean bean;

    private PojoProperty nameString = new SimplePojoProperty("name", "String");
    private PojoProperty numbersIntegerList = new SimplePojoProperty("numbers", "List<Integer>");
    private PojoProperty addressComplex = new SimplePojoProperty("address", "Address");
    private PojoProperty personsList = new SimplePojoProperty("persons", "List<Person>");

    @Before
    public void setUp() throws Exception {
        renderer = new MapSerializerFromMap(FORMAT);
        when(bean.getProperties()).thenReturn(asList(nameString));
    }

    @Test
    public void testModifyAddMapSerializerInterface() throws Exception {
        renderer.modify(bean);
        verify(bean).addToInterfaces(BeanModifier.MODIFIER_INTERFACE);
    }

    @Test
    public void testModifyAddMapAccessToImports() throws Exception {
        renderer.modify(bean);
        verify(bean).addToImports(MapSerializerFromMap.MAP_ACCESS);
    }

    @Test
    public void testRenderScalarBasicType() throws Exception {
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/MapSerializerFromMap-Simple.txt")));
    }

    @Test
    public void testRenderScalarComplexType() throws Exception {
        when(bean.getProperties()).thenReturn(asList(addressComplex));
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/MapSerializerFromMap-Object.txt")));
    }

    @Test
    public void testRenderListBasicType() throws Exception {
        when(bean.getProperties()).thenReturn(asList(numbersIntegerList));
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/MapSerializerFromMap-IntegerList.txt")));
    }

    @Test
    public void testRenderListComplexType() throws Exception {
        when(bean.getProperties()).thenReturn(asList(personsList));
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/MapSerializerFromMap-ListOfObjects.txt")));
    }

    @Test
    public void testRender() throws Exception {
        when(bean.getProperties()).thenReturn(asList(nameString, addressComplex, numbersIntegerList, personsList));
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/MapSerializerFromMap-Complex.txt")));
    }
}
