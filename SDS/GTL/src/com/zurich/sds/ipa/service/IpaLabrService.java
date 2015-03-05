/*   1:    */ package com.zurich.sds.ipa.service;
/*   2:    */ 
/*   3:    */ import com.zurich.core.util.CommonUtil;
/*   4:    */ import com.zurich.sds.ipa.dao.IpaLabrDao;
/*   5:    */ import com.zurich.sds.ipa.module.IpaLabrMain;
/*   6:    */ import com.zurich.sds.model.common.hibernate.entity.AppMTb;
/*   7:    */ import com.zurich.sds.model.common.hibernate.entity.CustDetailTb;
/*   8:    */ import com.zurich.sds.model.common.hibernate.entity.CustMTb;
/*   9:    */ import com.zurich.sds.model.common.hibernate.entity.pk.CustDetailId;
/*  10:    */ import com.zurich.sds.model.ipa.hibernate.entity.AppIpaMTb;
/*  11:    */ import java.io.BufferedReader;
/*  12:    */ import java.io.File;
/*  13:    */ import java.io.FileInputStream;
/*  14:    */ import java.io.IOException;
/*  15:    */ import java.io.InputStreamReader;
/*  16:    */ import java.sql.SQLException;
/*  17:    */ import java.util.ArrayList;
/*  18:    */ import java.util.Date;
/*  19:    */ import java.util.HashMap;
/*  20:    */ import java.util.List;
/*  21:    */ import java.util.Map;
/*  22:    */ import java.util.Vector;
/*  23:    */ import org.apache.commons.lang.StringUtils;
/*  24:    */ import org.apache.commons.lang.time.DateUtils;
/*  25:    */ import org.apache.log4j.Logger;
/*  26:    */ 
/*  27:    */ public class IpaLabrService
/*  28:    */ {
/*  29: 29 */   private final String[] parsePatterns = { "yyyyMMdd" };
/*  30: 30 */   private final String CREDITCARD = "B002";
/*  31: 31 */   private final String CASH = "D000";
/*  32:    */   private IpaLabrDao labrIpaDao;
/*  33: 35 */   Logger logger = Logger.getLogger(IpaLabrService.class);
/*  34:    */   private static Map<String, String> payerInsuredRelCdMap;
/*  35:    */   
/*  36:    */   public IpaLabrService()
/*  37:    */   {
/*  38: 38 */     this.labrIpaDao = new IpaLabrDao();
/*  39:    */   }
/*  40:    */   
/*  41:    */   public List<IpaLabrMain> getLabrIpaMainList(String filePath, String fileName)
/*  42:    */     throws IOException, SQLException
/*  43:    */   {
/*  44: 42 */     List<IpaLabrMain> labrIpaMainList = new ArrayList();
/*  45:    */     
/*  46: 44 */     File file = new File(filePath + fileName);
/*  47: 45 */     InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
/*  48: 46 */     BufferedReader reader = new BufferedReader(read);
/*  49:    */     
/*  50:    */ 
/*  51: 49 */     int index = 0;
/*  52:    */     String line;
/*  53: 50 */     while ((line = reader.readLine()) != null)
/*  54:    */     {
/*  55:    */       String line;
/*  56: 51 */       index++;
/*  57:    */       
/*  58: 53 */       String errorMessage = "";
/*  59: 54 */       String[] columns = line.split(",");
/*  60:    */       
/*  61: 56 */       IpaLabrMain labrIpaMain = new IpaLabrMain();
/*  62:    */       try
/*  63:    */       {
/*  64: 58 */         errorMessage = errorMessage + setAndCheckLabrIpaMain(columns, labrIpaMain);
/*  65: 60 */         if (StringUtils.isBlank(errorMessage)) {
/*  66: 61 */           fixLabrIpaMain(labrIpaMain, fileName);
/*  67:    */         }
/*  68:    */       }
/*  69:    */       catch (Exception e)
/*  70:    */       {
/*  71: 64 */         e.printStackTrace();
/*  72: 65 */         errorMessage = errorMessage + e.getMessage();
/*  73:    */       }
/*  74: 68 */       if (!StringUtils.isBlank(errorMessage)) {
/*  75: 69 */         labrIpaMain.setErrorMessage("第" + index + "筆-" + labrIpaMain.getAppMTb().getChlDataNo() + "-" + errorMessage);
/*  76:    */       }
/*  77:    */     }
/*  78: 73 */     return labrIpaMainList;
/*  79:    */   }
/*  80:    */   
/*  81:    */   private String setAndCheckLabrIpaMain(String[] columns, IpaLabrMain labrIpaMain)
/*  82:    */     throws SQLException
/*  83:    */   {
/*  84: 77 */     CustDetailTb ipaCustI = labrIpaMain.getIpaCustI();
/*  85: 78 */     CustDetailTb ipaCustA = labrIpaMain.getIpaCustA();
/*  86: 79 */     CustDetailTb ipaCustP = labrIpaMain.getIpaCustP();
/*  87: 80 */     AppMTb appMTb = labrIpaMain.getAppMTb();
/*  88: 81 */     AppIpaMTb appIpaMTb = labrIpaMain.getAppIpaMTb();
/*  89: 82 */     StringBuffer errorMessage = new StringBuffer();
/*  90: 85 */     if ((StringUtils.isBlank(columns[26])) || (columns[26].length() != 8)) {
/*  91: 86 */       return "錠嵂受理編號格式錯誤 ";
/*  92:    */     }
/*  93: 87 */     if (!this.labrIpaDao.isValidChlDataNo(columns[26]).booleanValue()) {
/*  94: 88 */       return "錠嵂受理編號重複 ";
/*  95:    */     }
/*  96: 90 */     appMTb.setChlDataNo(columns[26]);
/*  97: 93 */     if (columns.length != 49) {
/*  98: 94 */       return "csv 欄位長度錯誤";
/*  99:    */     }
/* 100: 97 */     ipaCustI.setCustID(columns[0]);
/* 101: 98 */     ipaCustI.setCustNm(columns[1]);
/* 102: 99 */     ipaCustA.setCustID(columns[2]);
/* 103:100 */     ipaCustA.setCustNm(columns[3]);
/* 104:101 */     ipaCustI.setCustSex(columns[4]);
/* 105:102 */     if (!StringUtils.isBlank(columns[5])) {
/* 106:    */       try
/* 107:    */       {
/* 108:104 */         Date custBth = DateUtils.parseDate(columns[5], this.parsePatterns);
/* 109:105 */         ipaCustI.setCustBth(custBth);
/* 110:    */       }
/* 111:    */       catch (Exception e)
/* 112:    */       {
/* 113:107 */         e.printStackTrace();
/* 114:108 */         errorMessage.append(" 被保人出生日期轉換錯誤 ");
/* 115:    */       }
/* 116:    */     }
/* 117:111 */     ipaCustI.setCustZipcd(columns[6]);
/* 118:112 */     ipaCustI.setCustAdrs(columns[7]);
/* 119:    */     
/* 120:    */ 
/* 121:115 */     ipaCustI.setCustTelH(columns[10]);
/* 122:116 */     ipaCustI.setCustTelO(columns[11]);
/* 123:117 */     ipaCustI.setCustMobile(columns[12]);
/* 124:118 */     ipaCustI.setCustEmail(columns[13]);
/* 125:119 */     ipaCustA.setCustSex(columns[14]);
/* 126:120 */     if (!StringUtils.isBlank(columns[15])) {
/* 127:    */       try
/* 128:    */       {
/* 129:122 */         Date custBth = DateUtils.parseDate(columns[15], this.parsePatterns);
/* 130:123 */         ipaCustA.setCustBth(custBth);
/* 131:    */       }
/* 132:    */       catch (Exception e)
/* 133:    */       {
/* 134:125 */         e.printStackTrace();
/* 135:126 */         errorMessage.append(" 要保人出生日期轉換錯誤 ");
/* 136:    */       }
/* 137:    */     }
/* 138:129 */     ipaCustA.setCustZipcd(columns[16]);
/* 139:130 */     ipaCustA.setCustAdrs(columns[17]);
/* 140:    */     
/* 141:    */ 
/* 142:133 */     ipaCustA.setCustTelH(columns[20]);
/* 143:134 */     ipaCustA.setCustTelO(columns[21]);
/* 144:135 */     ipaCustA.setCustMobile(columns[22]);
/* 145:136 */     ipaCustA.setCustEmail(columns[23]);
/* 146:138 */     if (!"2".equals(columns[24])) {
/* 147:139 */       errorMessage.append(" 險種類別錯誤 ");
/* 148:    */     }
/* 149:142 */     appMTb.setSalesCd(columns[25]);
/* 150:144 */     if (!StringUtils.isBlank(columns[27])) {
/* 151:    */       try
/* 152:    */       {
/* 153:146 */         Date toInsDt = DateUtils.parseDate(columns[27], this.parsePatterns);
/* 154:147 */         appIpaMTb.setToInsDt(toInsDt);
/* 155:    */       }
/* 156:    */       catch (Exception e)
/* 157:    */       {
/* 158:149 */         e.printStackTrace();
/* 159:150 */         errorMessage.append(" 投保日轉換錯誤 ");
/* 160:    */       }
/* 161:    */     }
/* 162:153 */     if (!StringUtils.isBlank(columns[28])) {
/* 163:    */       try
/* 164:    */       {
/* 165:155 */         Integer insYearLimit = Integer.valueOf(columns[28]);
/* 166:156 */         appIpaMTb.setInsYearLimit(insYearLimit);
/* 167:157 */         if (1 == insYearLimit.intValue()) {
/* 168:    */           break label499;
/* 169:    */         }
/* 170:158 */         errorMessage.append(" 年限只能為1 ");
/* 171:    */       }
/* 172:    */       catch (Exception e)
/* 173:    */       {
/* 174:161 */         e.printStackTrace();
/* 175:162 */         errorMessage.append(" 年限轉換錯誤 ");
/* 176:    */       }
/* 177:    */     } else {
/* 178:165 */       errorMessage.append(" 年限不可為空值 ");
/* 179:    */     }
/* 180:    */     label499:
/* 181:167 */     appIpaMTb.setPaymntTypCd(columns[29]);
/* 182:168 */     appIpaMTb.setPaymntTypCd("2".equals(columns[30]) ? "B002" : "D000");
/* 183:171 */     if ((!StringUtils.isBlank(columns[32])) && (!"2".equals(columns[30]))) {
/* 184:173 */       errorMessage.append(" 首期繳費方式不是信用卡時，首期信用卡號不可有值 ");
/* 185:    */     }
/* 186:176 */     if ((!StringUtils.isBlank(columns[33])) && (!"2".equals(columns[30]))) {
/* 187:178 */       errorMessage.append(" 首期繳費方式不是信用卡時，首期信用卡到期日不可有值 ");
/* 188:    */     }
/* 189:181 */     if ((!StringUtils.isBlank(columns[34])) && (!"2".equals(columns[30]))) {
/* 190:183 */       errorMessage.append(" 首期繳費方式不是信用卡時，首期信用卡別不可有值 ");
/* 191:    */     }
/* 192:190 */     appIpaMTb.setContMthdCd("3".equals(columns[40]) ? "B002" : "D000");
/* 193:193 */     if ((!StringUtils.isBlank(columns[42])) && (!"3".equals(columns[40]))) {
/* 194:195 */       errorMessage.append(" 續期繳費方式不是信用卡時，續期信用卡號不可有值 ");
/* 195:    */     }
/* 196:197 */     appIpaMTb.setCmpgnCd(columns[43]);
/* 197:198 */     appIpaMTb.setCmpgnSelMk("P");
/* 198:199 */     if (!StringUtils.isBlank(columns[44])) {
/* 199:    */       try
/* 200:    */       {
/* 201:201 */         appIpaMTb.setCmpgnPrm(Integer.valueOf(columns[44]));
/* 202:    */       }
/* 203:    */       catch (Exception e)
/* 204:    */       {
/* 205:203 */         e.printStackTrace();
/* 206:204 */         errorMessage.append(" 險種保費轉換錯誤 ");
/* 207:    */       }
/* 208:    */     }
/* 209:208 */     ipaCustP.setCustID(columns[46]);
/* 210:209 */     ipaCustP.setCustNm(columns[47]);
/* 211:210 */     ipaCustP.setPayerInsuredRelCd(columns[48]);
/* 212:    */     
/* 213:212 */     return errorMessage.toString();
/* 214:    */   }
/* 215:    */   
/* 216:    */   private void fixLabrIpaMain(IpaLabrMain labrIpaMain, String fileName)
/* 217:    */     throws SQLException
/* 218:    */   {
/* 219:216 */     Date now = new Date();
/* 220:    */     
/* 221:218 */     fixCustDetailTb(labrIpaMain.getIpaCustI());
/* 222:    */     
/* 223:220 */     fixCustDetailTb(labrIpaMain.getIpaCustA());
/* 224:    */     
/* 225:222 */     fixCustDetailTb(labrIpaMain.getIpaCustP());
/* 226:223 */     String payerInsuredRelCd = labrIpaMain.getIpaCustP().getPayerInsuredRelCd();
/* 227:224 */     if (!StringUtils.isBlank(payerInsuredRelCd)) {
/* 228:225 */       labrIpaMain.getIpaCustP().setPayerInsuredRelCd((String)getPayerInsuredRelCdMap().get(payerInsuredRelCd));
/* 229:    */     }
/* 230:229 */     if (!StringUtils.isBlank(labrIpaMain.getAppMTb().getSalesId()))
/* 231:    */     {
/* 232:230 */       String salesCd = this.labrIpaDao.getSalesCd(labrIpaMain.getAppMTb().getSalesId());
/* 233:231 */       labrIpaMain.getAppMTb().setSalesCd(salesCd);
/* 234:    */     }
/* 235:233 */     labrIpaMain.getAppMTb().setPrdtCd("IPA");
/* 236:234 */     labrIpaMain.getAppMTb().setDataSrcCd("DE");
/* 237:    */     
/* 238:236 */     labrIpaMain.getAppIpaMTb().setProcStatusCd("13");
/* 239:237 */     labrIpaMain.getAppIpaMTb().setCreateDt(now);
/* 240:238 */     labrIpaMain.getAppIpaMTb().setCreateUsrid("FTPIMP_JOB");
/* 241:239 */     labrIpaMain.getAppIpaMTb().setSrcFileNm(fileName);
/* 242:240 */     labrIpaMain.getAppIpaMTb().setProcStatusCd("13");
/* 243:    */   }
/* 244:    */   
/* 245:    */   private void fixCustDetailTb(CustDetailTb cust)
/* 246:    */   {
/* 247:244 */     String custId = cust.getCustID();
/* 248:245 */     if (StringUtils.isBlank(custId))
/* 249:    */     {
/* 250:246 */       cust.setCustID(" ");
/* 251:247 */       cust.setCustCnty("TAW");
/* 252:248 */       cust.setCustSex(null);
/* 253:249 */       cust.setCustTyp(null);
/* 254:    */     }
/* 255:251 */     else if (CommonUtil.isValidTWPID(custId))
/* 256:    */     {
/* 257:252 */       if (custId.charAt(1) == '1') {
/* 258:253 */         cust.setCustSex("1");
/* 259:    */       } else {
/* 260:255 */         cust.setCustSex("2");
/* 261:    */       }
/* 262:258 */       cust.setCustCnty("TWN");
/* 263:259 */       cust.setCustTyp("P");
/* 264:    */     }
/* 265:260 */     else if (CommonUtil.isValidAPRC(custId).booleanValue())
/* 266:    */     {
/* 267:261 */       if ((custId.charAt(1) == 'A') || (custId.charAt(1) == 'C')) {
/* 268:262 */         cust.setCustSex("1");
/* 269:    */       } else {
/* 270:264 */         cust.setCustSex("2");
/* 271:    */       }
/* 272:267 */       cust.setCustCnty("FOR");
/* 273:268 */       cust.setCustTyp("P");
/* 274:    */     }
/* 275:269 */     else if (CommonUtil.isValidTWBID(custId))
/* 276:    */     {
/* 277:270 */       cust.setCustCnty("TAW");
/* 278:271 */       cust.setCustSex(null);
/* 279:272 */       cust.setCustTyp("C");
/* 280:    */     }
/* 281:    */     else
/* 282:    */     {
/* 283:274 */       cust.setCustID(" ");
/* 284:275 */       cust.setCustCnty("TAW");
/* 285:276 */       cust.setCustSex(null);
/* 286:277 */       cust.setCustTyp(null);
/* 287:    */     }
/* 288:281 */     if ("P".equals(cust.getCustTyp())) {
/* 289:282 */       cust.setBthShowTyp("A");
/* 290:    */     }
/* 291:    */   }
/* 292:    */   
/* 293:    */   public void saveLabrIpaMainList(List<IpaLabrMain> labrIpaMainList)
/* 294:    */     throws Exception
/* 295:    */   {
/* 296:288 */     this.labrIpaDao.setAutoCommit(Boolean.valueOf(false));
/* 297:    */     
/* 298:290 */     String chlDataNo = null;
/* 299:    */     try
/* 300:    */     {
/* 301:292 */       for (IpaLabrMain labrIpaMain : labrIpaMainList)
/* 302:    */       {
/* 303:293 */         this.labrIpaDao.setSavepoint();
/* 304:    */         
/* 305:295 */         chlDataNo = labrIpaMain.getAppMTb().getChlDataNo();
/* 306:296 */         saveLabrIpaMain(labrIpaMain);
/* 307:    */         
/* 308:298 */         this.labrIpaDao.releaseConnection();
/* 309:    */       }
/* 310:    */     }
/* 311:    */     catch (Exception e)
/* 312:    */     {
/* 313:301 */       this.labrIpaDao.rollback();
/* 314:302 */       this.labrIpaDao.releaseConnection();
/* 315:303 */       this.labrIpaDao.setAutoCommit(Boolean.valueOf(true));
/* 316:304 */       e.printStackTrace();
/* 317:305 */       throw new Exception("受理編號：" + chlDataNo + "於儲存時出錯，原因：" + e.getMessage());
/* 318:    */     }
/* 319:308 */     this.labrIpaDao.setAutoCommit(Boolean.valueOf(true));
/* 320:    */   }
/* 321:    */   
/* 322:    */   private void saveLabrIpaMain(IpaLabrMain labrIpaMain)
/* 323:    */     throws SQLException
/* 324:    */   {
/* 325:313 */     Integer custNoI = getAndSetCustNo(labrIpaMain.getIpaCustI());
/* 326:314 */     Integer custNoA = getAndSetCustNo(labrIpaMain.getIpaCustA());
/* 327:315 */     Integer custNoP = getAndSetCustNo(labrIpaMain.getIpaCustP());
/* 328:    */     
/* 329:317 */     String dataId = this.labrIpaDao.getDataId();
/* 330:    */     
/* 331:319 */     labrIpaMain.getAppMTb().setDataId(dataId);
/* 332:320 */     labrIpaMain.getAppMTb().setInsuredCustNo(custNoI);
/* 333:321 */     labrIpaMain.getAppMTb().setApplicantCustNo(custNoA);
/* 334:322 */     labrIpaMain.getAppMTb().setPayerCustNo(custNoP);
/* 335:323 */     this.labrIpaDao.insert(labrIpaMain.getAppMTb(), "app_m_tb");
/* 336:    */     
/* 337:325 */     labrIpaMain.getAppIpaMTb().setDataId(dataId);
/* 338:326 */     labrIpaMain.getAppIpaMTb().setDataIdVerno(Integer.valueOf(1));
/* 339:327 */     this.labrIpaDao.insert(labrIpaMain.getAppIpaMTb(), "app_ipa_m_tb");
/* 340:    */     
/* 341:329 */     labrIpaMain.getIpaCustI().getId().setDataId(dataId);
/* 342:330 */     labrIpaMain.getIpaCustI().getId().setDataIdVerNo(Integer.valueOf(1));
/* 343:331 */     this.labrIpaDao.insert(labrIpaMain.getIpaCustI(), "cust_detail_tb");
/* 344:    */     
/* 345:333 */     labrIpaMain.getIpaCustA().getId().setDataId(dataId);
/* 346:334 */     labrIpaMain.getIpaCustA().getId().setDataIdVerNo(Integer.valueOf(1));
/* 347:335 */     this.labrIpaDao.insert(labrIpaMain.getIpaCustA(), "cust_detail_tb");
/* 348:    */     
/* 349:337 */     labrIpaMain.getIpaCustP().getId().setDataId(dataId);
/* 350:338 */     labrIpaMain.getIpaCustP().getId().setDataIdVerNo(Integer.valueOf(1));
/* 351:339 */     this.labrIpaDao.insert(labrIpaMain.getIpaCustP(), "cust_detail_tb");
/* 352:    */   }
/* 353:    */   
/* 354:    */   private Integer getAndSetCustNo(CustDetailTb custDetailTb)
/* 355:    */     throws SQLException
/* 356:    */   {
/* 357:343 */     String custId = custDetailTb.getCustID();
/* 358:    */     
/* 359:    */ 
/* 360:346 */     Vector<Object> paramsI = new Vector();
/* 361:347 */     paramsI.add(custId);
/* 362:348 */     CustMTb custMTb = (CustMTb)this.labrIpaDao.uniqueResult(CustMTb.class, "SELECT TOP 1 FROM cust_m_tb WHERE cust_id = ? ", paramsI);
/* 363:349 */     if (custMTb == null)
/* 364:    */     {
/* 365:350 */       custMTb = new CustMTb(custDetailTb);
/* 366:351 */       this.labrIpaDao.insert(custMTb, "cust_m_tb");
/* 367:352 */       custMTb = (CustMTb)this.labrIpaDao.uniqueResult(CustMTb.class, "SELECT TOP 1 FROM cust_m_tb WHERE cust_id = ? ", paramsI);
/* 368:    */     }
/* 369:355 */     Integer custNo = Integer.valueOf(custMTb.getCustNo());
/* 370:356 */     custDetailTb.getId().setCustNo(custNo);
/* 371:    */     
/* 372:358 */     return custNo;
/* 373:    */   }
/* 374:    */   
/* 375:    */   private Map<String, String> getPayerInsuredRelCdMap()
/* 376:    */   {
/* 377:363 */     if (payerInsuredRelCdMap == null)
/* 378:    */     {
/* 379:374 */       payerInsuredRelCdMap = new HashMap();
/* 380:    */       
/* 381:376 */       payerInsuredRelCdMap.put("0", "01");
/* 382:377 */       payerInsuredRelCdMap.put("1", "02");
/* 383:378 */       payerInsuredRelCdMap.put("2", "04");
/* 384:379 */       payerInsuredRelCdMap.put("3", "04");
/* 385:380 */       payerInsuredRelCdMap.put("4", "03");
/* 386:381 */       payerInsuredRelCdMap.put("5", "12");
/* 387:382 */       payerInsuredRelCdMap.put("6", "00");
/* 388:383 */       payerInsuredRelCdMap.put("7", "00");
/* 389:384 */       payerInsuredRelCdMap.put("8", "05");
/* 390:385 */       payerInsuredRelCdMap.put("9", "00");
/* 391:386 */       payerInsuredRelCdMap.put("10", "00");
/* 392:    */     }
/* 393:389 */     return payerInsuredRelCdMap;
/* 394:    */   }
/* 395:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.service.IpaLabrService
 * JD-Core Version:    0.7.0.1
 */