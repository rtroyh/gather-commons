package com.gather.gathercommons.util;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
        //FALSE = ENCRIPTAR
        final Encryptor encryptor = new Encryptor();

        String text = "GFUENTT";
        LOG.info("TEXTO A ENCRIPTAR = " + text);


        final String encryptedText = encryptor.encryptorBAC(text,
                                                            false);
        LOG.info("TEXTO ENCRIPTADO = " + encryptedText);


        final String decipherText = encryptor.encryptorBAC(encryptedText,
                                                           true);
        LOG.info("TEXTO DESENCRIPTADO = " + decipherText);


        try {
            PrintWriter writer = new PrintWriter("encriptados.txt",
                                                 "UTF-8");
            writer.println(text);
            writer.println(encryptedText);
            writer.println(decipherText);

            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        LOG.info(encryptedText.trim() + " length =" + encryptedText.length());

        for (int x = 0; x < encryptedText.length(); x++) {
            int value = encryptedText.charAt(x);
            LOG.info(x + ". " + encryptedText.charAt(x) + " ==> " + value);
        }
    }
}