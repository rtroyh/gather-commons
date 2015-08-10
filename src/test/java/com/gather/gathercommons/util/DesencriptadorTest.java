package com.gather.gathercommons.util;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.*;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 05-07-14
 * Time: 15:10
 */
public class DesencriptadorTest {
    private static final Logger LOG = Logger.getLogger(DesencriptadorTest.class);

    @Test
    public void testMethod() {
        LOG.info("INICIO TEST DESENCRIPTADORTEST");
        //FALSE = ENCRIPTAR
        final Encryptor encryptor = new Encryptor();

        final URL resource = DesencriptadorTest.class.getResource("/T1.txt");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(resource.getPath()));

            String line = br.readLine();

            while (line != null) {
                LOG.info(line);
                String par1 = line.substring(line.indexOf("par2") + 5,
                                             line.indexOf("&par3"));

                String par2 = line.substring(line.indexOf("par1") + 5,
                                             line.indexOf("&par2"));

                LOG.info("par1 = " + par1);
                LOG.info("par2 = " + par2);
                LOG.info("par1 des " + encryptor.encryptorBAC(par1,
                                                               true));
                LOG.info("par2 des " + encryptor.encryptorBAC(par2,
                                                               true));


                line = br.readLine();
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
