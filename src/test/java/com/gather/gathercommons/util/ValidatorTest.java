package com.gather.gathercommons.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 23-05-16
 * Time: 14:52
 */
public class ValidatorTest {
    @DataProvider
    public Object[][] listaMail() {
        return new Object[][]{
                {"rtroy@gather.cl", true},
                {"1", false},
                {"rtroy@.com", false},
                {"pepe@pepe.pepe", true}};
    }

    @Test(dataProvider = "listaMail")
    public void testEmailGenerator(Object o1,
                                   Object o2) {
        Assert.assertEquals(Validator.validateMail(o1.toString()),
                            o2);
    }
}
