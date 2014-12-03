package com.github.sergejsamsonow.codegenerator.producer.pojo.renderer.javalang;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.producer.pojo.renderer.javalang.BeanModifier;

@RunWith(MockitoJUnitRunner.class)
public class BeanModifierTest {

    private static final SCNewLineAndIndentationFormat FORMAT = SCNewLineAndIndentationFormat.unixWithSpaces(4);

    private BeanModifier render;

    @Mock
    private PojoBean bean;

    @Before
    public void setUp() {
        render = new BeanModifier(FORMAT);
    }

    @Test
    public void testModify() throws Exception {
        render.modify(bean);
        verify(bean).addToImports("java.util.Objects");
    }

}
