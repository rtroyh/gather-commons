package com.gather.gathercommons.util.printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUtil {
    public static String convertTOstring(Map<String, Object> map) {
        if (map != null) {
            StringBuilder sb = new StringBuilder(map.size() * 2);
            sb.append("(");

            for (Object o : map.values()) {
                if (o == null) {
                    sb.append("null");
                } else if (o instanceof String) {
                    sb.append("'");
                    sb.append(o);
                    sb.append("'");
                } else if (o instanceof Double) {
                    sb.append(String.format("%s",
                                            o));
                } else {
                    sb.append(o.toString());
                }

                sb.append(",");
            }

            sb.delete(sb.length() - 1,
                      sb.length());
            sb.append(")");

            return sb.toString();
        }

        return null;
    }

    public static List<List<Object>> convertTOList(Map<String, Object> map) {
        List<List<Object>> list = new ArrayList<>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            List<Object> row = new ArrayList<>();
            row.add(entry.getKey());
            row.add(entry.getValue());
            list.add(row);
        }

        return list;
    }
}
