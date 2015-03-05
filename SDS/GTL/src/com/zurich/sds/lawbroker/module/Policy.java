/*   1:    */ package com.zurich.sds.lawbroker.module;
/*   2:    */ 
/*   3:    */ import java.sql.Date;
/*   4:    */ import javax.persistence.Column;
/*   5:    */ 
/*   6:    */ public class Policy
/*   7:    */ {
/*   8:    */   @Column(name="policy_no")
/*   9:    */   private String policyNo;
/*  10:    */   @Column(name="income_id")
/*  11:    */   private String incomeId;
/*  12:    */   @Column(name="cmpgn_nm")
/*  13:    */   private String cmpgnNm;
/*  14:    */   @Column(name="cust_nm")
/*  15:    */   private String custNm;
/*  16:    */   @Column(name="cust_bth")
/*  17:    */   private Date custBth;
/*  18:    */   @Column(name="cust_id")
/*  19:    */   private String custId;
/*  20:    */   @Column(name="cust_mobile")
/*  21:    */   private String custMobile;
/*  22:    */   @Column(name="cust_zip_cd")
/*  23:    */   private String custZipCd;
/*  24:    */   @Column(name="cust_adrs")
/*  25:    */   private String custAdrs;
/*  26:    */   @Column(name="ins_eff_dt")
/*  27:    */   private Date insEffDt;
/*  28:    */   @Column(name="ins_eff_tm")
/*  29:    */   private String insEffTm;
/*  30:    */   @Column(name="ins_exp_dt")
/*  31:    */   private Date insExpDt;
/*  32:    */   @Column(name="income_dt")
/*  33:    */   private Date incomeDt;
/*  34:    */   @Column(name="tour_days")
/*  35:    */   private Integer tourDays;
/*  36:    */   @Column(name="tourcust_cnt")
/*  37:    */   private Integer tourcustCnt;
/*  38:    */   @Column(name="tot_prm")
/*  39:    */   private Integer totPrm;
/*  40:    */   @Column(name="tourplace_desc")
/*  41:    */   private String tourplaceDesc;
/*  42:    */   @Column(name="data_id")
/*  43:    */   private String dataId;
/*  44:    */   @Column(name="data_id_verno")
/*  45:    */   private Integer dataIdVerno;
/*  46:    */   @Column(name="recpt_no")
/*  47:    */   private String recptNo;
/*  48:    */   
/*  49:    */   public String getPolicyNo()
/*  50:    */   {
/*  51: 86 */     return this.policyNo;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void setPolicyNo(String policyNo)
/*  55:    */   {
/*  56: 89 */     this.policyNo = policyNo;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public String getIncomeId()
/*  60:    */   {
/*  61: 92 */     return this.incomeId;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void setIncomeId(String incomeId)
/*  65:    */   {
/*  66: 95 */     this.incomeId = incomeId;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public String getCmpgnNm()
/*  70:    */   {
/*  71: 98 */     return this.cmpgnNm;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void setCmpgnNm(String cmpgnNm)
/*  75:    */   {
/*  76:101 */     this.cmpgnNm = cmpgnNm;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public String getCustNm()
/*  80:    */   {
/*  81:104 */     return this.custNm;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void setCustNm(String custNm)
/*  85:    */   {
/*  86:107 */     this.custNm = custNm;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public Date getCustBth()
/*  90:    */   {
/*  91:110 */     return this.custBth;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void setCustBth(Date custBth)
/*  95:    */   {
/*  96:113 */     this.custBth = custBth;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public String getCustId()
/* 100:    */   {
/* 101:116 */     return this.custId;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void setCustId(String custId)
/* 105:    */   {
/* 106:119 */     this.custId = custId;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public String getCustMobile()
/* 110:    */   {
/* 111:122 */     return this.custMobile;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setCustMobile(String custMobile)
/* 115:    */   {
/* 116:125 */     this.custMobile = custMobile;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public String getCustZipCd()
/* 120:    */   {
/* 121:128 */     return this.custZipCd;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public void setCustZipCd(String custZipCd)
/* 125:    */   {
/* 126:131 */     this.custZipCd = custZipCd;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public String getCustAdrs()
/* 130:    */   {
/* 131:134 */     return this.custAdrs;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public void setCustAdrs(String custAdrs)
/* 135:    */   {
/* 136:137 */     this.custAdrs = custAdrs;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public Date getInsEffDt()
/* 140:    */   {
/* 141:140 */     return this.insEffDt;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void setInsEffDt(Date insEffDt)
/* 145:    */   {
/* 146:143 */     this.insEffDt = insEffDt;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public String getInsEffTm()
/* 150:    */   {
/* 151:146 */     return this.insEffTm;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void setInsEffTm(String insEffTm)
/* 155:    */   {
/* 156:149 */     this.insEffTm = insEffTm;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public Date getInsExpDt()
/* 160:    */   {
/* 161:152 */     return this.insExpDt;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public void setInsExpDt(Date insExpDt)
/* 165:    */   {
/* 166:155 */     this.insExpDt = insExpDt;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public Date getIncomeDt()
/* 170:    */   {
/* 171:158 */     return this.incomeDt;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public void setIncomeDt(Date incomeDt)
/* 175:    */   {
/* 176:161 */     this.incomeDt = incomeDt;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public Integer getTourDays()
/* 180:    */   {
/* 181:164 */     return this.tourDays;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void setTourDays(Integer tourDays)
/* 185:    */   {
/* 186:167 */     this.tourDays = tourDays;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public Integer getTourcustCnt()
/* 190:    */   {
/* 191:170 */     return this.tourcustCnt;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public void setTourcustCnt(Integer tourcustCnt)
/* 195:    */   {
/* 196:173 */     this.tourcustCnt = tourcustCnt;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public Integer getTotPrm()
/* 200:    */   {
/* 201:176 */     return this.totPrm;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void setTotPrm(Integer totPrm)
/* 205:    */   {
/* 206:179 */     this.totPrm = totPrm;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public String getTourplaceDesc()
/* 210:    */   {
/* 211:182 */     return this.tourplaceDesc;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public void setTourplaceDesc(String tourplaceDesc)
/* 215:    */   {
/* 216:185 */     this.tourplaceDesc = tourplaceDesc;
/* 217:    */   }
/* 218:    */   
/* 219:    */   public String getDataId()
/* 220:    */   {
/* 221:194 */     return this.dataId;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void setDataId(String dataId)
/* 225:    */   {
/* 226:197 */     this.dataId = dataId;
/* 227:    */   }
/* 228:    */   
/* 229:    */   public Integer getDataIdVerno()
/* 230:    */   {
/* 231:200 */     return this.dataIdVerno;
/* 232:    */   }
/* 233:    */   
/* 234:    */   public void setDataIdVerno(Integer dataIdVerno)
/* 235:    */   {
/* 236:203 */     this.dataIdVerno = dataIdVerno;
/* 237:    */   }
/* 238:    */   
/* 239:    */   public String getRecptNo()
/* 240:    */   {
/* 241:206 */     return this.recptNo;
/* 242:    */   }
/* 243:    */   
/* 244:    */   public void setRecptNo(String recptNo)
/* 245:    */   {
/* 246:209 */     this.recptNo = recptNo;
/* 247:    */   }
/* 248:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.lawbroker.module.Policy
 * JD-Core Version:    0.7.0.1
 */