/*  1:   */ package com.zurich.sds.synchronous.dao;
/*  2:   */ 
/*  3:   */ import com.zurich.sds.tool.PropertiesTool;
/*  4:   */ import java.util.Properties;
/*  5:   */ import org.apache.log4j.Logger;
/*  6:   */ import org.apache.log4j.PropertyConfigurator;
/*  7:   */ 
/*  8:   */ public class Test
/*  9:   */ {
/* 10:11 */   Logger logger = Logger.getLogger(Test.class);
/* 11:   */   
/* 12:   */   public Test()
/* 13:   */   {
/* 14:   */     try
/* 15:   */     {
/* 16:15 */       Properties pro = PropertiesTool.getProperties("Log4j.properties");
/* 17:16 */       PropertyConfigurator.configure(pro);
/* 18:   */     }
/* 19:   */     catch (Exception localException) {}
/* 20:   */   }
/* 21:   */   
/* 22:   */   public static void main(String[] args)
/* 23:   */     throws Exception
/* 24:   */   {}
/* 25:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.synchronous.dao.Test
 * JD-Core Version:    0.7.0.1
 */