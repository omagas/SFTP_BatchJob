/*   1:    */ package com.zurich.sds.synchronous.dao;
/*   2:    */ 
/*   3:    */ import com.zurich.core.dao.DatabaseDao;
/*   4:    */ import com.zurich.sds.synchronous.module.SynchronousChannel;
/*   5:    */ import com.zurich.sds.synchronous.module.SynchronousColumnSetting;
/*   6:    */ import com.zurich.sds.tool.PropertiesTool;
/*   7:    */ import java.sql.ResultSet;
/*   8:    */ import java.sql.SQLException;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ import java.util.List;
/*  11:    */ import java.util.Properties;
/*  12:    */ import org.apache.log4j.Logger;
/*  13:    */ import org.apache.log4j.PropertyConfigurator;
/*  14:    */ 
/*  15:    */ public class SynchronousDao
/*  16:    */   extends DatabaseDao
/*  17:    */ {
/*  18: 18 */   Logger logger = Logger.getLogger(DatabaseDao.class);
/*  19:    */   
/*  20:    */   public SynchronousDao()
/*  21:    */   {
/*  22:    */     try
/*  23:    */     {
/*  24: 22 */       Properties pro = PropertiesTool.getProperties("Log4j.properties");
/*  25: 23 */       PropertyConfigurator.configure(pro);
/*  26:    */     }
/*  27:    */     catch (Exception localException) {}
/*  28:    */   }
/*  29:    */   
/*  30:    */   public List<SynchronousChannel> selectSyncableChannel()
/*  31:    */     throws SQLException
/*  32:    */   {
/*  33: 28 */     List<SynchronousChannel> list = new ArrayList();
/*  34:    */     
/*  35: 30 */     StringBuffer sql = new StringBuffer();
/*  36: 31 */     sql.append(" SELECT a.channel_id, a.syncable, b.is_internal, b.host_agnt_kind_cd, b.is_separate_internal FROM adm_channel a ")
/*  37: 32 */       .append(" INNER JOIN adm_channel_type b ON 1 = 1 ")
/*  38: 33 */       .append(" AND a.channel_type_id = b.channel_type_id ")
/*  39: 34 */       .append(" WHERE 1 = 1 ")
/*  40: 35 */       .append(" AND a.effective_datetime < GETDATE() ")
/*  41: 36 */       .append(" AND (a.expire_datetime IS NULL OR a.expire_datetime > GETDATE()) ");
/*  42:    */     
/*  43: 38 */     ResultSet rs = getResultSet(sql.toString());
/*  44: 39 */     while (rs.next())
/*  45:    */     {
/*  46: 40 */       SynchronousChannel channel = new SynchronousChannel();
/*  47: 41 */       channel.setChannelId(Integer.valueOf(rs.getInt("channel_id")));
/*  48: 42 */       channel.setSyncable(Boolean.valueOf(rs.getBoolean("syncable")));
/*  49: 43 */       channel.setIsInternal(Boolean.valueOf(rs.getBoolean("is_internal")));
/*  50: 44 */       channel.setHostAgntKindCd(rs.getString("host_agnt_kind_cd"));
/*  51: 45 */       channel.setIsSeparateInternal(Boolean.valueOf(rs.getBoolean("is_separate_internal")));
/*  52: 46 */       list.add(channel);
/*  53:    */     }
/*  54: 49 */     return list;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void synchronizedChannel(Integer channelId)
/*  58:    */     throws Exception
/*  59:    */   {
/*  60: 53 */     if (channelId == null) {
/*  61: 54 */       throw new Exception("未給通路ID");
/*  62:    */     }
/*  63: 57 */     String sql = " EXEC dbo.account_sync_job " + channelId;
/*  64: 58 */     executeSQL(sql);
/*  65:    */     
/*  66:    */ 
/*  67: 61 */     sql = " EXEC dbo.group_sync_job " + channelId;
/*  68: 62 */     executeSQL(sql);
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void revokedAccount(Integer channelId, String hostAgntKindCd_list, String prefixList, SynchronousColumnSetting setting)
/*  72:    */     throws SQLException
/*  73:    */   {
/*  74: 67 */     String sql2 = 
/*  75: 68 */       " SELECT count(1) FROM " + setting.getTableName() + 
/*  76: 69 */       " WHERE 1 = 1 " + 
/*  77: 70 */       " AND " + setting.getAgntIdName() + " = a.account_id " + 
/*  78: 71 */       " AND agnt_kind_cd IN (" + hostAgntKindCd_list + ") ";
/*  79: 72 */     if ((!hostAgntKindCd_list.contains("AGS")) && (!hostAgntKindCd_list.contains("AGT"))) {
/*  80: 73 */       sql2 = sql2 + " AND (" + prefixList + ") ";
/*  81:    */     }
/*  82: 76 */     String sql = 
/*  83: 77 */       " UPDATE a SET a.expire_datetime = GETDATE()  FROM adm_account a  WHERE 1 = 1  AND 0 = ( " + 
/*  84:    */       
/*  85:    */ 
/*  86: 80 */       sql2 + ") " + 
/*  87: 81 */       " AND 1 = ( " + 
/*  88: 82 */       " SELECT COUNT(1) FROM adm_account AS b WHERE 1 = 1 " + 
/*  89: 83 */       " AND a.account_id = b.account_id " + 
/*  90: 84 */       " AND b.effective_datetime < GETDATE() " + 
/*  91: 85 */       " AND (b.expire_dateTime IS NULL OR b.expire_datetime > GETDATE()) " + 
/*  92: 86 */       " ) " + 
/*  93: 87 */       " AND effective_datetime < GETDATE() " + 
/*  94: 88 */       " AND (expire_dateTime IS NULL OR expire_datetime > GETDATE()) " + 
/*  95: 89 */       " AND channel_id = " + channelId + 
/*  96: 90 */       " AND created_by = 'SYNC_JOB' ";
/*  97:    */     
/*  98: 92 */     executeSQL(sql);
/*  99:    */   }
/* 100:    */   
/* 101:    */   public List<String> selectAgentCodePrefix(Integer channelId)
/* 102:    */     throws SQLException
/* 103:    */   {
/* 104: 96 */     List<String> list = new ArrayList();
/* 105: 97 */     String sql = " SELECT agent_code_prefix FROM adm_channel_agent_code_prefix WHERE channel_id = " + channelId;
/* 106:    */     
/* 107: 99 */     ResultSet rs = getResultSet(sql);
/* 108:101 */     while (rs.next()) {
/* 109:102 */       list.add(rs.getString("agent_code_prefix"));
/* 110:    */     }
/* 111:104 */     return list;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void synchronousOtherAgntCode()
/* 115:    */     throws SQLException
/* 116:    */   {
/* 117:108 */     String sql = " exec dbo.allocate_nvested_agntCode_job ";
/* 118:109 */     executeSQL(sql);
/* 119:    */   }
/* 120:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.synchronous.dao.SynchronousDao
 * JD-Core Version:    0.7.0.1
 */