/*   1:    */ package com.zurich.sds.lawbroker.action;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.lawbroker.module.InsuredRoster;
/*   4:    */ import com.zurich.sds.lawbroker.module.Policy;
/*   5:    */ import com.zurich.sds.lawbroker.service.LawbrokerService;
/*   6:    */ import com.zurich.sds.tool.PropertiesTool;
/*   7:    */ import java.io.FileNotFoundException;
/*   8:    */ import java.io.PrintStream;
/*   9:    */ import java.text.ParseException;
/*  10:    */ import java.text.SimpleDateFormat;
/*  11:    */ import java.util.ArrayList;
/*  12:    */ import java.util.Date;
/*  13:    */ import java.util.List;
/*  14:    */ import java.util.Properties;
/*  15:    */ import java.util.Scanner;
/*  16:    */ import org.apache.commons.lang.StringUtils;
/*  17:    */ import org.apache.log4j.Logger;
/*  18:    */ import org.apache.log4j.PropertyConfigurator;
/*  19:    */ 
/*  20:    */ public class LawbrokerActionV2
/*  21:    */   extends LawbrokerAction
/*  22:    */ {
/*  23: 22 */   private String manualPolicyFileName = this.properties.getProperty("manualPolicyFileName").replace("{0}", this.today);
/*  24: 23 */   private String manualRosterFileName = this.properties.getProperty("manualRosterFileName").replace("{0}", this.today);
/*  25: 24 */   private static Logger logger = Logger.getLogger(LawbrokerActionV2.class);
/*  26:    */   
/*  27:    */   public LawbrokerActionV2()
/*  28:    */   {
/*  29: 27 */     Properties pro = PropertiesTool.getProperties("Log4j.properties");
/*  30: 28 */     PropertyConfigurator.configure(pro);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public static void main(String[] args)
/*  34:    */     throws Exception
/*  35:    */   {
/*  36: 33 */     LawbrokerService lawbrokerService = new LawbrokerService();
/*  37: 34 */     LawbrokerActionV2 lawbroker = new LawbrokerActionV2();
/*  38:    */     
/*  39:    */ 
/*  40:    */ 
/*  41:    */ 
/*  42: 39 */     String parseFailedHint = "時間格式為" + sdf.toPattern();
/*  43: 40 */     Date logTimeBegin = getInputTime("請輸入開始時間: ", parseFailedHint);
/*  44: 41 */     Date logTimeEnd = getInputTime("請輸入結束時間: ", parseFailedHint);
/*  45:    */     try
/*  46:    */     {
/*  47: 44 */       List<Policy> totalPolicyList = new ArrayList();
/*  48: 45 */       List<InsuredRoster> totalRosterList = new ArrayList();
/*  49:    */       
/*  50: 47 */       List<Policy> policyList = lawbrokerService.getManualPolicyDataList(logTimeBegin, logTimeEnd);
/*  51: 48 */       totalPolicyList.addAll(policyList);
/*  52:    */       
/*  53: 50 */       List<Policy> cancelPolicyDataList = lawbrokerService.getCancelManualPolicyDataList(logTimeBegin, logTimeEnd);
/*  54: 51 */       totalPolicyList.addAll(cancelPolicyDataList);
/*  55:    */       
/*  56:    */ 
/*  57: 54 */       List<InsuredRoster> rosterList = lawbrokerService.getManualRosterDataList(logTimeBegin, logTimeEnd);
/*  58: 55 */       totalRosterList.addAll(rosterList);
/*  59:    */       
/*  60: 57 */       List<InsuredRoster> cancelRosterDataList = lawbrokerService.getCancelManualRosterDataList(logTimeBegin, logTimeEnd);
/*  61: 58 */       totalRosterList.addAll(cancelRosterDataList);
/*  62:    */       
/*  63:    */ 
/*  64:    */ 
/*  65: 62 */       lawbrokerService.genManualPolicyCsv(totalPolicyList, lawbroker.filePath, lawbroker.manualPolicyFileName);
/*  66:    */       
/*  67:    */ 
/*  68: 65 */       lawbrokerService.genManualRosterCsv(totalRosterList, lawbroker.filePath, lawbroker.manualRosterFileName);
/*  69:    */     }
/*  70:    */     catch (FileNotFoundException e)
/*  71:    */     {
/*  72: 71 */       e.printStackTrace();
/*  73: 72 */       logger.error("檔案建立失敗：", e);
/*  74:    */     }
/*  75:    */     catch (Throwable e)
/*  76:    */     {
/*  77: 82 */       e.printStackTrace();
/*  78: 83 */       logger.error("Exception：", e);
/*  79:    */     }
/*  80:    */   }
/*  81:    */   
/*  82:    */   private static Date getInputTime(String showMessage, String parseFailedHint)
/*  83:    */   {
/*  84: 89 */     Scanner scanner = new Scanner(System.in);
/*  85: 90 */     String inputValue = null;
/*  86: 91 */     Date returnValue = null;
/*  87: 93 */     while (StringUtils.isBlank(inputValue))
/*  88:    */     {
/*  89: 94 */       System.out.print(showMessage);
/*  90: 95 */       if (scanner.hasNextLine()) {
/*  91: 96 */         inputValue = scanner.nextLine();
/*  92:    */       }
/*  93:    */       try
/*  94:    */       {
/*  95: 99 */         returnValue = sdf.parse(inputValue);
/*  96:    */       }
/*  97:    */       catch (ParseException e)
/*  98:    */       {
/*  99:101 */         System.out.println(parseFailedHint);
/* 100:102 */         inputValue = null;
/* 101:    */       }
/* 102:    */     }
/* 103:109 */     return returnValue;
/* 104:    */   }
/* 105:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.lawbroker.action.LawbrokerActionV2
 * JD-Core Version:    0.7.0.1
 */