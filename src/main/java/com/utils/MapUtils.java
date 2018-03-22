//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtils {
    public MapUtils() {
    }

    public static boolean isEmpty(Map<?, ?> sourceMap) {
        return sourceMap == null || sourceMap.size() == 0;
    }

    public static boolean isNotEmpty(Map<?, ?> sourceMap) {
        return !isEmpty(sourceMap);
    }

    public static boolean putMapNotEmptyKey(Map<String, String> map, String key, String value) {
        if(map != null && !StringUtils.isEmpty(key)) {
            try {
                map.put(key, value);
                return true;
            } catch (Exception var4) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean putMapNotEmptyValue(Map<String, String> map, String key, String value) {
        if(map != null && !StringUtils.isEmpty(key)) {
            if(!StringUtils.isEmpty(value)) {
                try {
                    map.put(key, value);
                } catch (Exception var4) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static boolean putMapNotEmptyValue(Map<String, String> map, String key, String value, String defaultValue) {
        if(map != null && key != null) {
            if(StringUtils.isEmpty(value)) {
                value = defaultValue;
            }

            try {
                map.put(key, value);
                return true;
            } catch (Exception var5) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static <V> String getKeyByValue(Map<String, V> map, V value) {
        if(isEmpty(map)) {
            return null;
        } else {
            Iterator i$ = map.entrySet().iterator();

            Entry entry;
            do {
                if(!i$.hasNext()) {
                    return null;
                }

                entry = (Entry)i$.next();
            } while(!ObjectUtils.isEquals(entry.getValue(), value));

            return (String)entry.getKey();
        }
    }
}
