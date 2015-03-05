/*  1:   */ package com.zurich.sds.synchronous.action;
/*  2:   */ 
/*  3:   */ import com.zurich.sds.mail.model.MailBean;
/*  4:   */ import com.zurich.sds.mail.service.MailService;
/*  5:   */ import com.zurich.sds.synchronous.module.SynchronousChannel;
/*  6:   */ import com.zurich.sds.synchronous.service.SynchronousService;
/*  7:   */ import com.zurich.sds.tool.PropertiesTool;
/*  8:   */ import java.text.SimpleDateFormat;
/*  9:   */ import java.util.Calendar;
/* 10:   */ import java.util.List;
/* 11:   */ import java.util.Properties;
/* 12:   */ import org.apache.log4j.Logger;
/* 13:   */ import org.apache.log4j.PropertyConfigurator;
/* 14:   */ 
/* 15:   */ public class CopyOfSynchronousAction
/* 16:   */ {
/* 17:18 */   Logger logger = Logger.getLogger(CopyOfSynchronousAction.class);
/* 18:19 */   private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/* 19:20 */   private final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
/* 20:   */   
/* 21:   */   public CopyOfSynchronousAction()
/* 22:   */   {
/* 23:   */     try
/* 24:   */     {
/* 25:24 */       Properties pro = PropertiesTool.getProperties("Log4j.properties");
/* 26:25 */       PropertyConfigurator.configure(pro);
/* 27:   */     }
/* 28:   */     catch (Exception e)
/* 29:   */     {
/* 30:27 */       e.printStackTrace();
/* 31:   */     }
/* 32:   */   }
/* 33:   */   
/* 34:   */   public static void main(String[] args)
/* 35:   */     throws Exception
/* 36:   */   {
/* 37:32 */     CopyOfSynchronousAction sync = new CopyOfSynchronousAction();
/* 38:33 */     SynchronousService service = new SynchronousService();
/* 39:   */     try
/* 40:   */     {
/* 41:35 */       long start = Calendar.getInstance().getTimeInMillis();
/* 42:   */       
/* 43:37 */       List<SynchronousChannel> channelList = service.selectSyncableChannel();
/* 44:   */       
/* 45:   */ 
/* 46:   */ 
/* 47:   */ 
/* 48:   */ 
/* 49:   */ 
/* 50:   */ 
/* 51:   */ 
/* 52:   */ 
/* 53:   */ 
/* 54:   */ 
/* 55:   */ 
/* 56:   */ 
/* 57:   */ 
/* 58:   */ 
/* 59:53 */       service.synchronousChannel(channelList);
/* 60:   */       
/* 61:55 */       long end = Calendar.getInstance().getTimeInMillis();
/* 62:56 */       long diff = end - start;
/* 63:   */       
/* 64:58 */       long diffSec = diff / 1000L;
/* 65:   */       
/* 66:60 */       String date = sync.sdf.format(Calendar.getInstance().getTime());
/* 67:61 */       sync.logger.info(date + "同步完成，共花時：" + diffSec + " seconds");
/* 68:   */     }
/* 69:   */     catch (Exception e)
/* 70:   */     {
/* 71:64 */       sync.sendMail();
/* 72:65 */       e.printStackTrace();
/* 73:66 */       sync.logger.error("Exception：", e);
/* 74:   */     }
/* 75:   */   }
/* 76:   */   
/* 77:   */   private void sendMail()
/* 78:   */   {
/* 79:   */     try
/* 80:   */     {
/* 81:72 */       String date = this.sdf2.format(Calendar.getInstance().getTime());
/* 82:73 */       Properties properties = PropertiesTool.getProperties("MailSetting.properties");
/* 83:   */       
/* 84:75 */       MailService mailService = new MailService();
/* 85:   */       
/* 86:77 */       MailBean mailBean = new MailBean();
/* 87:78 */       mailBean.setMailMainAddress(properties.getProperty("synchronousITMailAddress"));
/* 88:79 */       mailBean.setMailSubject(date + "_同步作業失敗");
/* 89:   */       
/* 90:   */ 
/* 91:82 */       mailBean.setMailId(mailService.genMailId());
/* 92:   */       
/* 93:84 */       mailBean.setMailBody("同步作業失敗");
/* 94:   */       
/* 95:86 */       mailService.inputMail(mailBean);
/* 96:   */       
/* 97:88 */       mailService.mailServiceClose();
/* 98:   */     }
/* 99:   */     catch (Exception e)
/* :0:   */     {
/* :1:90 */       e.printStackTrace();
/* :2:91 */       this.logger.error("Exception：", e);
/* :3:   */     }
/* :4:   */   }
/* :5:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.synchronous.action.CopyOfSynchronousAction
 * JD-Core Version:    0.7.0.1
 */