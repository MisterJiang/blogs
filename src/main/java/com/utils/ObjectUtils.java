//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.utils;

public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {
    public ObjectUtils() {
    }

    public static boolean isEquals(Object actual, Object expected) {
        return actual == null?expected == null:actual.equals(expected);
    }

    public static Long[] transformLongArray(long[] source) {
        Long[] destin = new Long[source.length];

        for(int i = 0; i < source.length; ++i) {
            destin[i] = Long.valueOf(source[i]);
        }

        return destin;
    }

    public static long[] transformLongArray(Long[] source) {
        long[] destin = new long[source.length];

        for(int i = 0; i < source.length; ++i) {
            destin[i] = source[i].longValue();
        }

        return destin;
    }

    public static Integer[] transformIntArray(int[] source) {
        Integer[] destin = new Integer[source.length];

        for(int i = 0; i < source.length; ++i) {
            destin[i] = Integer.valueOf(source[i]);
        }

        return destin;
    }

    public static int[] transformIntArray(Integer[] source) {
        int[] destin = new int[source.length];

        for(int i = 0; i < source.length; ++i) {
            destin[i] = source[i].intValue();
        }

        return destin;
    }
}
