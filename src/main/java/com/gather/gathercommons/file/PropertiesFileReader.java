package com.gather.gathercommons.file;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * $ Project: bci_corredor_formulario_masivo
 * User: rodrigotroy
 * Date: 23-08-16
 * Time: 18:28
 */
public class PropertiesFileReader {
    private static final Logger LOG = Logger.getLogger(PropertiesFileReader.class);

    public static Properties getProperties(String url) {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(url);

            properties.load(input);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage());
                }
            }
        }

        return properties;
    }
}
