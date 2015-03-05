/*   1:    */ package com.zurich.sds.ipa.module;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.utils.AS400Element;
/*   4:    */ import java.util.Date;
/*   5:    */ 
/*   6:    */ public class T7039
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
/*  20:    */   @AS400Element(length=2, ordinalPosition=7)
/*  21:    */   private String branch;
/*  22:    */   @AS400Element(length=1, ordinalPosition=8)
/*  23:    */   private String zsbuCode;
/*  24:    */   @AS400Element(length=2, ordinalPosition=9)
/*  25:    */   private String zcollect;
/*  26:    */   @AS400Element(length=3, ordinalPosition=10)
/*  27:    */   private String allocstat;
/*  28:    */   @AS400Element(length=3, ordinalPosition=11)
/*  29:    */   private String target;
/*  30:    */   @AS400Element(length=8, ordinalPosition=12)
/*  31:    */   private String campan;
/*  32:    */   @AS400Element(length=2, ordinalPosition=13)
/*  33:    */   private String seqNo;
/*  34:    */   @AS400Element(length=30, ordinalPosition=14)
/*  35:    */   private String surName;
/*  36:    */   @AS400Element(length=5, ordinalPosition=15)
/*  37:    */   private Double surRate;
/*  38:    */   @AS400Element(length=20, ordinalPosition=16)
/*  39:    */   private String cDesc;
/*  40:    */   
/*  41:    */   public String getTable()
/*  42:    */   {
/*  43: 73 */     return this.table;
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void setTable(String table)
/*  47:    */   {
/*  48: 77 */     this.table = table;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public String getItem()
/*  52:    */   {
/*  53: 81 */     return this.item;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void setItem(String item)
/*  57:    */   {
/*  58: 85 */     this.item = item;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public String getFlag()
/*  62:    */   {
/*  63: 89 */     return this.flag;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void setFlag(String flag)
/*  67:    */   {
/*  68: 93 */     this.flag = flag;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public String getLongDesc()
/*  72:    */   {
/*  73: 97 */     return this.longDesc;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void setLongDesc(String longDesc)
/*  77:    */   {
/*  78:101 */     this.longDesc = longDesc;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public Date getDateEff()
/*  82:    */   {
/*  83:105 */     return this.dateEff;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void setDateEff(Date dateEff)
/*  87:    */   {
/*  88:109 */     this.dateEff = dateEff;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public Date getDateEnd()
/*  92:    */   {
/*  93:113 */     return this.dateEnd;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public void setDateEnd(Date dateEnd)
/*  97:    */   {
/*  98:117 */     this.dateEnd = dateEnd;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public String getBranch()
/* 102:    */   {
/* 103:121 */     return this.branch;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public void setBranch(String branch)
/* 107:    */   {
/* 108:125 */     this.branch = branch;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public String getZsbuCode()
/* 112:    */   {
/* 113:129 */     return this.zsbuCode;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public void setZsbuCode(String zsbuCode)
/* 117:    */   {
/* 118:133 */     this.zsbuCode = zsbuCode;
/* 119:    */   }
/* 120:    */   
/* 121:    */   public String getZcollect()
/* 122:    */   {
/* 123:137 */     return this.zcollect;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public void setZcollect(String zcollect)
/* 127:    */   {
/* 128:141 */     this.zcollect = zcollect;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public String getAllocstat()
/* 132:    */   {
/* 133:145 */     return this.allocstat;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public void setAllocstat(String allocstat)
/* 137:    */   {
/* 138:149 */     this.allocstat = allocstat;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public String getTarget()
/* 142:    */   {
/* 143:153 */     return this.target;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public void setTarget(String target)
/* 147:    */   {
/* 148:157 */     this.target = target;
/* 149:    */   }
/* 150:    */   
/* 151:    */   public String getCampan()
/* 152:    */   {
/* 153:161 */     return this.campan;
/* 154:    */   }
/* 155:    */   
/* 156:    */   public void setCampan(String campan)
/* 157:    */   {
/* 158:165 */     this.campan = campan;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public String getSeqNo()
/* 162:    */   {
/* 163:169 */     return this.seqNo;
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void setSeqNo(String seqNo)
/* 167:    */   {
/* 168:173 */     this.seqNo = seqNo;
/* 169:    */   }
/* 170:    */   
/* 171:    */   public String getSurName()
/* 172:    */   {
/* 173:177 */     return this.surName;
/* 174:    */   }
/* 175:    */   
/* 176:    */   public void setSurName(String surName)
/* 177:    */   {
/* 178:181 */     this.surName = surName;
/* 179:    */   }
/* 180:    */   
/* 181:    */   public Double getSurRate()
/* 182:    */   {
/* 183:185 */     return this.surRate;
/* 184:    */   }
/* 185:    */   
/* 186:    */   public void setSurRate(Double surRate)
/* 187:    */   {
/* 188:189 */     this.surRate = surRate;
/* 189:    */   }
/* 190:    */   
/* 191:    */   public String getcDesc()
/* 192:    */   {
/* 193:193 */     return this.cDesc;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void setcDesc(String cDesc)
/* 197:    */   {
/* 198:197 */     this.cDesc = cDesc;
/* 199:    */   }
/* 200:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.module.T7039
 * JD-Core Version:    0.7.0.1
 */