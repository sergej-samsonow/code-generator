package com.github.sergejsamsonow.codegenerator.api.parser.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import org.junit.Test;
import org.mockito.Mockito;

public class SimpleParsedBeanTest {

    private static final String TYPE = "Form";
    private static final String PARENT = "Parent";
    private static final String NAMESPACE = "page";

    @Test
    public void testGetNamespace() throws Exception {
        ParsedBean bean = new SimpleParsedBean(NAMESPACE, TYPE, PARENT, Collections.emptyList());
        assertThat(bean.getNamespace(), equalTo(NAMESPACE));
    }

    @Test
    public void testGetType() throws Exception {
        ParsedBean bean = new SimpleParsedBean(NAMESPACE, TYPE, PARENT, Collections.emptyList());
        assertThat(bean.getType(), equalTo(TYPE));
    }

    @Test
    public void testGetProperties() throws Exception {
        ParsedBean bean = new SimpleParsedBean(NAMESPACE, TYPE, PARENT, Collections.emptyList());
        assertThat(bean.getProperties(), equalTo(Collections.emptyList()));
    }

    @Test
    public void testPropertiesImmutableSetter() throws Exception {
        LinkedList<ParsedProperty> properties = new LinkedList<>();
        properties.add(Mockito.mock(SimpleParsedProperty.class));
        properties.add(Mockito.mock(SimpleParsedProperty.class));

        ParsedBean bean = new SimpleParsedBean(NAMESPACE, TYPE, PARENT, properties);
        properties.pollFirst();
        assertThat(bean.getProperties().size(), equalTo(2));
    }

    @Test
    public void testPropertiesImmutableGetter() throws Exception {
        LinkedList<ParsedProperty> properties = new LinkedList<>();
        properties.add(Mockito.mock(SimpleParsedProperty.class));
        properties.add(Mockito.mock(SimpleParsedProperty.class));

        ParsedBean bean = new SimpleParsedBean(NAMESPACE, TYPE, PARENT, properties);
        bean.getProperties().clear();
        assertThat(bean.getProperties().size(), equalTo(2));
    }

    @Test
    public void testHashCode() throws Exception {
        int expected = Objects.hash(NAMESPACE, TYPE, Collections.emptyList(), PARENT);
        ParsedBean bean = new SimpleParsedBean(NAMESPACE, TYPE, PARENT, Collections.emptyList());
        assertThat(bean.hashCode(), equalTo(expected));
    }

    @Test
    public void testEqualsEquals() throws Exception {
        ParsedBean a = new SimpleParsedBean(NAMESPACE, TYPE, PARENT, Collections.emptyList());
        ParsedBean b = new SimpleParsedBean(NAMESPACE, TYPE, PARENT, Collections.emptyList());
        assertThat(a.equals(b), equalTo(true));
    }

    @Test
    public void testEqualsNotEquals() throws Exception {
        ParsedBean a = new SimpleParsedBean(NAMESPACE, TYPE, PARENT, Collections.emptyList());
        ParsedBean b = new SimpleParsedBean(NAMESPACE, TYPE, "A", Collections.emptyList());
        assertThat(a.equals(b), not(equalTo(true)));
    }

    @Test
    public void testGetParentTypeConvertToEmptyString() throws Exception {
        ParsedBean a = new SimpleParsedBean(NAMESPACE, TYPE, null, Collections.emptyList());
        assertThat(a.getParentType(), equalTo(""));
    }

}
