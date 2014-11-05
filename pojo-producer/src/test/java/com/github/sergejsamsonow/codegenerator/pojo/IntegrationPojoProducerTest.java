package com.github.sergejsamsonow.codegenerator.pojo;

import static java.util.Arrays.asList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.Content;
import com.github.sergejsamsonow.codegenerator.api.WriterAccess;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.parser.ParsedBean;
import com.github.sergejsamsonow.codegenerator.parser.ParsedProperty;

@RunWith(MockitoJUnitRunner.class)
public class IntegrationPojoProducerTest {

    private static final SCNewLineAndIndentationFormat FORMAT = SCNewLineAndIndentationFormat.unixWithSpaces(4);

    private PojoProducer producer;

    private ParsedBean bean;

    @Mock
    private WriterAccess writer;

    @Before
    public void setUp() {
        ParsedProperty place = new ParsedProperty("place", "String");
        ParsedProperty age = new ParsedProperty("age", "Integer");
        ParsedProperty attributes = new ParsedProperty("attributes", "List<fragments.Attribute>");
        bean = new ParsedBean("frontend", "Form", "", asList(place, age, attributes));
        producer = new PojoProducer(writer, FORMAT);
    }

    @Test
    public void renderPojo() {
        producer.newItem(bean);
        Mockito.verify(writer).write("frontend/Form.java", Content.of("/pojo-renderer/PojoProducer-Integration.txt"));
    }

}
