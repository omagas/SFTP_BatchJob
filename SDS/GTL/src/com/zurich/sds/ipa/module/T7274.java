/*   1:    */ package com.zurich.sds.ipa.module;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.utils.AS400Element;
/*   4:    */ import java.util.Date;
/*   5:    */ 
/*   6:    */ public class T7274
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
/*  20:    */   @AS400Element(length=9, ordinalPosition=7, riskId="DH7")
/*  21:    */   private Integer insuranceCoverage01;
/*  22:    */   @AS400Element(length=9, ordinalPosition=8, riskId="DH8")
/*  23:    */   private Integer insuranceCoverage02;
/*  24:    */   @AS400Element(length=9, ordinalPosition=9, riskId="DH9")
/*  25:    */   private Integer insuranceCoverage03;
/*  26:    */   @AS400Element(length=9, ordinalPosition=10, riskId="D10")
/*  27:    */   private Integer insuranceCoverage04;
/*  28:    */   @AS400Element(length=9, ordinalPosition=11, riskId="D11")
/*  29:    */   private Integer insuranceCoverage05;
/*  30:    */   @AS400Element(length=9, ordinalPosition=12, riskId="D12")
/*  31:    */   private Integer insuranceCoverage06;
/*  32:    */   @AS400Element(length=9, ordinalPosition=13, riskId="D13")
/*  33:    */   private Integer insuranceCoverage07;
/*  34:    */   @AS400Element(length=9, ordinalPosition=14, riskId="D14")
/*  35:    */   private Integer insuranceCoverage08;
/*  36:    */   
/*  37:    */   public String getTable()
/*  38:    */   {
/*  39: 65 */     return this.table;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void setTable(String table)
/*  43:    */   {
/*  44: 69 */     this.table = table;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public String getItem()
/*  48:    */   {
/*  49: 73 */     return this.item;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void setItem(String item)
/*  53:    */   {
/*  54: 77 */     this.item = item;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public String getFlag()
/*  58:    */   {
/*  59: 81 */     return this.flag;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void setFlag(String flag)
/*  63:    */   {
/*  64: 85 */     this.flag = flag;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public String getLongDesc()
/*  68:    */   {
/*  69: 89 */     return this.longDesc;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void setLongDesc(String longDesc)
/*  73:    */   {
/*  74: 93 */     this.longDesc = longDesc;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public Date getDateEff()
/*  78:    */   {
/*  79: 97 */     return this.dateEff;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void setDateEff(Date dateEff)
/*  83:    */   {
/*  84:101 */     this.dateEff = dateEff;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public Date getDateEnd()
/*  88:    */   {
/*  89:105 */     return this.dateEnd;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void setDateEnd(Date dateEnd)
/*  93:    */   {
/*  94:109 */     this.dateEnd = dateEnd;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public Integer getInsuranceCoverage01()
/*  98:    */   {
/*  99:113 */     return this.insuranceCoverage01;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void setInsuranceCoverage01(Integer insuranceCoverage01)
/* 103:    */   {
/* 104:117 */     this.insuranceCoverage01 = insuranceCoverage01;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public Integer getInsuranceCoverage02()
/* 108:    */   {
/* 109:121 */     return this.insuranceCoverage02;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void setInsuranceCoverage02(Integer insuranceCoverage02)
/* 113:    */   {
/* 114:125 */     this.insuranceCoverage02 = insuranceCoverage02;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public Integer getInsuranceCoverage03()
/* 118:    */   {
/* 119:129 */     return this.insuranceCoverage03;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public void setInsuranceCoverage03(Integer insuranceCoverage03)
/* 123:    */   {
/* 124:133 */     this.insuranceCoverage03 = insuranceCoverage03;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public Integer getInsuranceCoverage04()
/* 128:    */   {
/* 129:137 */     return this.insuranceCoverage04;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void setInsuranceCoverage04(Integer insuranceCoverage04)
/* 133:    */   {
/* 134:141 */     this.insuranceCoverage04 = insuranceCoverage04;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public Integer getInsuranceCoverage05()
/* 138:    */   {
/* 139:145 */     return this.insuranceCoverage05;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void setInsuranceCoverage05(Integer insuranceCoverage05)
/* 143:    */   {
/* 144:149 */     this.insuranceCoverage05 = insuranceCoverage05;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public Integer getInsuranceCoverage06()
/* 148:    */   {
/* 149:153 */     return this.insuranceCoverage06;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public void setInsuranceCoverage06(Integer insuranceCoverage06)
/* 153:    */   {
/* 154:157 */     this.insuranceCoverage06 = insuranceCoverage06;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public Integer getInsuranceCoverage07()
/* 158:    */   {
/* 159:161 */     return this.insuranceCoverage07;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void setInsuranceCoverage07(Integer insuranceCoverage07)
/* 163:    */   {
/* 164:165 */     this.insuranceCoverage07 = insuranceCoverage07;
/* 165:    */   }
/* 166:    */   
/* 167:    */   public Integer getInsuranceCoverage08()
/* 168:    */   {
/* 169:169 */     return this.insuranceCoverage08;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public void setInsuranceCoverage08(Integer insuranceCoverage08)
/* 173:    */   {
/* 174:173 */     this.insuranceCoverage08 = insuranceCoverage08;
/* 175:    */   }
/* 176:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.module.T7274
 * JD-Core Version:    0.7.0.1
 */