/*   1:    */ package com.zurich.sds.ipa.action;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.ftp.service.FtpService;
/*   4:    */ import com.zurich.sds.ipa.module.IpaLabrMain;
/*   5:    */ import com.zurich.sds.ipa.service.IpaLabrService;
/*   6:    */ import com.zurich.sds.mail.model.MailBean;
/*   7:    */ import com.zurich.sds.mail.service.MailService;
/*   8:    */ import com.zurich.sds.tool.PropertiesTool;
/*   9:    */ import java.text.SimpleDateFormat;
/*  10:    */ import java.util.ArrayList;
/*  11:    */ import java.util.Arrays;
/*  12:    */ import java.util.Date;
/*  13:    */ import java.util.List;
/*  14:    */ import java.util.Properties;
/*  15:    */ import org.apache.commons.lang.StringUtils;
/*  16:    */ import org.apache.log4j.Logger;
/*  17:    */ 
/*  18:    */ public class IpaLabrAction
/*  19:    */ {
/*  20: 21 */   Logger logger = Logger.getLogger(IpaLabrAction.class);
/*  21:    */   private static final String ftpFilePath = "";
/*  22:    */   private static final String localFilePath = "";
/*  23:    */   
/*  24:    */   public static void main(String[] args)
/*  25:    */   {
/*  26: 28 */     FtpService ftpService = new FtpService();
/*  27: 29 */     IpaLabrService ipaService = new IpaLabrService();
/*  28:    */     try
/*  29:    */     {
/*  30: 32 */       List<String> fileNameList = ftpService.getFileNameList("");
/*  31: 34 */       for (String fileName : fileNameList) {
/*  32: 36 */         if (fileName.startsWith("192B01"))
/*  33:    */         {
/*  34: 39 */           String errorMessage = "";
/*  35:    */           
/*  36: 41 */           List<IpaLabrMain> labrIpaMainList = new ArrayList();
/*  37: 42 */           List<IpaLabrMain> successList = new ArrayList();
/*  38: 43 */           List<IpaLabrMain> errorList = new ArrayList();
/*  39:    */           try
/*  40:    */           {
/*  41: 47 */             ftpService.downFile("", fileName, "");
/*  42:    */           }
/*  43:    */           catch (Exception e)
/*  44:    */           {
/*  45: 49 */             errorMessage = errorMessage + " 下載失敗：" + e.getMessage() + " ";
/*  46: 50 */             e.printStackTrace();
/*  47:    */           }
/*  48: 54 */           if (StringUtils.isBlank(errorMessage)) {
/*  49: 55 */             labrIpaMainList = ipaService.getLabrIpaMainList("", fileName);
/*  50:    */           }
/*  51: 59 */           if (StringUtils.isBlank(errorMessage))
/*  52:    */           {
/*  53: 60 */             for (IpaLabrMain labrIpaMain : labrIpaMainList) {
/*  54: 61 */               if (StringUtils.isBlank(labrIpaMain.getErrorMessage())) {
/*  55: 62 */                 successList.add(labrIpaMain);
/*  56:    */               } else {
/*  57: 64 */                 errorList.add(labrIpaMain);
/*  58:    */               }
/*  59:    */             }
/*  60:    */             try
/*  61:    */             {
/*  62: 69 */               ipaService.saveLabrIpaMainList(successList);
/*  63:    */             }
/*  64:    */             catch (Exception e)
/*  65:    */             {
/*  66: 71 */               errorMessage = errorMessage + " " + e.getMessage() + " ";
/*  67:    */             }
/*  68:    */           }
/*  69: 76 */           if (StringUtils.isBlank(errorMessage)) {
/*  70:    */             try
/*  71:    */             {
/*  72: 78 */               ftpService.deleteFile("", fileName);
/*  73:    */             }
/*  74:    */             catch (Exception e)
/*  75:    */             {
/*  76: 80 */               errorMessage = errorMessage + " " + e.getMessage() + " ";
/*  77:    */             }
/*  78:    */           }
/*  79: 85 */           StringBuffer mailBody = new StringBuffer();
/*  80: 86 */           mailBody.append("時間：" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "\n")
/*  81: 87 */             .append("檔案名稱：" + fileName + "\n")
/*  82: 88 */             .append("檔案資料總筆數：" + labrIpaMainList.size() + "\n")
/*  83: 89 */             .append("匯入失敗筆數：" + errorList.size() + "\n")
/*  84: 90 */             .append("匯入成功筆數：" + successList.size() + "\n");
/*  85: 92 */           if ((errorList.size() != 0) || (!StringUtils.isBlank(errorMessage))) {
/*  86: 93 */             mailBody.append("錯誤訊息：\n");
/*  87:    */           }
/*  88: 96 */           if (!StringUtils.isBlank(errorMessage)) {
/*  89: 97 */             mailBody.append(errorMessage + "\n");
/*  90:    */           }
/*  91: 99 */           for (IpaLabrMain labrIpaMain : errorList) {
/*  92:100 */             mailBody.append(labrIpaMain.getErrorMessage() + "\n");
/*  93:    */           }
/*  94:103 */           sendMail(mailBody.toString());
/*  95:    */         }
/*  96:    */       }
/*  97:    */     }
/*  98:    */     catch (Exception e)
/*  99:    */     {
/* 100:106 */       e.printStackTrace();
/* 101:107 */       sendMail("作業失敗，原因：" + e.getMessage());
/* 102:    */     }
/* 103:    */   }
/* 104:    */   
/* 105:    */   private static void sendMail(String mailBody)
/* 106:    */   {
/* 107:113 */     MailService mailService = new MailService();
/* 108:    */     try
/* 109:    */     {
/* 110:115 */       Properties properties = PropertiesTool.getProperties("MailSetting.properties");
/* 111:    */       
/* 112:117 */       MailBean mailBean = new MailBean();
/* 113:118 */       mailBean.setMailMainAddress(properties.getProperty("ipaAddress"));
/* 114:119 */       mailBean.setMailSubject("錠嵂IPA要保資料檔批次匯入");
/* 115:    */       
/* 116:    */ 
/* 117:122 */       mailBean.setMailId(mailService.genMailId());
/* 118:    */       
/* 119:124 */       mailBean.setMailBody(mailBody);
/* 120:    */       
/* 121:    */ 
/* 122:127 */       String labrCarbonCopyAddress = properties.getProperty("ipaCarbonCopyAddress");
/* 123:128 */       List<String> mailOtherAddress = new ArrayList(Arrays.asList(labrCarbonCopyAddress.split(",")));
/* 124:129 */       mailBean.setMailOtherAddress(mailOtherAddress);
/* 125:    */       
/* 126:131 */       mailService.inputMail(mailBean);
/* 127:    */       
/* 128:133 */       mailService.mailServiceClose();
/* 129:    */     }
/* 130:    */     catch (Exception e)
/* 131:    */     {
/* 132:135 */       e.printStackTrace();
/* 133:    */     }
/* 134:    */   }
/* 135:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.action.IpaLabrAction
 * JD-Core Version:    0.7.0.1
 */