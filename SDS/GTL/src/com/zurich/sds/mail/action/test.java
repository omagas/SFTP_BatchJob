/*  1:   */ package com.zurich.sds.mail.action;
/*  2:   */ 
/*  3:   */ import com.zurich.sds.mail.model.MailBean;
/*  4:   */ import com.zurich.sds.mail.service.MailService;
/*  5:   */ import com.zurich.sds.tool.PropertiesTool;
/*  6:   */ import java.util.ArrayList;
/*  7:   */ import java.util.Arrays;
/*  8:   */ import java.util.List;
/*  9:   */ import java.util.Properties;
/* 10:   */ 
/* 11:   */ public class test
/* 12:   */ {
/* 13:   */   public static void main(String[] args)
/* 14:   */     throws Exception
/* 15:   */   {
/* 16:14 */     MailService mailService = new MailService();
/* 17:   */     
/* 18:16 */     Properties properties = PropertiesTool.getProperties("MailSetting.properties");
/* 19:   */     
/* 20:18 */     MailBean mailBean = new MailBean();
/* 21:19 */     mailBean.setMailMainAddress(properties.getProperty("labrMainAddress"));
/* 22:20 */     mailBean.setMailSubject("錠嵂回饋檔FTP傳送失敗");
/* 23:   */     
/* 24:   */ 
/* 25:23 */     mailBean.setMailId(mailService.genMailId());
/* 26:   */     
/* 27:25 */     mailBean.setMailBody("測試中");
/* 28:   */     
/* 29:   */ 
/* 30:28 */     String labrCarbonCopyAddress = properties.getProperty("labrCarbonCopyAddress");
/* 31:29 */     List<String> mailOtherAddress = new ArrayList(Arrays.asList(labrCarbonCopyAddress.split(",")));
/* 32:30 */     mailBean.setMailOtherAddress(mailOtherAddress);
/* 33:   */     
/* 34:32 */     mailService.inputMail(mailBean);
/* 35:   */     
/* 36:34 */     mailService.mailServiceClose();
/* 37:   */   }
/* 38:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.mail.action.test
 * JD-Core Version:    0.7.0.1
 */