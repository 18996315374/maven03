package com.baidu;

import java.security.MessageDigest;

public class Getcezuma {
    public static void main(String[] args) {
//        爬取一千个文档
//        String key=xiaolajiaoMD5("a17410cb208a428a49582c0531eaa59b", "caiji");
////改价
//        String key=xiaolajiaoMD5("c221f9a044a1855e199980e11ac2e623", "ChangePrice");
////        付费转vip
//        String key=xiaolajiaoMD5("af6d6d5e03b899ec01f729b0d3f90c62", "PriceToVIP");
//普通上传
//        String key=xiaolajiaoMD5("96202b601b0d908e1db769c68c037b46", "xiaolajiao");
//        旗舰店上传
//        String key=xiaolajiaoMD5("7115369ea72cedb829db668baab748e5", "qjb");
//        活动文档上传
//        String key=xiaolajiaoMD5("47b94508487571f4d2a4f510b07143ac", "hdb");
//        百度vip转付费
        String key=xiaolajiaoMD5("3891a977a99a1660b580b7c9f6815f80", "VIPToPrice");
        //道客巴巴上传
//          String key=xiaolajiaoMD5("62b4c01fcf313dd8b9d329c6f95b4ddc", "doc88");
        //豆丁上传
//          String key=xiaolajiaoMD5("a2ae9daf97f06a592b93e2c2a26af6b0", "doudingwang");


        System.out.println(key);
    }
    public static  String xiaolajiaoMD5(String s, String softName) {
        /*     */     try {
            /* 398 */       String re = MD5(String.valueOf(MD5(s)) + MD5(s) + MD5(softName));
            /* 399 */       return re.toLowerCase();
            /* 400 */     } catch (Exception e) {
            /* 401 */       e.printStackTrace();
            /* 402 */       return null;
            /*     */     }
        /*     */   }
    public static  String MD5(String s) {
        /*  37 */     char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        /*     */
        /*     */     try {
            /*  40 */       byte[] btInput = (String.valueOf(s) + "szxblog").getBytes();
            /*     */
            /*  42 */       MessageDigest mdInst = MessageDigest.getInstance("MD5");
            /*     */
            /*  44 */       mdInst.update(btInput);
            /*     */
            /*  46 */       byte[] md = mdInst.digest();
            /*     */
            /*  48 */       int j = md.length;
            /*  49 */       char[] str = new char[j * 2];
            /*  50 */       int k = 0;
            /*  51 */       for (int i = 0; i < j; i++) {
                /*  52 */         byte byte0 = md[i];
                /*  53 */         str[k++] = hexDigits[byte0 >>> 4 & 0xF];
                /*  54 */         str[k++] = hexDigits[byte0 & 0xF];
                /*     */       }
            /*  56 */       return new String(str);
            /*  57 */     } catch (Exception e) {
            /*  58 */       e.printStackTrace();
            /*  59 */       return null;
            /*     */     }
        /*     */   }
    /*     */
}
