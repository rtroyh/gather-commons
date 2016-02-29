package com.gather.gathercommons.html;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 29-02-16
 * Time: 17:10
 */
public class Base64URLUtil {
    private static final Logger LOG = Logger.getLogger(Base64URLUtil.class);

    public String decode(String text) {
        LOG.debug(text);
        String temp = text;

        temp = temp.replaceAll("-",
                               "+");
        temp = temp.replaceAll("_",
                               "/");
        LOG.debug(temp);

        return temp;
    }
}
