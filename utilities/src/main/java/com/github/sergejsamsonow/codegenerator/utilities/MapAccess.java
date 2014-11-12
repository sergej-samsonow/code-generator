package com.github.sergejsamsonow.codegenerator.utilities;

import java.util.Map;

public class MapAccess {

    private static final String STRING_DEFAULT = "";
    private static final char CHARACTER_DEFAULT = '\0';
    private static final double DOUBLE_DEFAULT = 0.0D;
    private static final long LONG_DEFAULT = 0L;
    private static final float FLOAT_DEFAULT = 0.0F;
    private static final int INTEGER_DEFAULT = 0;
    private static final Boolean BOOLEAN_DEFAULT = Boolean.FALSE;

    private Map<String, Object> map;

    public MapAccess(Map<String, Object> map) {
        this.map = map;
    }

    private <T> T castTo(Class<T> clazz, Object value) {
        try {
            return clazz.cast(value);
        }
        catch (ClassCastException e) {
            return null;
        }
    }

    private <T> T fetchedOrDefault(T fetchedValue, T defaultValue) {
        return fetchedValue == null ? defaultValue : fetchedValue;
    }

    /**
     * Return object if exists or null
     */
    public Object getObject(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }

    /**
     * Return null if expected value not found or cast exception occurred.
     */
    public <T> T getType(String key, Class<T> clazz) {
        if (map.containsKey(key)) {
            return castTo(clazz, map.get(key));
        }
        return null;
    }

    /**
     * Return false if expected value not found or cast exception occurred.
     */
    public Boolean getBoolean(String key) {
        return fetchedOrDefault(getType(key, Boolean.class), BOOLEAN_DEFAULT);
    }

    /**
     * Return 0 if expected value not found or cast exception occurred.
     */
    public Integer getInteger(String key) {
        return fetchedOrDefault(getType(key, Integer.class), INTEGER_DEFAULT);
    }

    /**
     * Return 0.0F if expected value not found or cast exception occurred.
     */
    public Float getFloat(String key) {
        return fetchedOrDefault(getType(key, Float.class), FLOAT_DEFAULT);
    }

    /**
     * Return 0L if expected value not found or cast exception occurred.
     */
    public Long getLong(String key) {
        return fetchedOrDefault(getType(key, Long.class), LONG_DEFAULT);
    }

    /**
     * Return 0.0D if expected value not found or cast exception occurred.
     */
    public Double getDouble(String key) {
        return fetchedOrDefault(getType(key, Double.class), DOUBLE_DEFAULT);
    }

    /**
     * Return '\0' if expected value not found or cast exception occurred.
     */
    public Character getCharacter(String key) {
        return fetchedOrDefault(getType(key, Character.class), CHARACTER_DEFAULT);
    }

    /**
     * Return "" if expected value not found or cast exception occurred.
     */
    public String getString(String key) {
        return fetchedOrDefault(getType(key, String.class), STRING_DEFAULT);
    }

}
