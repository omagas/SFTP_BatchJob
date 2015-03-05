/*   1:    */ package com.zurich.sds.ipa.module;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.utils.AS400Element;
/*   4:    */ import java.util.Date;
/*   5:    */ 
/*   6:    */ public class T9995
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
/*  20:    */   @AS400Element(length=9, ordinalPosition=7, riskId="C01")
/*  21:    */   private Integer insuranceCoverage01;
/*  22:    */   @AS400Element(length=9, ordinalPosition=8, riskId="C02")
/*  23:    */   private Integer insuranceCoverage02;
/*  24:    */   @AS400Element(length=9, ordinalPosition=9, riskId="C03")
/*  25:    */   private Integer insuranceCoverage03;
/*  26:    */   @AS400Element(length=9, ordinalPosition=10, riskId="C04")
/*  27:    */   private Integer insuranceCoverage04;
/*  28:    */   @AS400Element(length=9, ordinalPosition=11, riskId="C05")
/*  29:    */   private Integer insuranceCoverage05;
/*  30:    */   @AS400Element(length=9, ordinalPosition=12, riskId="C06")
/*  31:    */   private Integer insuranceCoverage06;
/*  32:    */   @AS400Element(length=9, ordinalPosition=13, riskId="C07")
/*  33:    */   private Integer insuranceCoverage07;
/*  34:    */   @AS400Element(length=9, ordinalPosition=14, riskId="C08")
/*  35:    */   private Integer insuranceCoverage08;
/*  36:    */   @AS400Element(length=9, ordinalPosition=15, riskId="C09")
/*  37:    */   private Integer insuranceCoverage09;
/*  38:    */   @AS400Element(length=9, ordinalPosition=16, riskId="C10")
/*  39:    */   private Integer insuranceCoverage10;
/*  40:    */   @AS400Element(length=9, ordinalPosition=17, riskId="C11")
/*  41:    */   private Integer insuranceCoverage11;
/*  42:    */   @AS400Element(length=9, ordinalPosition=18, riskId="C12")
/*  43:    */   private Integer insuranceCoverage12;
/*  44:    */   @AS400Element(length=9, ordinalPosition=19, riskId="C13")
/*  45:    */   private Integer insuranceCoverage13;
/*  46:    */   @AS400Element(length=9, ordinalPosition=20, riskId="C14")
/*  47:    */   private Integer insuranceCoverage14;
/*  48:    */   @AS400Element(length=9, ordinalPosition=21, riskId="C15")
/*  49:    */   private Integer insuranceCoverage15;
/*  50:    */   @AS400Element(length=9, ordinalPosition=22, riskId="C16")
/*  51:    */   private Integer insuranceCoverage16;
/*  52:    */   @AS400Element(length=9, ordinalPosition=23, riskId="C17")
/*  53:    */   private Integer insuranceCoverage17;
/*  54:    */   
/*  55:    */   public String getTable()
/*  56:    */   {
/*  57:101 */     return this.table;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void setTable(String table)
/*  61:    */   {
/*  62:105 */     this.table = table;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public String getItem()
/*  66:    */   {
/*  67:109 */     return this.item;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void setItem(String item)
/*  71:    */   {
/*  72:113 */     this.item = item;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public String getFlag()
/*  76:    */   {
/*  77:117 */     return this.flag;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void setFlag(String flag)
/*  81:    */   {
/*  82:121 */     this.flag = flag;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public String getLongDesc()
/*  86:    */   {
/*  87:125 */     return this.longDesc;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void setLongDesc(String longDesc)
/*  91:    */   {
/*  92:129 */     this.longDesc = longDesc;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public Date getDateEff()
/*  96:    */   {
/*  97:133 */     return this.dateEff;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void setDateEff(Date dateEff)
/* 101:    */   {
/* 102:137 */     this.dateEff = dateEff;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public Date getDateEnd()
/* 106:    */   {
/* 107:141 */     return this.dateEnd;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void setDateEnd(Date dateEnd)
/* 111:    */   {
/* 112:145 */     this.dateEnd = dateEnd;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public Integer getInsuranceCoverage01()
/* 116:    */   {
/* 117:149 */     return this.insuranceCoverage01;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void setInsuranceCoverage01(Integer insuranceCoverage01)
/* 121:    */   {
/* 122:153 */     this.insuranceCoverage01 = insuranceCoverage01;
/* 123:    */   }
/* 124:    */   
/* 125:    */   public Integer getInsuranceCoverage02()
/* 126:    */   {
/* 127:157 */     return this.insuranceCoverage02;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public void setInsuranceCoverage02(Integer insuranceCoverage02)
/* 131:    */   {
/* 132:161 */     this.insuranceCoverage02 = insuranceCoverage02;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public Integer getInsuranceCoverage03()
/* 136:    */   {
/* 137:165 */     return this.insuranceCoverage03;
/* 138:    */   }
/* 139:    */   
/* 140:    */   public void setInsuranceCoverage03(Integer insuranceCoverage03)
/* 141:    */   {
/* 142:169 */     this.insuranceCoverage03 = insuranceCoverage03;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public Integer getInsuranceCoverage04()
/* 146:    */   {
/* 147:173 */     return this.insuranceCoverage04;
/* 148:    */   }
/* 149:    */   
/* 150:    */   public void setInsuranceCoverage04(Integer insuranceCoverage04)
/* 151:    */   {
/* 152:177 */     this.insuranceCoverage04 = insuranceCoverage04;
/* 153:    */   }
/* 154:    */   
/* 155:    */   public Integer getInsuranceCoverage05()
/* 156:    */   {
/* 157:181 */     return this.insuranceCoverage05;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public void setInsuranceCoverage05(Integer insuranceCoverage05)
/* 161:    */   {
/* 162:185 */     this.insuranceCoverage05 = insuranceCoverage05;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public Integer getInsuranceCoverage06()
/* 166:    */   {
/* 167:189 */     return this.insuranceCoverage06;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void setInsuranceCoverage06(Integer insuranceCoverage06)
/* 171:    */   {
/* 172:193 */     this.insuranceCoverage06 = insuranceCoverage06;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public Integer getInsuranceCoverage07()
/* 176:    */   {
/* 177:197 */     return this.insuranceCoverage07;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public void setInsuranceCoverage07(Integer insuranceCoverage07)
/* 181:    */   {
/* 182:201 */     this.insuranceCoverage07 = insuranceCoverage07;
/* 183:    */   }
/* 184:    */   
/* 185:    */   public Integer getInsuranceCoverage08()
/* 186:    */   {
/* 187:205 */     return this.insuranceCoverage08;
/* 188:    */   }
/* 189:    */   
/* 190:    */   public void setInsuranceCoverage08(Integer insuranceCoverage08)
/* 191:    */   {
/* 192:209 */     this.insuranceCoverage08 = insuranceCoverage08;
/* 193:    */   }
/* 194:    */   
/* 195:    */   public Integer getInsuranceCoverage09()
/* 196:    */   {
/* 197:213 */     return this.insuranceCoverage09;
/* 198:    */   }
/* 199:    */   
/* 200:    */   public void setInsuranceCoverage09(Integer insuranceCoverage09)
/* 201:    */   {
/* 202:217 */     this.insuranceCoverage09 = insuranceCoverage09;
/* 203:    */   }
/* 204:    */   
/* 205:    */   public Integer getInsuranceCoverage10()
/* 206:    */   {
/* 207:221 */     return this.insuranceCoverage10;
/* 208:    */   }
/* 209:    */   
/* 210:    */   public void setInsuranceCoverage10(Integer insuranceCoverage10)
/* 211:    */   {
/* 212:225 */     this.insuranceCoverage10 = insuranceCoverage10;
/* 213:    */   }
/* 214:    */   
/* 215:    */   public Integer getInsuranceCoverage11()
/* 216:    */   {
/* 217:229 */     return this.insuranceCoverage11;
/* 218:    */   }
/* 219:    */   
/* 220:    */   public void setInsuranceCoverage11(Integer insuranceCoverage11)
/* 221:    */   {
/* 222:233 */     this.insuranceCoverage11 = insuranceCoverage11;
/* 223:    */   }
/* 224:    */   
/* 225:    */   public Integer getInsuranceCoverage12()
/* 226:    */   {
/* 227:237 */     return this.insuranceCoverage12;
/* 228:    */   }
/* 229:    */   
/* 230:    */   public void setInsuranceCoverage12(Integer insuranceCoverage12)
/* 231:    */   {
/* 232:241 */     this.insuranceCoverage12 = insuranceCoverage12;
/* 233:    */   }
/* 234:    */   
/* 235:    */   public Integer getInsuranceCoverage13()
/* 236:    */   {
/* 237:245 */     return this.insuranceCoverage13;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public void setInsuranceCoverage13(Integer insuranceCoverage13)
/* 241:    */   {
/* 242:249 */     this.insuranceCoverage13 = insuranceCoverage13;
/* 243:    */   }
/* 244:    */   
/* 245:    */   public Integer getInsuranceCoverage14()
/* 246:    */   {
/* 247:253 */     return this.insuranceCoverage14;
/* 248:    */   }
/* 249:    */   
/* 250:    */   public void setInsuranceCoverage14(Integer insuranceCoverage14)
/* 251:    */   {
/* 252:257 */     this.insuranceCoverage14 = insuranceCoverage14;
/* 253:    */   }
/* 254:    */   
/* 255:    */   public Integer getInsuranceCoverage15()
/* 256:    */   {
/* 257:261 */     return this.insuranceCoverage15;
/* 258:    */   }
/* 259:    */   
/* 260:    */   public void setInsuranceCoverage15(Integer insuranceCoverage15)
/* 261:    */   {
/* 262:265 */     this.insuranceCoverage15 = insuranceCoverage15;
/* 263:    */   }
/* 264:    */   
/* 265:    */   public Integer getInsuranceCoverage16()
/* 266:    */   {
/* 267:269 */     return this.insuranceCoverage16;
/* 268:    */   }
/* 269:    */   
/* 270:    */   public void setInsuranceCoverage16(Integer insuranceCoverage16)
/* 271:    */   {
/* 272:273 */     this.insuranceCoverage16 = insuranceCoverage16;
/* 273:    */   }
/* 274:    */   
/* 275:    */   public Integer getInsuranceCoverage17()
/* 276:    */   {
/* 277:277 */     return this.insuranceCoverage17;
/* 278:    */   }
/* 279:    */   
/* 280:    */   public void setInsuranceCoverage17(Integer insuranceCoverage17)
/* 281:    */   {
/* 282:281 */     this.insuranceCoverage17 = insuranceCoverage17;
/* 283:    */   }
/* 284:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.module.T9995
 * JD-Core Version:    0.7.0.1
 */