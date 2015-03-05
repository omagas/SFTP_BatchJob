/*  1:   */ package com.zurich.sds.mail.action;
/*  2:   */ 
/*  3:   */ import com.zurich.sds.mail.model.MailBean;
/*  4:   */ import com.zurich.sds.mail.model.MailExtraBean;
/*  5:   */ import com.zurich.sds.mail.model.NotesMail;
/*  6:   */ import com.zurich.sds.mail.service.MailService;
/*  7:   */ import com.zurich.sds.tool.PropertiesTool;
/*  8:   */ import java.util.ArrayList;
/*  9:   */ import java.util.List;
/* 10:   */ import java.util.Properties;
/* 11:   */ import java.util.Vector;
/* 12:   */ import org.apache.commons.lang.StringUtils;
/* 13:   */ import org.apache.log4j.Logger;
/* 14:   */ import org.apache.log4j.PropertyConfigurator;
/* 15:   */ 
/* 16:   */ public class MailAction
/* 17:   */ {
/* 18:20 */   static Logger logger = Logger.getLogger(MailAction.class);
/* 19:   */   
/* 20:   */   public static void main(String[] args)
/* 21:   */   {
/* 22:   */     try
/* 23:   */     {
/* 24:24 */       Properties pro = PropertiesTool.getProperties("Log4j.properties");
/* 25:25 */       PropertyConfigurator.configure(pro);
/* 26:   */       
/* 27:27 */       MailService mailService = new MailService();
/* 28:28 */       List<MailBean> mailBeanList = mailService.getMailNotSend();
/* 29:30 */       for (MailBean mailBean : mailBeanList)
/* 30:   */       {
/* 31:31 */         logger.info("寄送mail準備開始:");
/* 32:32 */         NotesMail NM = new NotesMail();
/* 33:33 */         Vector<String> userV = new Vector();
/* 34:34 */         Vector<String> attachList = new Vector();
/* 35:   */         
/* 36:   */ 
/* 37:37 */         userV.add(mailBean.getMailMainAddress());
/* 38:38 */         logger.info("主要人員: " + mailBean.getMailMainAddress());
/* 39:39 */         List<MailExtraBean> mailExtraBeans = mailService.getMailExtra(mailBean.getMailId(), "A");
/* 40:40 */         for (MailExtraBean mailExtraBean : mailExtraBeans)
/* 41:   */         {
/* 42:41 */           userV.add(mailExtraBean.getExtraContent());
/* 43:42 */           logger.info("副件人員: " + mailExtraBean.getContentType());
/* 44:   */         }
/* 45:44 */         if ((userV == null) || (userV.size() == 0))
/* 46:   */         {
/* 47:45 */           mailService.updateMailStatus(mailBean.getMailId(), "E");
/* 48:   */         }
/* 49:   */         else
/* 50:   */         {
/* 51:48 */           NM.setSendTo(userV);
/* 52:   */           
/* 53:   */ 
/* 54:51 */           mailExtraBeans = mailService.getMailExtra(mailBean.getMailId(), "F");
/* 55:52 */           List<MailExtraBean> deleteFileList = new ArrayList();
/* 56:53 */           for (MailExtraBean mailExtraBean : mailExtraBeans)
/* 57:   */           {
/* 58:54 */             attachList.add(mailExtraBean.getExtraContent());
/* 59:55 */             logger.info("附件: " + mailExtraBean.getExtraContent());
/* 60:56 */             if (("F".equals(mailExtraBean.getContentType())) && (mailExtraBean.getExtraContent().contains("LABR-"))) {
/* 61:57 */               deleteFileList.add(mailExtraBean);
/* 62:   */             }
/* 63:   */           }
/* 64:60 */           NM.setAttachList(attachList);
/* 65:   */           
/* 66:   */ 
/* 67:63 */           NM.setSubject(mailBean.getMailSubject());
/* 68:   */           
/* 69:65 */           NM.setBody(mailBean.getMailBody());
/* 70:   */           
/* 71:67 */           String sendNotes = NM.sendNotes();
/* 72:68 */           logger.info(mailBean.getMailSubject() + ": 執行寄件");
/* 73:71 */           if (StringUtils.isBlank(sendNotes))
/* 74:   */           {
/* 75:72 */             logger.info("寄件成功!");
/* 76:   */             
/* 77:   */ 
/* 78:75 */             mailService.updateMailStatus(mailBean.getMailId(), "Y");
/* 79:   */             
/* 80:   */ 
/* 81:78 */             mailService.deleteFile(deleteFileList);
/* 82:   */           }
/* 83:   */           else
/* 84:   */           {
/* 85:80 */             logger.info("寄件失敗: " + sendNotes);
/* 86:   */           }
/* 87:   */         }
/* 88:   */       }
/* 89:84 */       mailService.mailServiceClose();
/* 90:   */     }
/* 91:   */     catch (Exception e)
/* 92:   */     {
/* 93:86 */       e.printStackTrace();
/* 94:87 */       logger.error("Exception:", e);
/* 95:   */     }
/* 96:   */   }
/* 97:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.mail.action.MailAction
 * JD-Core Version:    0.7.0.1
 */