package com.gather.gathercommons.util;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 11-04-16
 * Time: 15:26
 */
public class ObjectUtil {

    public static String toString(Object o) {
        if (o != null) {
            return o.toString();
        }

        return "";
    }
}
