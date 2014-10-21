package com.github.sergejsamsonow.codegenerator.pojo.model;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.HashSet;
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
    private static final String PN = "component";
    private static final String CN = "Unit";
    private SimplePojoBean bean;

    @Mock
    private PojoProperty one;

    @Mock
    private PojoProperty two;

    @Before
    public void setUp() {
        Mockito.when(one.getImportedTypes()).thenReturn(new HashSet<>(asList(IMPORT_ONE)));
        Mockito.when(two.getImportedTypes()).thenReturn(new HashSet<>(asList(IMPORT_TWO)));
        bean = new SimplePojoBean(PN, CN, asList(one, two));
    }

    @Test
    public void testGetPackageName() throws Exception {
        assertThat(bean.getPackageName(), equalTo(PN));
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
}
