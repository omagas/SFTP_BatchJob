/*   1:    */ package com.zurich.sds.mail.service;
/*   2:    */ 
/*   3:    */ import com.zurich.core.dao.DatabaseDao;
/*   4:    */ import com.zurich.sds.mail.model.MailBean;
/*   5:    */ import com.zurich.sds.mail.model.MailExtraBean;
/*   6:    */ import com.zurich.sds.tool.PropertiesTool;
/*   7:    */ import java.io.File;
/*   8:    */ import java.io.PrintStream;
/*   9:    */ import java.sql.ResultSet;
/*  10:    */ import java.sql.SQLException;
/*  11:    */ import java.text.SimpleDateFormat;
/*  12:    */ import java.util.ArrayList;
/*  13:    */ import java.util.Date;
/*  14:    */ import java.util.List;
/*  15:    */ import java.util.Properties;
/*  16:    */ import org.apache.commons.lang.StringUtils;
/*  17:    */ import org.apache.log4j.Logger;
/*  18:    */ import org.apache.log4j.PropertyConfigurator;
/*  19:    */ 
/*  20:    */ public class MailService
/*  21:    */ {
/*  22:    */   private DatabaseDao databaseDao;
/*  23: 23 */   Logger logger = Logger.getLogger(MailService.class);
/*  24:    */   
/*  25:    */   public MailService()
/*  26:    */   {
/*  27: 26 */     this.databaseDao = new DatabaseDao();
/*  28:    */     try
/*  29:    */     {
/*  30: 28 */       Properties pro = PropertiesTool.getProperties("Log4j.properties");
/*  31: 29 */       PropertyConfigurator.configure(pro);
/*  32:    */     }
/*  33:    */     catch (Exception e)
/*  34:    */     {
/*  35: 31 */       e.printStackTrace();
/*  36:    */     }
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void mailServiceClose()
/*  40:    */   {
/*  41: 36 */     this.databaseDao.releaseConnection();
/*  42:    */   }
/*  43:    */   
/*  44:    */   public List<MailBean> getMailNotSend()
/*  45:    */   {
/*  46: 40 */     List<MailBean> result = new ArrayList();
/*  47: 41 */     ResultSet resultSet = null;
/*  48: 42 */     StringBuffer sql = new StringBuffer();
/*  49: 43 */     sql.append(" SELECT * FROM ADM_mail_tb WHERE mail_status = 'N' ");
/*  50:    */     try
/*  51:    */     {
/*  52: 45 */       resultSet = this.databaseDao.getResultSet(sql.toString());
/*  53: 46 */       while (resultSet.next())
/*  54:    */       {
/*  55: 47 */         MailBean mailBean = new MailBean();
/*  56: 48 */         mailBean.setMailId(resultSet.getString("mail_id"));
/*  57: 49 */         mailBean.setMailMainAddress(resultSet
/*  58: 50 */           .getString("mail_main_address"));
/*  59: 51 */         mailBean.setMailSubject(resultSet.getString("mail_subject"));
/*  60: 52 */         mailBean.setMailBody(resultSet.getString("mail_body"));
/*  61: 53 */         mailBean.setMailStatus(resultSet.getString("mail_status"));
/*  62: 54 */         mailBean.setInputTime(resultSet.getTimestamp("input_time"));
/*  63: 55 */         mailBean.setOutputTime(resultSet.getTimestamp("output_time"));
/*  64: 56 */         result.add(mailBean);
/*  65:    */       }
/*  66:    */     }
/*  67:    */     catch (SQLException e)
/*  68:    */     {
/*  69: 59 */       this.logger.error("Exception:", e);
/*  70: 60 */       e.printStackTrace();
/*  71:    */     }
/*  72: 62 */     return result;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public List<MailExtraBean> getMailExtra(String id, String type)
/*  76:    */   {
/*  77: 66 */     List<MailExtraBean> result = new ArrayList();
/*  78: 67 */     ResultSet resultSet = null;
/*  79: 68 */     StringBuffer sql = new StringBuffer();
/*  80: 69 */     sql.append(" SELECT * from ADM_mail_extra_tb  WHERE mail_EXTRA_id like '" + 
/*  81: 70 */       id + "%' " + 
/*  82: 71 */       " AND content_type = '" + type + "' ");
/*  83:    */     try
/*  84:    */     {
/*  85: 73 */       resultSet = this.databaseDao.getResultSet(sql.toString());
/*  86: 74 */       while (resultSet.next())
/*  87:    */       {
/*  88: 75 */         MailExtraBean mailExtraBean = new MailExtraBean();
/*  89: 76 */         mailExtraBean.setMailExtraId(resultSet.getString("mail_EXTRA_id"));
/*  90: 77 */         mailExtraBean.setContentType(resultSet.getString("Content_Type"));
/*  91: 78 */         mailExtraBean.setExtraContent(resultSet.getString("extra_content"));
/*  92: 79 */         result.add(mailExtraBean);
/*  93:    */       }
/*  94:    */     }
/*  95:    */     catch (SQLException e)
/*  96:    */     {
/*  97: 82 */       this.logger.error("Exception:", e);
/*  98: 83 */       e.printStackTrace();
/*  99:    */     }
/* 100: 85 */     return result;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void inputMail(MailBean mailBean)
/* 104:    */     throws SQLException
/* 105:    */   {
/* 106: 89 */     if ((mailBean.getMailId() == null) || 
/* 107: 90 */       (mailBean.getMailMainAddress() == null) || 
/* 108: 91 */       (mailBean.getMailSubject() == null) || 
/* 109: 92 */       (mailBean.getMailBody() == null))
/* 110:    */     {
/* 111: 93 */       this.logger.info("格式不對或某些必要值為空");
/* 112: 94 */       return;
/* 113:    */     }
/* 114: 96 */     StringBuffer sql = new StringBuffer();
/* 115: 97 */     sql.append(" INSERT INTO ADM_Mail_tb ");
/* 116: 98 */     sql.append(" (mail_id, mail_main_Address, mail_subject, mail_body, input_Time) ");
/* 117: 99 */     sql.append(" VALUES ");
/* 118:100 */     sql.append(" ( ");
/* 119:101 */     sql = nullOrSql(sql, mailBean.getMailId());
/* 120:102 */     sql = nullOrSql(sql, mailBean.getMailMainAddress());
/* 121:103 */     sql = nullOrSql(sql, mailBean.getMailSubject());
/* 122:104 */     sql = nullOrSql(sql, mailBean.getMailBody());
/* 123:105 */     sql.append(" GETDATE() ");
/* 124:106 */     sql.append(" ) ");
/* 125:107 */     this.databaseDao.executeSQL(sql.toString());
/* 126:108 */     if (mailBean.getMailOtherAddress() != null)
/* 127:    */     {
/* 128:109 */       int j = 1;
/* 129:110 */       for (String val : mailBean.getMailOtherAddress())
/* 130:    */       {
/* 131:111 */         StringBuffer sqlAddress = new StringBuffer();
/* 132:112 */         sqlAddress.append(" INSERT INTO adm_mail_extra_tb ");
/* 133:113 */         sqlAddress.append(" (mail_Extra_id, extra_content, content_type) ");
/* 134:114 */         sqlAddress.append(" VALUES ");
/* 135:115 */         sqlAddress.append(" ( ");
/* 136:116 */         sqlAddress = nullOrSql(sqlAddress, mailBean.getMailId() + "a" + j);
/* 137:117 */         sqlAddress = nullOrSql(sqlAddress, val);
/* 138:118 */         sqlAddress.append(" 'A' ");
/* 139:119 */         sqlAddress.append(" ) ");
/* 140:120 */         System.out.println(sqlAddress.toString());
/* 141:121 */         this.databaseDao.executeSQL(sqlAddress.toString());
/* 142:122 */         j++;
/* 143:    */       }
/* 144:    */     }
/* 145:125 */     if (mailBean.getMailFiles() != null)
/* 146:    */     {
/* 147:126 */       int i = 1;
/* 148:127 */       for (String val : mailBean.getMailFiles())
/* 149:    */       {
/* 150:128 */         StringBuffer sqlFiles = new StringBuffer();
/* 151:129 */         sqlFiles.append(" INSERT INTO adm_mail_extra_tb ");
/* 152:130 */         sqlFiles.append(" (mail_extra_id, extra_content, content_type) ");
/* 153:131 */         sqlFiles.append(" VALUES ");
/* 154:132 */         sqlFiles.append(" ( ");
/* 155:133 */         sqlFiles = nullOrSql(sqlFiles, mailBean.getMailId() + "f" + i);
/* 156:134 */         sqlFiles = nullOrSql(sqlFiles, val);
/* 157:135 */         sqlFiles.append(" 'F' ");
/* 158:136 */         sqlFiles.append(" ) ");
/* 159:137 */         System.out.println(sqlFiles.toString());
/* 160:138 */         this.databaseDao.executeSQL(sqlFiles.toString());
/* 161:139 */         i++;
/* 162:    */       }
/* 163:    */     }
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void updateMailStatus(String id, String status)
/* 167:    */     throws SQLException
/* 168:    */   {
/* 169:146 */     StringBuffer sql = new StringBuffer();
/* 170:147 */     sql.append(" UPDATE adm_mail_tb ");
/* 171:148 */     sql.append(" SET mail_status = '" + status + "', output_time = GETDATE() ");
/* 172:149 */     sql.append(" WHERE  MAIL_ID = '" + id + "'");
/* 173:150 */     this.databaseDao.executeSQL(sql.toString());
/* 174:    */   }
/* 175:    */   
/* 176:    */   public void deleteFile(List<MailExtraBean> deleteFileList)
/* 177:    */   {
/* 178:157 */     if ((deleteFileList == null) || (deleteFileList.size() == 0)) {
/* 179:158 */       return;
/* 180:    */     }
/* 181:161 */     for (MailExtraBean mailExtraBean : deleteFileList)
/* 182:    */     {
/* 183:162 */       File file = new File(mailExtraBean.getExtraContent());
/* 184:163 */       if (file.exists()) {
/* 185:164 */         file.delete();
/* 186:    */       }
/* 187:    */     }
/* 188:    */   }
/* 189:    */   
/* 190:    */   public StringBuffer nullOrSql(StringBuffer sql, String val)
/* 191:    */   {
/* 192:170 */     if (!StringUtils.isEmpty(val)) {
/* 193:171 */       sql.append(" '" + val + "', ");
/* 194:    */     }
/* 195:173 */     return sql;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public String genMailId()
/* 199:    */   {
/* 200:177 */     SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
/* 201:178 */     Date current = new Date();
/* 202:179 */     return "M" + sdf.format(current);
/* 203:    */   }
/* 204:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.mail.service.MailService
 * JD-Core Version:    0.7.0.1
 */