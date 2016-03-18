package com.gather.gathercommons.pdf;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 18-03-16
 * Time: 10:47
 */
public class ReportePDF implements Serializable {
    private InputStream stream;
    private String contentType;
    private String name;

    public ReportePDF(InputStream stream,
                      String contentType,
                      String name) {
        this.stream = stream;
        this.contentType = contentType;
        this.name = name;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
