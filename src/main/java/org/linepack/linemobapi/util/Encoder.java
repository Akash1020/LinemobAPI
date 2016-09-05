/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.util;

import java.util.Base64;

/**
 *
 * @author Leandro
 */
public class Encoder {

    public static String encodeBase64(String value) {
        byte[] bytesEncoded;
        bytesEncoded = Base64.getEncoder().encode(value.getBytes());
        return new String(bytesEncoded);
    }

}
