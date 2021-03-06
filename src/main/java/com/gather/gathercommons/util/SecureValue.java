package com.gather.gathercommons.util;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 4/5/17
 * Time: 18:20
 */
public class SecureValue {
    private SecureValue() {
        throw new IllegalStateException("Utility class");
    }

    public static Boolean objectToBoolean(Object o) {
        if (o != null) {
            if (o.toString().trim().equals("1")) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    public static String objectToString(Object o) {
        if (o != null) {
            return o.toString().trim();
        }

        return "";
    }

    public static Boolean cellRowToBoolean(List<Object> row,
                                           Integer cellIndex) {
        if (Validator.validateList(row) && row.size() > cellIndex && row.get(cellIndex) != null) {
            return SecureValue.objectToBoolean(row.get(cellIndex));
        }

        return Boolean.FALSE;
    }

    public static String cellRowToString(List<Object> row,
                                         Integer cellIndex) {
        if (Validator.validateList(row) && row.size() > cellIndex && row.get(cellIndex) != null) {
            return row.get(cellIndex).toString().trim();
        }

        return "";
    }
}
