/*  1:   */ package com.zurich.core.dao.utils;
/*  2:   */ 
/*  3:   */ public class EntityMapping
/*  4:   */ {
/*  5:   */   private String dbColumnName;
/*  6:   */   private String javaColumnName;
/*  7:   */   private Class<?> javaColumnClass;
/*  8:   */   private Boolean isGeneratedValueIdentity;
/*  9:   */   
/* 10:   */   public String getDbColumnName()
/* 11:   */   {
/* 12:14 */     return this.dbColumnName;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void setDbColumnName(String dbColumnName)
/* 16:   */   {
/* 17:18 */     this.dbColumnName = dbColumnName;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String getJavaColumnName()
/* 21:   */   {
/* 22:22 */     return this.javaColumnName;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void setJavaColumnName(String javaColumnName)
/* 26:   */   {
/* 27:26 */     this.javaColumnName = javaColumnName;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public Class<?> getJavaColumnClass()
/* 31:   */   {
/* 32:30 */     return this.javaColumnClass;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void setJavaColumnClass(Class<?> javaColumnClass)
/* 36:   */   {
/* 37:34 */     this.javaColumnClass = javaColumnClass;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public Boolean getIsGeneratedValueIdentity()
/* 41:   */   {
/* 42:38 */     return this.isGeneratedValueIdentity;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void setIsGeneratedValueIdentity(Boolean isGeneratedValueIdentity)
/* 46:   */   {
/* 47:42 */     this.isGeneratedValueIdentity = isGeneratedValueIdentity;
/* 48:   */   }
/* 49:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.core.dao.utils.EntityMapping
 * JD-Core Version:    0.7.0.1
 */