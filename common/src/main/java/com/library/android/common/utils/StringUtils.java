package com.library.android.common.utils;

import java.util.Arrays;

public final class StringUtils {

    private StringUtils() {
    }

    public static String getDefaultString(String value, String defaultString) {
        if (isNotNullNotEmpty(value)) {
            return value;
        } else {
            return defaultString;
        }
    }

    public static boolean isNotNullNotEmpty(String s) {
        return s != null && !s.isEmpty() && !s.equalsIgnoreCase("null");
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty() || s.equalsIgnoreCase("null");
    }

    public static boolean isNotNullNotEmpty(String... strings) {
        return strings != null && strings.length > 0 && Utils.getFilteredList(Arrays.asList(strings), StringUtils::isNullOrEmpty).size() == 0;
    }
}
