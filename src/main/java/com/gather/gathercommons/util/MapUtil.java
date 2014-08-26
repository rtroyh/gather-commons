package com.gather.gathercommons.util;

import com.gather.gathercommons.util.interfaces.IMapUtil;

import java.util.Map;

public class MapUtil implements IMapUtil {

    public String convertTOstring(Map<String, Object> mapa) throws
                                                            RuntimeException {
        if (mapa != null) {
            StringBuilder sb = new StringBuilder(mapa.size() * 2);
            sb.append("(");

            for (Object o : mapa.values()) {
                if (o == null) {
                    sb.append("null");
                } else if (o instanceof String) {
                    sb.append("'");
                    sb.append(o);
                    sb.append("'");
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
}
