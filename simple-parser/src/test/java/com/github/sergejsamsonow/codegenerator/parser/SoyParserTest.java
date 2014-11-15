package com.github.sergejsamsonow.codegenerator.parser;

import static java.util.Arrays.asList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.Content;
import com.github.sergejsamsonow.codegenerator.api.ProducerAccess;
import com.github.sergejsamsonow.codegenerator.api.parser.model.ParsedBean;

@RunWith(MockitoJUnitRunner.class)
public class SoyParserTest {

    private static final ParsedBean INDEX = new SimpleParsedBean("pages", "Index", null, asList(
        new SimpleParsedProperty("header", "fragments.Header"),
        new SimpleParsedProperty("welcomMessage", "String"),
        new SimpleParsedProperty("form", "fragments.Form"),
        new SimpleParsedProperty("result", "List<pages.index.ResultItemInfo>")
        ));

    @Mock
    private ProducerAccess<ParsedBean> producer;

    private SoyParser parser;

    @Test
    public void testParse() throws Exception {
        parser = new SoyParser(producer);
        parser.parse(Content.of("/soy-parser-input.txt"));
        Mockito.verify(producer).newItem(INDEX);
    }

}
