/*  1:   */ package com.zurich.sds.ipa.action;
/*  2:   */ 
/*  3:   */ import com.zurich.sds.ipa.service.IpaDefAutoJobService;
/*  4:   */ import com.zurich.sds.tool.PropertiesTool;
/*  5:   */ import java.util.Properties;
/*  6:   */ import org.apache.log4j.PropertyConfigurator;
/*  7:   */ 
/*  8:   */ public class IpaDefAutoJobAction
/*  9:   */ {
/* 10:   */   public IpaDefAutoJobAction()
/* 11:   */   {
/* 12:16 */     Properties pro = PropertiesTool.getProperties("Log4j.properties");
/* 13:17 */     PropertyConfigurator.configure(pro);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public static void main(String[] args)
/* 17:   */     throws Exception
/* 18:   */   {
/* 19:21 */     IpaDefAutoJobService service = new IpaDefAutoJobService();
/* 20:22 */     service.doIpaDefAutoJob();
/* 21:   */   }
/* 22:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.action.IpaDefAutoJobAction
 * JD-Core Version:    0.7.0.1
 */