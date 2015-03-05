/*   1:    */ package com.zurich.sds.lawbroker.action;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.ftp.service.FtpService;
/*   4:    */ import com.zurich.sds.lawbroker.module.InsuredRoster;
/*   5:    */ import com.zurich.sds.lawbroker.module.Policy;
/*   6:    */ import com.zurich.sds.lawbroker.service.LawbrokerService;
/*   7:    */ import com.zurich.sds.mail.model.MailBean;
/*   8:    */ import com.zurich.sds.mail.service.MailService;
/*   9:    */ import com.zurich.sds.tool.PropertiesTool;
/*  10:    */ import java.io.BufferedWriter;
/*  11:    */ import java.io.File;
/*  12:    */ import java.io.FileOutputStream;
/*  13:    */ import java.io.OutputStreamWriter;
/*  14:    */ import java.io.PrintWriter;
/*  15:    */ import java.io.StringWriter;
/*  16:    */ import java.io.Writer;
/*  17:    */ import java.text.SimpleDateFormat;
/*  18:    */ import java.util.ArrayList;
/*  19:    */ import java.util.Arrays;
/*  20:    */ import java.util.Date;
/*  21:    */ import java.util.List;
/*  22:    */ import java.util.Properties;
/*  23:    */ import org.apache.commons.lang.StringUtils;
/*  24:    */ import org.apache.log4j.Logger;
/*  25:    */ import org.apache.log4j.PropertyConfigurator;
/*  26:    */ 
/*  27:    */ public class LawbrokerAction
/*  28:    */ {
/*  29: 31 */   protected static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*  30: 32 */   protected String today = sdf.format(new Date());
/*  31: 33 */   protected Properties properties = PropertiesTool.getProperties("MailSetting.properties");
/*  32: 34 */   protected String filePath = this.properties.getProperty("eTmp.dir");
/*  33: 35 */   private String policyFileName = this.properties.getProperty("policyFileName").replace("{0}", this.today);
/*  34: 36 */   private String policyFileTempName = "temp_" + this.policyFileName;
/*  35: 37 */   private String rosterFileName = this.properties.getProperty("rosterFileName").replace("{0}", this.today);
/*  36: 38 */   private String rosterFileTempName = "temp_" + this.rosterFileName;
/*  37: 39 */   private String updatePolicyFileName = this.properties.getProperty("updatePolicyFileName");
/*  38: 40 */   private String updateRosterFileName = this.properties.getProperty("updateRosterFileName");
/*  39: 41 */   protected String exceptionFileName = this.properties.getProperty("exceptionFileName").replace("{0}", this.today);
/*  40: 42 */   private String labrMainAddress2Labr = this.properties.getProperty("labrMainAddress2Labr");
/*  41: 43 */   private String labrMainAddress = this.properties.getProperty("labrMainAddress");
/*  42: 44 */   private String labrCarbonCopyAddress = this.properties.getProperty("labrCarbonCopyAddress");
/*  43: 45 */   private static Logger logger = Logger.getLogger(LawbrokerAction.class);
/*  44:    */   
/*  45:    */   public LawbrokerAction()
/*  46:    */   {
/*  47: 48 */     Properties pro = PropertiesTool.getProperties("Log4j.properties");
/*  48: 49 */     PropertyConfigurator.configure(pro);
/*  49:    */   }
/*  50:    */   
/*  51:    */   public static void main(String[] args)
/*  52:    */     throws Exception
/*  53:    */   {
/*  54: 53 */     LawbrokerService lawbrokerService = new LawbrokerService();
/*  55: 54 */     LawbrokerAction lawbroker = new LawbrokerAction();
/*  56:    */     
/*  57: 56 */     List<Policy> totalPolicyList = new ArrayList();
/*  58: 57 */     List<InsuredRoster> totalRosterList = new ArrayList();
/*  59:    */     try
/*  60:    */     {
/*  61: 61 */       List<Policy> policyList = lawbrokerService.getPolicyDataList();
/*  62: 62 */       totalPolicyList.addAll(policyList);
/*  63:    */       
/*  64: 64 */       List<Policy> cancelPolicyDataList = lawbrokerService.getCancelPolicyDataList();
/*  65: 65 */       totalPolicyList.addAll(cancelPolicyDataList);
/*  66:    */       
/*  67:    */ 
/*  68: 68 */       List<InsuredRoster> rosterList = lawbrokerService.getRosterDataList();
/*  69: 69 */       totalRosterList.addAll(rosterList);
/*  70:    */       
/*  71: 71 */       List<InsuredRoster> cancelRosterDataList = lawbrokerService.getCancelRosterDataList();
/*  72: 72 */       totalRosterList.addAll(cancelRosterDataList);
/*  73:    */       
/*  74:    */ 
/*  75:    */ 
/*  76: 76 */       Integer policySize = lawbrokerService.genPolicyCsv(totalPolicyList, lawbroker.filePath, lawbroker.policyFileName, lawbroker.policyFileTempName);
/*  77: 77 */       logger.info("建立主檔資料成功！");
/*  78:    */       
/*  79:    */ 
/*  80: 80 */       lawbrokerService.genRosterCsv(totalRosterList, lawbroker.filePath, lawbroker.rosterFileName, lawbroker.rosterFileTempName);
/*  81: 81 */       logger.info("建立名冊資料成功");
/*  82:    */       
/*  83:    */ 
/*  84: 84 */       lawbrokerService.updateFtpSendMk(policyList, cancelPolicyDataList);
/*  85:    */       
/*  86:    */ 
/*  87: 87 */       lawbrokerService.deleteFile(lawbroker.filePath, lawbroker.policyFileName);
/*  88: 88 */       lawbrokerService.renameFile(lawbroker.filePath, lawbroker.policyFileTempName, lawbroker.policyFileName);
/*  89:    */       
/*  90: 90 */       lawbrokerService.deleteFile(lawbroker.filePath, lawbroker.rosterFileName);
/*  91: 91 */       lawbrokerService.renameFile(lawbroker.filePath, lawbroker.rosterFileTempName, lawbroker.rosterFileName);
/*  92:    */     }
/*  93:    */     catch (Exception e)
/*  94:    */     {
/*  95: 93 */       e.printStackTrace();
/*  96: 94 */       logger.error("檔案建立失敗：", e);
/*  97:    */       
/*  98: 96 */       lawbrokerService.rollback();
/*  99:    */       
/* 100: 98 */       String message = e.getMessage();
/* 101: 99 */       String filePath = lawbroker.filePath;
/* 102:100 */       filePath = filePath.replace("/", "\\");
/* 103:101 */       if (message.contains(filePath)) {
/* 104:102 */         message = message.substring(message.indexOf(filePath) + filePath.length(), message.length());
/* 105:    */       }
/* 106:104 */       lawbroker.sendMail(message);
/* 107:    */       
/* 108:106 */       lawbrokerService.deleteFile(lawbroker.filePath, lawbroker.policyFileTempName);
/* 109:107 */       lawbrokerService.deleteFile(lawbroker.filePath, lawbroker.rosterFileTempName);
/* 110:    */       
/* 111:109 */       throw e;
/* 112:    */     }
/* 113:    */     try
/* 114:    */     {
/* 115:    */       Integer policySize;
/* 116:117 */       FtpService ftp = new FtpService();
/* 117:    */       
/* 118:119 */       ftp.uploadFile(lawbroker.filePath, lawbroker.policyFileName, lawbroker.updatePolicyFileName);
/* 119:120 */       logger.info("主檔FTP上傳成功");
/* 120:    */       
/* 121:122 */       ftp.uploadFile(lawbroker.filePath, lawbroker.rosterFileName, lawbroker.updateRosterFileName);
/* 122:123 */       logger.info("名冊FTP上傳成功");
/* 123:    */       
/* 124:    */ 
/* 125:126 */       lawbroker.sendFeedBacktCount(policySize.intValue());
/* 126:    */     }
/* 127:    */     catch (Exception e)
/* 128:    */     {
/* 129:137 */       e.printStackTrace();
/* 130:138 */       logger.error("FTP上傳失敗：", e);
/* 131:    */       
/* 132:    */ 
/* 133:    */ 
/* 134:    */ 
/* 135:143 */       lawbroker.sendMailHasFile(e);
/* 136:    */       
/* 137:145 */       throw e;
/* 138:    */     }
/* 139:    */   }
/* 140:    */   
/* 141:    */   protected void genExceptionLog(Throwable exception)
/* 142:    */     throws Exception
/* 143:    */   {
/* 144:153 */     Writer result = new StringWriter();
/* 145:154 */     PrintWriter printWriter = new PrintWriter(result);
/* 146:155 */     exception.printStackTrace(printWriter);
/* 147:    */     
/* 148:    */ 
/* 149:158 */     File file = new File(this.filePath + this.exceptionFileName);
/* 150:159 */     FileOutputStream out = new FileOutputStream(file);
/* 151:160 */     OutputStreamWriter osw = new OutputStreamWriter(out, "BIG5");
/* 152:    */     
/* 153:162 */     BufferedWriter bw = new BufferedWriter(osw);
/* 154:163 */     bw.write(result.toString());
/* 155:    */     
/* 156:165 */     bw.flush();
/* 157:    */     
/* 158:167 */     bw.close();
/* 159:168 */     osw.close();
/* 160:169 */     out.close();
/* 161:    */   }
/* 162:    */   
/* 163:    */   protected void sendMailHasFile(Throwable exception)
/* 164:    */     throws Exception
/* 165:    */   {
/* 166:191 */     genExceptionLog(exception);
/* 167:    */     
/* 168:193 */     MailService mailService = new MailService();
/* 169:    */     
/* 170:195 */     MailBean mailBean = new MailBean();
/* 171:196 */     mailBean.setMailMainAddress(this.properties.getProperty("labrMainAddress"));
/* 172:197 */     mailBean.setMailSubject("錠嵂回饋檔FTP傳送失敗");
/* 173:    */     
/* 174:    */ 
/* 175:200 */     mailBean.setMailId(mailService.genMailId());
/* 176:    */     
/* 177:202 */     mailBean.setMailBody("錯誤訊息詳見附件：" + this.exceptionFileName);
/* 178:    */     
/* 179:    */ 
/* 180:205 */     String labrCarbonCopyAddress = this.properties.getProperty("labrCarbonCopyAddress");
/* 181:206 */     List<String> mailOtherAddress = new ArrayList(Arrays.asList(labrCarbonCopyAddress.split(",")));
/* 182:207 */     mailBean.setMailOtherAddress(mailOtherAddress);
/* 183:    */     
/* 184:209 */     List<String> mailFiles = new ArrayList();
/* 185:210 */     mailFiles.add(this.filePath + this.exceptionFileName);
/* 186:211 */     mailBean.setMailFiles(mailFiles);
/* 187:    */     
/* 188:    */ 
/* 189:214 */     mailFiles.add(this.filePath + this.policyFileName);
/* 190:215 */     mailFiles.add(this.filePath + this.rosterFileName);
/* 191:    */     
/* 192:217 */     mailService.inputMail(mailBean);
/* 193:    */     
/* 194:219 */     mailService.mailServiceClose();
/* 195:    */   }
/* 196:    */   
/* 197:    */   protected void sendMail(String message)
/* 198:    */     throws Exception
/* 199:    */   {
/* 200:223 */     MailService mailService = new MailService();
/* 201:    */     
/* 202:225 */     MailBean mailBean = new MailBean();
/* 203:226 */     mailBean.setMailMainAddress(this.properties.getProperty("labrMainAddress"));
/* 204:227 */     mailBean.setMailSubject("錠嵂回饋檔FTP傳送失敗");
/* 205:    */     
/* 206:    */ 
/* 207:230 */     mailBean.setMailId(mailService.genMailId());
/* 208:    */     
/* 209:232 */     mailBean.setMailBody("錯誤訊息：" + message);
/* 210:    */     
/* 211:    */ 
/* 212:235 */     String labrCarbonCopyAddress = this.properties.getProperty("labrCarbonCopyAddress");
/* 213:236 */     List<String> mailOtherAddress = new ArrayList(Arrays.asList(labrCarbonCopyAddress.split(",")));
/* 214:237 */     mailBean.setMailOtherAddress(mailOtherAddress);
/* 215:    */     
/* 216:239 */     mailService.inputMail(mailBean);
/* 217:    */     
/* 218:241 */     mailService.mailServiceClose();
/* 219:    */   }
/* 220:    */   
/* 221:    */   private void sendFeedBacktCount(int count)
/* 222:    */     throws Exception
/* 223:    */   {
/* 224:245 */     MailService mailService = new MailService();
/* 225:    */     
/* 226:247 */     MailBean mailBean = new MailBean();
/* 227:    */     
/* 228:249 */     mailBean.setMailId(mailService.genMailId());
/* 229:251 */     if (count != 0)
/* 230:    */     {
/* 231:252 */       mailBean.setMailMainAddress(this.labrMainAddress2Labr);
/* 232:253 */       mailBean.setMailSubject("Zurich 旅平險回饋檔作業完成通知");
/* 233:    */       
/* 234:255 */       mailBean.setMailBody(
/* 235:256 */         "敬啟者:\n     今日旅平險回饋檔作業已完成" + 
/* 236:257 */         count + "筆\n" + 
/* 237:258 */         "                                     特此告知!\n" + 
/* 238:259 */         "                                    祝    工作順心愉快\n" + 
/* 239:260 */         "                                               蘇黎世產物保險");
/* 240:    */     }
/* 241:    */     else
/* 242:    */     {
/* 243:262 */       mailBean.setMailMainAddress(this.labrMainAddress);
/* 244:    */       
/* 245:    */ 
/* 246:265 */       List<String> mailOtherAddress = new ArrayList();
/* 247:266 */       if (!StringUtils.isBlank(this.labrCarbonCopyAddress)) {
/* 248:267 */         mailOtherAddress.addAll(Arrays.asList(this.labrCarbonCopyAddress.split(",")));
/* 249:    */       }
/* 250:269 */       mailBean.setMailOtherAddress(mailOtherAddress);
/* 251:    */       
/* 252:271 */       mailBean.setMailSubject("Zurich 旅平險回饋檔作業異常通知");
/* 253:    */       
/* 254:273 */       mailBean.setMailBody("回饋檔案件筆數為0，請盡速處理");
/* 255:    */     }
/* 256:276 */     mailService.inputMail(mailBean);
/* 257:    */     
/* 258:278 */     mailService.mailServiceClose();
/* 259:    */   }
/* 260:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.lawbroker.action.LawbrokerAction
 * JD-Core Version:    0.7.0.1
 */