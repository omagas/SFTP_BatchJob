/*  1:   */ package com.zurich.sds.utils;
/*  2:   */ 
/*  3:   */ import java.beans.PropertyDescriptor;
/*  4:   */ import java.lang.reflect.Field;
/*  5:   */ import java.lang.reflect.Method;
/*  6:   */ 
/*  7:   */ public class BeanUtil
/*  8:   */ {
/*  9:   */   public static void trimBeanFields(Object bean)
/* 10:   */   {
/* 11:   */     try
/* 12:   */     {
/* 13:11 */       if (bean == null) {
/* 14:12 */         return;
/* 15:   */       }
/* 16:15 */       Class<?> beanClass = bean.getClass();
/* 17:17 */       for (Field field : beanClass.getDeclaredFields()) {
/* 18:18 */         if (field.getType().equals(String.class))
/* 19:   */         {
/* 20:19 */           String fieldName = field.getName();
/* 21:   */           
/* 22:21 */           PropertyDescriptor pd = new PropertyDescriptor(fieldName, beanClass);
/* 23:22 */           Method methodGet = pd.getReadMethod();
/* 24:   */           
/* 25:24 */           String value = (String)methodGet.invoke(bean, new Object[0]);
/* 26:25 */           if (value != null)
/* 27:   */           {
/* 28:28 */             value = value.trim();
/* 29:   */             
/* 30:30 */             Method methodSet = pd.getWriteMethod();
/* 31:31 */             methodSet.invoke(bean, new Object[] { value });
/* 32:   */           }
/* 33:   */         }
/* 34:   */       }
/* 35:   */     }
/* 36:   */     catch (Exception e)
/* 37:   */     {
/* 38:36 */       e.printStackTrace();
/* 39:   */     }
/* 40:   */   }
/* 41:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.utils.BeanUtil
 * JD-Core Version:    0.7.0.1
 */