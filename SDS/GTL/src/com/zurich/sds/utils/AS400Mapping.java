/*  1:   */ package com.zurich.sds.utils;
/*  2:   */ 
/*  3:   */ import java.lang.reflect.Field;
/*  4:   */ import java.lang.reflect.Method;
/*  5:   */ import java.util.Map;
/*  6:   */ 
/*  7:   */ public class AS400Mapping
/*  8:   */ {
/*  9:   */   private Map<String, Method> setMethodMap;
/* 10:   */   private Map<Integer, Field> fieldOrderMap;
/* 11:   */   private Integer totalLength;
/* 12:   */   
/* 13:   */   public Map<String, Method> getSetMethodMap()
/* 14:   */   {
/* 15:15 */     return this.setMethodMap;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void setSetMethodMap(Map<String, Method> setMethodMap)
/* 19:   */   {
/* 20:19 */     this.setMethodMap = setMethodMap;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public Map<Integer, Field> getFieldOrderMap()
/* 24:   */   {
/* 25:23 */     return this.fieldOrderMap;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void setFieldOrderMap(Map<Integer, Field> fieldOrderMap)
/* 29:   */   {
/* 30:27 */     this.fieldOrderMap = fieldOrderMap;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public Integer getTotalLength()
/* 34:   */   {
/* 35:31 */     return this.totalLength;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void setTotalLength(Integer totalLength)
/* 39:   */   {
/* 40:35 */     this.totalLength = totalLength;
/* 41:   */   }
/* 42:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.utils.AS400Mapping
 * JD-Core Version:    0.7.0.1
 */