package com.gather.gathercommons.xml;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 3/23/17
 * Time: 12:33
 * ES IMPORTANTE EL ORDEN EN QUE SE CARGAN LOS SCHEMAS: LOS ESQUEMAS QUE DEPENDEN DE OTROS DEBEN SER CARGADOS DESPUES DE LO QUE DEPENDEN.
 */
public class XMLValidator {
    private static final Logger LOG = Logger.getLogger(XMLValidator.class);

    private List<URL> schemas;
    private ErrorHandler errorHandler;

    public void addSchema(URL schema) {
        this.getSchemas().add(schema);
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    private List<URL> getSchemas() {
        if (schemas == null) {
            schemas = new ArrayList<>();
        }

        return schemas;
    }

    public void isValid(URL xmlFile) throws
                                     URISyntaxException,
                                     SAXException,
                                     IOException {
        LOG.info("INICIO VALIDACION");

        this.isValid(xmlFile.openStream());
    }

    public void isValid(InputStream xmlFile) throws
                                             URISyntaxException,
                                             SAXException,
                                             IOException {
        LOG.info("INICIO VALIDACION");

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Schema schema = this.getSchema(factory);
        Validator validator = schema.newValidator();

        if (this.errorHandler != null) {
            validator.setErrorHandler(this.errorHandler);
        }

        validator.validate(new StreamSource(xmlFile));
    }

    private Schema getSchema(SchemaFactory factory) throws
                                                    URISyntaxException,
                                                    SAXException {
        List<Source> sources = new ArrayList<>();

        for (URL url : this.getSchemas()) {
            sources.add(new StreamSource(new File(url.toURI())));
        }

        Schema schema = factory.newSchema(sources.toArray(new Source[0]));

        return schema;
    }
}
