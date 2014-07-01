package com.gather.gathercommons;

import com.gather.gathercommons.util.Encryptor;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 01-07-14
 * Time: 11:26
 */
public class EncryptorTest {
    private static final Logger LOG = Logger.getLogger(EncryptorTest.class);

    @Test
    public void testMethod() {
        LOG.info("INICIO TEST ENCRIPTACION");

        Encryptor encryptor = new Encryptor();

        LOG.info(encryptor.encryptorBAC("test",
                                        true));
    }
}
