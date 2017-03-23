package com.gather.gathercommons.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 11-03-16
 * Time: 11:33
 */
public class ZIPUtil {
    //todo: sacar parametro extensionArchivos
    public static ByteArrayOutputStream getZIP(Map<String, ByteArrayOutputStream> byteArrayOutputStreamStringMap,
                                               String extensionArchivos) throws
                                                                         IOException {
        if (byteArrayOutputStreamStringMap != null) {
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream archivosOS = new ByteArrayOutputStream();
            ZipOutputStream archivosZIP = new ZipOutputStream(archivosOS);

            for (Map.Entry<String, ByteArrayOutputStream> stringByteArrayOutputStreamEntry : byteArrayOutputStreamStringMap.entrySet()) {
                Map.Entry mapEntry = stringByteArrayOutputStreamEntry;
                String nombreArchivo = mapEntry.getKey().toString();

                ZipEntry ze = new ZipEntry(nombreArchivo + extensionArchivos);

                InputStream fileIS = new ByteArrayInputStream(((ByteArrayOutputStream) mapEntry.getValue()).toByteArray());
                archivosZIP.putNextEntry(ze);

                int len;
                while ((len = fileIS.read(buffer)) > 0) {
                    archivosZIP.write(buffer,
                                      0,
                                      len);
                }

                fileIS.close();
            }

            archivosZIP.closeEntry();
            archivosZIP.close();

            return archivosOS;
        }

        return null;
    }
}
