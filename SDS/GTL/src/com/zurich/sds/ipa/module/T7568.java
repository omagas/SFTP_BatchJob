/*  1:   */ package com.zurich.sds.ipa.module;
/*  2:   */ 
/*  3:   */ import com.zurich.sds.utils.AS400Element;
/*  4:   */ import java.util.Date;
/*  5:   */ 
/*  6:   */ public class T7568
/*  7:   */ {
/*  8:   */   @AS400Element(length=5, ordinalPosition=1)
/*  9:   */   private String table;
/* 10:   */   @AS400Element(length=8, ordinalPosition=2)
/* 11:   */   private String item;
/* 12:   */   @AS400Element(length=1, ordinalPosition=3)
/* 13:   */   private String flag;
/* 14:   */   @AS400Element(length=30, ordinalPosition=4)
/* 15:   */   private String longDesc;
/* 16:   */   @AS400Element(length=8, ordinalPosition=5)
/* 17:   */   private Date dateEff;
/* 18:   */   @AS400Element(length=8, ordinalPosition=6)
/* 19:   */   private Date dateEnd;
/* 20:   */   @AS400Element(length=60, ordinalPosition=7)
/* 21:   */   private String genPlne;
/* 22:   */   
/* 23:   */   public String getTable()
/* 24:   */   {
/* 25:37 */     return this.table;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void setTable(String table)
/* 29:   */   {
/* 30:41 */     this.table = table;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public String getItem()
/* 34:   */   {
/* 35:45 */     return this.item;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void setItem(String item)
/* 39:   */   {
/* 40:49 */     this.item = item;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public String getFlag()
/* 44:   */   {
/* 45:53 */     return this.flag;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void setFlag(String flag)
/* 49:   */   {
/* 50:57 */     this.flag = flag;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public String getLongDesc()
/* 54:   */   {
/* 55:61 */     return this.longDesc;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public void setLongDesc(String longDesc)
/* 59:   */   {
/* 60:65 */     this.longDesc = longDesc;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public Date getDateEff()
/* 64:   */   {
/* 65:69 */     return this.dateEff;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void setDateEff(Date dateEff)
/* 69:   */   {
/* 70:73 */     this.dateEff = dateEff;
/* 71:   */   }
/* 72:   */   
/* 73:   */   public Date getDateEnd()
/* 74:   */   {
/* 75:77 */     return this.dateEnd;
/* 76:   */   }
/* 77:   */   
/* 78:   */   public void setDateEnd(Date dateEnd)
/* 79:   */   {
/* 80:81 */     this.dateEnd = dateEnd;
/* 81:   */   }
/* 82:   */   
/* 83:   */   public String getGenPlne()
/* 84:   */   {
/* 85:85 */     return this.genPlne;
/* 86:   */   }
/* 87:   */   
/* 88:   */   public void setGenPlne(String genPlne)
/* 89:   */   {
/* 90:89 */     this.genPlne = genPlne;
/* 91:   */   }
/* 92:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.module.T7568
 * JD-Core Version:    0.7.0.1
 */