/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gather.gathercommons.xml;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * This class is used as a test class to generate the cryptographic keys ie
 * public and private key.
 *
 * @author <a href="mailto:debadatta.mishra@gmail.com">Debadatta Mishra</a>
 * @since 2013
 */
public class TestGenerateKeys {
    private static final Logger LOG = Logger.getLogger(TestGenerateKeys.class);
    /*
     * Main method to generate the keys
     */
    public static void main(String[] args) {
        LOG.info("INICIO TEST");

        String keysDirPath = new File("").getAbsolutePath();
        LOG.info("keysDirPath: " + keysDirPath);

        KryptoUtil util = new KryptoUtil();
        util.storeKeyPairs(keysDirPath);
        LOG.info("Private and Public Keys generated successfully ...");
        LOG.info("Las llaves estan creadas en el directorio workspace ...");
    }
}
