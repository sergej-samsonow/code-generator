package com.github.sergejsamsonow.codegenerator.parser;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Objects;
import org.junit.Test;

public class ParsedPropertyTest {

    private static final String NAME = "message";
    private static final String TYPE = "SimpleType";

    @Test
    public void testGetName() throws Exception {
        ParsedProperty property = new ParsedProperty(NAME, TYPE);
        assertThat(property.getName(), equalTo(NAME));
    }

    @Test
    public void testGetType() throws Exception {
        ParsedProperty property = new ParsedProperty(NAME, TYPE);
        assertThat(property.getType(), equalTo(TYPE));
    }

    @Test
    public void testHashCode() throws Exception {
        int expected = Objects.hash(NAME, TYPE);
        ParsedProperty property = new ParsedProperty(NAME, TYPE);
        assertThat(property.hashCode(), equalTo(expected));
    }

    @Test
    public void testEquals() throws Exception {
        ParsedProperty a = new ParsedProperty(NAME, TYPE);
        ParsedProperty b = new ParsedProperty(NAME, TYPE);
        assertThat(a.equals(b), equalTo(true));
    }

}
