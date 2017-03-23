package com.gather.gathercommons.xml;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 3/23/17
 * Time: 12:33
 */
public class SchemaValidator {
    private List<URL> schemas;

    private void addSchema(URL schema) {
        this.getSchemas().add(schema);
    }

    private List<URL> getSchemas() {
        if (schemas == null) {
            schemas = new ArrayList();
        }

        return schemas;
    }

    public Boolean isValid(URL xmlFile) {
        return false;
    }
}
