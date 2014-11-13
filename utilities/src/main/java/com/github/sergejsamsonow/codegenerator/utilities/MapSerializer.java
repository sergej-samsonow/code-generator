package com.github.sergejsamsonow.codegenerator.utilities;

import java.util.Map;

/**
 * Transform content of object to map an load content form map.
 */
public interface MapSerializer {

    /**
     * Populate object from incoming map.
     *
     * @param map
     */
    public void fromMap(Map<String, Object> map);

    /**
     * Return object content as map.
     *
     * @return
     */
    public Map<String, Object> toMap();
}
