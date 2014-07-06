package com.gather.gathercommons;

import com.gather.gathercommons.util.Encryptor;
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

        String text = "123";
        LOG.info(text);
        final String textEncriptado = encryptor.encryptorBAC(text,
                                                             false);
        final String desEncriptado = encryptor.encryptorBAC(textEncriptado,
                                                            true);

        try {
            PrintWriter writer = new PrintWriter("encriptados.txt",
                                                 "UTF-8");
            writer.println(text);
            writer.println(textEncriptado);
            writer.println(desEncriptado);

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        LOG.info("'" + textEncriptado.trim() + "' length=" + textEncriptado.length());

        for (int x = 0; x < textEncriptado.length(); x++) {
            int value = textEncriptado.charAt(x);
            LOG.info(textEncriptado.charAt(x) + " ==> " + value);
        }
    }
}