/*   1:    */ package com.zurich.sds.ipa.module;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.utils.AS400Element;
/*   4:    */ import java.util.Date;
/*   5:    */ 
/*   6:    */ public class T7308
/*   7:    */ {
/*   8:    */   @AS400Element(length=5, ordinalPosition=1)
/*   9:    */   private String table;
/*  10:    */   @AS400Element(length=8, ordinalPosition=2)
/*  11:    */   private String item;
/*  12:    */   @AS400Element(length=1, ordinalPosition=3)
/*  13:    */   private String flag;
/*  14:    */   @AS400Element(length=30, ordinalPosition=4)
/*  15:    */   private String longDesc;
/*  16:    */   @AS400Element(length=8, ordinalPosition=5)
/*  17:    */   private Date dateEff;
/*  18:    */   @AS400Element(length=8, ordinalPosition=6)
/*  19:    */   private Date dateEnd;
/*  20:    */   @AS400Element(length=7, ordinalPosition=7)
/*  21:    */   private Integer totalPremium;
/*  22:    */   @AS400Element(length=9, ordinalPosition=8, riskId="ADD")
/*  23:    */   private Integer insuranceCoverage01;
/*  24:    */   @AS400Element(length=9, ordinalPosition=9, riskId="MR")
/*  25:    */   private Integer insuranceCoverage02;
/*  26:    */   @AS400Element(length=9, ordinalPosition=10, riskId="DH1")
/*  27:    */   private Integer insuranceCoverage03;
/*  28:    */   @AS400Element(length=9, ordinalPosition=11, riskId="DH2")
/*  29:    */   private Integer insuranceCoverage04;
/*  30:    */   @AS400Element(length=9, ordinalPosition=12, riskId="DH3")
/*  31:    */   private Integer insuranceCoverage05;
/*  32:    */   @AS400Element(length=9, ordinalPosition=13, riskId="DH4")
/*  33:    */   private Integer insuranceCoverage06;
/*  34:    */   @AS400Element(length=9, ordinalPosition=14, riskId="DH5")
/*  35:    */   private Integer insuranceCoverage07;
/*  36:    */   @AS400Element(length=9, ordinalPosition=15, riskId="DH6")
/*  37:    */   private Integer insuranceCoverage08;
/*  38:    */   @AS400Element(length=9, ordinalPosition=16, riskId="AX1")
/*  39:    */   private Integer insuranceCoverage09;
/*  40:    */   @AS400Element(length=9, ordinalPosition=17, riskId="AX2")
/*  41:    */   private Integer insuranceCoverage10;
/*  42:    */   @AS400Element(length=9, ordinalPosition=18, riskId="AX3")
/*  43:    */   private Integer insuranceCoverage11;
/*  44:    */   @AS400Element(length=9, ordinalPosition=19, riskId="AX4")
/*  45:    */   private Integer insuranceCoverage12;
/*  46:    */   @AS400Element(length=9, ordinalPosition=20, riskId="AX5")
/*  47:    */   private Integer insuranceCoverage13;
/*  48:    */   @AS400Element(length=9, ordinalPosition=21, riskId="AX6")
/*  49:    */   private Integer insuranceCoverage14;
/*  50:    */   @AS400Element(length=9, ordinalPosition=22, riskId="AX7")
/*  51:    */   private Integer insuranceCoverage15;
/*  52:    */   @AS400Element(length=2, ordinalPosition=23)
/*  53:    */   private Integer newAgeMin;
/*  54:    */   @AS400Element(length=2, ordinalPosition=24)
/*  55:    */   private Integer newAgeMax;
/*  56:    */   @AS400Element(length=2, ordinalPosition=25)
/*  57:    */   private Integer renewalAgeMin;
/*  58:    */   @AS400Element(length=2, ordinalPosition=26)
/*  59:    */   private Integer renewalAgeMax;
/*  60:    */   @AS400Element(length=5, ordinalPosition=27)
/*  61:    */   private String otherHospitalTable;
/*  62:    */   @AS400Element(length=5, ordinalPosition=28)
/*  63:    */   private String additionalCoverageTable;
/*  64:    */   @AS400Element(length=5, ordinalPosition=29)
/*  65:    */   private String otherAdditionalTable;
/*  66:    */   @AS400Element(length=5, ordinalPosition=30)
/*  67:    */   private String doubleProtectionTable;
/*  68:    */   
/*  69:    */   public String getTable()
/*  70:    */   {
/*  71:129 */     return this.table;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void setTable(String table)
/*  75:    */   {
/*  76:133 */     this.table = table;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public String getItem()
/*  80:    */   {
/*  81:137 */     return this.item;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void setItem(String item)
/*  85:    */   {
/*  86:141 */     this.item = item;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public String getFlag()
/*  90:    */   {
/*  91:145 */     return this.flag;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void setFlag(String flag)
/*  95:    */   {
/*  96:149 */     this.flag = flag;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public String getLongDesc()
/* 100:    */   {
/* 101:153 */     return this.longDesc;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void setLongDesc(String longDesc)
/* 105:    */   {
/* 106:157 */     this.longDesc = longDesc;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public Date getDateEff()
/* 110:    */   {
/* 111:161 */     return this.dateEff;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setDateEff(Date dateEff)
/* 115:    */   {
/* 116:165 */     this.dateEff = dateEff;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public Date getDateEnd()
/* 120:    */   {
/* 121:169 */     return this.dateEnd;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public void setDateEnd(Date dateEnd)
/* 125:    */   {
/* 126:173 */     this.dateEnd = dateEnd;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public Integer getTotalPremium()
/* 130:    */   {
/* 131:177 */     return this.totalPremium;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public void setTotalPremium(Integer totalPremium)
/* 135:    */   {
/* 136:181 */     this.totalPremium = totalPremium;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public Integer getInsuranceCoverage01()
/* 140:    */   {
/* 141:185 */     return this.insuranceCoverage01;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void setInsuranceCoverage01(Integer insuranceCoverage01)
/* 145:    */   {
/* 146:189 */     this.insuranceCoverage01 = insuranceCoverage01;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public Integer getInsuranceCoverage02()
/* 150:    */   {
/* 151:193 */     return this.insuranceCoverage02;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void setInsuranceCoverage02(Integer insuranceCoverage02)
/* 155:    */   {
/* 156:197 */     this.insuranceCoverage02 = insuranceCoverage02;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public Integer getInsuranceCoverage03()
/* 160:    */   {
/* 161:201 */     return this.insuranceCoverage03;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public void setInsuranceCoverage03(Integer insuranceCoverage03)
/* 165:    */   {
/* 166:205 */     this.insuranceCoverage03 = insuranceCoverage03;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public Integer getInsuranceCoverage04()
/* 170:    */   {
/* 171:209 */     return this.insuranceCoverage04;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public void setInsuranceCoverage04(Integer insuranceCoverage04)
/* 175:    */   {
/* 176:213 */     this.insuranceCoverage04 = insuranceCoverage04;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public Integer getInsuranceCoverage05()
/* 180:    */   {
/* 181:217 */     return this.insuranceCoverage05;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void setInsuranceCoverage05(Integer insuranceCoverage05)
/* 185:    */   {
/* 186:221 */     this.insuranceCoverage05 = insuranceCoverage05;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public Integer getInsuranceCoverage06()
/* 190:    */   {
/* 191:225 */     return this.insuranceCoverage06;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public void setInsuranceCoverage06(Integer insuranceCoverage06)
/* 195:    */   {
/* 196:229 */     this.insuranceCoverage06 = insuranceCoverage06;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public Integer getInsuranceCoverage07()
/* 200:    */   {
/* 201:233 */     return this.insuranceCoverage07;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void setInsuranceCoverage07(Integer insuranceCoverage07)
/* 205:    */   {
/* 206:237 */     this.insuranceCoverage07 = insuranceCoverage07;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public Integer getInsuranceCoverage08()
/* 210:    */   {
/* 211:241 */     return this.insuranceCoverage08;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public void setInsuranceCoverage08(Integer insuranceCoverage08)
/* 215:    */   {
/* 216:245 */     this.insuranceCoverage08 = insuranceCoverage08;
/* 217:    */   }
/* 218:    */   
/* 219:    */   public Integer getInsuranceCoverage09()
/* 220:    */   {
/* 221:249 */     return this.insuranceCoverage09;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void setInsuranceCoverage09(Integer insuranceCoverage09)
/* 225:    */   {
/* 226:253 */     this.insuranceCoverage09 = insuranceCoverage09;
/* 227:    */   }
/* 228:    */   
/* 229:    */   public Integer getInsuranceCoverage10()
/* 230:    */   {
/* 231:257 */     return this.insuranceCoverage10;
/* 232:    */   }
/* 233:    */   
/* 234:    */   public void setInsuranceCoverage10(Integer insuranceCoverage10)
/* 235:    */   {
/* 236:261 */     this.insuranceCoverage10 = insuranceCoverage10;
/* 237:    */   }
/* 238:    */   
/* 239:    */   public Integer getInsuranceCoverage11()
/* 240:    */   {
/* 241:265 */     return this.insuranceCoverage11;
/* 242:    */   }
/* 243:    */   
/* 244:    */   public void setInsuranceCoverage11(Integer insuranceCoverage11)
/* 245:    */   {
/* 246:269 */     this.insuranceCoverage11 = insuranceCoverage11;
/* 247:    */   }
/* 248:    */   
/* 249:    */   public Integer getInsuranceCoverage12()
/* 250:    */   {
/* 251:273 */     return this.insuranceCoverage12;
/* 252:    */   }
/* 253:    */   
/* 254:    */   public void setInsuranceCoverage12(Integer insuranceCoverage12)
/* 255:    */   {
/* 256:277 */     this.insuranceCoverage12 = insuranceCoverage12;
/* 257:    */   }
/* 258:    */   
/* 259:    */   public Integer getInsuranceCoverage13()
/* 260:    */   {
/* 261:281 */     return this.insuranceCoverage13;
/* 262:    */   }
/* 263:    */   
/* 264:    */   public void setInsuranceCoverage13(Integer insuranceCoverage13)
/* 265:    */   {
/* 266:285 */     this.insuranceCoverage13 = insuranceCoverage13;
/* 267:    */   }
/* 268:    */   
/* 269:    */   public Integer getInsuranceCoverage14()
/* 270:    */   {
/* 271:289 */     return this.insuranceCoverage14;
/* 272:    */   }
/* 273:    */   
/* 274:    */   public void setInsuranceCoverage14(Integer insuranceCoverage14)
/* 275:    */   {
/* 276:293 */     this.insuranceCoverage14 = insuranceCoverage14;
/* 277:    */   }
/* 278:    */   
/* 279:    */   public Integer getInsuranceCoverage15()
/* 280:    */   {
/* 281:297 */     return this.insuranceCoverage15;
/* 282:    */   }
/* 283:    */   
/* 284:    */   public void setInsuranceCoverage15(Integer insuranceCoverage15)
/* 285:    */   {
/* 286:301 */     this.insuranceCoverage15 = insuranceCoverage15;
/* 287:    */   }
/* 288:    */   
/* 289:    */   public Integer getNewAgeMin()
/* 290:    */   {
/* 291:305 */     return this.newAgeMin;
/* 292:    */   }
/* 293:    */   
/* 294:    */   public void setNewAgeMin(Integer newAgeMin)
/* 295:    */   {
/* 296:309 */     this.newAgeMin = newAgeMin;
/* 297:    */   }
/* 298:    */   
/* 299:    */   public Integer getNewAgeMax()
/* 300:    */   {
/* 301:313 */     return this.newAgeMax;
/* 302:    */   }
/* 303:    */   
/* 304:    */   public void setNewAgeMax(Integer newAgeMax)
/* 305:    */   {
/* 306:317 */     this.newAgeMax = newAgeMax;
/* 307:    */   }
/* 308:    */   
/* 309:    */   public Integer getRenewalAgeMin()
/* 310:    */   {
/* 311:321 */     return this.renewalAgeMin;
/* 312:    */   }
/* 313:    */   
/* 314:    */   public void setRenewalAgeMin(Integer renewalAgeMin)
/* 315:    */   {
/* 316:325 */     this.renewalAgeMin = renewalAgeMin;
/* 317:    */   }
/* 318:    */   
/* 319:    */   public Integer getRenewalAgeMax()
/* 320:    */   {
/* 321:329 */     return this.renewalAgeMax;
/* 322:    */   }
/* 323:    */   
/* 324:    */   public void setRenewalAgeMax(Integer renewalAgeMax)
/* 325:    */   {
/* 326:333 */     this.renewalAgeMax = renewalAgeMax;
/* 327:    */   }
/* 328:    */   
/* 329:    */   public String getOtherHospitalTable()
/* 330:    */   {
/* 331:337 */     return this.otherHospitalTable;
/* 332:    */   }
/* 333:    */   
/* 334:    */   public void setOtherHospitalTable(String otherHospitalTable)
/* 335:    */   {
/* 336:341 */     this.otherHospitalTable = otherHospitalTable;
/* 337:    */   }
/* 338:    */   
/* 339:    */   public String getAdditionalCoverageTable()
/* 340:    */   {
/* 341:345 */     return this.additionalCoverageTable;
/* 342:    */   }
/* 343:    */   
/* 344:    */   public void setAdditionalCoverageTable(String additionalCoverageTable)
/* 345:    */   {
/* 346:349 */     this.additionalCoverageTable = additionalCoverageTable;
/* 347:    */   }
/* 348:    */   
/* 349:    */   public String getOtherAdditionalTable()
/* 350:    */   {
/* 351:353 */     return this.otherAdditionalTable;
/* 352:    */   }
/* 353:    */   
/* 354:    */   public void setOtherAdditionalTable(String otherAdditionalTable)
/* 355:    */   {
/* 356:357 */     this.otherAdditionalTable = otherAdditionalTable;
/* 357:    */   }
/* 358:    */   
/* 359:    */   public String getDoubleProtectionTable()
/* 360:    */   {
/* 361:361 */     return this.doubleProtectionTable;
/* 362:    */   }
/* 363:    */   
/* 364:    */   public void setDoubleProtectionTable(String doubleProtectionTable)
/* 365:    */   {
/* 366:365 */     this.doubleProtectionTable = doubleProtectionTable;
/* 367:    */   }
/* 368:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.module.T7308
 * JD-Core Version:    0.7.0.1
 */