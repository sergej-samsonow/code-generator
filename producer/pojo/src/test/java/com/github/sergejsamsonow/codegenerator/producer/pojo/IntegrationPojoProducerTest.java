package com.github.sergejsamsonow.codegenerator.producer.pojo;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.Content;
import com.github.sergejsamsonow.codegenerator.api.WriterAccess;
import com.github.sergejsamsonow.codegenerator.api.parser.model.ParsedBean;
import com.github.sergejsamsonow.codegenerator.api.parser.model.ParsedProperty;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.producer.pojo.PojoProducer;

@RunWith(MockitoJUnitRunner.class)
public class IntegrationPojoProducerTest {

    private static final SCNewLineAndIndentationFormat FORMAT = SCNewLineAndIndentationFormat.unixWithSpaces(4);

    private PojoProducer producer;

    @Mock
    private WriterAccess writer;

    @Mock
    private ParsedProperty place;

    @Mock
    private ParsedProperty age;

    @Mock
    private ParsedProperty attributes;

    @Mock
    private ParsedBean bean;

    @Before
    public void setUp() {
        when(place.getName()).thenReturn("place");
        when(place.getType()).thenReturn("String");

        when(age.getName()).thenReturn("age");
        when(age.getType()).thenReturn("Integer");

        when(attributes.getName()).thenReturn("attributes");
        when(attributes.getType()).thenReturn("List<fragments.Attribute>");

        when(bean.getNamespace()).thenReturn("frontend");
        when(bean.getType()).thenReturn("Form");
        when(bean.getParentType()).thenReturn("");
        when(bean.getProperties()).thenReturn(asList(place, age, attributes));

        producer = new PojoProducer(writer, FORMAT);
    }

    @Test
    public void renderPojo() {
        producer.newItem(bean);
        Mockito.verify(writer).write("frontend/Form.java", Content.of("/pojo-renderer/PojoProducer-Integration.txt"));
    }

}
