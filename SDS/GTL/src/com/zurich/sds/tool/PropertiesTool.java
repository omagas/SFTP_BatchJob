/*  1:   */ package com.zurich.sds.tool;
/*  2:   */ 
/*  3:   */ import java.io.FileInputStream;
/*  4:   */ import java.io.IOException;
/*  5:   */ import java.util.Properties;
/*  6:   */ import org.apache.log4j.Logger;
/*  7:   */ 
/*  8:   */ public class PropertiesTool
/*  9:   */ {
/* 10:10 */   private static Logger logger = Logger.getLogger(PropertiesTool.class);
/* 11:   */   
/* 12:   */   public static Properties getProperties(String filename)
/* 13:   */   {
/* 14:26 */     Properties properties = new Properties();
/* 15:   */     try
/* 16:   */     {
/* 17:29 */       properties.load(new FileInputStream(System.getProperty("user.dir") + "/properties/" + filename));
/* 18:   */     }
/* 19:   */     catch (IOException ex)
/* 20:   */     {
/* 21:31 */       logger.error("Can't load properties file: " + filename, ex);
/* 22:   */     }
/* 23:33 */     return properties;
/* 24:   */   }
/* 25:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.tool.PropertiesTool
 * JD-Core Version:    0.7.0.1
 */