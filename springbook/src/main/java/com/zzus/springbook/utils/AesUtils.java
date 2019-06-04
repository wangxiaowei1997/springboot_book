package com.zzus.springbook.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author wangwei
 */
public class AesUtils {

    public static final String ECB = "AES/ECB/PKCS5Padding";
    public static final String CBC = "AES/CBC/PKCS5Padding";
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        byte[] bytes = { 1,1,0,0,0,0,1,0,1,1,1,1,0,1,1,1 };
        String bb = "1100001011110111";
        System.out.println(bb.getBytes().length);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        IvParameterSpec ivspec = new IvParameterSpec(bb.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivspec);
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new org.apache.commons.codec.binary.Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    public static void main(String[] arg) throws Exception{
        String t = Encrypt("{\"user_name\":\"002097@haojue.com\",\"date\":\"2018-03-08\"}","pD5V9iwjQyyKHIoV");
        String s = Encrypt("150046,"+ System.currentTimeMillis(),"a935t82cy3me45l0");
//        String s = Encrypt("210005,"+ "1543995013090","a935t82cy3me45l0");
//        s = "tuChFIK5cMSvu1gIHe+T4hJ0u440SZ/kFn454/bWhOA=";
        System.out.println(s);
//        byte[] iv = {1,1,0,0,0,0,1,0,1,1,1,1,0,1,1,1 };
        String k = Decrypt(s,"a935t82cy3me45l0",CBC,"1100001011110111".getBytes());
        System.out.println(k);
    }

    public static String Decrypt(String sSrc,String sKey,String entryMode,byte[] bytes) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(entryMode);
            //CBC需要增加一个初始矢量
            if(CBC.equals(entryMode)){
                System.out.println("yes");
                IvParameterSpec ivspec = new IvParameterSpec(bytes);
                cipher.init(Cipher.DECRYPT_MODE, skeySpec,ivspec);
            }else if(ECB.equals(entryMode)){
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            }
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

}
