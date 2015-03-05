/*   1:    */ package com.zurich.sds.ipa.service;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.ipa.dao.IpaDefManualJobDao;
/*   4:    */ import com.zurich.sds.ipa.module.T7568;
/*   5:    */ import com.zurich.sds.ipa.module.T9972;
/*   6:    */ import com.zurich.sds.ipa.module.ZJOBPF;
/*   7:    */ import com.zurich.sds.model.common.hibernate.entity.AppParameterTb;
/*   8:    */ import com.zurich.sds.model.common.hibernate.entity.HostJobClassDefTb;
/*   9:    */ import com.zurich.sds.model.ipa.hibernate.entity.IpaRiskDefTb;
/*  10:    */ import com.zurich.sds.tool.PropertiesTool;
/*  11:    */ import com.zurich.sds.utils.AS400Util;
/*  12:    */ import java.io.BufferedReader;
/*  13:    */ import java.io.File;
/*  14:    */ import java.io.FileInputStream;
/*  15:    */ import java.io.InputStreamReader;
/*  16:    */ import java.util.Date;
/*  17:    */ import java.util.HashMap;
/*  18:    */ import java.util.Map;
/*  19:    */ import java.util.Properties;
/*  20:    */ 
/*  21:    */ public class IpaDefManualJobService
/*  22:    */ {
/*  23: 24 */   private IpaDefManualJobDao ipaDefManualJobDao = new IpaDefManualJobDao();
/*  24: 25 */   private Date now = new Date();
/*  25: 27 */   private Properties properties = PropertiesTool.getProperties("IpaJobSetting.properties");
/*  26: 28 */   private String as400_filePath = this.properties.getProperty("AS400.dir");
/*  27: 29 */   private String zjobpf_fileName = this.properties.getProperty("zjobpf.fileName");
/*  28: 30 */   private String T7568_fileName = this.properties.getProperty("T7568.fileName");
/*  29: 31 */   private String T9972_fileName = this.properties.getProperty("T9972.fileName");
/*  30:    */   
/*  31:    */   public void doIpaDefManualJob()
/*  32:    */     throws Exception
/*  33:    */   {
/*  34: 34 */     this.ipaDefManualJobDao.setAutoCommit(Boolean.valueOf(false));
/*  35:    */     
/*  36: 36 */     doJobZJOBPF(this.as400_filePath, this.zjobpf_fileName);
/*  37:    */     
/*  38: 38 */     this.ipaDefManualJobDao.executeSQL(" DELETE FROM app_parameter_tb WHERE prdt_CD = 'IPA' AND parameter_group = 'ILLNESS_TYPE' ");
/*  39: 39 */     doJobT7568(this.as400_filePath, this.T7568_fileName);
/*  40:    */     
/*  41: 41 */     this.ipaDefManualJobDao.executeSQL(" DELETE FROM ipa_risk_ref_tb ");
/*  42: 42 */     doJobT9972(this.as400_filePath, this.T9972_fileName);
/*  43:    */   }
/*  44:    */   
/*  45:    */   private void doJobZJOBPF(String filePath, String fileName)
/*  46:    */     throws Exception
/*  47:    */   {
/*  48: 47 */     this.ipaDefManualJobDao.executeSQL("DELETE FROM host_job_class_def_tb ");
/*  49:    */     
/*  50: 49 */     File file = new File(filePath + fileName);
/*  51: 50 */     InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
/*  52: 51 */     BufferedReader reader = new BufferedReader(read);
/*  53:    */     String line;
/*  54: 54 */     while ((line = reader.readLine()) != null)
/*  55:    */     {
/*  56:    */       String line;
/*  57: 55 */       ZJOBPF zjobpf = (ZJOBPF)AS400Util.trans(ZJOBPF.class, line);
/*  58:    */       
/*  59: 57 */       HostJobClassDefTb hostJobClassDefTb = new HostJobClassDefTb();
/*  60: 58 */       hostJobClassDefTb.setClassType(zjobpf.getZJobType());
/*  61: 59 */       hostJobClassDefTb.setJobDesc(zjobpf.getZJobNam());
/*  62: 60 */       hostJobClassDefTb.setJobCd(zjobpf.getZJobCode());
/*  63: 61 */       hostJobClassDefTb.setJobLongdesc(zjobpf.getZJobLongdesc());
/*  64: 62 */       hostJobClassDefTb.setEffMk("Y");
/*  65: 63 */       hostJobClassDefTb.setCreateDt(this.now);
/*  66:    */       
/*  67: 65 */       this.ipaDefManualJobDao.insert(hostJobClassDefTb, "host_job_class_def_tb");
/*  68:    */     }
/*  69:    */   }
/*  70:    */   
/*  71:    */   private void doJobT7568(String filePath, String fileName)
/*  72:    */     throws Exception
/*  73:    */   {
/*  74: 70 */     File file = new File(filePath + fileName);
/*  75: 71 */     InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
/*  76: 72 */     BufferedReader reader = new BufferedReader(read);
/*  77:    */     
/*  78:    */ 
/*  79:    */ 
/*  80: 76 */     AppParameterTb appParameterTbModule = new AppParameterTb();
/*  81: 77 */     appParameterTbModule.setPrdtCd("IPA");
/*  82: 78 */     appParameterTbModule.setDeleteMK("N");
/*  83: 79 */     appParameterTbModule.setParameterGroup("ILLNESS_TYPE");
/*  84:    */     String line;
/*  85: 82 */     while ((line = reader.readLine()) != null)
/*  86:    */     {
/*  87:    */       String line;
/*  88: 83 */       T7568 t7568 = (T7568)AS400Util.trans(T7568.class, line);
/*  89: 85 */       if ("1".equals(t7568.getFlag()))
/*  90:    */       {
/*  91: 86 */         AppParameterTb appParameterTb = appParameterTbModule.clone();
/*  92:    */         
/*  93: 88 */         appParameterTb.setParameterCode(t7568.getItem());
/*  94: 89 */         appParameterTb.setParameterName(t7568.getGenPlne());
/*  95:    */         
/*  96: 91 */         this.ipaDefManualJobDao.insert(appParameterTb, "app_parameter_tb");
/*  97:    */       }
/*  98:    */     }
/*  99:    */   }
/* 100:    */   
/* 101:    */   private void doJobT9972(String filePath, String fileName)
/* 102:    */     throws Exception
/* 103:    */   {
/* 104:102 */     File file = new File(filePath + fileName);
/* 105:103 */     InputStreamReader read = new InputStreamReader(new FileInputStream(file), "BIG5");
/* 106:104 */     BufferedReader reader = new BufferedReader(read);
/* 107:    */     
/* 108:106 */     Map<String, IpaRiskDefTb> map = new HashMap();
/* 109:107 */     for (IpaRiskDefTb ipaRiskDefTb : this.ipaDefManualJobDao.listResult(IpaRiskDefTb.class, " SELECT * FROM ipa_risk_def_tb ")) {
/* 110:108 */       map.put(ipaRiskDefTb.getRiskId(), ipaRiskDefTb);
/* 111:    */     }
/* 112:114 */     Integer index = Integer.valueOf(1);
/* 113:115 */     Class<?> as400Class = T9972.class;
/* 114:    */     String line;
/* 115:116 */     while ((line = reader.readLine()) != null)
/* 116:    */     {
/* 117:    */       String line;
/* 118:117 */       T9972 t9972 = (T9972)AS400Util.trans(as400Class, line);
/* 119:    */       
/* 120:119 */       String riskId = t9972.getItem();
/* 121:120 */       IpaRiskDefTb ipaRiskDefTb = new IpaRiskDefTb();
/* 122:121 */       ipaRiskDefTb.setRiskId(riskId);
/* 123:122 */       ipaRiskDefTb.setRiskDesc(t9972.getInsuranceName());
/* 124:123 */       ipaRiskDefTb.setRiskYinsDesc(t9972.getInsurance15Name());
/* 125:124 */       ipaRiskDefTb.setOrdNo(index);
/* 126:125 */       ipaRiskDefTb.setPrmAmtBas(Integer.valueOf(map.get(riskId) == null ? 1 : ((IpaRiskDefTb)map.get(riskId)).getPrmAmtBas().intValue()));
/* 127:126 */       ipaRiskDefTb.setPrmAmtDesc(map.get(riskId) == null ? null : ((IpaRiskDefTb)map.get(riskId)).getPrmAmtDesc());
/* 128:127 */       ipaRiskDefTb.setEffDt(t9972.getDateEff());
/* 129:128 */       ipaRiskDefTb.setExpDt(t9972.getDateEnd());
/* 130:129 */       if ("1".equals(t9972.getFlag())) {
/* 131:130 */         ipaRiskDefTb.setEffMk("Y");
/* 132:131 */       } else if ("2".equals(t9972.getFlag())) {
/* 133:132 */         ipaRiskDefTb.setEffMk("N");
/* 134:    */       }
/* 135:134 */       ipaRiskDefTb.setCreateDt(this.now);
/* 136:    */       
/* 137:    */ 
/* 138:137 */       this.ipaDefManualJobDao.insert(ipaRiskDefTb, "ipa_risk_ref_tb");
/* 139:138 */       index = Integer.valueOf(index.intValue() + 1);
/* 140:    */     }
/* 141:    */   }
/* 142:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.service.IpaDefManualJobService
 * JD-Core Version:    0.7.0.1
 */