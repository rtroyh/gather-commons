package com.gather.gathercommons.xml;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 3/23/17
 * Time: 15:32
 */
public class XmlValidator {
    private static final Logger LOG = Logger.getLogger(XmlValidator.class);

    /**
     * Validate provided XML against the provided XSD schema files.
     *
     * @param xmlFilePathAndName    Path/name of XML file to be validated;
     *                              should not be null or empty.
     * @param xsdFilesPathsAndNames XSDs against which to validate the XML;
     *                              should not be null or empty.
     */
    public static void validateXmlAgainstXsds(final String xmlFilePathAndName,
                                              final String[] xsdFilesPathsAndNames) {
        if (xmlFilePathAndName == null || xmlFilePathAndName.isEmpty()) {
            LOG.info("ERROR: Path/name of XML to be validated cannot be null.");
            return;
        }
        if (xsdFilesPathsAndNames == null || xsdFilesPathsAndNames.length < 1) {
            LOG.info("ERROR: At least one XSD must be provided to validate XML against.");
            return;
        }

        final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        final StreamSource[] xsdSources = generateStreamSourcesFromXsdPathsJdk8(xsdFilesPathsAndNames);

        try {
            final Schema schema = schemaFactory.newSchema(xsdSources);
            final Validator validator = schema.newValidator();
            LOG.info("Validating " + xmlFilePathAndName + " against XSDs " + Arrays.toString(xsdFilesPathsAndNames) + "...");
            validator.validate(new StreamSource(new File(xmlFilePathAndName)));
        } catch (IOException | SAXException exception) {
            LOG.info("ERROR: Unable to validate " + xmlFilePathAndName + " against XSDs " + Arrays.toString(xsdFilesPathsAndNames)
                     + " - " + exception);
        }

        LOG.info("Validation process completed.");
    }

    /**
     * Generates array of StreamSource instances representing XSDs
     * associated with the file paths/names provided and use JDK 8
     * Stream API.
     * <p>
     * This method can be commented out if using a version of
     * Java prior to JDK 8.
     *
     * @param xsdFilesPaths String representations of paths/names
     *                      of XSD files.
     * @return StreamSource instances representing XSDs.
     */
    private static StreamSource[] generateStreamSourcesFromXsdPathsJdk8(final String[] xsdFilesPaths) {
        return Arrays.stream(xsdFilesPaths)
                     .map(StreamSource::new)
                     .collect(Collectors.toList())
                     .toArray(new StreamSource[xsdFilesPaths.length]);
    }

    /**
     * Validates provided XML against provided XSD.
     *
     * @param arguments XML file to be validated (first argument) and
     *                  XSD against which it should be validated (second and later
     *                  arguments).
     */
    public static void main(final String[] arguments) {
        LOG.info("USAGE: java XmlValidator <xmlFile> <xsdFile1> ... <xsdFileN>\n");
        LOG.info("\tOrder of XSDs can be significant (place XSDs that are");
        LOG.info("\tdependent on other XSDs after those they depend on)");

        final String[] schemas = {"xml/fatca/oecdtypes_v4.2.xsd", "xml/fatca/isofatcatypes_v1.1.xsd", "xml/fatca/stffatcatypes_v2.0.xsd", "xml/fatca/FatcaXML_v2.0.xsd"};
        validateXmlAgainstXsds("xml/fatca/valido.xml",
                               schemas);
    }
}  