package com.github.sergejsamsonow.codegenerator.parser;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.Content;
import com.github.sergejsamsonow.codegenerator.api.ProducerAccess;

@RunWith(MockitoJUnitRunner.class)
public class SimpleParserTest {

    private static final ParsedBean PAGE = new SimpleParsedBean("frontend", "Page", "Content", Arrays.asList(
        new SimpleParsedProperty("header", "Header"),
        new SimpleParsedProperty("message", "String"),
        new SimpleParsedProperty("footer", "Footer")));

    private static final ParsedBean HEADER = new SimpleParsedBean("frontend", "Header", null, Arrays.asList(new SimpleParsedProperty(
        "content",
        "String")));

    private static final ParsedBean FOOTER = new SimpleParsedBean("frontend", "Footer", null, Arrays.asList(new SimpleParsedProperty(
        "content",
        "String")));

    @Mock
    private ProducerAccess<ParsedBean> producer;

    private SimpleParser parser;

    @Before
    public void setUp() {
        parser = new SimpleParser(producer);
    }

    @Test
    public void testParse() throws Exception {
        parser.parse(Content.of("/simple-parser-input.txt"));
        Mockito.verify(producer).newItem(PAGE);
        Mockito.verify(producer).newItem(HEADER);
        Mockito.verify(producer).newItem(FOOTER);
    }

}
