/*   1:    */ package com.zurich.sds.lawbroker.module;
/*   2:    */ 
/*   3:    */ import java.sql.Date;
/*   4:    */ import javax.persistence.Column;
/*   5:    */ 
/*   6:    */ public class InsuredRoster
/*   7:    */ {
/*   8:    */   @Column(name="policy_no")
/*   9:    */   private String policyNo;
/*  10:    */   @Column(name="cust_nm_a")
/*  11:    */   private String custNmA;
/*  12:    */   @Column(name="cust_nm_i")
/*  13:    */   private String custNmI;
/*  14:    */   @Column(name="cust_id")
/*  15:    */   private String custId;
/*  16:    */   @Column(name="cust_bth")
/*  17:    */   private Date custBth;
/*  18:    */   @Column(name="cust_rel")
/*  19:    */   private String custRel;
/*  20:    */   @Column(name="ins_eff_dt")
/*  21:    */   private Date insEffDt;
/*  22:    */   @Column(name="ins_exp_dt")
/*  23:    */   private Date insExpDt;
/*  24:    */   @Column(name="risk_amt_09")
/*  25:    */   private Integer riskAmt09;
/*  26:    */   @Column(name="risk_amt_10")
/*  27:    */   private Integer riskAmt10;
/*  28:    */   @Column(name="risk_amt_11_12")
/*  29:    */   private Integer riskAmt11_12;
/*  30:    */   @Column(name="tour_days")
/*  31:    */   private Integer tourDays;
/*  32:    */   @Column(name="per_age_cnt")
/*  33:    */   private Integer perAgeCnt;
/*  34:    */   @Column(name="per_prm")
/*  35:    */   private Integer perPrm;
/*  36:    */   @Column(name="tourplace_desc")
/*  37:    */   private String tourplaceDesc;
/*  38:    */   @Column(name="income_dt")
/*  39:    */   private Date incomeDt;
/*  40:    */   @Column(name="benef_nm")
/*  41:    */   private String benefNm;
/*  42:    */   @Column(name="ben_rel")
/*  43:    */   private String benRel;
/*  44:    */   @Column(name="ins_eff_tm")
/*  45:    */   private String insEffTm;
/*  46:    */   @Column(name="income_id")
/*  47:    */   private String incomeId;
/*  48:    */   @Column(name="chl_cd")
/*  49:    */   private String chlCd;
/*  50:    */   @Column(name="data_id")
/*  51:    */   private String dataId;
/*  52:    */   @Column(name="data_id_verno")
/*  53:    */   private Integer dataIdVerno;
/*  54:    */   @Column(name="recpt_no")
/*  55:    */   private String recptNo;
/*  56:    */   
/*  57:    */   public String getPolicyNo()
/*  58:    */   {
/*  59: 81 */     return this.policyNo;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void setPolicyNo(String policyNo)
/*  63:    */   {
/*  64: 85 */     this.policyNo = policyNo;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public String getCustNmA()
/*  68:    */   {
/*  69: 89 */     return this.custNmA;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void setCustNmA(String custNmA)
/*  73:    */   {
/*  74: 93 */     this.custNmA = custNmA;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public String getCustNmI()
/*  78:    */   {
/*  79: 97 */     return this.custNmI;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void setCustNmI(String custNmI)
/*  83:    */   {
/*  84:101 */     this.custNmI = custNmI;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public String getCustId()
/*  88:    */   {
/*  89:105 */     return this.custId;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void setCustId(String custId)
/*  93:    */   {
/*  94:109 */     this.custId = custId;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public Date getCustBth()
/*  98:    */   {
/*  99:113 */     return this.custBth;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void setCustBth(Date custBth)
/* 103:    */   {
/* 104:117 */     this.custBth = custBth;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public String getCustRel()
/* 108:    */   {
/* 109:121 */     return this.custRel;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void setCustRel(String custRel)
/* 113:    */   {
/* 114:125 */     this.custRel = custRel;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public Date getInsEffDt()
/* 118:    */   {
/* 119:129 */     return this.insEffDt;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public void setInsEffDt(Date insEffDt)
/* 123:    */   {
/* 124:133 */     this.insEffDt = insEffDt;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public Date getInsExpDt()
/* 128:    */   {
/* 129:137 */     return this.insExpDt;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void setInsExpDt(Date insExpDt)
/* 133:    */   {
/* 134:141 */     this.insExpDt = insExpDt;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public Integer getRiskAmt09()
/* 138:    */   {
/* 139:145 */     return this.riskAmt09;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void setRiskAmt09(Integer riskAmt09)
/* 143:    */   {
/* 144:149 */     this.riskAmt09 = riskAmt09;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public Integer getRiskAmt10()
/* 148:    */   {
/* 149:153 */     return this.riskAmt10;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public void setRiskAmt10(Integer riskAmt10)
/* 153:    */   {
/* 154:157 */     this.riskAmt10 = riskAmt10;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public Integer getRiskAmt11_12()
/* 158:    */   {
/* 159:161 */     return this.riskAmt11_12;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void setRiskAmt11_12(Integer riskAmt11_12)
/* 163:    */   {
/* 164:165 */     this.riskAmt11_12 = riskAmt11_12;
/* 165:    */   }
/* 166:    */   
/* 167:    */   public Integer getTourDays()
/* 168:    */   {
/* 169:169 */     return this.tourDays;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public void setTourDays(Integer tourDays)
/* 173:    */   {
/* 174:173 */     this.tourDays = tourDays;
/* 175:    */   }
/* 176:    */   
/* 177:    */   public Integer getPerAgeCnt()
/* 178:    */   {
/* 179:177 */     return this.perAgeCnt;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public void setPerAgeCnt(Integer perAgeCnt)
/* 183:    */   {
/* 184:181 */     this.perAgeCnt = perAgeCnt;
/* 185:    */   }
/* 186:    */   
/* 187:    */   public Integer getPerPrm()
/* 188:    */   {
/* 189:185 */     return this.perPrm;
/* 190:    */   }
/* 191:    */   
/* 192:    */   public void setPerPrm(Integer perPrm)
/* 193:    */   {
/* 194:189 */     this.perPrm = perPrm;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public String getTourplaceDesc()
/* 198:    */   {
/* 199:193 */     return this.tourplaceDesc;
/* 200:    */   }
/* 201:    */   
/* 202:    */   public void setTourplaceDesc(String tourplaceDesc)
/* 203:    */   {
/* 204:197 */     this.tourplaceDesc = tourplaceDesc;
/* 205:    */   }
/* 206:    */   
/* 207:    */   public Date getIncomeDt()
/* 208:    */   {
/* 209:201 */     return this.incomeDt;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public void setIncomeDt(Date incomeDt)
/* 213:    */   {
/* 214:205 */     this.incomeDt = incomeDt;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public String getBenefNm()
/* 218:    */   {
/* 219:209 */     return this.benefNm;
/* 220:    */   }
/* 221:    */   
/* 222:    */   public void setBenefNm(String benefNm)
/* 223:    */   {
/* 224:213 */     this.benefNm = benefNm;
/* 225:    */   }
/* 226:    */   
/* 227:    */   public String getBenRel()
/* 228:    */   {
/* 229:217 */     return this.benRel;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public void setBenRel(String benRel)
/* 233:    */   {
/* 234:221 */     this.benRel = benRel;
/* 235:    */   }
/* 236:    */   
/* 237:    */   public String getInsEffTm()
/* 238:    */   {
/* 239:225 */     return this.insEffTm;
/* 240:    */   }
/* 241:    */   
/* 242:    */   public void setInsEffTm(String insEffTm)
/* 243:    */   {
/* 244:229 */     this.insEffTm = insEffTm;
/* 245:    */   }
/* 246:    */   
/* 247:    */   public String getIncomeId()
/* 248:    */   {
/* 249:233 */     return this.incomeId;
/* 250:    */   }
/* 251:    */   
/* 252:    */   public void setIncomeId(String incomeId)
/* 253:    */   {
/* 254:237 */     this.incomeId = incomeId;
/* 255:    */   }
/* 256:    */   
/* 257:    */   public String getChlCd()
/* 258:    */   {
/* 259:241 */     return this.chlCd;
/* 260:    */   }
/* 261:    */   
/* 262:    */   public void setChlCd(String chlCd)
/* 263:    */   {
/* 264:245 */     this.chlCd = chlCd;
/* 265:    */   }
/* 266:    */   
/* 267:    */   public String getDataId()
/* 268:    */   {
/* 269:249 */     return this.dataId;
/* 270:    */   }
/* 271:    */   
/* 272:    */   public void setDataId(String dataId)
/* 273:    */   {
/* 274:253 */     this.dataId = dataId;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public Integer getDataIdVerno()
/* 278:    */   {
/* 279:257 */     return this.dataIdVerno;
/* 280:    */   }
/* 281:    */   
/* 282:    */   public void setDataIdVerno(Integer dataIdVerno)
/* 283:    */   {
/* 284:261 */     this.dataIdVerno = dataIdVerno;
/* 285:    */   }
/* 286:    */   
/* 287:    */   public String getRecptNo()
/* 288:    */   {
/* 289:265 */     return this.recptNo;
/* 290:    */   }
/* 291:    */   
/* 292:    */   public void setRecptNo(String recptNo)
/* 293:    */   {
/* 294:269 */     this.recptNo = recptNo;
/* 295:    */   }
/* 296:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.lawbroker.module.InsuredRoster
 * JD-Core Version:    0.7.0.1
 */