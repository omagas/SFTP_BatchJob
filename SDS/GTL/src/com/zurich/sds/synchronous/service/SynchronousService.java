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
/*  15:    */ public class SynchronousService
/*  16:    */ {
/*  17: 18 */   Logger logger = Logger.getLogger(SynchronousService.class);
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
/*  28:    */   public SynchronousService()
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
/*  76: 76 */     for (SynchronousChannel channel : channelList) {
/*  77: 78 */       if ((channel.getIsInternal() == null) || (channel.getIsInternal().booleanValue())) {
/*  78: 82 */         if (channel.getSyncable() != null) {
/*  79: 83 */           if (channel.getSyncable().booleanValue())
/*  80:    */           {
/*  81: 84 */             long time1 = Calendar.getInstance().getTimeInMillis();
/*  82:    */             
/*  83: 86 */             this.logger.info("同步通路：" + channel.getChannelId());
/*  84: 87 */             getSynchronousDao().synchronizedChannel(channel.getChannelId());
/*  85:    */             
/*  86: 89 */             long time2 = Calendar.getInstance().getTimeInMillis();
/*  87: 90 */             long diff = time2 - time1;
/*  88:    */             
/*  89: 92 */             long diffSec = diff / 1000L;
/*  90: 93 */             this.logger.info("同步完成，共花時：" + diffSec + " seconds");
/*  91:    */           }
/*  92:    */           else
/*  93:    */           {
/*  94: 95 */             this.logger.info("通路" + channel.getChannelId() + "設定為不同步");
/*  95:    */           }
/*  96:    */         }
/*  97:    */       }
/*  98:    */     }
/*  99:100 */     for (SynchronousChannel channel : channelList) {
/* 100:102 */       if ((channel.getIsInternal() == null) || (!channel.getIsInternal().booleanValue())) {
/* 101:106 */         if (channel.getSyncable() != null) {
/* 102:107 */           if (channel.getSyncable().booleanValue())
/* 103:    */           {
/* 104:108 */             long time1 = Calendar.getInstance().getTimeInMillis();
/* 105:    */             
/* 106:110 */             this.logger.info("同步通路：" + channel.getChannelId());
/* 107:111 */             getSynchronousDao().synchronizedChannel(channel.getChannelId());
/* 108:    */             
/* 109:113 */             long time2 = Calendar.getInstance().getTimeInMillis();
/* 110:114 */             long diff = time2 - time1;
/* 111:    */             
/* 112:116 */             long diffSec = diff / 1000L;
/* 113:117 */             this.logger.info("同步完成，共花時：" + diffSec + " seconds");
/* 114:    */           }
/* 115:    */           else
/* 116:    */           {
/* 117:119 */             this.logger.info("通路" + channel.getChannelId() + "設定為不同步");
/* 118:    */           }
/* 119:    */         }
/* 120:    */       }
/* 121:    */     }
/* 122:    */   }
/* 123:    */   
/* 124:    */   private SynchronousColumnSetting getSynchronousColumnSetting(String agntCode)
/* 125:    */   {
/* 126:127 */     SynchronousColumnSetting setting = new SynchronousColumnSetting();
/* 127:128 */     if ((agntCode.contains("ABX")) || 
/* 128:129 */       (agntCode.contains("ABB")) || 
/* 129:130 */       (agntCode.contains("AGS")) || 
/* 130:131 */       (agntCode.contains("AGT")) || 
/* 131:132 */       (agntCode.contains("CAW")) || 
/* 132:133 */       (agntCode.contains("CCD")) || 
/* 133:134 */       (agntCode.contains("NCA")))
/* 134:    */     {
/* 135:136 */       setting.setTableName("host_agnt_def_tb");
/* 136:137 */       setting.setAgntIdName("agnt_id");
/* 137:138 */       setting.setEmailName("agnt_email");
/* 138:139 */       setting.setName("agnt_nm");
/* 139:140 */       setting.setIntrAgntIdName("intragnt_id");
/* 140:    */     }
/* 141:142 */     else if ((agntCode.contains("CLF")) || 
/* 142:143 */       (agntCode.contains("CBK")))
/* 143:    */     {
/* 144:145 */       setting.setTableName("host_sales_def_tb");
/* 145:146 */       setting.setAgntIdName("sales_id");
/* 146:147 */       setting.setEmailName("sales_email");
/* 147:148 */       setting.setName("sales_nm");
/* 148:149 */       setting.setSalesCdName("sales_cd");
/* 149:    */     }
/* 150:151 */     return setting;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public List<SynchronousChannel> selectSyncableChannel()
/* 154:    */     throws SQLException
/* 155:    */   {
/* 156:155 */     return getSynchronousDao().selectSyncableChannel();
/* 157:    */   }
/* 158:    */   
/* 159:    */   private String genHostAgntKindCdList(SynchronousChannel channel)
/* 160:    */   {
/* 161:159 */     String hostAgntKindCd = channel.getHostAgntKindCd();
/* 162:160 */     String hostAgntKindCd_list = "";
/* 163:161 */     if (StringUtils.isNotBlank(hostAgntKindCd)) {
/* 164:162 */       if (hostAgntKindCd.contains(","))
/* 165:    */       {
/* 166:163 */         for (String s : hostAgntKindCd.split(",")) {
/* 167:164 */           hostAgntKindCd_list = hostAgntKindCd_list + "'" + s + "',";
/* 168:    */         }
/* 169:166 */         hostAgntKindCd_list = hostAgntKindCd_list.substring(0, hostAgntKindCd_list.lastIndexOf(","));
/* 170:    */       }
/* 171:    */       else
/* 172:    */       {
/* 173:168 */         hostAgntKindCd_list = "'" + hostAgntKindCd + "'";
/* 174:    */       }
/* 175:    */     }
/* 176:172 */     return hostAgntKindCd_list;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void synchronousOtherAgntCode()
/* 180:    */     throws SQLException
/* 181:    */   {
/* 182:176 */     getSynchronousDao().synchronousOtherAgntCode();
/* 183:    */   }
/* 184:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.synchronous.service.SynchronousService
 * JD-Core Version:    0.7.0.1
 */