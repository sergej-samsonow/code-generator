package com.github.sergejsamsonow.codegenerator.pojo;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.api.WriterAccess;
import com.github.sergejsamsonow.codegenerator.api.producer.Renderer;
import com.github.sergejsamsonow.codegenerator.parser.ParsedBean;
import com.github.sergejsamsonow.codegenerator.parser.ParsedProperty;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.pojo.model.SimplePojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.SimplePojoProperty;

@RunWith(MockitoJUnitRunner.class)
public class PojoProducerTest {

    private static final String PACKAGE = "frontend.simple";
    private static final String CLASS = "Form";
    private static final String PARENT = "Parent";
    private PojoProducer producer;

    @Mock
    private WriterAccess writer;

    @Mock
    private Renderer<PojoBean> renderer;

    @Mock
    private ParsedBean parsedBean;

    @Mock
    private ParsedProperty parsedProperty;

    private PojoBean pojoBean;
    private PojoProperty pojoProperty;

    @Before
    public void setUp() {
        when(parsedProperty.getName()).thenReturn("name");
        when(parsedProperty.getType()).thenReturn("String");

        when(parsedBean.getNamespace()).thenReturn(PACKAGE);
        when(parsedBean.getType()).thenReturn(CLASS);
        when(parsedBean.getParentType()).thenReturn(PARENT);
        when(parsedBean.getProperties()).thenReturn(asList(parsedProperty));

        producer = new PojoProducer(writer, renderer);
        pojoProperty = new SimplePojoProperty(parsedProperty.getName(), parsedProperty.getType());
        pojoBean = new SimplePojoBean(parsedBean.getNamespace(), parsedBean.getType(), PARENT, asList(pojoProperty));
    }

    @Test
    public void testTransform() throws Exception {
        assertThat(producer.transform(parsedBean), equalTo(pojoBean));
    }

    @Test
    public void testSubpath() throws Exception {
        assertThat(producer.subpath(pojoBean), equalTo("frontend/simple/Form.java"));
    }

}
