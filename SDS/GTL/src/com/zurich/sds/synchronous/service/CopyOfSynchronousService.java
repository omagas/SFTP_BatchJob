/*   1:    */ package com.zurich.sds.synchronous.service;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.synchronous.dao.SynchronousDao;
/*   4:    */ import com.zurich.sds.synchronous.module.SynchronousChannel;
/*   5:    */ import com.zurich.sds.synchronous.module.SynchronousColumnSetting;
/*   6:    */ import com.zurich.sds.tool.PropertiesTool;
/*   7:    */ import java.sql.SQLException;
/*   8:    */ import java.util.Calendar;
/*   9:    */ import java.util.List;
/*  10:    */ import java.util.Properties;
/*  11:    */ import org.apache.commons.lang.StringUtils;
/*  12:    */ import org.apache.log4j.Logger;
/*  13:    */ import org.apache.log4j.PropertyConfigurator;
/*  14:    */ 
/*  15:    */ public class CopyOfSynchronousService
/*  16:    */ {
/*  17: 18 */   Logger logger = Logger.getLogger(CopyOfSynchronousService.class);
/*  18:    */   SynchronousDao synchronousDao;
/*  19:    */   
/*  20:    */   private SynchronousDao getSynchronousDao()
/*  21:    */   {
/*  22: 22 */     if (this.synchronousDao == null) {
/*  23: 23 */       this.synchronousDao = new SynchronousDao();
/*  24:    */     }
/*  25: 25 */     return this.synchronousDao;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public CopyOfSynchronousService()
/*  29:    */   {
/*  30:    */     try
/*  31:    */     {
/*  32: 30 */       Properties pro = PropertiesTool.getProperties("Log4j.properties");
/*  33: 31 */       PropertyConfigurator.configure(pro);
/*  34:    */     }
/*  35:    */     catch (Exception e)
/*  36:    */     {
/*  37: 33 */       e.printStackTrace();
/*  38:    */     }
/*  39:    */   }
/*  40:    */   
/*  41:    */   public void revokedAccount(List<SynchronousChannel> channelList)
/*  42:    */     throws SQLException
/*  43:    */   {
/*  44: 38 */     for (SynchronousChannel channel : channelList) {
/*  45: 40 */       if (channel.getSyncable() != null) {
/*  46: 42 */         if (channel.getSyncable().booleanValue())
/*  47:    */         {
/*  48: 47 */           List<String> prefix = getSynchronousDao().selectAgentCodePrefix(channel.getChannelId());
/*  49:    */           
/*  50: 49 */           String prefixList = "";
/*  51: 50 */           for (int i = 0; i < prefix.size(); i++)
/*  52:    */           {
/*  53: 51 */             String pre = (String)prefix.get(i);
/*  54: 52 */             if (i == 0) {
/*  55: 53 */               prefixList = prefixList + " agnt_cd LIKE '" + pre + "%' ";
/*  56:    */             } else {
/*  57: 55 */               prefixList = prefixList + " OR agnt_cd LIKE '" + pre + "%' ";
/*  58:    */             }
/*  59:    */           }
/*  60: 59 */           if ((!StringUtils.isEmpty(prefixList)) || (
/*  61: 60 */             ((channel.getHostAgntKindCd().contains("AGT")) || (channel.getHostAgntKindCd().contains("AGS"))) && (!channel.getIsSeparateInternal().booleanValue())))
/*  62:    */           {
/*  63: 65 */             SynchronousColumnSetting setting = getSynchronousColumnSetting(channel.getHostAgntKindCd());
/*  64: 66 */             String hostAgntKindCdList = genHostAgntKindCdList(channel);
/*  65:    */             
/*  66: 68 */             getSynchronousDao().revokedAccount(channel.getChannelId(), hostAgntKindCdList, prefixList, setting);
/*  67:    */           }
/*  68:    */         }
/*  69:    */       }
/*  70:    */     }
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void synchronousChannel(List<SynchronousChannel> channelList)
/*  74:    */     throws Exception
/*  75:    */   {
/*  76:101 */     for (SynchronousChannel channel : channelList) {
/*  77:103 */       if ((channel.getIsInternal() == null) || (!channel.getIsInternal().booleanValue())) {
/*  78:107 */         if (channel.getChannelId().intValue() == 65) {
/*  79:111 */           if (channel.getSyncable() != null) {
/*  80:112 */             if (channel.getSyncable().booleanValue())
/*  81:    */             {
/*  82:113 */               long time1 = Calendar.getInstance().getTimeInMillis();
/*  83:    */               
/*  84:115 */               this.logger.info("同步通路：" + channel.getChannelId());
/*  85:116 */               getSynchronousDao().synchronizedChannel(channel.getChannelId());
/*  86:    */               
/*  87:118 */               long time2 = Calendar.getInstance().getTimeInMillis();
/*  88:119 */               long diff = time2 - time1;
/*  89:    */               
/*  90:121 */               long diffSec = diff / 1000L;
/*  91:122 */               this.logger.info("同步完成，共花時：" + diffSec + " seconds");
/*  92:    */             }
/*  93:    */             else
/*  94:    */             {
/*  95:124 */               this.logger.info("通路" + channel.getChannelId() + "設定為不同步");
/*  96:    */             }
/*  97:    */           }
/*  98:    */         }
/*  99:    */       }
/* 100:    */     }
/* 101:    */   }
/* 102:    */   
/* 103:    */   private SynchronousColumnSetting getSynchronousColumnSetting(String agntCode)
/* 104:    */   {
/* 105:132 */     SynchronousColumnSetting setting = new SynchronousColumnSetting();
/* 106:133 */     if ((agntCode.contains("ABX")) || 
/* 107:134 */       (agntCode.contains("ABB")) || 
/* 108:135 */       (agntCode.contains("AGS")) || 
/* 109:136 */       (agntCode.contains("AGT")) || 
/* 110:137 */       (agntCode.contains("CAW")) || 
/* 111:138 */       (agntCode.contains("CCD")) || 
/* 112:139 */       (agntCode.contains("NCA")))
/* 113:    */     {
/* 114:141 */       setting.setTableName("host_agnt_def_tb");
/* 115:142 */       setting.setAgntIdName("agnt_id");
/* 116:143 */       setting.setEmailName("agnt_email");
/* 117:144 */       setting.setName("agnt_nm");
/* 118:145 */       setting.setIntrAgntIdName("intragnt_id");
/* 119:    */     }
/* 120:147 */     else if ((agntCode.contains("CLF")) || 
/* 121:148 */       (agntCode.contains("CBK")))
/* 122:    */     {
/* 123:150 */       setting.setTableName("host_sales_def_tb");
/* 124:151 */       setting.setAgntIdName("sales_id");
/* 125:152 */       setting.setEmailName("sales_email");
/* 126:153 */       setting.setName("sales_nm");
/* 127:154 */       setting.setSalesCdName("sales_cd");
/* 128:    */     }
/* 129:156 */     return setting;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public List<SynchronousChannel> selectSyncableChannel()
/* 133:    */     throws SQLException
/* 134:    */   {
/* 135:160 */     return getSynchronousDao().selectSyncableChannel();
/* 136:    */   }
/* 137:    */   
/* 138:    */   private String genHostAgntKindCdList(SynchronousChannel channel)
/* 139:    */   {
/* 140:164 */     String hostAgntKindCd = channel.getHostAgntKindCd();
/* 141:165 */     String hostAgntKindCd_list = "";
/* 142:166 */     if (StringUtils.isNotBlank(hostAgntKindCd)) {
/* 143:167 */       if (hostAgntKindCd.contains(","))
/* 144:    */       {
/* 145:168 */         for (String s : hostAgntKindCd.split(",")) {
/* 146:169 */           hostAgntKindCd_list = hostAgntKindCd_list + "'" + s + "',";
/* 147:    */         }
/* 148:171 */         hostAgntKindCd_list = hostAgntKindCd_list.substring(0, hostAgntKindCd_list.lastIndexOf(","));
/* 149:    */       }
/* 150:    */       else
/* 151:    */       {
/* 152:173 */         hostAgntKindCd_list = "'" + hostAgntKindCd + "'";
/* 153:    */       }
/* 154:    */     }
/* 155:177 */     return hostAgntKindCd_list;
/* 156:    */   }
/* 157:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.synchronous.service.CopyOfSynchronousService
 * JD-Core Version:    0.7.0.1
 */