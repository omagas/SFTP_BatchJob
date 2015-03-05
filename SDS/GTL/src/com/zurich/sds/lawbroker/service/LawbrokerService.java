/*   1:    */ package com.zurich.sds.lawbroker.service;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.ftp.dao.FtpDatabaseDao;
/*   4:    */ import com.zurich.sds.lawbroker.action.LawbrokerAction;
/*   5:    */ import com.zurich.sds.lawbroker.module.InsuredRoster;
/*   6:    */ import com.zurich.sds.lawbroker.module.Policy;
/*   7:    */ import com.zurich.sds.tool.PropertiesTool;
/*   8:    */ import com.zurich.sds.utils.BeanUtil;
/*   9:    */ import java.io.BufferedReader;
/*  10:    */ import java.io.BufferedWriter;
/*  11:    */ import java.io.File;
/*  12:    */ import java.io.FileInputStream;
/*  13:    */ import java.io.FileOutputStream;
/*  14:    */ import java.io.InputStreamReader;
/*  15:    */ import java.io.OutputStreamWriter;
/*  16:    */ import java.io.Writer;
/*  17:    */ import java.sql.SQLException;
/*  18:    */ import java.text.SimpleDateFormat;
/*  19:    */ import java.util.Calendar;
/*  20:    */ import java.util.Date;
/*  21:    */ import java.util.List;
/*  22:    */ import java.util.Properties;
/*  23:    */ import org.apache.log4j.Logger;
/*  24:    */ import org.apache.log4j.PropertyConfigurator;
/*  25:    */ 
/*  26:    */ public class LawbrokerService
/*  27:    */ {
/*  28: 28 */   private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  29: 29 */   private final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
/*  30: 30 */   private final String newLine = System.lineSeparator();
/*  31: 37 */   private Logger logger = Logger.getLogger(LawbrokerAction.class);
/*  32: 38 */   private FtpDatabaseDao dao = new FtpDatabaseDao();
/*  33:    */   
/*  34:    */   public LawbrokerService()
/*  35:    */   {
/*  36:    */     try
/*  37:    */     {
/*  38: 50 */       Properties pro = PropertiesTool.getProperties("Log4j.properties");
/*  39: 51 */       PropertyConfigurator.configure(pro);
/*  40:    */     }
/*  41:    */     catch (Exception e)
/*  42:    */     {
/*  43: 54 */       e.printStackTrace();
/*  44:    */     }
/*  45:    */   }
/*  46:    */   
/*  47:    */   public List<Policy> getPolicyDataList()
/*  48:    */   {
/*  49: 60 */     return this.dao.getPolicyDataList(null, null, Boolean.valueOf(true), Boolean.valueOf(false));
/*  50:    */   }
/*  51:    */   
/*  52:    */   public List<Policy> getManualPolicyDataList(Date logTimeBegin, Date logTimeEnd)
/*  53:    */   {
/*  54: 64 */     return this.dao.getPolicyDataList(logTimeBegin, logTimeEnd, Boolean.valueOf(false), Boolean.valueOf(false));
/*  55:    */   }
/*  56:    */   
/*  57:    */   public List<InsuredRoster> getRosterDataList()
/*  58:    */   {
/*  59: 68 */     return this.dao.getRosterDataList(null, null, Boolean.valueOf(true), Boolean.valueOf(false));
/*  60:    */   }
/*  61:    */   
/*  62:    */   public List<InsuredRoster> getManualRosterDataList(Date logTimeBegin, Date logTimeEnd)
/*  63:    */   {
/*  64: 72 */     return this.dao.getRosterDataList(logTimeBegin, logTimeEnd, Boolean.valueOf(false), Boolean.valueOf(false));
/*  65:    */   }
/*  66:    */   
/*  67:    */   public List<Policy> getCancelPolicyDataList()
/*  68:    */   {
/*  69: 77 */     return this.dao.getPolicyDataList(null, null, Boolean.valueOf(true), Boolean.valueOf(true));
/*  70:    */   }
/*  71:    */   
/*  72:    */   public List<Policy> getCancelManualPolicyDataList(Date logTimeBegin, Date logTimeEnd)
/*  73:    */   {
/*  74: 81 */     return this.dao.getPolicyDataList(logTimeBegin, logTimeEnd, Boolean.valueOf(false), Boolean.valueOf(true));
/*  75:    */   }
/*  76:    */   
/*  77:    */   public List<InsuredRoster> getCancelRosterDataList()
/*  78:    */   {
/*  79: 85 */     return this.dao.getRosterDataList(null, null, Boolean.valueOf(true), Boolean.valueOf(true));
/*  80:    */   }
/*  81:    */   
/*  82:    */   public List<InsuredRoster> getCancelManualRosterDataList(Date logTimeBegin, Date logTimeEnd)
/*  83:    */   {
/*  84: 89 */     return this.dao.getRosterDataList(logTimeBegin, logTimeEnd, Boolean.valueOf(false), Boolean.valueOf(true));
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void updateFtpSendMk(List<Policy> policyList, List<Policy> cancelPolicyList)
/*  88:    */     throws SQLException
/*  89:    */   {
/*  90: 94 */     this.dao.setAutoCommit(Boolean.valueOf(false));
/*  91: 95 */     this.dao.setSavepoint();
/*  92: 97 */     for (Policy policy : policyList) {
/*  93: 98 */       this.dao.updateFtpSendMk(policy.getDataId(), policy.getDataIdVerno(), "Y");
/*  94:    */     }
/*  95:100 */     for (Policy policy : cancelPolicyList) {
/*  96:101 */       this.dao.updateFtpSendMk(policy.getDataId(), policy.getDataIdVerno(), "C");
/*  97:    */     }
/*  98:103 */     this.dao.setAutoCommit(Boolean.valueOf(true));
/*  99:    */   }
/* 100:    */   
/* 101:    */   public void genManualPolicyCsv(List<Policy> policyList, String filePath, String policyFileName)
/* 102:    */     throws Exception
/* 103:    */   {
/* 104:108 */     genPolicyCsv(policyList, filePath, null, policyFileName, Boolean.valueOf(true));
/* 105:    */   }
/* 106:    */   
/* 107:    */   public Integer genPolicyCsv(List<Policy> policyList, String filePath, String policyFileName, String policyFileTempName)
/* 108:    */     throws Exception
/* 109:    */   {
/* 110:113 */     return genPolicyCsv(policyList, filePath, policyFileName, policyFileTempName, Boolean.valueOf(false));
/* 111:    */   }
/* 112:    */   
/* 113:    */   private Integer genPolicyCsv(List<Policy> policyList, String filePath, String policyFileName, String policyFileTempName, Boolean isManual)
/* 114:    */     throws Exception
/* 115:    */   {
/* 116:118 */     Calendar now = Calendar.getInstance();
/* 117:119 */     Calendar yestoday = (Calendar)now.clone();
/* 118:120 */     yestoday.add(5, -1);
/* 119:121 */     Date nowDate = now.getTime();
/* 120:122 */     Date yestodayDate = yestoday.getTime();
/* 121:123 */     int countPolicySize = 0;
/* 122:    */     
/* 123:    */ 
/* 124:126 */     File orgFile = new File(filePath + policyFileName);
/* 125:127 */     File tempFile = new File(filePath + policyFileTempName);
/* 126:    */     OutputStreamWriter osw;
/* 127:    */     BufferedWriter bw;
/* 128:130 */     if ((!isManual.booleanValue()) && (orgFile.exists()))
/* 129:    */     {
/* 130:132 */       InputStreamReader read = new InputStreamReader(new FileInputStream(orgFile), "BIG5");
/* 131:133 */       BufferedReader reader = new BufferedReader(read);
/* 132:134 */       StringBuffer orgFileData = new StringBuffer();
/* 133:    */       String line;
/* 134:136 */       while ((line = reader.readLine()) != null)
/* 135:    */       {
/* 136:    */         String line;
/* 137:137 */         countPolicySize++;
/* 138:138 */         orgFileData.append(line);
/* 139:139 */         orgFileData.append(this.newLine);
/* 140:    */       }
/* 141:141 */       read.close();
/* 142:142 */       reader.close();
/* 143:    */       
/* 144:144 */       countPolicySize -= 2;
/* 145:    */       
/* 146:146 */       FileOutputStream out = new FileOutputStream(tempFile);
/* 147:147 */       OutputStreamWriter osw = new OutputStreamWriter(out, "BIG5");
/* 148:148 */       BufferedWriter bw = new BufferedWriter(osw);
/* 149:    */       
/* 150:    */ 
/* 151:151 */       bw.append(orgFileData);
/* 152:    */       
/* 153:    */ 
/* 154:    */ 
/* 155:    */ 
/* 156:156 */       bw.flush();
/* 157:    */     }
/* 158:    */     else
/* 159:    */     {
/* 160:158 */       FileOutputStream out = new FileOutputStream(tempFile);
/* 161:159 */       osw = new OutputStreamWriter(out, "BIG5");
/* 162:160 */       bw = new BufferedWriter(osw);
/* 163:    */       
/* 164:    */ 
/* 165:163 */       bw.append(" ");
/* 166:164 */       bw.append("蘇黎世產險旅平險保單資料主檔,").append(",").append("受理日期：" + this.sdf.format(yestodayDate) + " 00:00:00 ~ 23:59:59,").append(",").append("製表日期：" + this.sdf.format(nowDate));
/* 167:165 */       bw.append(this.newLine);
/* 168:    */       
/* 169:    */ 
/* 170:168 */       bw.append("要保書編號,").append("TA保單號碼,").append("經代 / 旅行社,").append("電傳人,").append("投保類別,");
/* 171:169 */       bw.append("要保人姓名,").append("要保人出生日期,").append("要保人身分證字號,").append("生效日期,").append("生效時分,");
/* 172:170 */       bw.append("滿期日期,").append("天數,").append("被保險人數,").append("總保費,").append("目的地,");
/* 173:171 */       bw.append("要保人電話,").append("要保人郵遞區號,").append("要保人地址,").append("要保日期,").append("險種代號");
/* 174:172 */       bw.append(this.newLine);
/* 175:    */       
/* 176:174 */       bw.flush();
/* 177:    */     }
/* 178:177 */     if ((policyList != null) && (policyList.size() != 0))
/* 179:    */     {
/* 180:178 */       countPolicySize += policyList.size();
/* 181:180 */       for (Policy policy : policyList)
/* 182:    */       {
/* 183:182 */         BeanUtil.trimBeanFields(policy);
/* 184:    */         
/* 185:184 */         String tourplaceDesc = policy.getTourplaceDesc();
/* 186:185 */         if (tourplaceDesc != null) {
/* 187:186 */           tourplaceDesc = tourplaceDesc.replaceAll(",", "、");
/* 188:    */         }
/* 189:190 */         bw.append(policy.getRecptNo() + ",").append(policy.getPolicyNo() + ",").append("錠嵂,").append(policy.getIncomeId() + ",").append(policy.getCmpgnNm() + ",");
/* 190:191 */         bw.append(policy.getCustNm() + ",").append(convertDateToString(policy.getCustBth()) + ",").append(policy.getCustId() + ",").append(convertDateToString(policy.getInsEffDt()) + ",").append(policy.getInsEffTm() + ",");
/* 191:192 */         bw.append(convertDateToString(policy.getInsExpDt()) + ",").append(policy.getTourDays() + ",").append(policy.getTourcustCnt() + ",").append(policy.getTotPrm() + ",").append(tourplaceDesc + ",");
/* 192:193 */         bw.append(policy.getCustMobile() + ",").append(policy.getCustZipCd() + ",").append(policy.getCustAdrs() + ",").append(convertDateToString(policy.getIncomeDt()) + ",").append("GTL");
/* 193:194 */         bw.append(this.newLine);
/* 194:    */       }
/* 195:    */     }
/* 196:198 */     bw.flush();
/* 197:    */     
/* 198:200 */     bw.close();
/* 199:    */     
/* 200:202 */     return Integer.valueOf(countPolicySize);
/* 201:    */   }
/* 202:    */   
/* 203:    */   public void genManualRosterCsv(List<InsuredRoster> rosterList, String filePath, String rosterFileName)
/* 204:    */     throws Exception
/* 205:    */   {
/* 206:207 */     genRosterCsv(rosterList, filePath, null, rosterFileName, Boolean.valueOf(true));
/* 207:    */   }
/* 208:    */   
/* 209:    */   public void genRosterCsv(List<InsuredRoster> rosterList, String filePath, String rosterFileName, String rosterFileTempName)
/* 210:    */     throws Exception
/* 211:    */   {
/* 212:212 */     genRosterCsv(rosterList, filePath, rosterFileName, rosterFileTempName, Boolean.valueOf(false));
/* 213:    */   }
/* 214:    */   
/* 215:    */   private void genRosterCsv(List<InsuredRoster> rosterList, String filePath, String rosterFileName, String rosterFileTempName, Boolean isManual)
/* 216:    */     throws Exception
/* 217:    */   {
/* 218:218 */     Calendar now = Calendar.getInstance();
/* 219:219 */     Calendar yestoday = (Calendar)now.clone();
/* 220:220 */     yestoday.add(5, -1);
/* 221:221 */     Date nowDate = now.getTime();
/* 222:222 */     Date yestodayDate = yestoday.getTime();
/* 223:    */     
/* 224:    */ 
/* 225:225 */     File orgFile = new File(filePath + rosterFileName);
/* 226:226 */     File tempFile = new File(filePath + rosterFileTempName);
/* 227:    */     OutputStreamWriter osw;
/* 228:    */     BufferedWriter bw;
/* 229:229 */     if ((!isManual.booleanValue()) && (orgFile.exists()))
/* 230:    */     {
/* 231:231 */       InputStreamReader read = new InputStreamReader(new FileInputStream(orgFile), "BIG5");
/* 232:232 */       BufferedReader reader = new BufferedReader(read);
/* 233:233 */       StringBuffer orgFileData = new StringBuffer();
/* 234:    */       String line;
/* 235:235 */       while ((line = reader.readLine()) != null)
/* 236:    */       {
/* 237:    */         String line;
/* 238:236 */         orgFileData.append(line);
/* 239:237 */         orgFileData.append(this.newLine);
/* 240:    */       }
/* 241:239 */       read.close();
/* 242:240 */       reader.close();
/* 243:    */       
/* 244:242 */       FileOutputStream out = new FileOutputStream(tempFile);
/* 245:243 */       OutputStreamWriter osw = new OutputStreamWriter(out, "BIG5");
/* 246:244 */       BufferedWriter bw = new BufferedWriter(osw);
/* 247:    */       
/* 248:    */ 
/* 249:247 */       bw.append(orgFileData);
/* 250:    */       
/* 251:    */ 
/* 252:    */ 
/* 253:    */ 
/* 254:252 */       bw.flush();
/* 255:    */     }
/* 256:    */     else
/* 257:    */     {
/* 258:254 */       FileOutputStream out = new FileOutputStream(tempFile);
/* 259:255 */       osw = new OutputStreamWriter(out, "BIG5");
/* 260:256 */       bw = new BufferedWriter(osw);
/* 261:    */       
/* 262:    */ 
/* 263:259 */       bw.append(" ");
/* 264:260 */       bw.append("蘇黎世產險旅平險保單資料TA被保險人名冊檔,").append(",").append("受理日期：" + this.sdf.format(yestodayDate) + " 00:00:00 ~ 23:59:59,").append(",").write("製表日期：" + this.sdf.format(nowDate));
/* 265:261 */       bw.append(this.newLine);
/* 266:    */       
/* 267:    */ 
/* 268:264 */       bw.append("要保書編號,").append("保單號碼,").append("要保人姓名,").append("被保險人姓名,").append("被保險人身份證號碼,");
/* 269:265 */       bw.append("被保險人出生年月日,").append("要/被保險人關係,").append("生效日期,").append("滿期日期,").append("身故及殘廢保險金額,");
/* 270:266 */       bw.append("傷害醫療保險金額,").append("海外突發疾病健康保險金額,").append("天數,").append("保費,").append("目的地,");
/* 271:267 */       bw.append("要保日期,").append("受益人姓名,").append("受益人與被保險人關係,").append("生效時分,").append("人數,");
/* 272:268 */       bw.append("電傳人,").append("通路代號");
/* 273:269 */       bw.append(this.newLine);
/* 274:    */       
/* 275:271 */       bw.flush();
/* 276:    */     }
/* 277:274 */     if ((rosterList != null) && (rosterList.size() != 0)) {
/* 278:276 */       for (InsuredRoster roster : rosterList)
/* 279:    */       {
/* 280:278 */         BeanUtil.trimBeanFields(roster);
/* 281:    */         
/* 282:280 */         String tourplaceDesc = roster.getTourplaceDesc();
/* 283:281 */         if (tourplaceDesc != null) {
/* 284:282 */           tourplaceDesc = tourplaceDesc.replaceAll(",", "、");
/* 285:    */         }
/* 286:285 */         String benefNm = roster.getBenefNm();
/* 287:286 */         if (benefNm != null) {
/* 288:287 */           benefNm = benefNm.replaceAll(",", "、");
/* 289:    */         }
/* 290:291 */         bw.append(roster.getRecptNo() + ",").append(roster.getPolicyNo() + ",").append(roster.getCustNmA() + ",").append(roster.getCustNmI() + ",").append(roster.getCustId() + ",");
/* 291:292 */         bw.append(convertDateToString(roster.getCustBth()) + ",").append(roster.getCustRel() + ",").append(convertDateToString(roster.getInsEffDt()) + ",").append(convertDateToString(roster.getInsExpDt()) + ",").append(roster.getRiskAmt09() + ",");
/* 292:293 */         bw.append(roster.getRiskAmt10() + ",").append(roster.getRiskAmt11_12() + ",").append(roster.getTourDays() + ",").append(roster.getPerPrm() + ",").append(tourplaceDesc + ",");
/* 293:294 */         bw.append(convertDateToString(roster.getIncomeDt()) + ",").append(benefNm + ",").append(roster.getBenRel() + ",").append(roster.getInsEffTm() + ",").append(roster.getPerAgeCnt() + ",");
/* 294:295 */         bw.append(roster.getIncomeId() + ",").append(roster.getChlCd());
/* 295:296 */         bw.append(this.newLine);
/* 296:    */       }
/* 297:    */     }
/* 298:300 */     bw.flush();
/* 299:    */     
/* 300:302 */     bw.close();
/* 301:    */   }
/* 302:    */   
/* 303:    */   public void deleteFile(String filePath, String fileName)
/* 304:    */   {
/* 305:306 */     File file = new File(filePath + fileName);
/* 306:307 */     if (file.exists()) {
/* 307:308 */       file.delete();
/* 308:    */     }
/* 309:    */   }
/* 310:    */   
/* 311:    */   public void renameFile(String filePath, String fileName, String newFileName)
/* 312:    */   {
/* 313:313 */     File file = new File(filePath + fileName);
/* 314:314 */     if (file.exists())
/* 315:    */     {
/* 316:315 */       File newFile = new File(filePath + newFileName);
/* 317:316 */       file.renameTo(newFile);
/* 318:    */     }
/* 319:    */   }
/* 320:    */   
/* 321:    */   public void rollback()
/* 322:    */   {
/* 323:321 */     this.dao.rollback();
/* 324:    */   }
/* 325:    */   
/* 326:    */   private String convertDateToString(Date date)
/* 327:    */   {
/* 328:325 */     if (date == null) {
/* 329:326 */       return "";
/* 330:    */     }
/* 331:328 */     return this.sdf2.format(date);
/* 332:    */   }
/* 333:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.lawbroker.service.LawbrokerService
 * JD-Core Version:    0.7.0.1
 */