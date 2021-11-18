package com.dewu.api.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author fanjie
 * @date 2021/11/18 16:40
 */
public class EncryptUtil {
    private static final String[] DICT_TABLE = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static Object decrypt(JSONObject params, String httpMethod, Object n) {
        String a = createUUID(48,16);
        String i = "";
        String o = "";
        String s = "0";
        JSONObject c = params;

        return null;
    }

    private static String createUUID(int t, int e) {
        String[] res;
        if (t > 0) {
            res = new String[t];
            initString(res);
            for (int i = 0; i < t; i++) {
                res[i] = DICT_TABLE[(int) Math.floor(Math.random() * e)];
            }
        } else {
            res = new String[36];
            initString(res);
            res[8] = res[13] = res[18] = res[23] = "-";
            res[14] = "4";
            for (int i = 0; i < 36; i++) {
                if (res[i] != null) {
                    int r = (int) Math.floor(Math.random() * 16);
                    res[i] = DICT_TABLE[i == 19 ? 3 & r | 8 : r];
                }
            }
        }
        return String.join("", res);
    }
    private static void initString(String [] str){
        for (int i = 0; i < str.length; i++) {
            str[i] = "";
        }
    }

    public static void main(String[] args) {
        System.out.println(createUUID(0, 0));
        System.out.println(createUUID(48, 16));
    }

}
