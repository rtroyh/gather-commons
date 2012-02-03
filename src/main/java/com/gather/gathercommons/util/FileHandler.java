package com.gather.gathercommons.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * CLASE DE AYUDA PARA MANIPULACION DE ARCHIVOS.
 * 
 * @author RTROY
 * 
 */
public class FileHandler {

    /**
     * COPIA UN ARCHIVO DESDE UNA RUTA ORIGEN A OTRA RUTA DESTINO.
     * 
     * @param inFile
     *            STRING
     * @param outFile
     *            STRING
     * @throws java.io.IOException
     */
    public synchronized static void copy(String inFile,
                                         String outFile) throws IOException {
        File fileIN = new File(inFile);
        File fileOUT = new File(outFile);

        FileHandler.copy(fileIN,
                         fileOUT);
    }

    /**
     * COPIA UN ARCHIVO DESDE UNA RUTA ORIGEN A OTRA RUTA DESTINO.
     * 
     * @param inFile
     *            java.io.File
     * @param outFile
     *            java.io.File
     * @throws java.io.IOException
     */
    public synchronized static void copy(File inFile,
                                         File outFile) throws IOException {

        if (!inFile.exists()) {
            return;
        }

        if (inFile.getCanonicalPath().equals(outFile.getCanonicalPath())) {
            // inFile and outFile are the same;
            // hence no copying is required.
            return;
        }

        InputStream in = null;
        OutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream(inFile));
            out = new BufferedOutputStream(new FileOutputStream(outFile));

            for (int c = in.read(); c != -1; c = in.read()) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
