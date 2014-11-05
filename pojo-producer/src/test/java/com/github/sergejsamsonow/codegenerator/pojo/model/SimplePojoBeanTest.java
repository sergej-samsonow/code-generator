package com.github.sergejsamsonow.codegenerator.pojo.model;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimplePojoBeanTest {

    private static final String IMPORT_ONE = "module.a";
    private static final String IMPORT_TWO = "module.b";
    private static final String NN = "component";
    private static final String CN = "Unit";
    private static final String PN = "Parent";
    private SimplePojoBean bean;

    @Mock
    private PojoProperty one;

    @Mock
    private PojoProperty two;

    private List<PojoProperty> propertiesMocks() {
        Mockito.when(one.getImportedTypes()).thenReturn(new HashSet<>(asList(IMPORT_ONE)));
        Mockito.when(two.getImportedTypes()).thenReturn(new HashSet<>(asList(IMPORT_TWO)));
        return asList(one, two);
    }

    @Before
    public void setUp() {
        bean = new SimplePojoBean(NN, CN, PN, propertiesMocks());
    }

    @Test
    public void testGetPackageName() throws Exception {
        assertThat(bean.getPackageName(), equalTo(NN));
    }

    @Test
    public void testGetClassName() throws Exception {
        assertThat(bean.getClassName(), equalTo(CN));
    }

    @Test
    public void testGetProperties() throws Exception {
        assertThat(bean.getProperties(), hasItems(one, two));
    }

    @Test
    public void testGetImports() throws Exception {
        assertThat(bean.getImports(), hasItems(IMPORT_ONE, IMPORT_TWO));
    }

    @Test
    public void testAddToImports() throws Exception {
        bean.addToImports("module.c");
        assertThat(bean.getImports(), hasItems(IMPORT_ONE, IMPORT_TWO, "module.c"));
    }

    @Test
    public void testRemoveFromImports() throws Exception {
        bean.removeFromImports(IMPORT_ONE);
        assertThat(bean.getImports(), not(hasItems(IMPORT_ONE)));
    }

    @Test
    public void testGetParentClass() throws Exception {
        assertThat(bean.getParentClass(), equalTo(PN));
    }

    @Test
    public void testGetParentOnNullType() throws Exception {
        bean = new SimplePojoBean(NN, CN, null, propertiesMocks());
        assertThat(bean.getParentClass(), equalTo(""));
    }

    @Test
    public void testGetParentAddParentToImports() throws Exception {
        bean = new SimplePojoBean(NN, CN, "some.Parent", propertiesMocks());
        assertThat(bean.getImports(), hasItems("some.Parent"));
        assertThat(bean.getParentClass(), equalTo("Parent"));
    }

    @Test
    public void testHashCode() throws Exception {
        int hash = Objects.hash(NN, CN, PN, new HashSet<>(asList(IMPORT_ONE, IMPORT_TWO)), propertiesMocks());
        assertThat(bean.hashCode(), equalTo(hash));
    }

    @Test
    public void testEquals() throws Exception {
        SimplePojoBean a = new SimplePojoBean(NN, CN, PN, propertiesMocks());
        SimplePojoBean b = new SimplePojoBean(NN, CN, PN, propertiesMocks());
        assertThat(a.equals(b), equalTo(true));
    }

    @Test
    public void testEqualsNot() throws Exception {
        SimplePojoBean a = new SimplePojoBean(NN, CN, PN, propertiesMocks());
        SimplePojoBean b = new SimplePojoBean(NN, CN, null, propertiesMocks());
        assertThat(a.equals(b), not(equalTo(true)));
    }
}
