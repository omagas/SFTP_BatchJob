/*   1:    */ package com.zurich.sds.ipa.service;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.ipa.dao.IpaDefAutoJobDao;
/*   4:    */ import com.zurich.sds.ipa.module.T7039;
/*   5:    */ import com.zurich.sds.ipa.module.T7274;
/*   6:    */ import com.zurich.sds.ipa.module.T7308;
/*   7:    */ import com.zurich.sds.ipa.module.T7961;
/*   8:    */ import com.zurich.sds.ipa.module.T8046;
/*   9:    */ import com.zurich.sds.ipa.module.T9995;
/*  10:    */ import com.zurich.sds.model.common.hibernate.entity.HostCollecterDefTb;
/*  11:    */ import com.zurich.sds.model.ipa.hibernate.entity.IpaCmpgnDefTb;
/*  12:    */ import com.zurich.sds.model.ipa.hibernate.entity.IpaCmpgnRiskAmtRefTb;
/*  13:    */ import com.zurich.sds.tool.PropertiesTool;
/*  14:    */ import com.zurich.sds.utils.AS400Element;
/*  15:    */ import com.zurich.sds.utils.AS400Util;
/*  16:    */ import java.io.BufferedReader;
/*  17:    */ import java.io.File;
/*  18:    */ import java.io.FileInputStream;
/*  19:    */ import java.io.InputStreamReader;
/*  20:    */ import java.lang.reflect.Field;
/*  21:    */ import java.lang.reflect.Method;
/*  22:    */ import java.util.Date;
/*  23:    */ import java.util.Properties;
/*  24:    */ 
/*  25:    */ public class IpaDefAutoJobService
/*  26:    */ {
/*  27: 26 */   private Date now = new Date();
/*  28: 27 */   private IpaDefAutoJobDao ipaDefDao = new IpaDefAutoJobDao();
/*  29: 29 */   private Properties properties = PropertiesTool.getProperties("IpaJobSetting.properties");
/*  30: 30 */   private String as400_filePath = this.properties.getProperty("AS400.dir");
/*  31: 31 */   private String t7308_fileName = this.properties.getProperty("T7308.fileName");
/*  32: 32 */   private String t9995_fileName = this.properties.getProperty("T9995.fileName");
/*  33: 33 */   private String t7274_fileName = this.properties.getProperty("T7274.fileName");
/*  34: 34 */   private String t7961_fileName = this.properties.getProperty("T7961.fileName");
/*  35: 35 */   private String t8046_fileName = this.properties.getProperty("T8046.fileName");
/*  36: 36 */   private String t7039_fileName = this.properties.getProperty("T7039.fileName");
/*  37:    */   
/*  38:    */   public void doIpaDefAutoJob()
/*  39:    */     throws Exception
/*  40:    */   {
/*  41: 39 */     this.ipaDefDao.setAutoCommit(Boolean.valueOf(false));
/*  42:    */     
/*  43: 41 */     this.ipaDefDao.executeSQL(" DELETE FROM ipa_cmpgn_def_tb ");
/*  44: 42 */     this.ipaDefDao.executeSQL(" DELETE FROM ipa_cmpgnrisk_amt_ref_tb ");
/*  45:    */     
/*  46: 44 */     doJobT7308(this.as400_filePath, this.t7308_fileName);
/*  47: 45 */     doJobT9995(this.as400_filePath, this.t9995_fileName);
/*  48: 46 */     doJobT7274(this.as400_filePath, this.t7274_fileName);
/*  49: 47 */     doJobT7961(this.as400_filePath, this.t7961_fileName);
/*  50: 48 */     doJobT8046(this.as400_filePath, this.t8046_fileName);
/*  51:    */     
/*  52: 50 */     this.ipaDefDao.executeSQL(" DELETE FROM host_collecter_def_tb ");
/*  53:    */     
/*  54: 52 */     doJobT7039(this.as400_filePath, this.t7039_fileName);
/*  55:    */   }
/*  56:    */   
/*  57:    */   private void doJobT7308(String filePath, String fileName)
/*  58:    */     throws Exception
/*  59:    */   {
/*  60: 56 */     File file = new File(filePath + fileName);
/*  61: 57 */     InputStreamReader read = new InputStreamReader(new FileInputStream(file), "BIG5");
/*  62: 58 */     BufferedReader reader = new BufferedReader(read);
/*  63:    */     
/*  64:    */ 
/*  65:    */ 
/*  66:    */ 
/*  67:    */ 
/*  68: 64 */     Class<?> as400Class = T7308.class;
/*  69:    */     String line;
/*  70: 65 */     while ((line = reader.readLine()) != null)
/*  71:    */     {
/*  72:    */       String line;
/*  73: 66 */       T7308 t7308 = (T7308)AS400Util.trans(as400Class, line);
/*  74:    */       
/*  75: 68 */       IpaCmpgnDefTb ipaCmpgnDefTb = new IpaCmpgnDefTb();
/*  76: 69 */       ipaCmpgnDefTb.setCmpgnCd(t7308.getItem().substring(1, t7308.getItem().length()));
/*  77: 70 */       ipaCmpgnDefTb.setCmpgnNm(t7308.getLongDesc());
/*  78: 71 */       ipaCmpgnDefTb.setClassType(t7308.getItem().substring(0, 1));
/*  79: 72 */       ipaCmpgnDefTb.setItemCd(t7308.getItem());
/*  80: 73 */       ipaCmpgnDefTb.setCmpgnNoteDesc(t7308.getNewAgeMin().intValue() < 15 ? "15歲以下適用" : null);
/*  81: 74 */       ipaCmpgnDefTb.setDltCmpgnPrm(t7308.getTotalPremium());
/*  82: 75 */       ipaCmpgnDefTb.setSuitAgeMax(t7308.getNewAgeMax());
/*  83: 76 */       ipaCmpgnDefTb.setSuitAgeMin(t7308.getNewAgeMin());
/*  84: 77 */       ipaCmpgnDefTb.setAgeoverMax(t7308.getRenewalAgeMax());
/*  85: 78 */       ipaCmpgnDefTb.setAgeoverMin(t7308.getRenewalAgeMin());
/*  86: 79 */       ipaCmpgnDefTb.setAgeoverReviseMk(ipaCmpgnDefTb.getAgeoverMax() != null ? "Y" : "N");
/*  87: 80 */       ipaCmpgnDefTb.setEffDt(t7308.getDateEff());
/*  88: 81 */       ipaCmpgnDefTb.setExpDt(t7308.getDateEnd());
/*  89: 82 */       if ("1".equals(t7308.getFlag())) {
/*  90: 83 */         ipaCmpgnDefTb.setEffMk("Y");
/*  91: 84 */       } else if ("2".equals(t7308.getFlag())) {
/*  92: 85 */         ipaCmpgnDefTb.setEffMk("N");
/*  93:    */       }
/*  94: 87 */       ipaCmpgnDefTb.setCreateDt(this.now);
/*  95:    */       
/*  96: 89 */       this.ipaDefDao.insert(ipaCmpgnDefTb, "ipa_cmpgn_def_tb");
/*  97:    */       
/*  98:    */ 
/*  99: 92 */       doRiskAmtRefJob(t7308);
/* 100:    */     }
/* 101:    */   }
/* 102:    */   
/* 103:    */   private void doJobT9995(String filePath, String fileName)
/* 104:    */     throws Exception
/* 105:    */   {
/* 106: 97 */     File file = new File(filePath + fileName);
/* 107: 98 */     InputStreamReader read = new InputStreamReader(new FileInputStream(file), "BIG5");
/* 108: 99 */     BufferedReader reader = new BufferedReader(read);
/* 109:    */     String line;
/* 110:104 */     while ((line = reader.readLine()) != null)
/* 111:    */     {
/* 112:    */       String line;
/* 113:106 */       doRiskAmtRefJob(AS400Util.trans(T9995.class, line));
/* 114:    */     }
/* 115:    */   }
/* 116:    */   
/* 117:    */   private void doJobT7274(String filePath, String fileName)
/* 118:    */     throws Exception
/* 119:    */   {
/* 120:111 */     File file = new File(filePath + fileName);
/* 121:112 */     InputStreamReader read = new InputStreamReader(new FileInputStream(file), "BIG5");
/* 122:113 */     BufferedReader reader = new BufferedReader(read);
/* 123:    */     String line;
/* 124:118 */     while ((line = reader.readLine()) != null)
/* 125:    */     {
/* 126:    */       String line;
/* 127:120 */       doRiskAmtRefJob(AS400Util.trans(T7274.class, line));
/* 128:    */     }
/* 129:    */   }
/* 130:    */   
/* 131:    */   private void doJobT7961(String filePath, String fileName)
/* 132:    */     throws Exception
/* 133:    */   {
/* 134:125 */     File file = new File(filePath + fileName);
/* 135:126 */     InputStreamReader read = new InputStreamReader(new FileInputStream(file), "BIG5");
/* 136:127 */     BufferedReader reader = new BufferedReader(read);
/* 137:    */     String line;
/* 138:132 */     while ((line = reader.readLine()) != null)
/* 139:    */     {
/* 140:    */       String line;
/* 141:134 */       doRiskAmtRefJob(AS400Util.trans(T7961.class, line));
/* 142:    */     }
/* 143:    */   }
/* 144:    */   
/* 145:    */   private void doJobT8046(String filePath, String fileName)
/* 146:    */     throws Exception
/* 147:    */   {
/* 148:139 */     File file = new File(filePath + fileName);
/* 149:140 */     InputStreamReader read = new InputStreamReader(new FileInputStream(file), "BIG5");
/* 150:141 */     BufferedReader reader = new BufferedReader(read);
/* 151:    */     String line;
/* 152:146 */     while ((line = reader.readLine()) != null)
/* 153:    */     {
/* 154:    */       String line;
/* 155:148 */       doRiskAmtRefJob(AS400Util.trans(T8046.class, line));
/* 156:    */     }
/* 157:    */   }
/* 158:    */   
/* 159:    */   private void doRiskAmtRefJob(Object object)
/* 160:    */     throws Exception
/* 161:    */   {
/* 162:198 */     Class<?> as400Class = object.getClass();
/* 163:    */     
/* 164:200 */     IpaCmpgnRiskAmtRefTb ipaCmpgnRiskAmtRefTbModule = new IpaCmpgnRiskAmtRefTb();
/* 165:    */     
/* 166:202 */     String itemCd = (String)as400Class.getMethod("getItem", new Class[0]).invoke(object, new Object[0]);
/* 167:203 */     ipaCmpgnRiskAmtRefTbModule.setItemCd(itemCd);
/* 168:    */     
/* 169:205 */     Date dateEff = (Date)as400Class.getMethod("getDateEff", new Class[0]).invoke(object, new Object[0]);
/* 170:206 */     ipaCmpgnRiskAmtRefTbModule.setEffDt(dateEff);
/* 171:    */     
/* 172:208 */     Date dateEnd = (Date)as400Class.getMethod("getDateEnd", new Class[0]).invoke(object, new Object[0]);
/* 173:209 */     ipaCmpgnRiskAmtRefTbModule.setExpDt(dateEnd);
/* 174:    */     
/* 175:211 */     String flag = (String)as400Class.getMethod("getFlag", new Class[0]).invoke(object, new Object[0]);
/* 176:212 */     if ("1".equals(flag)) {
/* 177:213 */       ipaCmpgnRiskAmtRefTbModule.setEffMk("Y");
/* 178:214 */     } else if ("2".equals(flag)) {
/* 179:215 */       ipaCmpgnRiskAmtRefTbModule.setEffMk("N");
/* 180:    */     }
/* 181:217 */     ipaCmpgnRiskAmtRefTbModule.setCreateDt(this.now);
/* 182:219 */     for (Field field : as400Class.getDeclaredFields())
/* 183:    */     {
/* 184:220 */       String fieldName = field.getName();
/* 185:221 */       if (fieldName.startsWith("insuranceCoverage"))
/* 186:    */       {
/* 187:222 */         String methodNameGet = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
/* 188:    */         
/* 189:224 */         IpaCmpgnRiskAmtRefTb ipaCmpgnRiskAmtRefTb = ipaCmpgnRiskAmtRefTbModule.clone();
/* 190:    */         
/* 191:226 */         AS400Element as400Element = (AS400Element)field.getAnnotation(AS400Element.class);
/* 192:227 */         ipaCmpgnRiskAmtRefTb.setRiskId(as400Element.riskId());
/* 193:    */         
/* 194:229 */         Integer riskAmt = (Integer)as400Class.getMethod(methodNameGet, new Class[0]).invoke(object, new Object[0]);
/* 195:230 */         ipaCmpgnRiskAmtRefTb.setRiskAmt(riskAmt);
/* 196:    */         
/* 197:    */ 
/* 198:233 */         this.ipaDefDao.insert(ipaCmpgnRiskAmtRefTb, "ipa_cmpgnrisk_amt_ref_tb");
/* 199:    */       }
/* 200:    */     }
/* 201:    */   }
/* 202:    */   
/* 203:    */   private void doJobT7039(String filePath, String fileName)
/* 204:    */     throws Exception
/* 205:    */   {
/* 206:285 */     File file = new File(filePath + fileName);
/* 207:286 */     InputStreamReader read = new InputStreamReader(new FileInputStream(file), "BIG5");
/* 208:287 */     BufferedReader reader = new BufferedReader(read);
/* 209:    */     
/* 210:    */ 
/* 211:    */ 
/* 212:    */ 
/* 213:292 */     Class<?> as400Class = T7039.class;
/* 214:    */     String line;
/* 215:293 */     while ((line = reader.readLine()) != null)
/* 216:    */     {
/* 217:    */       String line;
/* 218:294 */       T7039 t7039 = (T7039)AS400Util.trans(as400Class, line);
/* 219:    */       
/* 220:296 */       HostCollecterDefTb hostCollecterDefTb = new HostCollecterDefTb();
/* 221:297 */       hostCollecterDefTb.setAgntCd(t7039.getItem());
/* 222:298 */       hostCollecterDefTb.setIssueBrhCd(t7039.getBranch());
/* 223:299 */       hostCollecterDefTb.setCollectId(t7039.getZcollect());
/* 224:300 */       hostCollecterDefTb.setLongdesc(t7039.getLongDesc());
/* 225:301 */       hostCollecterDefTb.setEffDt(t7039.getDateEff());
/* 226:302 */       hostCollecterDefTb.setExpDt(t7039.getDateEnd());
/* 227:303 */       if ("1".equals(t7039.getFlag())) {
/* 228:304 */         hostCollecterDefTb.setEffMk("Y");
/* 229:305 */       } else if ("2".equals(t7039.getFlag())) {
/* 230:306 */         hostCollecterDefTb.setEffMk("N");
/* 231:    */       }
/* 232:308 */       hostCollecterDefTb.setCreateDt(this.now);
/* 233:    */       
/* 234:310 */       this.ipaDefDao.insert(hostCollecterDefTb, "host_collecter_def_tb");
/* 235:    */     }
/* 236:    */   }
/* 237:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.service.IpaDefAutoJobService
 * JD-Core Version:    0.7.0.1
 */