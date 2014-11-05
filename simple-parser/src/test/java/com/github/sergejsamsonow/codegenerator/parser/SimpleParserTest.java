package com.github.sergejsamsonow.codegenerator.parser;

import static java.util.Arrays.asList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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

    @Test
    public void testParse() throws Exception {
        parser = new SimpleParser(producer, Collections.emptySet());
        parser.parse(Content.of("/simple-parser-input.txt"));
        Mockito.verify(producer).newItem(PAGE);
        Mockito.verify(producer).newItem(HEADER);
        Mockito.verify(producer).newItem(FOOTER);
    }

    @Test
    public void testExtendedType() throws Exception {
        // Current object (frontend.Page) exist on other place an has as parent
        // Object that should be generated now.
        // Existing Object look like this
        // public class Page extends PageBase {...
        // Also we need to rename declared Page object to PageBase
        ParsedBean extended = new SimpleParsedBean("frontend", "PageBase", "Content", Arrays.asList(
            new SimpleParsedProperty("header", "Header"),
            new SimpleParsedProperty("message", "String"),
            new SimpleParsedProperty("footer", "Footer")));

        parser = new SimpleParser(producer, new HashSet<>(asList("frontend.Page")));
        parser.parse(Content.of("/simple-parser-input.txt"));
        Mockito.verify(producer).newItem(extended);
    }

}
