package com.ca.fire.until;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: yfzhaoya
 * Date: 14-6-6
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */
public class UserPwdEncodeUtils {
    public static String encode(String inStr) {
        if (inStr == null) {
            return null;
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        hexValue.insert(1, "AB");
        hexValue.insert(28, "C2");
        hexValue.insert(19, "H3");
        return Base64.encodeBase64String(hexValue.toString().getBytes());

//        try {
//            MessageDigest sha =  MessageDigest.getInstance("SHA");
//            sha.update(hexValue.toString().getBytes());
//            returnValue = new String(sha.digest(), "GB2312");
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//            ex.printStackTrace();
//        }
//        return returnValue;
    }

    public static String encodeByCookie(String str) {
        if (null == str || str.equals("")) {
            return "";
        }
        String temp = Base64.encodeBase64String(str.getBytes()).replace("=", "");
        StringBuffer sb = new StringBuffer();
        char[] charArray = temp.toCharArray();
        for (char c : charArray) {
            sb.append(c);
        }
        sb.insert(1, "a46");
        sb.insert(5, "95hj");
        sb.insert(2, "2346n");

        Pattern p = Pattern.compile("\\r\n");
        Matcher m = p.matcher(sb.toString());
        String strNoBlank = m.replaceAll("");

        return strNoBlank;

        //return (new BASE64Encoder()).encode(sb.toString().getBytes()).toString().replaceAll("\r\n", "");
    }

    public static String decodeByCookie(String str) {
        if (null == str || str.equals("")) {
            return "";
        }
        str += "=";
        str = str.replace("2346n", "").replace("a46", "").replace("95hj", "");
        char[] charArray = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < charArray.length; i++) {
            sb.append(charArray[i]);
        }
        byte[] decode = null;
        try {
            decode = Base64.decodeBase64(str);
        } catch (Exception ex) {
            throw new RuntimeException("decodeByCookie! return false" + ex);
        }
        String s = new String(decode);
        return s;
    }

    /**
     * 获取菜单随机数
     *
     * @return
     */
    public static String getMenuIdCode() {
        String menuCode = "wcsper_";
        Random rand = new Random();
        int randNum = rand.nextInt(999) + 10000000;
//        int n = 0 ;
//        while(n < 100000){
//            n = (int)(Math.random()*1000000);
//        }
        menuCode += randNum;
        return menuCode;
    }
}
