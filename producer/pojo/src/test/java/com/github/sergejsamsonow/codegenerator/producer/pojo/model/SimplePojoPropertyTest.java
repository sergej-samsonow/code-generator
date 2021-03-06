package com.github.sergejsamsonow.codegenerator.producer.pojo.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Collections;
import java.util.Set;
import org.junit.Test;
import com.github.sergejsamsonow.codegenerator.producer.pojo.model.SimplePojoProperty;

public class SimplePojoPropertyTest {

    private static final String N = "content";
    private static final String SETTER = "setContent";
    private static final String GETTER = "getContent";
    private static final String LIST = "java.util.List";
    private static final String ARRAY_LIST = "java.util.ArrayList";
    private static final Set<String> EMPTY = Collections.emptySet();
    private SimplePojoProperty basicTypeScalarProperty = new SimplePojoProperty(N, "String");
    private SimplePojoProperty basicTypeListProperty = new SimplePojoProperty(N, "List<Integer>");
    private SimplePojoProperty siblingTypeScalarProperty = new SimplePojoProperty(N, "Content");
    private SimplePojoProperty siblingTypeListProperty = new SimplePojoProperty(N, "List<Content>");
    private SimplePojoProperty externalTypeScalarProperty = new SimplePojoProperty(N, "x.Content");
    private SimplePojoProperty externalTypeListProperty = new SimplePojoProperty(N, "List<x.Content>");

    @Test
    public void testGetFieldName() throws Exception {
        assertThat(basicTypeScalarProperty.getFieldName(), equalTo(N));
    }

    @Test
    public void testGetSetterName() throws Exception {
        assertThat(siblingTypeScalarProperty.getSetterName(), equalTo(SETTER));
    }

    @Test
    public void testGetGetterName() throws Exception {
        assertThat(siblingTypeScalarProperty.getGetterName(), equalTo(GETTER));
    }

    @Test
    public void testGetImportedTypesForScalarTypes() throws Exception {
        assertThat(basicTypeScalarProperty.getImportedTypes(), equalTo(EMPTY));
        assertThat(siblingTypeScalarProperty.getImportedTypes(), equalTo(EMPTY));
    }

    @Test
    public void testGetImportedTypesForExternalTypeScalar() throws Exception {
        assertThat(externalTypeScalarProperty.getImportedTypes(), hasItems("x.Content"));
    }

    @Test
    public void testGetImportedTypesForBasicList() throws Exception {
        assertThat(basicTypeListProperty.getImportedTypes(), hasItems(LIST, ARRAY_LIST));
    }

    @Test
    public void testGetImportedTypesForSiblingTypeList() throws Exception {
        assertThat(siblingTypeListProperty.getImportedTypes(), hasItems(LIST, ARRAY_LIST));
        assertThat(siblingTypeListProperty.getImportedTypes(), not(hasItems("Content")));
    }

    @Test
    public void testGetImportedTypesForExternalTypeList() throws Exception {
        assertThat(externalTypeListProperty.getImportedTypes(), hasItems(LIST, ARRAY_LIST, "x.Content"));
    }

    @Test
    public void testIsListForScalarTypes() throws Exception {
        assertThat(basicTypeScalarProperty.isList(), equalTo(false));
        assertThat(siblingTypeScalarProperty.isList(), equalTo(false));
        assertThat(externalTypeScalarProperty.isList(), equalTo(false));
    }

    @Test
    public void testIsListForListTypes() throws Exception {
        assertThat(basicTypeListProperty.isList(), equalTo(true));
        assertThat(siblingTypeListProperty.isList(), equalTo(true));
        assertThat(externalTypeListProperty.isList(), equalTo(true));
    }

    @Test
    public void testGetDeclarationType() throws Exception {
        assertThat(basicTypeScalarProperty.getDeclarationType(), equalTo("String"));
        assertThat(basicTypeListProperty.getDeclarationType(), equalTo("List<Integer>"));
        assertThat(siblingTypeScalarProperty.getDeclarationType(), equalTo("Content"));
        assertThat(siblingTypeListProperty.getDeclarationType(), equalTo("List<Content>"));
        assertThat(externalTypeScalarProperty.getDeclarationType(), equalTo("Content"));
        assertThat(externalTypeListProperty.getDeclarationType(), equalTo("List<Content>"));
    }

    @Test
    public void testGetInitCodeForListTypes() throws Exception {
        assertThat(basicTypeListProperty.getInitCode(), equalTo("new ArrayList<>()"));
        assertThat(siblingTypeListProperty.getInitCode(), equalTo("new ArrayList<>()"));
        assertThat(externalTypeListProperty.getInitCode(), equalTo("new ArrayList<>()"));
    }

    @Test
    public void testGetInitCodeForCustomTypes() throws Exception {
        assertThat(siblingTypeScalarProperty.getInitCode(), equalTo("new Content()"));
        assertThat(externalTypeScalarProperty.getInitCode(), equalTo("new Content()"));
    }

    @Test
    public void testGetInitCodeForBasicTypeString() throws Exception {
        assertThat(basicTypeScalarProperty.getInitCode(), equalTo("\"\""));
    }

    @Test
    public void testGetInitCodeForBasicTypeInteger() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "Integer");
        assertThat(basicType.getInitCode(), equalTo("0"));
    }

    @Test
    public void testGetInitCodeForBasicTypeBoolean() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "Boolean");
        assertThat(basicType.getInitCode(), equalTo("false"));
    }

    @Test
    public void testGetInitCodeForBasicTypeFloat() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "Float");
        assertThat(basicType.getInitCode(), equalTo("0.0F"));
    }

    @Test
    public void testGetInitCodeForBasicTypeLong() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "Long");
        assertThat(basicType.getInitCode(), equalTo("0L"));
    }

    @Test
    public void testGetInitCodeForBasicTypeDouble() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "Double");
        assertThat(basicType.getInitCode(), equalTo("0.0D"));
    }

    @Test
    public void testIsSimpleTypeContainerBooleanScalar() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "Boolean");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testIsSimpleTypeContainerBooleanList() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "List<Boolean>");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testIsSimpleTypeContainerIntegerScalar() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "Integer");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testIsSimpleTypeContainerIntegerList() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "List<Integer>");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testIsSimpleTypeContainerFloatScalar() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "Float");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testIsSimpleTypeContainerFloatList() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "List<Float>");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testIsSimpleTypeContainerLongScalar() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "Long");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testIsSimpleTypeContainerLongList() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "List<Long>");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testIsSimpleTypeContainerDoubleScalar() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "Double");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testIsSimpleTypeContainerDoubleList() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "List<Double>");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testIsSimpleTypeContainerStringScalar() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "String");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testIsSimpleTypeContainerStringList() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "List<String>");
        assertThat(basicType.isSimpleTypeContainer(), equalTo(true));
    }

    @Test
    public void testGetContainedTypeForListObject() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "List<String>");
        assertThat(basicType.getContainedType(), equalTo("String"));
    }

    @Test
    public void testGetContainedTypeForScalarObject() throws Exception {
        SimplePojoProperty basicType = new SimplePojoProperty(N, "String");
        assertThat(basicType.getContainedType(), equalTo("String"));
    }
}
