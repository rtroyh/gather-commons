package com.gather.gathercommons.util;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 01-07-14
 * Time: 11:25
 */
public class Encryptor {
    public String encryptorBAC(String sPassword,
                               boolean bEncript) {
        final int lengthPassword = sPassword.length();

        int i = 0;
        final int LEN_PSW = 15;
        final int nMAGIC1 = 15;
        final int nMAGIC2 = 11;
        final int nMAGIC3 = 253;
        final String KEY_PSW = "1j2m3*4s5x6/7c8h9^0yr<=ze";

        int nAnt = nMAGIC1;
        int jDir = bEncript ? lengthPassword : 1;
        int kDir = 0;

        String cPsw = "";

        for (int iDir = 1; iDir <= lengthPassword; iDir++) {
            if (iDir > LEN_PSW) {
                kDir = 1;
            } else {
                kDir = kDir + 1;
            }

            final int nAsc = sPassword.charAt(jDir - 1);
            final int nKey = KEY_PSW.charAt(kDir - 1);
            final int nPsw = nAsc ^ nKey ^ nAnt ^ ((i / nMAGIC2) % nMAGIC3);

            if (bEncript) {
                cPsw = cPsw + Character.toString((char) nPsw);
                nAnt = nAsc;
                jDir = jDir - 1;
            } else {
                cPsw = Character.toString((char) nPsw) + cPsw;
                nAnt = nPsw;
                jDir = jDir + 1;
            }
        }

        return cPsw;
    }
}
