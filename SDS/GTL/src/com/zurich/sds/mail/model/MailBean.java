/*  1:   */ package com.zurich.sds.mail.model;
/*  2:   */ 
/*  3:   */ import java.util.Date;
/*  4:   */ import java.util.List;
/*  5:   */ 
/*  6:   */ public class MailBean
/*  7:   */ {
/*  8:   */   String mailId;
/*  9:   */   String mailMainAddress;
/* 10:   */   String mailSubject;
/* 11:   */   String mailBody;
/* 12:   */   List<String> mailOtherAddress;
/* 13:   */   List<String> mailFiles;
/* 14:   */   String mailStatus;
/* 15:   */   Date inputTime;
/* 16:   */   Date outputTime;
/* 17:   */   
/* 18:   */   public String getMailId()
/* 19:   */   {
/* 20:27 */     return this.mailId;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void setMailId(String mailId)
/* 24:   */   {
/* 25:30 */     this.mailId = mailId;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String getMailMainAddress()
/* 29:   */   {
/* 30:33 */     return this.mailMainAddress;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void setMailMainAddress(String mailMainAddress)
/* 34:   */   {
/* 35:36 */     this.mailMainAddress = mailMainAddress;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public String getMailSubject()
/* 39:   */   {
/* 40:39 */     return this.mailSubject;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void setMailSubject(String mailSubject)
/* 44:   */   {
/* 45:42 */     this.mailSubject = mailSubject;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public String getMailBody()
/* 49:   */   {
/* 50:45 */     return this.mailBody;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void setMailBody(String mailBody)
/* 54:   */   {
/* 55:48 */     this.mailBody = mailBody;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public List<String> getMailOtherAddress()
/* 59:   */   {
/* 60:51 */     return this.mailOtherAddress;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public void setMailOtherAddress(List<String> mailOtherAddress)
/* 64:   */   {
/* 65:54 */     this.mailOtherAddress = mailOtherAddress;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public List<String> getMailFiles()
/* 69:   */   {
/* 70:57 */     return this.mailFiles;
/* 71:   */   }
/* 72:   */   
/* 73:   */   public void setMailFiles(List<String> mailFiles)
/* 74:   */   {
/* 75:60 */     this.mailFiles = mailFiles;
/* 76:   */   }
/* 77:   */   
/* 78:   */   public String getMailStatus()
/* 79:   */   {
/* 80:63 */     return this.mailStatus;
/* 81:   */   }
/* 82:   */   
/* 83:   */   public void setMailStatus(String mailStatus)
/* 84:   */   {
/* 85:66 */     this.mailStatus = mailStatus;
/* 86:   */   }
/* 87:   */   
/* 88:   */   public Date getInputTime()
/* 89:   */   {
/* 90:69 */     return this.inputTime;
/* 91:   */   }
/* 92:   */   
/* 93:   */   public void setInputTime(Date inputTime)
/* 94:   */   {
/* 95:72 */     this.inputTime = inputTime;
/* 96:   */   }
/* 97:   */   
/* 98:   */   public Date getOutputTime()
/* 99:   */   {
/* :0:75 */     return this.outputTime;
/* :1:   */   }
/* :2:   */   
/* :3:   */   public void setOutputTime(Date outputTime)
/* :4:   */   {
/* :5:78 */     this.outputTime = outputTime;
/* :6:   */   }
/* :7:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.mail.model.MailBean
 * JD-Core Version:    0.7.0.1
 */