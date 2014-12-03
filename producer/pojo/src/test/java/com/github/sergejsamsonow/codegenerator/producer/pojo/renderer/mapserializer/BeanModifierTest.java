package com.github.sergejsamsonow.codegenerator.producer.pojo.renderer.mapserializer;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.SimplePojoProperty;
import com.github.sergejsamsonow.codegenerator.producer.pojo.renderer.mapserializer.BeanModifier;

@RunWith(MockitoJUnitRunner.class)
public class BeanModifierTest {

    @Mock
    private PojoBean bean;
    private PojoProperty scalar = new SimplePojoProperty("scalar", "String");
    private PojoProperty list = new SimplePojoProperty("list", "List<String>");

    private BeanModifier modifier;

    @Before
    public void setUp() {
        when(bean.getProperties()).thenReturn(asList(scalar, list));
        modifier = new BeanModifier();
    }

    @Test
    public void testModifyAddModifierInterface() throws Exception {
        modifier.modify(bean);
        verify(bean).addToInterfaces(BeanModifier.MODIFIER_INTERFACE);
    }

    @Test
    public void testModifyAddMapsObjects() throws Exception {
        modifier.modify(bean);
        verify(bean).addToImports("java.util.Map");
        verify(bean).addToImports("java.util.HashMap");
    }

    @Test
    public void testModifyAddListObjects() throws Exception {
        modifier.modify(bean);
        verify(bean).addToImports("java.util.List");
        verify(bean).addToImports("java.util.ArrayList");
    }

    @Test
    public void testModifyDontAddListObjectsIfNotNeeded() throws Exception {
        when(bean.getProperties()).thenReturn(asList(scalar));
        modifier.modify(bean);
        verify(bean, never()).addToImports("java.util.List");
        verify(bean, never()).addToImports("java.util.ArrayList");
    }

}
