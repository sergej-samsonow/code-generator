package com.github.sergejsamsonow.codegenerator.api.parser.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Objects;
import org.junit.Test;
import com.github.sergejsamsonow.codegenerator.api.parser.model.SimpleParsedProperty;

public class SimpleParsedPropertyTest {

    private static final String NAME = "message";
    private static final String TYPE = "SimpleType";

    @Test
    public void testGetName() throws Exception {
        ParsedProperty property = new SimpleParsedProperty(NAME, TYPE);
        assertThat(property.getName(), equalTo(NAME));
    }

    @Test
    public void testGetType() throws Exception {
        ParsedProperty property = new SimpleParsedProperty(NAME, TYPE);
        assertThat(property.getType(), equalTo(TYPE));
    }

    @Test
    public void testHashCode() throws Exception {
        int expected = Objects.hash(NAME, TYPE);
        ParsedProperty property = new SimpleParsedProperty(NAME, TYPE);
        assertThat(property.hashCode(), equalTo(expected));
    }

    @Test
    public void testEquals() throws Exception {
        ParsedProperty a = new SimpleParsedProperty(NAME, TYPE);
        ParsedProperty b = new SimpleParsedProperty(NAME, TYPE);
        assertThat(a.equals(b), equalTo(true));
    }

}
