/*   1:    */ package com.zurich.sds.ftp.service;
/*   2:    */ 
/*   3:    */ import com.jcraft.jsch.Channel;
/*   4:    */ import com.jcraft.jsch.ChannelSftp;
/*   5:    */ import com.jcraft.jsch.JSch;
/*   6:    */ import com.jcraft.jsch.Session;
/*   7:    */ import com.zurich.sds.tool.PropertiesTool;
/*   8:    */ import java.io.File;
/*   9:    */ import java.io.FileInputStream;
/*  10:    */ import java.io.FileOutputStream;
/*  11:    */ import java.io.IOException;
/*  12:    */ import java.io.OutputStream;
/*  13:    */ import java.util.ArrayList;
/*  14:    */ import java.util.List;
/*  15:    */ import java.util.Properties;
/*  16:    */ import org.apache.commons.net.ftp.FTPClient;
/*  17:    */ import org.apache.commons.net.ftp.FTPFile;
/*  18:    */ import org.apache.commons.net.ftp.FTPReply;
/*  19:    */ 
/*  20:    */ public class FtpService
/*  21:    */ {
/*  22:    */   private String host;
/*  23:    */   private Integer port;
/*  24:    */   private String userName;
/*  25:    */   private String passWord;
/*  26:    */   private String updatePath;
/*  27:    */   
/*  28:    */   public FtpService()
/*  29:    */   {
/*  30:    */     try
/*  31:    */     {
/*  32: 33 */       Properties properties = PropertiesTool.getProperties("FtpSetting.properties");
/*  33:    */       
/*  34: 35 */       this.host = ((String)properties.get("host"));
/*  35: 36 */       this.port = Integer.valueOf((String)properties.get("port"));
/*  36: 37 */       this.userName = ((String)properties.get("userName"));
/*  37: 38 */       this.passWord = ((String)properties.get("passWord"));
/*  38: 39 */       this.updatePath = ((String)properties.get("updatePath"));
/*  39:    */     }
/*  40:    */     catch (Exception e)
/*  41:    */     {
/*  42: 42 */       e.printStackTrace();
/*  43:    */     }
/*  44:    */   }
/*  45:    */   
/*  46:    */   public Boolean uploadFile(String filePath, String fileName, String newFileName)
/*  47:    */     throws Exception
/*  48:    */   {
/*  49: 47 */     Boolean success = Boolean.FALSE;
/*  50:    */     
/*  51: 49 */     Session session = null;
/*  52: 50 */     Channel channel = null;
/*  53:    */     
/*  54:    */ 
/*  55: 53 */     JSch jsch = new JSch();
/*  56:    */     
/*  57:    */ 
/*  58: 56 */     session = jsch.getSession(this.userName, this.host, this.port.intValue());
/*  59: 58 */     if (session == null) {
/*  60: 59 */       throw new Exception("session is null");
/*  61:    */     }
/*  62: 63 */     session.setPassword(this.passWord);
/*  63:    */     
/*  64: 65 */     session.setConfig("StrictHostKeyChecking", "no");
/*  65:    */     
/*  66: 67 */     session.connect(30000);
/*  67:    */     try
/*  68:    */     {
/*  69: 71 */       channel = session.openChannel("sftp");
/*  70: 72 */       channel.connect(1000);
/*  71: 73 */       ChannelSftp sftp = (ChannelSftp)channel;
/*  72:    */       
/*  73:    */ 
/*  74: 76 */       sftp.cd(this.updatePath);
/*  75:    */       
/*  76: 78 */       File file = new File(filePath + fileName);
/*  77: 79 */       FileInputStream input = new FileInputStream(file);
/*  78: 80 */       input.read();
/*  79:    */       
/*  80:    */ 
/*  81: 83 */       newFileName = new String(newFileName.getBytes(), "UTF-8");
/*  82: 84 */       sftp.put(input, newFileName);
/*  83:    */       
/*  84: 86 */       input.close();
/*  85:    */       
/*  86: 88 */       success = Boolean.TRUE;
/*  87:    */     }
/*  88:    */     catch (IOException e)
/*  89:    */     {
/*  90: 90 */       throw e;
/*  91:    */     }
/*  92:    */     finally
/*  93:    */     {
/*  94: 92 */       if ((session != null) && (session.isConnected())) {
/*  95: 93 */         session.disconnect();
/*  96:    */       }
/*  97: 95 */       if ((channel != null) && (channel.isConnected())) {
/*  98: 96 */         channel.disconnect();
/*  99:    */       }
/* 100:    */     }
/* 101:100 */     return success;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public Boolean downFile(String remotePath, String fileName, String localPath)
/* 105:    */   {
/* 106:238 */     Boolean success = Boolean.FALSE;
/* 107:    */     
/* 108:240 */     FTPClient ftp = new FTPClient();
/* 109:    */     try
/* 110:    */     {
/* 111:245 */       ftp.connect(this.host, this.port.intValue());
/* 112:    */       
/* 113:247 */       ftp.login(this.userName, this.passWord);
/* 114:248 */       Integer reply = Integer.valueOf(ftp.getReplyCode());
/* 115:249 */       if (!FTPReply.isPositiveCompletion(reply.intValue()))
/* 116:    */       {
/* 117:250 */         ftp.disconnect();
/* 118:251 */         Boolean localBoolean1 = success;return localBoolean1;
/* 119:    */       }
/* 120:254 */       ftp.changeWorkingDirectory(remotePath);
/* 121:    */       
/* 122:256 */       FTPFile[] fileList = ftp.listFiles();
/* 123:258 */       for (FTPFile file : fileList) {
/* 124:259 */         if (file.getName().equals(fileName))
/* 125:    */         {
/* 126:261 */           File localFile = new File(localPath + "/" + file.getName());
/* 127:    */           
/* 128:263 */           OutputStream os = new FileOutputStream(localFile);
/* 129:    */           
/* 130:265 */           ftp.retrieveFile(file.getName(), os);
/* 131:266 */           os.close();
/* 132:    */         }
/* 133:    */       }
/* 134:270 */       ftp.logout();
/* 135:    */       
/* 136:272 */       success = Boolean.TRUE;
/* 137:    */     }
/* 138:    */     catch (IOException e)
/* 139:    */     {
/* 140:275 */       e.printStackTrace();
/* 141:277 */       if (ftp.isConnected()) {
/* 142:    */         try
/* 143:    */         {
/* 144:279 */           ftp.disconnect();
/* 145:    */         }
/* 146:    */         catch (IOException localIOException2) {}
/* 147:    */       }
/* 148:    */     }
/* 149:    */     finally
/* 150:    */     {
/* 151:277 */       if (ftp.isConnected()) {
/* 152:    */         try
/* 153:    */         {
/* 154:279 */           ftp.disconnect();
/* 155:    */         }
/* 156:    */         catch (IOException localIOException3) {}
/* 157:    */       }
/* 158:    */     }
/* 159:285 */     return success;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public List<String> getFileNameList(String remotePath)
/* 163:    */     throws Exception
/* 164:    */   {
/* 165:289 */     List<String> fileNameList = new ArrayList();
/* 166:    */     
/* 167:291 */     FTPClient ftp = new FTPClient();
/* 168:    */     try
/* 169:    */     {
/* 170:296 */       ftp.connect(this.host, this.port.intValue());
/* 171:    */       
/* 172:298 */       ftp.login(this.userName, this.passWord);
/* 173:299 */       Integer reply = Integer.valueOf(ftp.getReplyCode());
/* 174:300 */       if (!FTPReply.isPositiveCompletion(reply.intValue()))
/* 175:    */       {
/* 176:301 */         ftp.disconnect();
/* 177:302 */         throw new Exception("FTP登入失敗");
/* 178:    */       }
/* 179:306 */       ftp.changeWorkingDirectory(remotePath);
/* 180:    */       
/* 181:308 */       FTPFile[] fileList = ftp.listFiles();
/* 182:310 */       for (FTPFile file : fileList) {
/* 183:311 */         fileNameList.add(file.getName());
/* 184:    */       }
/* 185:314 */       ftp.logout();
/* 186:    */     }
/* 187:    */     catch (IOException e)
/* 188:    */     {
/* 189:316 */       e.printStackTrace();
/* 190:318 */       if (ftp.isConnected()) {
/* 191:    */         try
/* 192:    */         {
/* 193:320 */           ftp.disconnect();
/* 194:    */         }
/* 195:    */         catch (IOException localIOException1) {}
/* 196:    */       }
/* 197:    */     }
/* 198:    */     finally
/* 199:    */     {
/* 200:318 */       if (ftp.isConnected()) {
/* 201:    */         try
/* 202:    */         {
/* 203:320 */           ftp.disconnect();
/* 204:    */         }
/* 205:    */         catch (IOException localIOException2) {}
/* 206:    */       }
/* 207:    */     }
/* 208:326 */     return fileNameList;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public Boolean deleteFile(String remotePath, String fileName)
/* 212:    */   {
/* 213:331 */     Boolean success = Boolean.FALSE;
/* 214:    */     
/* 215:333 */     FTPClient ftp = new FTPClient();
/* 216:    */     try
/* 217:    */     {
/* 218:338 */       ftp.connect(this.host, this.port.intValue());
/* 219:    */       
/* 220:340 */       ftp.login(this.userName, this.passWord);
/* 221:341 */       Integer reply = Integer.valueOf(ftp.getReplyCode());
/* 222:342 */       if (!FTPReply.isPositiveCompletion(reply.intValue()))
/* 223:    */       {
/* 224:343 */         ftp.disconnect();
/* 225:344 */         Boolean localBoolean1 = success;return localBoolean1;
/* 226:    */       }
/* 227:347 */       ftp.changeWorkingDirectory(remotePath);
/* 228:    */       
/* 229:    */ 
/* 230:350 */       ftp.deleteFile(fileName);
/* 231:    */       
/* 232:    */ 
/* 233:353 */       ftp.logout();
/* 234:    */       
/* 235:355 */       success = Boolean.TRUE;
/* 236:    */     }
/* 237:    */     catch (IOException e)
/* 238:    */     {
/* 239:358 */       e.printStackTrace();
/* 240:360 */       if (ftp.isConnected()) {
/* 241:    */         try
/* 242:    */         {
/* 243:362 */           ftp.disconnect();
/* 244:    */         }
/* 245:    */         catch (IOException localIOException2) {}
/* 246:    */       }
/* 247:    */     }
/* 248:    */     finally
/* 249:    */     {
/* 250:360 */       if (ftp.isConnected()) {
/* 251:    */         try
/* 252:    */         {
/* 253:362 */           ftp.disconnect();
/* 254:    */         }
/* 255:    */         catch (IOException localIOException3) {}
/* 256:    */       }
/* 257:    */     }
/* 258:368 */     return success;
/* 259:    */   }
/* 260:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ftp.service.FtpService
 * JD-Core Version:    0.7.0.1
 */