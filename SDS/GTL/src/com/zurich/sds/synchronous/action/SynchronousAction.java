/*   1:    */ package com.zurich.sds.synchronous.action;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.mail.model.MailBean;
/*   4:    */ import com.zurich.sds.mail.service.MailService;
/*   5:    */ import com.zurich.sds.synchronous.module.SynchronousChannel;
/*   6:    */ import com.zurich.sds.synchronous.service.SynchronousService;
/*   7:    */ import com.zurich.sds.tool.PropertiesTool;
/*   8:    */ import java.text.SimpleDateFormat;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ import java.util.Arrays;
/*  11:    */ import java.util.Calendar;
/*  12:    */ import java.util.List;
/*  13:    */ import java.util.Properties;
/*  14:    */ import org.apache.log4j.Logger;
/*  15:    */ import org.apache.log4j.PropertyConfigurator;
/*  16:    */ 
/*  17:    */ public class SynchronousAction
/*  18:    */ {
/*  19: 20 */   Logger logger = Logger.getLogger(SynchronousAction.class);
/*  20: 21 */   private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/*  21: 22 */   private final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
/*  22:    */   
/*  23:    */   public SynchronousAction()
/*  24:    */   {
/*  25:    */     try
/*  26:    */     {
/*  27: 26 */       Properties pro = PropertiesTool.getProperties("Log4j.properties");
/*  28: 27 */       PropertyConfigurator.configure(pro);
/*  29:    */     }
/*  30:    */     catch (Exception e)
/*  31:    */     {
/*  32: 29 */       e.printStackTrace();
/*  33:    */     }
/*  34:    */   }
/*  35:    */   
/*  36:    */   public static void main(String[] args)
/*  37:    */     throws Exception
/*  38:    */   {
/*  39: 34 */     SynchronousAction sync = new SynchronousAction();
/*  40: 35 */     SynchronousService service = new SynchronousService();
/*  41:    */     try
/*  42:    */     {
/*  43: 37 */       long start = Calendar.getInstance().getTimeInMillis();
/*  44:    */       
/*  45: 39 */       List<SynchronousChannel> channelList = service.selectSyncableChannel();
/*  46:    */       
/*  47:    */ 
/*  48: 42 */       long time1 = Calendar.getInstance().getTimeInMillis();
/*  49:    */       
/*  50: 44 */       service.revokedAccount(channelList);
/*  51:    */       
/*  52: 46 */       long time2 = Calendar.getInstance().getTimeInMillis();
/*  53:    */       
/*  54: 48 */       long diff = time2 - time1;
/*  55:    */       
/*  56: 50 */       long diffSec = diff / 1000L;
/*  57: 51 */       sync.logger.info("停用通路花時：" + diffSec + " seconds");
/*  58:    */       
/*  59:    */ 
/*  60:    */ 
/*  61: 55 */       service.synchronousChannel(channelList);
/*  62:    */       
/*  63: 57 */       service.synchronousOtherAgntCode();
/*  64:    */       
/*  65: 59 */       long end = Calendar.getInstance().getTimeInMillis();
/*  66: 60 */       long diff = end - start;
/*  67:    */       
/*  68: 62 */       long diffSec = diff / 1000L;
/*  69:    */       
/*  70: 64 */       String date = sync.sdf.format(Calendar.getInstance().getTime());
/*  71: 65 */       sync.logger.info(date + "同步完成，共花時：" + diffSec + " seconds");
/*  72:    */     }
/*  73:    */     catch (Exception e)
/*  74:    */     {
/*  75: 68 */       sync.sendMail();
/*  76: 69 */       e.printStackTrace();
/*  77: 70 */       sync.logger.error("Exception：", e);
/*  78:    */     }
/*  79:    */   }
/*  80:    */   
/*  81:    */   private void sendMail()
/*  82:    */   {
/*  83:    */     try
/*  84:    */     {
/*  85: 76 */       String date = this.sdf2.format(Calendar.getInstance().getTime());
/*  86: 77 */       Properties properties = PropertiesTool.getProperties("MailSetting.properties");
/*  87:    */       
/*  88: 79 */       MailService mailService = new MailService();
/*  89:    */       
/*  90: 81 */       MailBean mailBean = new MailBean();
/*  91:    */       
/*  92: 83 */       mailBean.setMailMainAddress(properties.getProperty("synchronousITMailAddress"));
/*  93:    */       
/*  94: 85 */       String labrCarbonCopyAddress = properties.getProperty("synchronousITCarbonCopyAddress");
/*  95: 86 */       List<String> mailOtherAddress = new ArrayList(Arrays.asList(labrCarbonCopyAddress.split(",")));
/*  96: 87 */       mailBean.setMailOtherAddress(mailOtherAddress);
/*  97:    */       
/*  98: 89 */       mailBean.setMailSubject(date + "_同步作業失敗");
/*  99:    */       
/* 100:    */ 
/* 101: 92 */       mailBean.setMailId(mailService.genMailId());
/* 102:    */       
/* 103: 94 */       mailBean.setMailBody("同步作業失敗");
/* 104:    */       
/* 105: 96 */       mailService.inputMail(mailBean);
/* 106:    */       
/* 107: 98 */       mailService.mailServiceClose();
/* 108:    */     }
/* 109:    */     catch (Exception e)
/* 110:    */     {
/* 111:100 */       e.printStackTrace();
/* 112:101 */       this.logger.error("Exception：", e);
/* 113:    */     }
/* 114:    */   }
/* 115:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.synchronous.action.SynchronousAction
 * JD-Core Version:    0.7.0.1
 */