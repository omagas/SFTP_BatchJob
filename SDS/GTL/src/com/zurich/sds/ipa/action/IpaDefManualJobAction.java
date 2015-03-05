/* 1:  */ package com.zurich.sds.ipa.action;
/* 2:  */ 
/* 3:  */ import com.zurich.sds.ipa.service.IpaDefManualJobService;
/* 4:  */ 
/* 5:  */ public class IpaDefManualJobAction
/* 6:  */ {
/* 7:  */   public static void main(String[] args)
/* 8:  */     throws Exception
/* 9:  */   {
/* ::7 */     IpaDefManualJobService ipaDefManualJobService = new IpaDefManualJobService();
/* ;:8 */     ipaDefManualJobService.doIpaDefManualJob();
/* <:  */   }
/* =:  */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.action.IpaDefManualJobAction
 * JD-Core Version:    0.7.0.1
 */