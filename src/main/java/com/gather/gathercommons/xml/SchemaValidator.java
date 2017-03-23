package com.gather.gathercommons.xml;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
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
 */
public class SchemaValidator {
    private static final Logger LOG = Logger.getLogger(SchemaValidator.class);

    private List<URL> schemas;

    public void addSchema(URL schema) {
        this.getSchemas().add(schema);
    }

    private List<URL> getSchemas() {
        if (schemas == null) {
            schemas = new ArrayList();
        }

        return schemas;
    }

    public Boolean isValid(URL xmlFile) {
        LOG.info("INICIO VALIDACION");

        try {
            // parse an XML document into a DOM tree
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = parser.parse(new File(xmlFile.toURI()));

            // create a SchemaFactory capable of understanding WXS schemas
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // load a WXS schema, represented by a Schema instance
            Schema schema = getSchema(factory);

            // create a Validator instance, which can be used to validate an instance document
            Validator validator = schema.newValidator();

            // validate the DOM tree
            validator.validate(new DOMSource(document));
        } catch (SAXException | IOException | ParserConfigurationException | URISyntaxException e) {
            LOG.error(e.getMessage());
            return false;
        }

        return true;
    }

    private Schema getSchema(SchemaFactory factory) throws
                                                    URISyntaxException,
                                                    SAXException {
        List<Source> sources = new ArrayList<>();

        for (URL url : this.getSchemas()) {
            sources.add(new StreamSource(new File(url.toURI())));
        }

        return factory.newSchema(sources.toArray(new Source[]{}));
    }
}
