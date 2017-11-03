package com.gather.gathercommons.util;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 11/3/17
 * Time: 13:23
 */
public class Base64 {
    public String encode(String text,
                         boolean urlSafe) {
        java.util.Base64.Encoder encoder;

        if (urlSafe) {
            encoder = java.util.Base64.getUrlEncoder();
        } else {
            encoder = java.util.Base64.getEncoder();
        }

        return new String(encoder.encode(text.getBytes()));
    }

    public String decode(String text,
                         boolean urlSafe) {
        java.util.Base64.Decoder decoder;

        if (urlSafe) {
            decoder = java.util.Base64.getUrlDecoder();
        } else {
            decoder = java.util.Base64.getDecoder();
        }

        return new String(decoder.decode(text.getBytes()));
    }
}
