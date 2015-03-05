/*  1:   */ package com.zurich.sds.tool;
/*  2:   */ 
/*  3:   */ import java.security.SecureRandom;
/*  4:   */ import java.util.Calendar;
/*  5:   */ import javax.crypto.Cipher;
/*  6:   */ import javax.crypto.SecretKey;
/*  7:   */ import javax.crypto.spec.SecretKeySpec;
/*  8:   */ 
/*  9:   */ public class TripleDES
/* 10:   */ {
/* 11:   */   private static final String Algorithm = "DESede";
/* 12:   */   
/* 13:   */   public String encryptMode(byte[] keyByte, String src)
/* 14:   */     throws Exception
/* 15:   */   {
/* 16:15 */     SecretKey deskey = new SecretKeySpec(keyByte, "DESede");
/* 17:   */     
/* 18:17 */     Cipher cipher = Cipher.getInstance("DESede");
/* 19:18 */     cipher.init(1, deskey);
/* 20:19 */     byte[] resultByte = cipher.doFinal(src.getBytes());
/* 21:   */     
/* 22:21 */     return new String(resultByte);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public String decryptMode(byte[] keyByte, String src)
/* 26:   */     throws Exception
/* 27:   */   {
/* 28:26 */     SecretKey deskey = new SecretKeySpec(keyByte, "DESede");
/* 29:   */     
/* 30:28 */     Cipher cipher = Cipher.getInstance("DESede");
/* 31:29 */     cipher.init(2, deskey);
/* 32:   */     
/* 33:31 */     String result = new String(cipher.doFinal(src.getBytes()));
/* 34:   */     
/* 35:33 */     return result;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public byte[] createKey()
/* 39:   */   {
/* 40:36 */     SecureRandom random = new SecureRandom();
/* 41:37 */     random.setSeed(Calendar.getInstance().getTimeInMillis());
/* 42:38 */     return SecureRandom.getSeed(24);
/* 43:   */   }
/* 44:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.tool.TripleDES
 * JD-Core Version:    0.7.0.1
 */