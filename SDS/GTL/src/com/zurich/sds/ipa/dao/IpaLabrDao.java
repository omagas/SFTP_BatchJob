/*  1:   */ package com.zurich.sds.ipa.dao;
/*  2:   */ 
/*  3:   */ import com.zurich.core.dao.DatabaseDao;
/*  4:   */ import com.zurich.sds.model.common.hibernate.entity.SerialNoDefTb;
/*  5:   */ import com.zurich.sds.model.common.hibernate.entity.pk.SerialNoDefId;
/*  6:   */ import java.sql.ResultSet;
/*  7:   */ import java.sql.SQLException;
/*  8:   */ import java.text.SimpleDateFormat;
/*  9:   */ import java.util.Date;
/* 10:   */ import java.util.Vector;
/* 11:   */ import org.apache.commons.lang.StringUtils;
/* 12:   */ 
/* 13:   */ public class IpaLabrDao
/* 14:   */   extends DatabaseDao
/* 15:   */ {
/* 16:   */   public String getDataId()
/* 17:   */     throws SQLException
/* 18:   */   {
/* 19:18 */     Vector<Object> params = new Vector();
/* 20:   */     
/* 21:20 */     String sql = " SELECT * FROM serialno_def_tb WHERE yy = ? AND mm = ? AND dd = ? ";
/* 22:   */     
/* 23:22 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
/* 24:23 */     String nowString = simpleDateFormat.format(new Date());
/* 25:24 */     String yy = nowString.substring(2, 4);
/* 26:25 */     String mm = nowString.substring(4, 6);
/* 27:26 */     String dd = nowString.substring(6, 8);
/* 28:   */     
/* 29:28 */     params.add(yy);
/* 30:29 */     params.add(mm);
/* 31:30 */     params.add(dd);
/* 32:   */     
/* 33:32 */     SerialNoDefTb serialNoDefTb = (SerialNoDefTb)uniqueResult(SerialNoDefTb.class, sql, params);
/* 34:34 */     if (serialNoDefTb != null)
/* 35:   */     {
/* 36:35 */       Integer serialNo = serialNoDefTb.getSerialNo();
/* 37:36 */       serialNo = Integer.valueOf(serialNo.intValue() + 1);
/* 38:37 */       serialNoDefTb.setSerialNo(serialNo);
/* 39:   */     }
/* 40:   */     else
/* 41:   */     {
/* 42:39 */       serialNoDefTb = new SerialNoDefTb();
/* 43:40 */       SerialNoDefId serialNoDefId = new SerialNoDefId();
/* 44:41 */       serialNoDefId.setYy(yy);
/* 45:42 */       serialNoDefId.setMm(mm);
/* 46:43 */       serialNoDefId.setDd(dd);
/* 47:44 */       serialNoDefTb.setId(serialNoDefId);
/* 48:   */       
/* 49:46 */       serialNoDefTb.setSerialNo(Integer.valueOf(1));
/* 50:   */     }
/* 51:49 */     String dataId = "SDS" + yy + mm + dd + StringUtils.leftPad(serialNoDefTb.getSerialNo().toString(), 5, "0");
/* 52:   */     
/* 53:51 */     update(serialNoDefTb, "serialno_def_tb");
/* 54:   */     
/* 55:53 */     return dataId;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public Boolean isValidChlDataNo(String chlDataNo)
/* 59:   */     throws SQLException
/* 60:   */   {
/* 61:58 */     Vector<Object> vector = new Vector();
/* 62:59 */     vector.add(chlDataNo);
/* 63:60 */     ResultSet resultSet = getResultSet(" SELECT COUNT(1) chlDataNoCount  FROM app_m_tb WHERE chl_data_no = ? ", vector);
/* 64:61 */     Integer count = Integer.valueOf(resultSet.getInt("chlDataNoCount"));
/* 65:   */     
/* 66:63 */     releaseConnection();
/* 67:65 */     if (count.intValue() > 0) {
/* 68:66 */       return Boolean.valueOf(false);
/* 69:   */     }
/* 70:68 */     return Boolean.valueOf(true);
/* 71:   */   }
/* 72:   */   
/* 73:   */   public String getSalesCd(String salesId)
/* 74:   */     throws SQLException
/* 75:   */   {
/* 76:73 */     Vector<Object> vector = new Vector();
/* 77:74 */     vector.add(salesId);
/* 78:   */     
/* 79:76 */     ResultSet resultSet = getResultSet(" SELECT TOP 1 sales_cd FROM host_sales_def_tb WHERE sales_id = ? ", vector);
/* 80:77 */     String salesCd = resultSet.getString("sales_cd");
/* 81:   */     
/* 82:79 */     releaseConnection();
/* 83:   */     
/* 84:81 */     return salesCd;
/* 85:   */   }
/* 86:   */   
/* 87:   */   public String getPayerInsuredRelCd(String labrPayerInsuredRelCd)
/* 88:   */     throws SQLException
/* 89:   */   {
/* 90:85 */     Vector<Object> vector = new Vector();
/* 91:86 */     vector.add(labrPayerInsuredRelCd);
/* 92:   */     
/* 93:88 */     ResultSet resultSet = getResultSet(" SELECT TOP 1 sales_cd FROM host_sales_def_tb WHERE sales_id = ? ", vector);
/* 94:89 */     String salesCd = resultSet.getString("sales_cd");
/* 95:   */     
/* 96:91 */     return salesCd;
/* 97:   */   }
/* 98:   */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.dao.IpaLabrDao
 * JD-Core Version:    0.7.0.1
 */