package com.github.sergejsamsonow.codegenerator.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapAccess {

    private static final String STRING_DEFAULT = "";
    private static final char CHARACTER_DEFAULT = '\0';
    private static final double DOUBLE_DEFAULT = 0.0D;
    private static final long LONG_DEFAULT = 0L;
    private static final float FLOAT_DEFAULT = 0.0F;
    private static final int INTEGER_DEFAULT = 0;
    private static final Boolean BOOLEAN_DEFAULT = Boolean.FALSE;

    private Map<String, Object> map;

    /**
     * Important object is immutable it creates an new copy from incoming map.
     */
    public MapAccess(Map<String, Object> map) {
        if (map == null) {
            this.map = new HashMap<>();
        }
        else {
            this.map = new HashMap<>(map);
        }
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

    /**
     * Return empty ArrayList if expected value not found. Iterating over list
     * and cast every list value to String if entry is Iterable or String array.
     * Insert default String value if cast of current Iterable entry fail or
     * entry is null.
     */
    public List<String> getStringList(String key) {
        List<String> casted = new ArrayList<>();
        Object value = getObject(key);
        if (value instanceof String[]) {
            for (String fetched : (String[]) value) {
                addToCasted(casted, String.class, fetched, STRING_DEFAULT);
            }
        }
        else if (value instanceof Iterable) {
            for (Object fetched : (Iterable<?>) value) {
                addToCasted(casted, String.class, fetched, STRING_DEFAULT);
            }
        }
        return casted;
    }

    /**
     * Return empty ArrayList if expected value not found. Iterating over list
     * and cast every list value to Character if entry is Iterable or char
     * array. Insert default Character value if cast of current Iterable entry
     * fail or entry is null.
     */
    public List<Character> getCharacterList(String key) {
        List<Character> casted = new ArrayList<>();
        Object value = getObject(key);
        if (value instanceof char[]) {
            for (char fetched : (char[]) value) {
                addToCasted(casted, Character.class, fetched, CHARACTER_DEFAULT);
            }
        }
        else if (value instanceof Iterable) {
            for (Object fetched : (Iterable<?>) value) {
                addToCasted(casted, Character.class, fetched, CHARACTER_DEFAULT);
            }
        }
        return casted;
    }

    /**
     * Return empty ArrayList if expected value not found. Return ArrayList
     * if entry is array of objects. Check NOT for null entries in result list.
     */
    public List<Object> getObjectList(String key) {
        List<Object> casted = new ArrayList<>();
        Object value = getObject(key);
        if (value instanceof Object[]) {
            for (Object fetched : (Object[]) value) {
                casted.add(fetched);
            }
        }
        else if (value instanceof Iterable) {
            for (Object fetched : (Iterable<?>) value) {
                casted.add(fetched);
            }
        }
        return casted;
    }

    /**
     * Return empty ArrayList if expected value not found. Return ArrayList
     * if entry is array of objects. Returned result list contains no null
     * entries null entries or entries with failed casting operation are
     * ignored.
     */
    public <T> List<T> getCastedList(Class<T> clazz, String key) {
        List<T> casted = new ArrayList<>();
        Object value = getObject(key);
        if (value instanceof Object[]) {
            for (Object fetched : (Object[]) value) {
                T castedValue = castTo(clazz, fetched);
                if (castedValue != null) {
                    casted.add(castedValue);
                }
            }
        }
        else if (value instanceof Iterable) {
            for (Object fetched : (Iterable<?>) value) {
                T castedValue = castTo(clazz, fetched);
                if (castedValue != null) {
                    casted.add(castedValue);
                }
            }
        }
        return casted;
    }

    private void populateMap(Map<String, Object> populated, Object from) {
        if (from instanceof Map) {
            for (Entry<?, ?> entry : ((Map<?, ?>) from).entrySet()) {
                Object key = entry.getKey();
                if (key instanceof String) {
                    populated.put((String) key, entry.getValue());
                }
            }
        }
    }

    /**
     * Return empty map if expected value not found, ignored map entries if
     * entry key is not a String.
     */
    public Map<String, Object> getMap(String key) {
        Map<String, Object> result = new HashMap<>();
        populateMap(result, getObject(key));
        return result;
    }

    /**
     * Return empty ArrayList if expected value not found. Return ArrayList
     * if entry is array of maps. Returned result contains no null values or
     * empty maps. All returned maps contains only string key entries.
     */
    public List<Map<String, Object>> getMapList(String key) {
        List<Map<String, Object>> result = new ArrayList<>();
        Object value = getObject(key);
        if (value instanceof Map[]) {
            for (Map<?, ?> fetched : (Map[]) value) {
                Map<String, Object> resultEntry = new HashMap<>();
                populateMap(resultEntry, fetched);
                if (!resultEntry.isEmpty()) {
                    result.add(resultEntry);
                }
            }
        }
        else if (value instanceof Iterable) {
            for (Object fetched : (Iterable<?>) value) {
                Map<String, Object> resultEntry = new HashMap<>();
                populateMap(resultEntry, fetched);
                if (!resultEntry.isEmpty()) {
                    result.add(resultEntry);
                }
            }
        }
        return result;
    }
}
