package com.gather.gathercommons.util;

import java.math.BigDecimal;
import java.util.Map;

import com.gather.gathercommons.util.interfaces.IMapUtil;

public class MapUtil implements IMapUtil {

    public String convertTOstring(Map<String, Object> mapa) throws
                                                            RuntimeException {
        if (mapa != null) {

            StringBuilder sb = new StringBuilder(mapa.size() * 2);
            sb.append("(");

            for (Object o : mapa.values()) {
                if (o instanceof String) {
                    sb.append("'");
                    sb.append(o);
                    sb.append("'");
                } else if (o instanceof Integer ||
                        o instanceof Double ||
                        o instanceof Short ||
                        o instanceof Long ||
                        o instanceof BigDecimal) {
                    sb.append(o);
                } else if (o == null) {
                    sb.append("null");
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
