/*   1:    */ package com.zurich.sds.ipa.module;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.utils.AS400Element;
/*   4:    */ import java.util.Date;
/*   5:    */ 
/*   6:    */ public class T7961
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
/*  20:    */   @AS400Element(length=9, ordinalPosition=7, riskId="C18")
/*  21:    */   private Integer insuranceCoverage01;
/*  22:    */   @AS400Element(length=9, ordinalPosition=8, riskId="C19")
/*  23:    */   private Integer insuranceCoverage02;
/*  24:    */   @AS400Element(length=9, ordinalPosition=9, riskId="C20")
/*  25:    */   private Integer insuranceCoverage03;
/*  26:    */   @AS400Element(length=9, ordinalPosition=10, riskId="C21")
/*  27:    */   private Integer insuranceCoverage04;
/*  28:    */   @AS400Element(length=9, ordinalPosition=11, riskId="C22")
/*  29:    */   private Integer insuranceCoverage05;
/*  30:    */   @AS400Element(length=9, ordinalPosition=12, riskId="C23")
/*  31:    */   private Integer insuranceCoverage06;
/*  32:    */   @AS400Element(length=9, ordinalPosition=13, riskId="C24")
/*  33:    */   private Integer insuranceCoverage07;
/*  34:    */   
/*  35:    */   public String getTable()
/*  36:    */   {
/*  37: 61 */     return this.table;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void setTable(String table)
/*  41:    */   {
/*  42: 65 */     this.table = table;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public String getItem()
/*  46:    */   {
/*  47: 69 */     return this.item;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void setItem(String item)
/*  51:    */   {
/*  52: 73 */     this.item = item;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public String getFlag()
/*  56:    */   {
/*  57: 77 */     return this.flag;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void setFlag(String flag)
/*  61:    */   {
/*  62: 81 */     this.flag = flag;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public String getLongDesc()
/*  66:    */   {
/*  67: 85 */     return this.longDesc;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void setLongDesc(String longDesc)
/*  71:    */   {
/*  72: 89 */     this.longDesc = longDesc;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public Date getDateEff()
/*  76:    */   {
/*  77: 93 */     return this.dateEff;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void setDateEff(Date dateEff)
/*  81:    */   {
/*  82: 97 */     this.dateEff = dateEff;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public Date getDateEnd()
/*  86:    */   {
/*  87:101 */     return this.dateEnd;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void setDateEnd(Date dateEnd)
/*  91:    */   {
/*  92:105 */     this.dateEnd = dateEnd;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public Integer getInsuranceCoverage01()
/*  96:    */   {
/*  97:109 */     return this.insuranceCoverage01;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void setInsuranceCoverage01(Integer insuranceCoverage01)
/* 101:    */   {
/* 102:113 */     this.insuranceCoverage01 = insuranceCoverage01;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public Integer getInsuranceCoverage02()
/* 106:    */   {
/* 107:117 */     return this.insuranceCoverage02;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void setInsuranceCoverage02(Integer insuranceCoverage02)
/* 111:    */   {
/* 112:121 */     this.insuranceCoverage02 = insuranceCoverage02;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public Integer getInsuranceCoverage03()
/* 116:    */   {
/* 117:125 */     return this.insuranceCoverage03;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void setInsuranceCoverage03(Integer insuranceCoverage03)
/* 121:    */   {
/* 122:129 */     this.insuranceCoverage03 = insuranceCoverage03;
/* 123:    */   }
/* 124:    */   
/* 125:    */   public Integer getInsuranceCoverage04()
/* 126:    */   {
/* 127:133 */     return this.insuranceCoverage04;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public void setInsuranceCoverage04(Integer insuranceCoverage04)
/* 131:    */   {
/* 132:137 */     this.insuranceCoverage04 = insuranceCoverage04;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public Integer getInsuranceCoverage05()
/* 136:    */   {
/* 137:141 */     return this.insuranceCoverage05;
/* 138:    */   }
/* 139:    */   
/* 140:    */   public void setInsuranceCoverage05(Integer insuranceCoverage05)
/* 141:    */   {
/* 142:145 */     this.insuranceCoverage05 = insuranceCoverage05;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public Integer getInsuranceCoverage06()
/* 146:    */   {
/* 147:149 */     return this.insuranceCoverage06;
/* 148:    */   }
/* 149:    */   
/* 150:    */   public void setInsuranceCoverage06(Integer insuranceCoverage06)
/* 151:    */   {
/* 152:153 */     this.insuranceCoverage06 = insuranceCoverage06;
/* 153:    */   }
/* 154:    */   
/* 155:    */   public Integer getInsuranceCoverage07()
/* 156:    */   {
/* 157:157 */     return this.insuranceCoverage07;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public void setInsuranceCoverage07(Integer insuranceCoverage07)
/* 161:    */   {
/* 162:161 */     this.insuranceCoverage07 = insuranceCoverage07;
/* 163:    */   }
/* 164:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.module.T7961
 * JD-Core Version:    0.7.0.1
 */