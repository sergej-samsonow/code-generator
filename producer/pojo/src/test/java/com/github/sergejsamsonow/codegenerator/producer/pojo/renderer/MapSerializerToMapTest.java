package com.github.sergejsamsonow.codegenerator.producer.pojo.renderer;

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
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.producer.pojo.renderer.MapSerializerToMap;
import com.github.sergejsamsonow.codegenerator.producer.pojo.renderer.mapserializer.BeanModifier;

@RunWith(MockitoJUnitRunner.class)
public class MapSerializerToMapTest {

    private MapSerializerToMap renderer;

    @Mock
    private PojoBean bean;

    private TestData data;

    @Before
    public void setUp() throws Exception {
        data = new TestData();
        renderer = new MapSerializerToMap(data.format);
        when(bean.getProperties()).thenReturn(asList(data.nameString));
    }

    @Test
    public void testModifyAddMapSerializerInterface() throws Exception {
        renderer.modify(bean);
        verify(bean).addToInterfaces(BeanModifier.MODIFIER_INTERFACE);
    }

    @Test
    public void testRenderScalarBasicType() throws Exception {
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/MapSerializerToMap-SimpleString.txt")));
    }

    @Test
    public void testRenderScalarComplexType() throws Exception {
        when(bean.getProperties()).thenReturn(asList(data.addressComplex));
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/MapSerializerToMap-Object.txt")));
    }

    @Test
    public void testRenderListBasicType() throws Exception {
        when(bean.getProperties()).thenReturn(asList(data.numbersIntegerList));
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/MapSerializerToMap-IntegerList.txt")));
    }

    @Test
    public void testRenderListComplexType() throws Exception {
        when(bean.getProperties()).thenReturn(asList(data.personsList));
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/MapSerializerToMap-ListOfObjects.txt")));
    }

    @Test
    public void testRender() throws Exception {
        when(bean.getProperties()).thenReturn(asList(data.nameString, data.addressComplex, data.numbersIntegerList, data.personsList));
        assertThat(renderer.render(bean),
            equalTo(Content.of("/pojo-renderer/MapSerializerToMap-Complex.txt")));
    }

}
