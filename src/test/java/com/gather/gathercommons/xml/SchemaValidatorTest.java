package com.gather.gathercommons.xml;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 3/23/17
 * Time: 12:44
 */
public class SchemaValidatorTest {
    private static final Logger LOG = Logger.getLogger(SchemaValidatorTest.class);

    private SchemaValidator schemaValidator;

    @BeforeTest
    private void init() {
        schemaValidator = new SchemaValidator();
    }

    @DataProvider
    public Object[][] validos() {
        return new Object[][]{
                {"xml/fatca/valido.xml", new String[]{"xml/fatca/oecdtypes_v4.2.xsd", "xml/fatca/isofatcatypes_v1.1.xsd", "xml/fatca/stffatcatypes_v2.0.xsd", "xml/fatca/FatcaXML_v2.0.xsd"}}};
    }

    @Test(dataProvider = "validos")
    public void testValidos(Object o1,
                            Object o2) {
        LOG.info("INICIO");

        URL xml = getClass().getClassLoader().getResource(o1.toString());

        String[] schema = (String[]) o2;
        for (String s : schema) {
            schemaValidator.addSchema(getClass().getClassLoader().getResource(s));
        }

        Assert.assertEquals(java.util.Optional.of(true),
                            schemaValidator.isValid(xml));
    }
}
