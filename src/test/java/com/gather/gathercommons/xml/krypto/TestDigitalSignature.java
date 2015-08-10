/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gather.gathercommons.xml.krypto;

import java.io.File;

/**
 * This class is used as a test class to sign an xml document digitally.
 *
 * @author <a href="mailto:debadatta.mishra@gmail.com">Debadatta Mishra</a>
 * @since 2013
 */
public class TestDigitalSignature {
    /*
     * Main method to generate a digitally signed xml document.
     */
    public static void main(String[] args) {
        String keysDirPath = new File("").getAbsolutePath();

        String xmlFilePath = keysDirPath + File.separator + "fatca.xml";
        String signedXmlFilePath = keysDirPath + File.separator + "fatca_payload.xml";
        String privateKeyFilePath = keysDirPath + File.separator + "privatekey.key";
        String publicKeyFilePath = keysDirPath + File.separator + "publickey.key";

        XmlDigitalSignatureGenerator xmlSig = new XmlDigitalSignatureGenerator();
        xmlSig.generateXMLDigitalSignature(xmlFilePath,
                                           signedXmlFilePath,
                                           privateKeyFilePath,
                                           publicKeyFilePath);
    }
}
