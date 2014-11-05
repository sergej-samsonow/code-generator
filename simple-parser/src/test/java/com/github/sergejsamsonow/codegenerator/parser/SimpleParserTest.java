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

    private static final ParsedBean PAGE = new ParsedBean("frontend", "Page", "Content", Arrays.asList(
        new ParsedProperty("header", "Header"),
        new ParsedProperty("message", "String"),
        new ParsedProperty("footer", "Footer")));

    private static final ParsedBean HEADER = new ParsedBean("frontend", "Header", null, Arrays.asList(new ParsedProperty(
        "content",
        "String")));

    private static final ParsedBean FOOTER = new ParsedBean("frontend", "Footer", null, Arrays.asList(new ParsedProperty(
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
