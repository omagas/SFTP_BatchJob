/*   1:    */ package com.zurich.sds.mail.model;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.tool.PropertiesTool;
/*   4:    */ import java.io.File;
/*   5:    */ import java.io.PrintStream;
/*   6:    */ import java.util.ArrayList;
/*   7:    */ import java.util.List;
/*   8:    */ import java.util.Properties;
/*   9:    */ import java.util.Vector;
/*  10:    */ import lotus.domino.Database;
/*  11:    */ import lotus.domino.Document;
/*  12:    */ import lotus.domino.NotesException;
/*  13:    */ import lotus.domino.NotesFactory;
/*  14:    */ import lotus.domino.RichTextItem;
/*  15:    */ import lotus.domino.Session;
/*  16:    */ import org.apache.log4j.Logger;
/*  17:    */ 
/*  18:    */ public class NotesMail
/*  19:    */ {
/*  20: 22 */   protected Logger log = Logger.getLogger(NotesMail.class);
/*  21:    */   private String host;
/*  22:    */   private String hostPort;
/*  23:    */   private String user;
/*  24:    */   private String pwd;
/*  25:    */   private String nsf;
/*  26:    */   private String subject;
/*  27:    */   private List<String> sendTo;
/*  28:    */   private List<String> copyTo;
/*  29:    */   private List<String> replyTo;
/*  30:    */   private String body;
/*  31:    */   private Database mailDatabase;
/*  32:    */   private Document mailDoc;
/*  33:    */   private Vector<String> attachList;
/*  34:    */   private Session session;
/*  35:    */   
/*  36:    */   public NotesMail()
/*  37:    */   {
/*  38:    */     try
/*  39:    */     {
/*  40: 43 */       Properties properties = PropertiesTool.getProperties("MailSetting.properties");
/*  41:    */       
/*  42: 45 */       setHost((String)properties.get("host"));
/*  43: 46 */       setHostPort((String)properties.get("port"));
/*  44: 47 */       setUser((String)properties.get("userName"));
/*  45: 48 */       setPwd((String)properties.get("passWord"));
/*  46: 49 */       setNsf((String)properties.get("nsf"));
/*  47:    */     }
/*  48:    */     catch (Exception e)
/*  49:    */     {
/*  50: 59 */       e.printStackTrace();
/*  51:    */     }
/*  52:    */   }
/*  53:    */   
/*  54:    */   public static void main(String[] args)
/*  55:    */   {
/*  56: 65 */     Vector<String> userV = new Vector();
/*  57:    */     
/*  58:    */ 
/*  59:    */ 
/*  60: 69 */     userV.add("chunwadr@gmail.com");
/*  61:    */     
/*  62:    */ 
/*  63:    */ 
/*  64:    */ 
/*  65: 74 */     NotesMail NM = new NotesMail();
/*  66:    */     
/*  67: 76 */     NM.setHost("192.168.128.31");
/*  68: 77 */     NM.setHostPort("63148");
/*  69: 78 */     NM.setUser("twztd");
/*  70: 79 */     NM.setPwd("triumph");
/*  71: 80 */     NM.setNsf("mail/twztd.nsf");
/*  72:    */     
/*  73:    */ 
/*  74:    */ 
/*  75:    */ 
/*  76: 85 */     NM.setSubject("This is a test mail");
/*  77: 86 */     NM.setSendTo(userV);
/*  78: 87 */     NM.setBody("Notes測試資料WAAAde");
/*  79:    */     
/*  80: 89 */     String sendNotes = NM.sendNotes();
/*  81: 93 */     if ((sendNotes == null) || (sendNotes.equals(null))) {
/*  82: 94 */       System.out.println("Send Mail Success");
/*  83:    */     } else {
/*  84: 96 */       System.out.println(sendNotes);
/*  85:    */     }
/*  86:    */   }
/*  87:    */   
/*  88:    */   public void getNotesConnect()
/*  89:    */   {
/*  90:    */     try
/*  91:    */     {
/*  92:104 */       this.log.debug("Email Host port ---->" + this.host + ":" + this.hostPort);
/*  93:105 */       this.log.debug("Email User  Pwd ---->" + this.user + " " + this.pwd);
/*  94:106 */       this.log.debug("Email Nsf       ---->" + this.nsf);
/*  95:    */       
/*  96:108 */       String sior = NotesFactory.getIOR(this.host + ":" + this.hostPort);
/*  97:110 */       if ((this.session == null) || (!this.session.isValid()))
/*  98:    */       {
/*  99:111 */         this.session = NotesFactory.createSessionWithIOR(sior, this.user, this.pwd);
/* 100:112 */         this.log.debug("Email session   ---->" + this.session.isValid());
/* 101:113 */         this.mailDatabase = this.session.getDatabase(this.host, this.nsf);
/* 102:114 */         this.log.debug("Email Database  ---->" + this.mailDatabase.isOpen());
/* 103:    */       }
/* 104:    */     }
/* 105:    */     catch (Exception e)
/* 106:    */     {
/* 107:118 */       this.log.debug("Exceptiont ---->" + e.toString());
/* 108:119 */       System.out.println("Exceptiont ---->" + e.toString());
/* 109:    */     }
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void getNotesDisConnect()
/* 113:    */   {
/* 114:    */     try
/* 115:    */     {
/* 116:125 */       if (this.mailDatabase != null) {
/* 117:126 */         this.mailDatabase.recycle();
/* 118:    */       }
/* 119:128 */       if ((this.session != null) && (this.session.isValid())) {
/* 120:129 */         this.session.recycle();
/* 121:    */       }
/* 122:    */     }
/* 123:    */     catch (NotesException e)
/* 124:    */     {
/* 125:133 */       e.printStackTrace();
/* 126:    */     }
/* 127:    */   }
/* 128:    */   
/* 129:    */   public String sendNotes()
/* 130:    */   {
/* 131:139 */     String returnValue = null;
/* 132:    */     try
/* 133:    */     {
/* 134:150 */       getNotesConnect();
/* 135:    */       
/* 136:152 */       this.mailDoc = this.mailDatabase.createDocument();
/* 137:    */       
/* 138:154 */       this.mailDoc.replaceItemValue("Form", "Memo");
/* 139:155 */       this.mailDoc.replaceItemValue("Subject", this.subject);
/* 140:156 */       this.mailDoc.replaceItemValue("SendTo", this.sendTo);
/* 141:157 */       this.mailDoc.replaceItemValue("CopyTo", this.copyTo);
/* 142:158 */       this.mailDoc.replaceItemValue("ReplyTo", this.replyTo);
/* 143:    */       
/* 144:    */ 
/* 145:    */ 
/* 146:    */ 
/* 147:    */ 
/* 148:    */ 
/* 149:    */ 
/* 150:    */ 
/* 151:    */ 
/* 152:    */ 
/* 153:    */ 
/* 154:    */ 
/* 155:171 */       RichTextItem rtiAttach = this.mailDoc.createRichTextItem("attach");
/* 156:172 */       List<String> loseFile = new ArrayList();
/* 157:173 */       if (this.attachList != null) {
/* 158:174 */         for (String attach : this.attachList)
/* 159:    */         {
/* 160:175 */           File file = new File(attach);
/* 161:177 */           if (!file.exists()) {
/* 162:178 */             loseFile.add(file.getName());
/* 163:    */           } else {
/* 164:180 */             rtiAttach.embedObject(1454, null, attach, "attach");
/* 165:    */           }
/* 166:    */         }
/* 167:    */       }
/* 168:186 */       RichTextItem rtiBody = this.mailDoc.createRichTextItem("body");
/* 169:187 */       rtiBody.appendText(this.body);
/* 170:189 */       if (loseFile.size() != 0)
/* 171:    */       {
/* 172:190 */         rtiBody.addNewLine();
/* 173:191 */         rtiBody.addNewLine();
/* 174:192 */         rtiBody.appendText("找不到" + loseFile.toString() + "檔案，無法寄出");
/* 175:    */       }
/* 176:195 */       this.mailDoc.save(true, true);
/* 177:    */       
/* 178:197 */       this.mailDoc.send();
/* 179:    */     }
/* 180:    */     catch (Exception e)
/* 181:    */     {
/* 182:201 */       this.log.debug("Exceptiont ---->" + e.toString());
/* 183:202 */       returnValue = e.toString();
/* 184:    */     }
/* 185:218 */     return returnValue;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public void setHost(String host)
/* 189:    */   {
/* 190:222 */     this.host = host;
/* 191:    */   }
/* 192:    */   
/* 193:    */   public void setHostPort(String hostPort)
/* 194:    */   {
/* 195:226 */     this.hostPort = hostPort;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public void setUser(String user)
/* 199:    */   {
/* 200:230 */     this.user = user;
/* 201:    */   }
/* 202:    */   
/* 203:    */   public void setPwd(String pwd)
/* 204:    */   {
/* 205:234 */     this.pwd = pwd;
/* 206:    */   }
/* 207:    */   
/* 208:    */   public void setNsf(String nsf)
/* 209:    */   {
/* 210:238 */     this.nsf = nsf;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public void setSubject(String subject)
/* 214:    */   {
/* 215:242 */     this.subject = subject;
/* 216:    */   }
/* 217:    */   
/* 218:    */   public void setSendTo(List<String> sendTo)
/* 219:    */   {
/* 220:246 */     this.sendTo = sendTo;
/* 221:    */   }
/* 222:    */   
/* 223:    */   public void setCopyTo(List<String> copyTo)
/* 224:    */   {
/* 225:250 */     this.copyTo = copyTo;
/* 226:    */   }
/* 227:    */   
/* 228:    */   public void setReplyTo(List<String> replyTo)
/* 229:    */   {
/* 230:254 */     this.replyTo = replyTo;
/* 231:    */   }
/* 232:    */   
/* 233:    */   public void setBody(String body)
/* 234:    */   {
/* 235:258 */     this.body = body;
/* 236:    */   }
/* 237:    */   
/* 238:    */   public void setAttachList(Vector<String> attachList)
/* 239:    */   {
/* 240:262 */     this.attachList = attachList;
/* 241:    */   }
/* 242:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.mail.model.NotesMail
 * JD-Core Version:    0.7.0.1
 */