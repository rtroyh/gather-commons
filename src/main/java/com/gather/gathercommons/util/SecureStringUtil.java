package com.gather.gathercommons.util;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 11-04-16
 * Time: 15:45
 */
public class SecureStringUtil {

    public static String objectToString(Object o) {
        if (o != null) {
            return o.toString().trim();
        }

        return "";
    }

    public static String cellRowToString(List<Object> row,
                                         Integer cellIndex) {
        if (Validator.validateList(row) && row.size() > cellIndex && row.get(cellIndex) != null) {
            return row.get(cellIndex).toString().trim();
        }

        return "";
    }
}
