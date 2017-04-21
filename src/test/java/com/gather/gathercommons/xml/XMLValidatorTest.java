package com.gather.gathercommons.xml;

import com.gather.gathercommons.util.Validator;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 3/23/17
 * Time: 12:44
 */
public class XMLValidatorTest {
    private static final Logger LOG = Logger.getLogger(XMLValidatorTest.class);

    private XMLValidator xmlvalidator;

    @BeforeTest
    private void init() {
        xmlvalidator = new XMLValidator();
    }

    @DataProvider
    public Object[][] validos() {
        return new Object[][]{
                {"xml/fatca/valido.xml", new String[]{"xml/fatca/oecdtypes_v4.2.xsd", "xml/fatca/isofatcatypes_v1.1.xsd", "xml/fatca/stffatcatypes_v2.0.xsd", "xml/fatca/FatcaXML_v2.0.xsd"}}};
    }

    @DataProvider
    public Object[][] invalidos() {
        return new Object[][]{
                /*{"xml/fatca/3Y0QKJ.00002.ME.152.xml", new String[]{"xml/fatca/oecdtypes_v4.2.xsd", "xml/fatca/isofatcatypes_v1.1.xsd", "xml/fatca/stffatcatypes_v2.0.xsd", "xml/fatca/FatcaXML_v2.0.xsd"}},*/
                {"xml/fatca/invalido2.xml", new String[]{"xml/fatca/oecdtypes_v4.2.xsd", "xml/fatca/isofatcatypes_v1.1.xsd", "xml/fatca/stffatcatypes_v2.0.xsd", "xml/fatca/FatcaXML_v2.0.xsd"}},
                /*{"xml/fatca/invalido3.xml", new String[]{"xml/fatca/oecdtypes_v4.2.xsd", "xml/fatca/isofatcatypes_v1.1.xsd", "xml/fatca/stffatcatypes_v2.0.xsd", "xml/fatca/FatcaXML_v2.0.xsd"}}*/};
    }

    @Test(dataProvider = "invalidos", expectedExceptions = {URISyntaxException.class, SAXException.class, URISyntaxException.class})
    public void testInvalidos(Object o1,
                              Object o2) throws
                                         SAXException,
                                         IOException,
                                         URISyntaxException {
        LOG.info("INICIO TEST XMLS INVALIDOS");

        URL xml = getClass().getClassLoader().getResource(o1.toString());

        String[] schema = (String[]) o2;
        for (String s : schema) {
            xmlvalidator.addSchema(getClass().getClassLoader().getResource(s));
        }

        try {
            xmlvalidator.isValid(xml);
        } catch (URISyntaxException | IOException | SAXException e) {
            LOG.error(e.getMessage());

            throw e;
        }
    }

    @Test(dataProvider = "validos")
    public void testValidos(Object o1,
                            Object o2) throws
                                       SAXException,
                                       IOException,
                                       URISyntaxException {
        LOG.info("INICIO TEST XMLS VALIDOS");

        URL xml = getClass().getClassLoader().getResource(o1.toString());

        String[] schema = (String[]) o2;
        for (String s : schema) {
            xmlvalidator.addSchema(getClass().getClassLoader().getResource(s));
        }

        try {
            xmlvalidator.isValid(xml);
        } catch (URISyntaxException | IOException | SAXException e) {
            LOG.error(e.getMessage());

            throw e;
        }
    }

    @Test(dataProvider = "invalidos")
    public void testInvalidosConErrorHandler(Object o1,
                                             Object o2) {
        LOG.info("INICIO TEST XMLS INVALIDOS USANDO ERROR HANDLER");

        xmlvalidator.setErrorHandler(new GatherErrorHandler());

        URL xml = getClass().getClassLoader().getResource(o1.toString());

        String[] schema = (String[]) o2;
        for (String s : schema) {
            xmlvalidator.addSchema(getClass().getClassLoader().getResource(s));
        }

        try {
            xmlvalidator.isValid(xml);
        } catch (URISyntaxException | IOException | SAXException e) {
            LOG.error(e.getMessage());
        }

        GatherErrorHandler errorHandler = (GatherErrorHandler) xmlvalidator.getErrorHandler();

        Assert.assertTrue(Validator.validateList(errorHandler.getExceptionList()));
    }
}
