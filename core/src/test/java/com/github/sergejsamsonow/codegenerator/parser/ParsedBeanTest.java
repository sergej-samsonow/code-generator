package com.github.sergejsamsonow.codegenerator.parser;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import org.junit.Test;
import org.mockito.Mockito;

public class ParsedBeanTest {

    private static final String TYPE = "Form";
    private static final String NAMESPACE = "page";

    @Test
    public void testGetNamespace() throws Exception {
        ParsedBean bean = new ParsedBean(NAMESPACE, TYPE, Collections.emptyList());
        assertThat(bean.getNamespace(), equalTo(NAMESPACE));
    }

    @Test
    public void testGetType() throws Exception {
        ParsedBean bean = new ParsedBean(NAMESPACE, TYPE, Collections.emptyList());
        assertThat(bean.getType(), equalTo(TYPE));
    }

    @Test
    public void testGetProperties() throws Exception {
        ParsedBean bean = new ParsedBean(NAMESPACE, TYPE, Collections.emptyList());
        assertThat(bean.getProperties(), equalTo(Collections.emptyList()));
    }

    @Test
    public void testPropertiesImmutableSetter() throws Exception {
        LinkedList<ParsedProperty> properties = new LinkedList<>();
        properties.add(Mockito.mock(ParsedProperty.class));
        properties.add(Mockito.mock(ParsedProperty.class));

        ParsedBean bean = new ParsedBean(NAMESPACE, TYPE, properties);
        properties.pollFirst();
        assertThat(bean.getProperties().size(), equalTo(2));
    }

    @Test
    public void testPropertiesImmutableGetter() throws Exception {
        LinkedList<ParsedProperty> properties = new LinkedList<>();
        properties.add(Mockito.mock(ParsedProperty.class));
        properties.add(Mockito.mock(ParsedProperty.class));

        ParsedBean bean = new ParsedBean(NAMESPACE, TYPE, properties);
        bean.getProperties().clear();
        assertThat(bean.getProperties().size(), equalTo(2));
    }

    @Test
    public void testHashCode() throws Exception {
        int expected = Objects.hash(NAMESPACE, TYPE, Collections.emptyList());
        ParsedBean bean = new ParsedBean(NAMESPACE, TYPE, Collections.emptyList());
        assertThat(bean.hashCode(), equalTo(expected));
    }

    @Test
    public void testEqualsEquals() throws Exception {
        ParsedBean a = new ParsedBean(NAMESPACE, TYPE, Collections.emptyList());
        ParsedBean b = new ParsedBean(NAMESPACE, TYPE, Collections.emptyList());
        assertThat(a.equals(b), equalTo(true));
    }
}
