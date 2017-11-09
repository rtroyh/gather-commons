package com.gather.gathercommons.pdf;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 11/9/17
 * Time: 12:16
 */
public class ReportePDF implements Serializable {
    private InputStream stream;
    private String contentType;
    private String name;

    public ReportePDF(InputStream var1,
                      String var2,
                      String var3) {
        this.stream = var1;
        this.contentType = var2;
        this.name = var3;
    }

    public InputStream getStream() {
        return this.stream;
    }

    public void setStream(InputStream var1) {
        this.stream = var1;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String var1) {
        this.contentType = var1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }
}
