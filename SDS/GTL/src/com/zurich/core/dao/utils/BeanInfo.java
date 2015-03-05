/*  1:   */ package com.zurich.core.dao.utils;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.HashMap;
/*  5:   */ import java.util.List;
/*  6:   */ import java.util.Map;
/*  7:   */ 
/*  8:   */ public class BeanInfo
/*  9:   */ {
/* 10:   */   private Class<?> embeddedIdClass;
/* 11:   */   private String embeddedIdFieldName;
/* 12:14 */   private List<EntityMapping> columnList = new ArrayList();
/* 13:16 */   private List<EntityMapping> primaryKeyList = new ArrayList();
/* 14:   */   private Map<String, EntityMapping> columnJavaToDb;
/* 15:   */   private Map<String, EntityMapping> columnDbToJava;
/* 16:   */   private Map<String, EntityMapping> primaryKeyJavaToDb;
/* 17:   */   private Map<String, EntityMapping> primaryKeyDbToJava;
/* 18:   */   
/* 19:   */   public Class<?> getEmbeddedIdClass()
/* 20:   */   {
/* 21:27 */     return this.embeddedIdClass;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void setEmbeddedIdClass(Class<?> embeddedIdClass)
/* 25:   */   {
/* 26:31 */     this.embeddedIdClass = embeddedIdClass;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public String getEmbeddedIdFieldName()
/* 30:   */   {
/* 31:35 */     return this.embeddedIdFieldName;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void setEmbeddedIdFieldName(String embeddedIdFieldName)
/* 35:   */   {
/* 36:39 */     this.embeddedIdFieldName = embeddedIdFieldName;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public List<EntityMapping> getColumnList()
/* 40:   */   {
/* 41:43 */     return this.columnList;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public List<EntityMapping> getPrimaryKeyList()
/* 45:   */   {
/* 46:47 */     return this.primaryKeyList;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void setPrimaryKeyList(List<EntityMapping> primaryKeyList)
/* 50:   */   {
/* 51:51 */     this.primaryKeyList = primaryKeyList;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public Map<String, EntityMapping> getColumnJavaToDb()
/* 55:   */   {
/* 56:55 */     if (this.columnJavaToDb == null)
/* 57:   */     {
/* 58:56 */       this.columnJavaToDb = new HashMap();
/* 59:57 */       for (EntityMapping mapping : this.columnList) {
/* 60:58 */         this.columnJavaToDb.put(mapping.getJavaColumnName(), mapping);
/* 61:   */       }
/* 62:   */     }
/* 63:61 */     return this.columnJavaToDb;
/* 64:   */   }
/* 65:   */   
/* 66:   */   public Map<String, EntityMapping> getColumnDbToJava()
/* 67:   */   {
/* 68:65 */     if (this.columnDbToJava == null)
/* 69:   */     {
/* 70:66 */       this.columnDbToJava = new HashMap();
/* 71:67 */       for (EntityMapping mapping : this.columnList) {
/* 72:68 */         this.columnDbToJava.put(mapping.getDbColumnName(), mapping);
/* 73:   */       }
/* 74:   */     }
/* 75:71 */     return this.columnDbToJava;
/* 76:   */   }
/* 77:   */   
/* 78:   */   public Map<String, EntityMapping> getPrimaryKeyJavaToDb()
/* 79:   */   {
/* 80:75 */     if (this.primaryKeyJavaToDb == null)
/* 81:   */     {
/* 82:76 */       this.primaryKeyJavaToDb = new HashMap();
/* 83:77 */       for (EntityMapping mapping : this.primaryKeyList) {
/* 84:78 */         this.primaryKeyJavaToDb.put(mapping.getJavaColumnName(), mapping);
/* 85:   */       }
/* 86:   */     }
/* 87:81 */     return this.primaryKeyJavaToDb;
/* 88:   */   }
/* 89:   */   
/* 90:   */   public Map<String, EntityMapping> getPrimaryKeyDbToJava()
/* 91:   */   {
/* 92:85 */     if (this.primaryKeyDbToJava == null)
/* 93:   */     {
/* 94:86 */       this.primaryKeyDbToJava = new HashMap();
/* 95:87 */       for (EntityMapping mapping : this.primaryKeyList) {
/* 96:88 */         this.primaryKeyDbToJava.put(mapping.getDbColumnName(), mapping);
/* 97:   */       }
/* 98:   */     }
/* 99:91 */     return this.primaryKeyDbToJava;
/* :0:   */   }
/* :1:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.core.dao.utils.BeanInfo
 * JD-Core Version:    0.7.0.1
 */