package com.gather.gathercommons.html;

import com.gather.gathercommons.util.Validator;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 29-02-16
 * Time: 17:10
 */
public class HTMLUtil {
    private static final Logger LOG = Logger.getLogger(HTMLUtil.class);

    public String encode(String text) {
        LOG.debug(text);
        String temp = text;

        temp = temp.replaceAll("&",
                               "&#38;");
        temp = temp.replaceAll("<",
                               "&#60;");
        temp = temp.replaceAll(">",
                               "&#62;");

        for (int x = 0; x < temp.length(); x++) {
            if (Character.isSpaceChar(temp.charAt(x))) {
                temp = temp.replace(temp.charAt(x),
                                    ' ');
            }
        }


        temp = temp.replaceAll("\n",
                               "<br/>");
        LOG.debug(temp);

        return temp;
    }

    public String decode(String text) {
        LOG.debug(text);

        if (Validator.validateString(text)) {
            String temp = text;

            temp = temp.replaceAll("&#38;",
                                   "&");
            temp = temp.replaceAll("&#60;",
                                   "<");
            temp = temp.replaceAll("&#62;",
                                   ">");

            temp = temp.replaceAll("&#160;",
                                   " ");
            temp = temp.replaceAll("<br/>",
                                   "\n");
            LOG.debug(temp);

            return temp;
        }

        return "";
    }
}
