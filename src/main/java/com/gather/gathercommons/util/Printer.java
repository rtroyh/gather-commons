package com.gather.gathercommons.util;

import com.gather.gathercommons.util.interfaces.IListUtil;
import com.gather.gathercommons.util.interfaces.IMapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Printer implements IMapUtil,
                                IListUtil {

    public String convertTOstring(Map<String, Object> map) throws
                                                           RuntimeException {
        String texto = "";

        if (map != null) {

            for (Object o : map.values()) {
                if (o instanceof String) {
                    texto += "'" +
                            o +
                            "'";
                } else if (o instanceof Integer ||
                        o instanceof Double ||
                        o instanceof Short ||
                        o instanceof Long) {
                    texto += String.valueOf(o);
                } else if (o instanceof ArrayList) {
                    texto += this.convertTOstring(new ArrayList<Object>((ArrayList<?>) o));
                }

                texto += ",";
            }
        }

        return texto;
    }

    public String convertTOstring(List<Object> list) throws
                                                     RuntimeException {
        String text = "";

        if (Validator.validateList(list)) {
            for (int x = 0; x < list.size(); x++) {
                Object obj = list.get(x);

                if (obj instanceof ArrayList) {
                    text += this.convertTOstring(new ArrayList<Object>((ArrayList<?>) obj));
                } else if (obj != null) {
                    text += String.valueOf(obj).trim();
                }
            }
        }

        return text;
    }
}
