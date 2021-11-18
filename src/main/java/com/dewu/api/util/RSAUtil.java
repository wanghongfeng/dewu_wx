package com.dewu.api.util;

import cn.hutool.core.codec.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author fanjie
 * @date 2021/11/18 19:47
 */
public class RSAUtil {
    private static final String PUB_KEY =
            "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANeBpp2h87T10BskMkdTU4Wlp+9phEqjkSGXttUpBW1s42y0EyHySNfwH7bTEvMN83Dtb40iYxiRFbALdMDgmzsCAwEAAQ==";
    private static final String PATTERN = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final String S = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String str) throws Exception {
        return encryptByPublicKey(PUB_KEY, str);
    }

    /**
     * 公钥加密
     *
     * @param publicKeyText
     * @param text
     * @return
     */
    public static String encryptByPublicKey(String publicKeyText, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decode(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encode(result);
    }

    /**
     * 公钥解密
     *
     * @param publicKeyText
     * @param text
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String publicKeyText, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(Base64.decode(text));
        return new String(result);
    }

    public static String pemToHex(String pemKey) {
        int o = 0;
        int n = 0;
        int r = 0;
        String i = "";
        for (int e = 0; e < pemKey.length() && pemKey.charAt(e) != '='; e++) {
            r = PATTERN.indexOf(pemKey.charAt(e));
            if (r < 0) {
                if (1 == o) {
                    i += S.charAt(n << 2);
                }
                return i;
            }
            n = 3 & r;

        }
        return i;
    }
}
