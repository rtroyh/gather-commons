package com.gather.gathercommons.util.printer;

import com.gather.gathercommons.util.Validator;
import com.gather.gathercommons.util.printer.IListUtil;
import com.gather.gathercommons.util.printer.IMapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Printer implements IMapUtil,
                                IListUtil {

    public String convertTOstring(Map<String, Object> map) throws
                                                           RuntimeException {
        StringBuilder sb = new StringBuilder();

        if (map != null) {

            for (Object o : map.values()) {
                if (o instanceof String) {
                    sb.append("'");
                    sb.append(o);
                    sb.append("'");
                } else if (o instanceof Integer ||
                        o instanceof Double ||
                        o instanceof Short ||
                        o instanceof Long) {
                    sb.append(o);
                } else if (o instanceof ArrayList) {
                    sb.append(this.convertTOstring(new ArrayList<Object>((ArrayList<?>) o)));
                }

                sb.append(",");
            }
        }

        return sb.toString();
    }

    public String convertTOstring(List<Object> list, String separator, String delimiter1, String delimiter2) throws
                                                                                                             RuntimeException {
        StringBuilder sb = new StringBuilder(delimiter1);

        if (Validator.validateList(list)) {
            for (int x = 0; x < list.size(); x++) {
                Object obj = list.get(x);

                if (obj instanceof ArrayList) {
                    sb.append(this.convertTOstring(new ArrayList<Object>((ArrayList<?>) obj),
                                                   separator,
                                                   delimiter1,
                                                   delimiter2));
                } else if (obj != null) {
                    sb.append(String.valueOf(obj).trim());
                }

                if (x < list.size() - 1) {
                    sb.append(separator);
                }
            }

            sb.append(delimiter2);
        }

        return sb.toString();
    }

    public String convertTOstring(List<Object> list) throws
                                                     RuntimeException {
        return this.convertTOstring(list,
                                    ",",
                                    "[",
                                    "]");
    }

    public String convertTOstring(Object[] param) throws
                                                  RuntimeException {
        StringBuilder sb = new StringBuilder("[");

        if (param != null && param.length > 0) {
            for (int x = 0; x < param.length; x++) {
                Object obj = param[x];

                if (obj instanceof ArrayList) {
                    sb.append(this.convertTOstring(new ArrayList<Object>((ArrayList<?>) obj)));
                } else if (obj != null) {
                    sb.append(String.valueOf(obj).trim());
                }
            }
        }

        return sb.toString();
    }
}
