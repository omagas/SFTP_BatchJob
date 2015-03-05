/*   1:    */ package com.zurich.sds.ipa.module;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.utils.AS400Element;
/*   4:    */ import java.util.Date;
/*   5:    */ 
/*   6:    */ public class T8046
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
/*  20:    */   @AS400Element(length=9, ordinalPosition=7)
/*  21:    */   private Integer insuranceCoverage01;
/*  22:    */   @AS400Element(length=9, ordinalPosition=8)
/*  23:    */   private Integer insuranceCoverage02;
/*  24:    */   
/*  25:    */   public String getTable()
/*  26:    */   {
/*  27: 41 */     return this.table;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public void setTable(String table)
/*  31:    */   {
/*  32: 45 */     this.table = table;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public String getItem()
/*  36:    */   {
/*  37: 49 */     return this.item;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void setItem(String item)
/*  41:    */   {
/*  42: 53 */     this.item = item;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public String getFlag()
/*  46:    */   {
/*  47: 57 */     return this.flag;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void setFlag(String flag)
/*  51:    */   {
/*  52: 61 */     this.flag = flag;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public String getLongDesc()
/*  56:    */   {
/*  57: 65 */     return this.longDesc;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void setLongDesc(String longDesc)
/*  61:    */   {
/*  62: 69 */     this.longDesc = longDesc;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public Date getDateEff()
/*  66:    */   {
/*  67: 73 */     return this.dateEff;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void setDateEff(Date dateEff)
/*  71:    */   {
/*  72: 77 */     this.dateEff = dateEff;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public Date getDateEnd()
/*  76:    */   {
/*  77: 81 */     return this.dateEnd;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void setDateEnd(Date dateEnd)
/*  81:    */   {
/*  82: 85 */     this.dateEnd = dateEnd;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public Integer getInsuranceCoverage01()
/*  86:    */   {
/*  87: 89 */     return this.insuranceCoverage01;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void setInsuranceCoverage01(Integer insuranceCoverage01)
/*  91:    */   {
/*  92: 93 */     this.insuranceCoverage01 = insuranceCoverage01;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public Integer getInsuranceCoverage02()
/*  96:    */   {
/*  97: 97 */     return this.insuranceCoverage02;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void setInsuranceCoverage02(Integer insuranceCoverage02)
/* 101:    */   {
/* 102:101 */     this.insuranceCoverage02 = insuranceCoverage02;
/* 103:    */   }
/* 104:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.module.T8046
 * JD-Core Version:    0.7.0.1
 */