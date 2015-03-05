/*  1:   */ package com.zurich.sds.ipa.dao;
/*  2:   */ 
/*  3:   */ import com.zurich.core.dao.DatabaseDao;
/*  4:   */ import java.sql.ResultSet;
/*  5:   */ import java.sql.SQLException;
/*  6:   */ 
/*  7:   */ public class IpaDefManualJobDao
/*  8:   */   extends DatabaseDao
/*  9:   */ {
/* 10:   */   public Integer getNextJobCd()
/* 11:   */     throws SQLException
/* 12:   */   {
/* 13:11 */     String sql = " SELECT MAX(CONVERT(int, job_cd)) AS job_cd FROM host_job_class_def_tb ";
/* 14:   */     
/* 15:13 */     ResultSet resultSet = getResultSet(sql);
/* 16:   */     
/* 17:15 */     return Integer.valueOf(resultSet.getInt("job_cd") + 1);
/* 18:   */   }
/* 19:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.dao.IpaDefManualJobDao
 * JD-Core Version:    0.7.0.1
 */