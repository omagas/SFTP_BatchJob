/*   1:    */ package com.zurich.sds.lawbroker.module;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
/*   4:    */ 
/*   5:    */ public class test
/*   6:    */ {
/*   7:    */   public static void main(String[] args)
/*   8:    */     throws Exception
/*   9:    */   {
/*  10:101 */     String sql = 
/*  11:102 */       " SELECT  CASE  WHEN (App_GTL.host_policy_no IS NULL OR LTRIM(RTRIM(App_GTL.host_policy_no)) = '') THEN  NULL  ELSE  App_M.issue_brh_cd + '-' +  CONVERT(VARCHAR, (CONVERT(VARCHAR(4), GETDATE(), 112) - 1911)) + '-' +  LTRIM(RTRIM(App_GTL.host_policy_no)) + '-' +  RIGHT(REPLICATE('0', 5) + LTRIM(RTRIM(App_GTL.host_policy_tranNo)), 5) + '-' +  App_M.prdt_cd  END AS policy_no,  Channel.channel_name,  App_M.income_id AS income_id,  Cmpgn.cmpgn_nm,  CustA.cust_nm,  CustA.cust_bth,  CustA.cust_id,  CustA.cust_mobile,  CustA.cust_zipcd,  CustA.cust_adrs,  App_M.ins_eff_dt,  App_M.ins_eff_tm,  App_M.ins_exp_dt,  App_M.income_dt,  App_GTL.tour_days,  App_GTL.tourcust_cnt,  App_GTL.tot_prm,  CASE  WHEN (App_GTL.tourplace_desc IS NULL OR LTRIM(RTRIM(App_GTL.tourplace_desc)) = '') THEN  Gtl_TourArea.tourarea_nm  ELSE  App_GTL.tourplace_desc  END AS tourplace_desc,  App_GTL.data_id,  App_GTL.data_id_verno,  App_GTL.recpt_no,  App_M.prdt_cd,  Status.proc_status_nm  FROM app_gtl_m_tb App_GTL  INNER JOIN app_m_tb App_M ON 1 = 1  AND App_M.data_id = App_GTL.data_id  INNER JOIN cmpgn_ref_tb Cmpgn ON 1 = 1  AND Cmpgn.cmpgn_cd = App_GTL.cmpgn_cd  INNER JOIN cust_detail_tb CustA ON 1 = 1  AND CustA.data_id = App_GTL.data_id  AND CustA.data_id_verno = App_GTL.data_id_verno  AND CustA.cust_role_cd = 'A'  LEFT JOIN gtl_tourarea_def_tb Gtl_TourArea ON 1 = 1  AND Gtl_TourArea.tourarea_cd = App_GTL.tourarea_cd  LEFT JOIN adm_channel Channel ON 1 = 1  AND App_M.chl_cd = Channel.manual_id  LEFT JOIN proc_status_def_tb Status ON 1 = 1  AND Status.proc_status_cd = App_GTL.proc_status_cd  WHERE 1 = 1  AND App_GTL.data_id = :dataId  AND App_GTL.data_id_verno = :dataIdVerno ";
/*  12:    */     
/*  13:    */ 
/*  14:    */ 
/*  15:    */ 
/*  16:    */ 
/*  17:    */ 
/*  18:    */ 
/*  19:    */ 
/*  20:    */ 
/*  21:    */ 
/*  22:    */ 
/*  23:    */ 
/*  24:    */ 
/*  25:    */ 
/*  26:    */ 
/*  27:    */ 
/*  28:    */ 
/*  29:    */ 
/*  30:    */ 
/*  31:    */ 
/*  32:    */ 
/*  33:    */ 
/*  34:    */ 
/*  35:    */ 
/*  36:    */ 
/*  37:    */ 
/*  38:    */ 
/*  39:    */ 
/*  40:    */ 
/*  41:    */ 
/*  42:    */ 
/*  43:    */ 
/*  44:    */ 
/*  45:    */ 
/*  46:    */ 
/*  47:    */ 
/*  48:    */ 
/*  49:    */ 
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55:    */ 
/*  56:    */ 
/*  57:    */ 
/*  58:    */ 
/*  59:    */ 
/*  60:    */ 
/*  61:    */ 
/*  62:    */ 
/*  63:    */ 
/*  64:    */ 
/*  65:    */ 
/*  66:    */ 
/*  67:    */ 
/*  68:    */ 
/*  69:    */ 
/*  70:161 */     System.out.println(sql);
/*  71:    */     
/*  72:    */ 
/*  73:    */ 
/*  74:165 */     System.out.println();
/*  75:    */   }
/*  76:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.lawbroker.module.test
 * JD-Core Version:    0.7.0.1
 */