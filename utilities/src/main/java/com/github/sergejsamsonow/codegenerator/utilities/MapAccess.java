package com.github.sergejsamsonow.codegenerator.utilities;

import java.util.ArrayList;
import java.util.List;
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

    private <T> void addToCasted(List<T> casted, Class<T> clazz, Object fetched, T defaultValue) {
        casted.add(fetchedOrDefault(castTo(clazz, fetched), defaultValue));
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

    /**
     * Return empty ArrayList if expected value not found. Iterating over list
     * and cast every list value to Boolean if entry is Iterable or boolean
     * array. Insert default boolean value if cast of current Iterable entry
     * fail or entry is null.
     */
    public List<Boolean> getBooleanList(String key) {
        List<Boolean> casted = new ArrayList<>();
        Object value = getObject(key);
        if (value == null) {
            return casted;
        }
        if (value instanceof boolean[]) {
            for (boolean fetched : (boolean[]) value) {
                addToCasted(casted, Boolean.class, fetched, BOOLEAN_DEFAULT);
            }
        }
        else if (value instanceof Iterable) {
            for (Object fetched : (Iterable<?>) value) {
                addToCasted(casted, Boolean.class, fetched, BOOLEAN_DEFAULT);
            }
        }

        return casted;
    }

    /**
     * Return empty ArrayList if expected value not found. Iterating over list
     * and cast every list value to Integer if entry is Iterable or int array.
     * Insert default Integer value if cast of current Iterable entry fail or
     * entry is null.
     */
    public List<Integer> getIntegerList(String key) {
        List<Integer> casted = new ArrayList<>();
        Object value = getObject(key);
        if (value instanceof int[]) {
            for (int fetched : (int[]) value) {
                addToCasted(casted, Integer.class, fetched, INTEGER_DEFAULT);
            }
        }
        else if (value instanceof Iterable) {
            for (Object fetched : (Iterable<?>) value) {
                addToCasted(casted, Integer.class, fetched, INTEGER_DEFAULT);
            }
        }
        return casted;
    }

    /**
     * Return empty ArrayList if expected value not found. Iterating over list
     * and cast every list value to Float if entry is Iterable or float array.
     * Insert default Float value if cast of current Iterable entry fail or
     * entry is null.
     */
    public List<Float> getFloatList(String key) {
        List<Float> casted = new ArrayList<>();
        Object value = getObject(key);
        if (value instanceof float[]) {
            for (float fetched : (float[]) value) {
                addToCasted(casted, Float.class, fetched, FLOAT_DEFAULT);
            }
        }
        else if (value instanceof Iterable) {
            for (Object fetched : (Iterable<?>) value) {
                addToCasted(casted, Float.class, fetched, FLOAT_DEFAULT);
            }
        }
        return casted;
    }

    /**
     * Return empty ArrayList if expected value not found. Iterating over list
     * and cast every list value to Long if entry is Iterable or long array.
     * Insert default Long value if cast of current Iterable entry fail or
     * entry is null.
     */
    public List<Long> getLongList(String key) {
        List<Long> casted = new ArrayList<>();
        Object value = getObject(key);
        if (value instanceof long[]) {
            for (long fetched : (long[]) value) {
                addToCasted(casted, Long.class, fetched, LONG_DEFAULT);
            }
        }
        else if (value instanceof Iterable) {
            for (Object fetched : (Iterable<?>) value) {
                addToCasted(casted, Long.class, fetched, LONG_DEFAULT);
            }
        }
        return casted;
    }

    /**
     * Return empty ArrayList if expected value not found. Iterating over list
     * and cast every list value to Double if entry is Iterable or double array.
     * Insert default Double value if cast of current Iterable entry fail or
     * entry is null.
     */
    public List<Double> getDoubleList(String key) {
        List<Double> casted = new ArrayList<>();
        Object value = getObject(key);
        if (value instanceof double[]) {
            for (double fetched : (double[]) value) {
                addToCasted(casted, Double.class, fetched, DOUBLE_DEFAULT);
            }
        }
        else if (value instanceof Iterable) {
            for (Object fetched : (Iterable<?>) value) {
                addToCasted(casted, Double.class, fetched, DOUBLE_DEFAULT);
            }
        }
        return casted;
    }
}
