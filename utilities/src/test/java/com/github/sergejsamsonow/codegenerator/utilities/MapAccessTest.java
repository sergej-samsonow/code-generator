package com.github.sergejsamsonow.codegenerator.utilities;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MapAccessTest {

    private static final int INTEGER_VALUE = 10;
    private static final String STRING_VALUE = "RESULT";
    private static final String EXISTING_KEY = "exists";
    private static final String MISSING_KEY = "missing";

    @Mock
    private Map<String, Object> map;

    private MapAccess access;

    @Before
    public void setUp() {
        when(map.containsKey(EXISTING_KEY)).thenReturn(true);
        when(map.containsKey(MISSING_KEY)).thenReturn(false);
        access = new MapAccess(map);
    }

    private void returnValue(Object value) {
        when(map.get(EXISTING_KEY)).thenReturn(value);
    }

    @Test
    public void testGetTypeExistingKey() throws Exception {
        returnValue(STRING_VALUE);
        assertThat(access.getType(EXISTING_KEY, String.class), equalTo(STRING_VALUE));
    }

    @Test
    public void testGetTypeMissingKey() throws Exception {
        assertThat(access.getType(MISSING_KEY, String.class), nullValue());
    }

    @Test
    public void testGetTypeExistingKeyButDifferentType() throws Exception {
        returnValue(INTEGER_VALUE);
        assertThat(access.getType(EXISTING_KEY, String.class), nullValue());
    }

    @Test
    public void testGetObjectExistingKey() throws Exception {
        returnValue(STRING_VALUE);
        assertThat(access.getObject(EXISTING_KEY), equalTo(STRING_VALUE));
    }

    @Test
    public void testGetObjectMissingKey() throws Exception {
        assertThat(access.getType(MISSING_KEY, String.class), nullValue());
    }

    @Test
    public void testGetBooleanExistingKey() throws Exception {
        returnValue(Boolean.TRUE);
        assertThat(access.getBoolean(EXISTING_KEY), equalTo(Boolean.TRUE));
    }

    @Test
    public void testGetBooleanMissingKey() throws Exception {
        assertThat(access.getBoolean(MISSING_KEY), equalTo(Boolean.FALSE));
    }

    @Test
    public void testGetBooleanExistingKeyButDifferntType() throws Exception {
        returnValue(INTEGER_VALUE);
        assertThat(access.getBoolean(EXISTING_KEY), equalTo(Boolean.FALSE));
    }

    @Test
    public void testGetIntegerExistingKey() throws Exception {
        returnValue(INTEGER_VALUE);
        assertThat(access.getInteger(EXISTING_KEY), equalTo(INTEGER_VALUE));
    }

    @Test
    public void testGetIntegerMissingKey() throws Exception {
        assertThat(access.getInteger(MISSING_KEY), equalTo(0));
    }

    @Test
    public void testGetIntegerExistingKeyBuDifferentType() throws Exception {
        returnValue(STRING_VALUE);
        assertThat(access.getInteger(EXISTING_KEY), equalTo(0));
    }

    @Test
    public void testGetFloatExistingKey() throws Exception {
        Float value = 0.1f;
        returnValue(value);
        assertThat(access.getFloat(EXISTING_KEY), equalTo(value));
    }

    @Test
    public void testGetFloatMissingKey() throws Exception {
        assertThat(access.getFloat(MISSING_KEY), equalTo(0.0F));
    }

    @Test
    public void testGetFloatExistingKeyButDifferentType() throws Exception {
        returnValue(STRING_VALUE);
        assertThat(access.getFloat(EXISTING_KEY), equalTo(0.0F));
    }

    @Test
    public void testGetLongExistingKey() throws Exception {
        Long value = 1L;
        returnValue(value);
        assertThat(access.getLong(EXISTING_KEY), equalTo(value));
    }

    @Test
    public void testGetLongMissingKey() throws Exception {
        assertThat(access.getLong(MISSING_KEY), equalTo(0L));
    }

    @Test
    public void testGetLongExistingKeyButDifferentType() throws Exception {
        returnValue(STRING_VALUE);
        assertThat(access.getLong(EXISTING_KEY), equalTo(0L));
    }

    @Test
    public void testGetDoubleExistingKey() throws Exception {
        Double value = 0.1D;
        returnValue(value);
        assertThat(access.getDouble(EXISTING_KEY), equalTo(value));
    }

    @Test
    public void testGetDoubleMissingKey() throws Exception {
        assertThat(access.getDouble(MISSING_KEY), equalTo(0.0D));
    }

    @Test
    public void testGetDoubleExistingKeyButDifferentType() throws Exception {
        returnValue(STRING_VALUE);
        assertThat(access.getDouble(EXISTING_KEY), equalTo(0.0D));
    }

    @Test
    public void testGetCharacterExistingKey() throws Exception {
        Character value = 'A';
        returnValue(value);
        assertThat(access.getCharacter(EXISTING_KEY), equalTo(value));
    }

    @Test
    public void testGetCharacterMissingKey() throws Exception {
        assertThat(access.getCharacter(MISSING_KEY), equalTo('\0'));
    }

    @Test
    public void testGetCharacterExistingKeyButDifferntType() throws Exception {
        returnValue(STRING_VALUE);
        assertThat(access.getCharacter(EXISTING_KEY), equalTo('\0'));
    }

    @Test
    public void testGetStringExistingKey() throws Exception {
        returnValue(STRING_VALUE);
        assertThat(access.getString(EXISTING_KEY), equalTo(STRING_VALUE));
    }

    @Test
    public void testGetStringMissingKey() throws Exception {
        assertThat(access.getString(MISSING_KEY), equalTo(""));
    }

    @Test
    public void testGetStringExistingKeyButDifferentType() throws Exception {
        returnValue(INTEGER_VALUE);
        assertThat(access.getString(EXISTING_KEY), equalTo(""));
    }

    @Test
    public void testGetBooleanListMissingKey() throws Exception {
        assertThat(access.getBooleanList(MISSING_KEY), equalTo(Collections.emptyList()));
    }

    @Test
    public void testGetBooleanListFromArray() throws Exception {
        returnValue(new boolean[] { true, true });
        assertThat(access.getBooleanList(EXISTING_KEY), equalTo(asList(true, true)));
    }

    @Test
    public void testGetBooleanListFromIterable() throws Exception {
        returnValue(asList(true, null, false));
        assertThat(access.getBooleanList(EXISTING_KEY), equalTo(asList(true, false, false)));
    }

    @Test
    public void testGetIntegerListMissingKey() throws Exception {
        assertThat(access.getIntegerList(MISSING_KEY), equalTo(Collections.emptyList()));
    }

    @Test
    public void testGetIntegerListFromArray() throws Exception {
        returnValue(new int[] { 1, 1 });
        assertThat(access.getIntegerList(EXISTING_KEY), equalTo(asList(1, 1)));
    }

    @Test
    public void testGetIntegerListFromIterable() throws Exception {
        returnValue(asList(1, null));
        assertThat(access.getIntegerList(EXISTING_KEY), equalTo(asList(1, 0)));
    }

    @Test
    public void testGetFloatListMissingKey() throws Exception {
        assertThat(access.getFloatList(MISSING_KEY), equalTo(Collections.emptyList()));
    }

    @Test
    public void testGetFloatListFromArray() throws Exception {
        returnValue(new float[] { 1.0f, 1.0f });
        assertThat(access.getFloatList(EXISTING_KEY), equalTo(asList(1.0f, 1.0f)));
    }

    @Test
    public void testGetFloatListFromIterable() throws Exception {
        returnValue(asList(1.0f, null));
        assertThat(access.getFloatList(EXISTING_KEY), equalTo(asList(1.0f, 0.0f)));
    }

}
