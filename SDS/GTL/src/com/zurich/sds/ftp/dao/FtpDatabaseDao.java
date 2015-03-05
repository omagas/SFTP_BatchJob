/*   1:    */ package com.zurich.sds.ftp.dao;
/*   2:    */ 
/*   3:    */ import com.zurich.core.dao.DatabaseDao;
/*   4:    */ import com.zurich.sds.lawbroker.module.InsuredRoster;
/*   5:    */ import com.zurich.sds.lawbroker.module.Policy;
/*   6:    */ import java.sql.SQLException;
/*   7:    */ import java.util.ArrayList;
/*   8:    */ import java.util.List;
/*   9:    */ import java.util.Vector;
/*  10:    */ import org.apache.commons.lang.StringUtils;
/*  11:    */ import org.apache.log4j.Logger;
/*  12:    */ 
/*  13:    */ public class FtpDatabaseDao
/*  14:    */   extends DatabaseDao
/*  15:    */ {
/*  16: 17 */   Logger logger = Logger.getLogger(DatabaseDao.class);
/*  17:    */   
/*  18:    */   public List<Policy> getPolicyDataList(java.util.Date logTimeBegin, java.util.Date logTimeEnd, Boolean needCheckFtpNotUploaded, Boolean isCancelPolicy)
/*  19:    */   {
/*  20: 30 */     List<Policy> list = new ArrayList();
/*  21:    */     
/*  22: 32 */     boolean hasLogTimeBegin = logTimeBegin != null;
/*  23: 33 */     boolean hasLogTimeEnd = logTimeEnd != null;
/*  24: 34 */     boolean isLogTimeBounded = (hasLogTimeBegin) && (hasLogTimeEnd);
/*  25:    */     try
/*  26:    */     {
/*  27: 37 */       Vector<Object> params = new Vector();
/*  28:    */       
/*  29: 39 */       String sql = 
/*  30: 40 */         " SELECT  App_M.Issue_Brh_CD + '-' +  CONVERT(nvarchar, (CONVERT(varchar(4), App_M.Ins_Eff_Dt, 112) - 1911)) + '-' +  LTRIM(RTRIM(App_GTL.Host_Policy_No)) + '-' +  RIGHT(REPLICATE('0', 5) + LTRIM(RTRIM(App_GTL.Host_Policy_TranNo)), 5) + '-' +  App_M.Prdt_CD  AS Policy_No,  App_M.Income_ID AS Income_ID,  Cmpgn.Cmpgn_Nm,  CustA.Cust_Nm,  CustA.Cust_Bth,  CustA.Cust_ID,  CustA.Cust_Mobile,  CustA.Cust_ZipCD,  CustA.Cust_Adrs,  App_M.Ins_Eff_Dt,  App_M.Ins_Eff_TM,  App_M.Ins_Exp_Dt,  App_M.Income_Dt,  App_GTL.Tour_Days,  App_GTL.TourCust_Cnt,  CASE  WHEN App_M.Issue_MK = 'C' THEN  0  ELSE  App_GTL.Tot_Prm  END AS Tot_Prm,  CASE  WHEN (App_GTL.TourPlace_Desc IS NULL OR LTRIM(RTRIM(App_GTL.TourPlace_Desc)) = '') THEN  Gtl_TourArea.TourArea_NM  ELSE  App_GTL.TourPlace_Desc  END AS TourPlace_Desc,  App_GTL.Data_ID,  App_GTL.Data_ID_Verno,  App_GTL.Recpt_No  FROM App_GTL_M_Tb App_GTL  INNER JOIN App_M_Tb App_M ON 1 = 1  AND App_M.Data_ID = App_GTL.Data_ID  AND App_M.Chl_CD = 'LABR'  INNER JOIN Cmpgn_Ref_Tb Cmpgn ON 1 = 1  AND Cmpgn.Cmpgn_CD = App_GTL.Cmpgn_CD  INNER JOIN Cust_Detail_Tb CustA ON 1 = 1  AND CustA.Data_ID = App_GTL.Data_ID  AND CustA.Data_ID_Verno = App_GTL.Data_ID_Verno  AND CustA.Cust_Role_CD = 'A'  LEFT JOIN GTL_TourArea_Def_Tb Gtl_TourArea ON 1 = 1  AND Gtl_TourArea.TourArea_CD = App_GTL.TourArea_CD  WHERE 1 = 1 ";
/*  31: 95 */       if (isCancelPolicy.booleanValue())
/*  32:    */       {
/*  33: 97 */         sql = 
/*  34: 98 */           sql + " AND App_M.Issue_MK = 'C' " + " AND App_GTL.Ftp_Send_MK = 'Y' ";
/*  35:    */       }
/*  36:    */       else
/*  37:    */       {
/*  38:101 */         sql = sql + " AND App_M.Issue_MK = 'Y' ";
/*  39:105 */         if (needCheckFtpNotUploaded.booleanValue()) {
/*  40:106 */           sql = sql + " AND (App_GTL.Ftp_Send_MK IS NULL OR (App_GTL.Ftp_Send_MK != 'Y' AND App_GTL.Ftp_Send_MK != 'C')) ";
/*  41:    */         }
/*  42:    */       }
/*  43:110 */       sql = 
/*  44:    */       
/*  45:    */ 
/*  46:    */ 
/*  47:114 */         sql + " AND EXISTS( " + " SELECT 1 FROM Log_Batch_Rec_Tb Log WHERE 1 = 1 " + " AND App_GTL.Data_ID = Log.Data_ID " + " AND App_GTL.Data_ID_Verno = Log.Data_ID_Verno ";
/*  48:119 */       if (isLogTimeBounded)
/*  49:    */       {
/*  50:120 */         sql = sql + " AND CONVERT(VARCHAR(12), Log.Exec_Upd_Dt, 112) BETWEEN CONVERT(VARCHAR(12), ?, 112) AND CONVERT(VARCHAR(12), ?, 112)  ";
/*  51:    */         
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55:    */ 
/*  56:126 */         params.addElement(new java.sql.Date(logTimeBegin.getTime()));
/*  57:127 */         params.addElement(new java.sql.Date(logTimeEnd.getTime()));
/*  58:    */       }
/*  59:128 */       else if (hasLogTimeBegin)
/*  60:    */       {
/*  61:129 */         sql = sql + " AND CONVERT(VARCHAR(12), Log.Exec_Upd_Dt, 112) BETWEEN CONVERT(VARCHAR(12), ?, 112) AND CONVERT(VARCHAR(12), GETDATE(), 112) ";
/*  62:130 */         params.addElement(new java.sql.Date(logTimeBegin.getTime()));
/*  63:    */       }
/*  64:131 */       else if (hasLogTimeEnd)
/*  65:    */       {
/*  66:132 */         sql = sql + " AND CONVERT(VARCHAR(12),Log.Exec_Upd_Dt, 112) <= CONVERT(VARCHAR(12), ?, 112) ";
/*  67:133 */         params.addElement(new java.sql.Date(logTimeEnd.getTime()));
/*  68:    */       }
/*  69:    */       else
/*  70:    */       {
/*  71:135 */         sql = sql + " AND CONVERT(VARCHAR(12),Log.Exec_Upd_Dt, 112) <= CONVERT(VARCHAR(12), GETDATE(), 112) ";
/*  72:    */       }
/*  73:137 */       sql = 
/*  74:    */       
/*  75:139 */         sql + " ) " + " ORDER BY App_GTL.Host_Policy_No ";
/*  76:    */       
/*  77:141 */       list = listResult(Policy.class, sql, params);
/*  78:    */     }
/*  79:    */     catch (SQLException e)
/*  80:    */     {
/*  81:144 */       this.logger.error("ftpDaoException:", e);
/*  82:145 */       e.printStackTrace();
/*  83:    */     }
/*  84:    */     finally
/*  85:    */     {
/*  86:147 */       releaseConnection();
/*  87:    */     }
/*  88:149 */     return list;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public List<InsuredRoster> getRosterDataList(java.util.Date logTimeBegin, java.util.Date logTimeEnd, Boolean needCheckFtpNotUploaded, Boolean isCancelPolicy)
/*  92:    */   {
/*  93:155 */     List<InsuredRoster> list = new ArrayList();
/*  94:    */     
/*  95:157 */     boolean hasLogTimeBegin = logTimeBegin != null;
/*  96:158 */     boolean hasLogTimeEnd = logTimeEnd != null;
/*  97:159 */     boolean isLogTimeBounded = (hasLogTimeBegin) && (hasLogTimeEnd);
/*  98:    */     try
/*  99:    */     {
/* 100:162 */       Vector<Object> params = new Vector();
/* 101:    */       
/* 102:164 */       String sql = 
/* 103:165 */         " SELECT  App_M.Issue_Brh_CD + '-' +  CONVERT(varchar, (CONVERT(varchar(4), App_M.Ins_Eff_Dt, 112) - 1911)) + '-' +  LTRIM(RTRIM(App_GTL.Host_Policy_No)) + '-' +  RIGHT(REPLICATE('0', 5) + LTRIM(RTRIM(App_GTL.Host_Policy_TranNo)), 5) + '-' +  App_M.Prdt_CD  AS Policy_No,  CustA.Cust_Nm AS Cust_Nm_A,  CustI.Cust_Nm AS Cust_Nm_I,  CustI.Cust_ID,  CustI.Cust_Bth,  RelCust.Rel_Desc AS Cust_Rel,  App_M.Ins_Eff_Dt,  App_M.Ins_Exp_Dt,  GTL_R_09.Risk_Amt AS Risk_Amt_09,  GTL_R_10.Risk_Amt AS Risk_Amt_10,  GTL_R_11.Risk_Amt + GTL_R_12.Risk_Amt AS Risk_Amt_11_12,  App_GTL.Tour_Days,  CASE  WHEN App_PA.Per_Age_Cnt is null THEN  1  ELSE  App_PA.Per_Age_Cnt  END as Per_Age_Cnt,  CASE  WHEN App_M.Issue_MK = 'C' THEN  0  WHEN App_PA.Per_Age_Cnt is null THEN  1 * App_PA.Per_Prm  ELSE  App_PA.Per_Age_Cnt * App_PA.Per_Prm  END as Per_Prm,  CASE  WHEN (App_GTL.TourPlace_Desc IS NULL OR LTRIM(RTRIM(App_GTL.TourPlace_Desc)) = '') THEN  Gtl_TourArea.TourArea_NM  ELSE  App_GTL.TourPlace_Desc  END as TourPlace_Desc,  App_M.Income_Dt,  Cust_Ben.Benef_Nm,  RelBen.Rel_Desc AS Ben_Rel,  App_M.Ins_Eff_TM,  App_M.Income_ID + '-' + Sales.Sales_Nm AS Income_ID,  App_M.Chl_CD,  App_GTL.Recpt_No  FROM App_GTL_M_Tb App_GTL  INNER JOIN Cust_Detail_Tb CustI ON 1 = 1  AND CustI.Data_ID = App_GTL.Data_ID  AND CustI.Data_ID_Verno = App_GTL.Data_ID_Verno  AND CustI.Cust_Role_CD = 'I'  INNER JOIN Cust_Detail_Tb CustA ON 1 = 1  AND CustA.Data_ID = App_GTL.Data_ID  AND CustA.Data_ID_Verno = App_GTL.Data_ID_Verno  AND CustA.Cust_Role_CD = 'A'  INNER JOIN App_GTL_PA_Tb App_PA ON 1 = 1  AND App_PA.Insured_Cust_No = CustI.Cust_No  AND App_PA.Data_ID = App_GTL.Data_ID  AND App_PA.Data_ID_Verno = App_GTL.Data_ID_Verno  LEFT JOIN Rel_Def_Tb RelCust ON 1 = 1  AND RelCust.Type_CD = 'A'  AND RelCust.Rel_CD = CustA.Applicant_Insured_Rel_CD  INNER JOIN App_M_Tb App_M ON 1 = 1  AND App_M.Data_ID = App_GTL.Data_ID  AND App_M.Chl_CD = 'LABR'  INNER JOIN App_GTL_Risk_Tb GTL_R_09 ON 1 = 1  AND GTL_R_09.Data_ID = App_GTL.Data_ID  AND GTL_R_09.Data_ID_Verno = App_GTL.Data_ID_Verno  AND GTL_R_09.Itm_SeqNo = App_PA.Itm_SeqNo  AND GTL_R_09.Risk_ID = '09'  LEFT JOIN App_GTL_Risk_Tb GTL_R_10 ON 1 = 1  AND GTL_R_10.Data_ID = App_GTL.Data_ID  AND GTL_R_10.Data_ID_Verno = App_GTL.Data_ID_Verno  AND GTL_R_10.Itm_SeqNo = App_PA.Itm_SeqNo  AND GTL_R_10.Risk_ID = '10'  LEFT JOIN App_GTL_Risk_Tb GTL_R_11 ON 1 = 1  AND GTL_R_11.Data_ID = App_GTL.Data_ID  AND GTL_R_11.Data_ID_Verno = App_GTL.Data_ID_Verno  AND GTL_R_11.Itm_SeqNo = App_PA.Itm_SeqNo  AND GTL_R_11.Risk_ID = '11'  LEFT JOIN App_GTL_Risk_Tb GTL_R_12 ON 1 = 1  AND GTL_R_12.Data_ID = App_GTL.Data_ID  AND GTL_R_12.Data_ID_Verno = App_GTL.Data_ID_Verno  AND GTL_R_12.Itm_SeqNo = App_PA.Itm_SeqNo  AND GTL_R_12.Risk_ID = '12'  LEFT JOIN Cust_Insured_Benef_Tb Cust_Ben ON 1 = 1  AND Cust_Ben.Data_ID = App_GTL.Data_ID  AND Cust_Ben.Data_ID_Verno = App_GTL.Data_ID_Verno  AND Cust_Ben.Insured_Cust_No = CustI.Cust_No  LEFT JOIN Rel_Def_Tb RelBen ON 1 = 1  AND RelBen.Type_CD = 'B'  AND RelBen.Rel_CD = Cust_Ben.Benef_Insured_Rel_CD  LEFT JOIN Host_Sales_Def_Tb Sales ON 1 = 1  AND Sales.Sales_Id = App_M.Income_ID  AND Sales.Agnt_CD = App_M.Agnt_CD  LEFT JOIN GTL_TourArea_Def_Tb Gtl_TourArea ON 1 = 1  AND Gtl_TourArea.TourArea_CD = App_GTL.TourArea_CD  WHERE 1 = 1 ";
/* 104:268 */       if (isCancelPolicy.booleanValue())
/* 105:    */       {
/* 106:270 */         sql = 
/* 107:271 */           sql + " AND App_M.Issue_MK = 'C' " + " AND App_GTL.Ftp_Send_MK = 'Y' ";
/* 108:    */       }
/* 109:    */       else
/* 110:    */       {
/* 111:274 */         sql = sql + " AND App_M.Issue_MK = 'Y' ";
/* 112:278 */         if (needCheckFtpNotUploaded.booleanValue()) {
/* 113:279 */           sql = sql + " AND (App_GTL.Ftp_Send_MK IS NULL OR (App_GTL.Ftp_Send_MK != 'Y' AND App_GTL.Ftp_Send_MK != 'C')) ";
/* 114:    */         }
/* 115:    */       }
/* 116:283 */       sql = 
/* 117:    */       
/* 118:    */ 
/* 119:    */ 
/* 120:287 */         sql + " AND EXISTS( " + " SELECT 1 FROM Log_Batch_Rec_Tb Log WHERE 1 = 1 " + " AND App_GTL.Data_ID = Log.Data_ID " + " AND App_GTL.Data_ID_Verno = Log.Data_ID_Verno ";
/* 121:292 */       if (isLogTimeBounded)
/* 122:    */       {
/* 123:293 */         sql = sql + " AND CONVERT(VARCHAR(12),Log.Exec_Upd_Dt, 112) BETWEEN CONVERT(VARCHAR(12), ?, 112) AND CONVERT(VARCHAR(12), ?, 112)  ";
/* 124:294 */         params.addElement(new java.sql.Date(logTimeBegin.getTime()));
/* 125:295 */         params.addElement(new java.sql.Date(logTimeEnd.getTime()));
/* 126:    */       }
/* 127:296 */       else if (hasLogTimeBegin)
/* 128:    */       {
/* 129:297 */         sql = sql + " AND CONVERT(VARCHAR(12),Log.Exec_Upd_Dt, 112) BETWEEN CONVERT(VARCHAR(12), ?, 112) AND CONVERT(VARCHAR(12), GETDATE(), 112)  ";
/* 130:298 */         params.addElement(new java.sql.Date(logTimeBegin.getTime()));
/* 131:    */       }
/* 132:299 */       else if (hasLogTimeEnd)
/* 133:    */       {
/* 134:300 */         sql = sql + " AND CONVERT(VARCHAR(12),Log.Exec_Upd_Dt, 112) <= CONVERT(VARCHAR(12), ?, 112)  ";
/* 135:301 */         params.addElement(new java.sql.Date(logTimeEnd.getTime()));
/* 136:    */       }
/* 137:    */       else
/* 138:    */       {
/* 139:303 */         sql = sql + " AND CONVERT(VARCHAR(12),Log.Exec_Upd_Dt, 112) <= CONVERT(VARCHAR(12), GETDATE(), 112)  ";
/* 140:    */       }
/* 141:305 */       sql = 
/* 142:    */       
/* 143:307 */         sql + " ) " + " ORDER BY App_GTL.Host_Policy_No ";
/* 144:    */       
/* 145:309 */       list = listResult(InsuredRoster.class, sql, params);
/* 146:    */     }
/* 147:    */     catch (SQLException e)
/* 148:    */     {
/* 149:312 */       this.logger.error("ftpDaoException:", e);
/* 150:313 */       e.printStackTrace();
/* 151:    */     }
/* 152:    */     finally
/* 153:    */     {
/* 154:315 */       releaseConnection();
/* 155:    */     }
/* 156:317 */     return list;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public void updateFtpSendMk(String dataId, Integer dataIdVerno, String sendMk)
/* 160:    */     throws SQLException
/* 161:    */   {
/* 162:321 */     if ((StringUtils.isBlank(dataId)) || (dataIdVerno == null)) {
/* 163:322 */       return;
/* 164:    */     }
/* 165:324 */     String sql = " UPDATE app_gtl_m_tb SET ftp_send_mk = ? WHERE data_id = ? AND data_id_verno = ? ";
/* 166:325 */     Vector<Object> vector = new Vector();
/* 167:326 */     vector.add(nvl(sendMk, ""));
/* 168:327 */     vector.add(dataId);
/* 169:328 */     vector.add(dataIdVerno);
/* 170:    */     
/* 171:330 */     executeSQL(sql, vector);
/* 172:    */   }
/* 173:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ftp.dao.FtpDatabaseDao
 * JD-Core Version:    0.7.0.1
 */