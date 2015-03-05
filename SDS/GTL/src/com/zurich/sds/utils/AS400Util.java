/*  1:   */ package com.zurich.sds.utils;
/*  2:   */ 
/*  3:   */ import java.lang.reflect.Field;
/*  4:   */ import java.lang.reflect.Method;
/*  5:   */ import java.text.SimpleDateFormat;
/*  6:   */ import java.util.Date;
/*  7:   */ import java.util.HashMap;
/*  8:   */ import java.util.Map;
/*  9:   */ 
/* 10:   */ public class AS400Util
/* 11:   */ {
/* 12:11 */   private static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
/* 13:12 */   private static Map<Class<?>, AS400Mapping> mapping = new HashMap();
/* 14:   */   
/* 15:   */   public static Object trans(Class<?> as400_Class, String as400_String)
/* 16:   */     throws Exception
/* 17:   */   {
/* 18:16 */     AS400Mapping as400Mapping = (AS400Mapping)mapping.get(as400_Class);
/* 19:17 */     if (as400Mapping == null)
/* 20:   */     {
/* 21:18 */       as400Mapping = genAS400Mapping(as400_Class);
/* 22:19 */       mapping.put(as400_Class, as400Mapping);
/* 23:   */     }
/* 24:23 */     Map<String, Method> methodMap = as400Mapping.getSetMethodMap();
/* 25:   */     
/* 26:25 */     Map<Integer, Field> fieldOrderMap = as400Mapping.getFieldOrderMap();
/* 27:27 */     if (as400Mapping.getTotalLength().intValue() != as400_String.length()) {
/* 28:28 */       throw new Exception(as400_Class.getSimpleName() + "定義欄位的總長度和txt不符");
/* 29:   */     }
/* 30:31 */     Object object = as400_Class.newInstance();
/* 31:32 */     for (Integer ordinalPosition : fieldOrderMap.keySet())
/* 32:   */     {
/* 33:33 */       Field field = (Field)fieldOrderMap.get(ordinalPosition);
/* 34:34 */       String fieldName = field.getName();
/* 35:   */       
/* 36:36 */       String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
/* 37:37 */       Integer length = Integer.valueOf(((AS400Element)field.getAnnotation(AS400Element.class)).length());
/* 38:   */       
/* 39:39 */       String value = as400_String.substring(0, length.intValue()).trim();
/* 40:40 */       as400_String = as400_String.substring(length.intValue(), as400_String.length());
/* 41:   */       
/* 42:42 */       Method setMethod = (Method)methodMap.get(setMethodName);
/* 43:43 */       if (setMethod.getParameterTypes()[0].equals(String.class)) {
/* 44:44 */         setMethod.invoke(object, new Object[] { value });
/* 45:45 */       } else if (setMethod.getParameterTypes()[0].equals(Date.class)) {
/* 46:46 */         setMethod.invoke(object, new Object[] { yyyyMMdd.parse(value) });
/* 47:47 */       } else if (setMethod.getParameterTypes()[0].equals(Integer.class)) {
/* 48:48 */         setMethod.invoke(object, new Object[] { Integer.valueOf(value) });
/* 49:49 */       } else if (setMethod.getParameterTypes()[0].equals(Double.class)) {
/* 50:50 */         setMethod.invoke(object, new Object[] { Double.valueOf(value) });
/* 51:   */       } else {
/* 52:52 */         throw new Exception("new type in " + as400_Class.getSimpleName() + " need to trans:" + setMethod.getParameterTypes()[0].getSimpleName());
/* 53:   */       }
/* 54:   */     }
/* 55:56 */     return object;
/* 56:   */   }
/* 57:   */   
/* 58:   */   private static AS400Mapping genAS400Mapping(Class<?> classes)
/* 59:   */   {
/* 60:60 */     AS400Mapping as400Mapping = new AS400Mapping();
/* 61:   */     
/* 62:62 */     Map<String, Method> setMethodMap = new HashMap();
/* 63:63 */     for (Method method : classes.getMethods())
/* 64:   */     {
/* 65:64 */       methodName = method.getName();
/* 66:65 */       if (methodName.startsWith("set")) {
/* 67:66 */         setMethodMap.put(methodName, method);
/* 68:   */       }
/* 69:   */     }
/* 70:69 */     as400Mapping.setSetMethodMap(setMethodMap);
/* 71:   */     
/* 72:71 */     Integer countLength = Integer.valueOf(0);
/* 73:72 */     Object fieldOrderMap = new HashMap();
/* 74:   */     Field[] arrayOfField;
/* 75:73 */     String methodName = (arrayOfField = classes.getDeclaredFields()).length;
/* 76:73 */     for (String str1 = 0; str1 < methodName; str1++)
/* 77:   */     {
/* 78:73 */       Field field = arrayOfField[str1];
/* 79:74 */       AS400Element column = (AS400Element)field.getAnnotation(AS400Element.class);
/* 80:75 */       if (column != null)
/* 81:   */       {
/* 82:78 */         countLength = Integer.valueOf(countLength.intValue() + column.length());
/* 83:79 */         ((Map)fieldOrderMap).put(Integer.valueOf(column.ordinalPosition()), field);
/* 84:   */       }
/* 85:   */     }
/* 86:82 */     as400Mapping.setFieldOrderMap((Map)fieldOrderMap);
/* 87:83 */     as400Mapping.setTotalLength(countLength);
/* 88:   */     
/* 89:85 */     return as400Mapping;
/* 90:   */   }
/* 91:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.utils.AS400Util
 * JD-Core Version:    0.7.0.1
 */