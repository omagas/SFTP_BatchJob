/*   1:    */ package com.zurich.core.dao.utils;
/*   2:    */ 
/*   3:    */ import java.lang.annotation.Annotation;
/*   4:    */ import java.lang.reflect.Field;
/*   5:    */ import java.lang.reflect.Method;
/*   6:    */ import java.lang.reflect.Type;
/*   7:    */ import java.util.HashMap;
/*   8:    */ import java.util.Iterator;
/*   9:    */ import java.util.List;
/*  10:    */ import java.util.Map;
/*  11:    */ import java.util.Map.Entry;
/*  12:    */ import java.util.Set;
/*  13:    */ import javax.persistence.Column;
/*  14:    */ import javax.persistence.EmbeddedId;
/*  15:    */ import javax.persistence.GeneratedValue;
/*  16:    */ import javax.persistence.GenerationType;
/*  17:    */ import javax.persistence.Id;
/*  18:    */ import javax.persistence.Transient;
/*  19:    */ 
/*  20:    */ public class ReflectBean
/*  21:    */ {
/*  22: 20 */   private static Map<Class<?>, BeanInfo> map = new HashMap();
/*  23:    */   
/*  24:    */   public static BeanInfo getBeanInfo(Class<?> entityClass)
/*  25:    */   {
/*  26: 23 */     if (map.get(entityClass) == null) {
/*  27: 24 */       getBeanInfo(entityClass, Boolean.valueOf(false));
/*  28:    */     }
/*  29: 27 */     return (BeanInfo)map.get(entityClass);
/*  30:    */   }
/*  31:    */   
/*  32:    */   private static void getBeanInfo(Class<?> entityClass, Boolean isEmbeddedIdClass)
/*  33:    */   {
/*  34: 31 */     Field[] fields = entityClass.getDeclaredFields();
/*  35: 32 */     Field fistField = "serialVersionUID".equals(fields[0].getName()) ? fields[1] : fields[0];
/*  36: 33 */     Map<String, Method> methodGet = new HashMap();
/*  37: 34 */     Map<String, Method> methodSet = new HashMap();
/*  38: 35 */     BeanInfo reflectBean = new BeanInfo();
/*  39: 37 */     for (Method method : entityClass.getMethods())
/*  40:    */     {
/*  41: 38 */       methodName = method.getName();
/*  42: 39 */       if (!methodName.equals("getClass")) {
/*  43: 41 */         if ((methodName.startsWith("get")) || (methodName.startsWith("is"))) {
/*  44: 42 */           methodGet.put(methodName, method);
/*  45: 43 */         } else if (methodName.startsWith("set")) {
/*  46: 44 */           methodSet.put(methodName, method);
/*  47:    */         }
/*  48:    */       }
/*  49:    */     }
/*  50: 48 */     Map.Entry<String, Method> entryGet = (Map.Entry)methodGet.entrySet().iterator().next();
/*  51: 49 */     Object entrySet = (Map.Entry)methodGet.entrySet().iterator().next();
/*  52:    */     
/*  53: 51 */     String annotationAt = null;
/*  54: 52 */     if (fistField.getAnnotations().length > 0) {
/*  55: 53 */       annotationAt = "column";
/*  56: 54 */     } else if (((Method)entryGet.getValue()).getAnnotations().length > 0) {
/*  57: 55 */       annotationAt = "get";
/*  58: 56 */     } else if (((Method)((Map.Entry)entrySet).getValue()).getAnnotations().length > 0) {
/*  59: 57 */       annotationAt = "set";
/*  60:    */     } else {
/*  61: 59 */       throw new RuntimeException("Annotations get error");
/*  62:    */     }
/*  63:    */     Field[] arrayOfField1;
/*  64: 62 */     String str1 = (arrayOfField1 = fields).length;
/*  65: 62 */     for (String methodName = 0; methodName < str1; methodName++)
/*  66:    */     {
/*  67: 62 */       Field field = arrayOfField1[methodName];
/*  68: 63 */       if (!"serialVersionUID".equals(field.getName()))
/*  69:    */       {
/*  70: 66 */         String fieldName = field.getName();
/*  71: 67 */         Annotation[] annotations = (Annotation[])null;
/*  72: 69 */         if (annotationAt.equals("column"))
/*  73:    */         {
/*  74: 70 */           annotations = field.getDeclaredAnnotations();
/*  75:    */         }
/*  76: 71 */         else if (annotationAt.equals("get"))
/*  77:    */         {
/*  78: 72 */           String methodGetName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
/*  79: 73 */           Method method = (Method)methodGet.get(methodGetName);
/*  80: 74 */           if ((method == null) && (field.getType().equals(Boolean.class)))
/*  81:    */           {
/*  82: 75 */             methodGetName = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
/*  83: 76 */             method = (Method)methodGet.get(methodGetName);
/*  84:    */           }
/*  85: 78 */           annotations = method.getAnnotations();
/*  86:    */         }
/*  87: 79 */         else if (annotationAt.equals("set"))
/*  88:    */         {
/*  89: 80 */           String methodSetName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
/*  90: 81 */           Method method = (Method)methodSet.get(methodSetName);
/*  91: 82 */           annotations = method.getAnnotations();
/*  92:    */         }
/*  93: 85 */         String columnName = null;
/*  94: 86 */         Boolean isId = Boolean.valueOf(false);
/*  95: 87 */         Boolean isTransient = Boolean.valueOf(false);
/*  96: 88 */         Boolean isEmbeddedId = Boolean.valueOf(false);
/*  97: 89 */         Boolean isGeneratedValueIdentity = Boolean.valueOf(false);
/*  98: 90 */         for (Annotation annotation : annotations)
/*  99:    */         {
/* 100: 91 */           Type type = annotation.annotationType();
/* 101: 92 */           if (Column.class.equals(type))
/* 102:    */           {
/* 103: 93 */             Column column = (Column)annotation;
/* 104: 94 */             columnName = column.name();
/* 105:    */           }
/* 106: 95 */           else if (EmbeddedId.class.equals(type))
/* 107:    */           {
/* 108: 96 */             isEmbeddedId = Boolean.valueOf(true);
/* 109: 97 */             getBeanInfo(field.getType(), Boolean.valueOf(true));
/* 110:    */           }
/* 111: 98 */           else if (Id.class.equals(type))
/* 112:    */           {
/* 113: 99 */             isId = Boolean.valueOf(true);
/* 114:    */           }
/* 115:    */           else
/* 116:    */           {
/* 117:100 */             if (Transient.class.equals(type))
/* 118:    */             {
/* 119:101 */               isTransient = Boolean.valueOf(true);
/* 120:102 */               break;
/* 121:    */             }
/* 122:103 */             if (GeneratedValue.class.equals(type))
/* 123:    */             {
/* 124:104 */               GeneratedValue generatedValue = (GeneratedValue)annotation;
/* 125:105 */               GenerationType strategy = generatedValue.strategy();
/* 126:106 */               if ((strategy.equals(GenerationType.AUTO)) || 
/* 127:107 */                 (strategy.equals(GenerationType.TABLE)) || 
/* 128:108 */                 (strategy.equals(GenerationType.IDENTITY))) {
/* 129:110 */                 isGeneratedValueIdentity = Boolean.valueOf(true);
/* 130:    */               }
/* 131:    */             }
/* 132:    */           }
/* 133:    */         }
/* 134:115 */         if (!isTransient.booleanValue()) {
/* 135:117 */           if (isEmbeddedId.booleanValue())
/* 136:    */           {
/* 137:118 */             reflectBean.setEmbeddedIdClass(field.getType());
/* 138:119 */             reflectBean.setEmbeddedIdFieldName(fieldName);
/* 139:    */           }
/* 140:    */           else
/* 141:    */           {
/* 142:122 */             if (columnName == null) {
/* 143:123 */               throw new RuntimeException("columnName is null at " + entityClass.getSimpleName() + "." + fieldName);
/* 144:    */             }
/* 145:126 */             EntityMapping mapping = new EntityMapping();
/* 146:127 */             mapping.setDbColumnName(columnName.toUpperCase());
/* 147:128 */             mapping.setJavaColumnName(fieldName);
/* 148:129 */             mapping.setJavaColumnClass(field.getType());
/* 149:130 */             mapping.setIsGeneratedValueIdentity(isGeneratedValueIdentity);
/* 150:132 */             if (isId.booleanValue()) {
/* 151:133 */               reflectBean.getPrimaryKeyList().add(mapping);
/* 152:    */             } else {
/* 153:135 */               reflectBean.getColumnList().add(mapping);
/* 154:    */             }
/* 155:    */           }
/* 156:    */         }
/* 157:    */       }
/* 158:    */     }
/* 159:140 */     map.put(entityClass, reflectBean);
/* 160:    */   }
/* 161:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.core.dao.utils.ReflectBean
 * JD-Core Version:    0.7.0.1
 */