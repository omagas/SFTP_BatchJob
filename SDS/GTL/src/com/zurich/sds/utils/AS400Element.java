package com.zurich.sds.utils;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface AS400Element
{
  int length() default 0;
  
  int ordinalPosition() default 0;
  
  String riskId() default "";
}


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.utils.AS400Element
 * JD-Core Version:    0.7.0.1
 */