/*  1:   */ package com.zurich.sds.ipa.module;
/*  2:   */ 
/*  3:   */ import com.zurich.sds.utils.AS400Element;
/*  4:   */ 
/*  5:   */ public class ZJOBPF
/*  6:   */ {
/*  7:   */   @AS400Element(length=6, ordinalPosition=1)
/*  8:   */   private String table;
/*  9:   */   @AS400Element(length=8, ordinalPosition=2)
/* 10:   */   private String zJobCode;
/* 11:   */   @AS400Element(length=50, ordinalPosition=3)
/* 12:   */   private String zJobNam;
/* 13:   */   @AS400Element(length=1, ordinalPosition=4)
/* 14:   */   private String zJobType;
/* 15:   */   @AS400Element(length=30, ordinalPosition=5)
/* 16:   */   private String zJobLongdesc;
/* 17:   */   
/* 18:   */   public String getTable()
/* 19:   */   {
/* 20:27 */     return this.table;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void setTable(String table)
/* 24:   */   {
/* 25:31 */     this.table = table;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String getZJobCode()
/* 29:   */   {
/* 30:35 */     return this.zJobCode;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void setZJobCode(String zJobCode)
/* 34:   */   {
/* 35:39 */     this.zJobCode = zJobCode;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public String getZJobNam()
/* 39:   */   {
/* 40:43 */     return this.zJobNam;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void setZJobNam(String zJobNam)
/* 44:   */   {
/* 45:47 */     this.zJobNam = zJobNam;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public String getZJobType()
/* 49:   */   {
/* 50:51 */     return this.zJobType;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void setZJobType(String zJobType)
/* 54:   */   {
/* 55:55 */     this.zJobType = zJobType;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public String getZJobLongdesc()
/* 59:   */   {
/* 60:59 */     return this.zJobLongdesc;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public void setZJobLongdesc(String zJobLongdesc)
/* 64:   */   {
/* 65:63 */     this.zJobLongdesc = zJobLongdesc;
/* 66:   */   }
/* 67:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.module.ZJOBPF
 * JD-Core Version:    0.7.0.1
 */