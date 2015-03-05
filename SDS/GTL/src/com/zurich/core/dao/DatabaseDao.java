/*   1:    */ package com.zurich.core.dao;
/*   2:    */ 
/*   3:    */ import com.zurich.core.dao.utils.BeanInfo;
/*   4:    */ import com.zurich.core.dao.utils.EntityMapping;
/*   5:    */ import com.zurich.core.dao.utils.ReflectBean;
/*   6:    */ import com.zurich.sds.tool.PropertiesTool;
/*   7:    */ import java.lang.reflect.Field;
/*   8:    */ import java.lang.reflect.InvocationTargetException;
/*   9:    */ import java.lang.reflect.Method;
/*  10:    */ import java.math.BigDecimal;
/*  11:    */ import java.sql.Connection;
/*  12:    */ import java.sql.DriverManager;
/*  13:    */ import java.sql.PreparedStatement;
/*  14:    */ import java.sql.ResultSet;
/*  15:    */ import java.sql.ResultSetMetaData;
/*  16:    */ import java.sql.SQLException;
/*  17:    */ import java.sql.Statement;
/*  18:    */ import java.util.ArrayList;
/*  19:    */ import java.util.HashMap;
/*  20:    */ import java.util.List;
/*  21:    */ import java.util.Map;
/*  22:    */ import java.util.Properties;
/*  23:    */ import java.util.Vector;
/*  24:    */ import org.apache.log4j.Logger;
/*  25:    */ import org.apache.log4j.PropertyConfigurator;
/*  26:    */ 
/*  27:    */ public class DatabaseDao
/*  28:    */ {
/*  29: 33 */   Logger logger = Logger.getLogger(DatabaseDao.class);
/*  30:    */   
/*  31:    */   private static enum ColumnMode
/*  32:    */   {
/*  33: 34 */     Column,  PrimaryKey,  Both;
/*  34:    */   }
/*  35:    */   
/*  36:    */   private static enum DMLType
/*  37:    */   {
/*  38: 35 */     Insert,  Update,  Delete,  Select;
/*  39:    */   }
/*  40:    */   
/*  41: 37 */   private Boolean autoCommit = Boolean.valueOf(true);
/*  42:    */   
/*  43:    */   public void setAutoCommit(Boolean autoCommit)
/*  44:    */   {
/*  45: 40 */     this.autoCommit = autoCommit;
/*  46: 41 */     if (this.conn != null) {
/*  47:    */       try
/*  48:    */       {
/*  49: 43 */         this.conn.setAutoCommit(autoCommit.booleanValue());
/*  50:    */       }
/*  51:    */       catch (Exception e)
/*  52:    */       {
/*  53: 45 */         e.printStackTrace();
/*  54:    */       }
/*  55:    */     }
/*  56:    */   }
/*  57:    */   
/*  58: 50 */   private Connection conn = null;
/*  59:    */   
/*  60:    */   public DatabaseDao()
/*  61:    */   {
/*  62:    */     try
/*  63:    */     {
/*  64: 54 */       Properties pro = PropertiesTool.getProperties("Log4j.properties");
/*  65: 55 */       PropertyConfigurator.configure(pro);
/*  66:    */     }
/*  67:    */     catch (Exception localException) {}
/*  68:    */   }
/*  69:    */   
/*  70:    */   private Connection getConnection()
/*  71:    */   {
/*  72: 68 */     Connection conn = null;
/*  73:    */     try
/*  74:    */     {
/*  75: 72 */       Properties properties = PropertiesTool.getProperties("DataBaseSetting.properties");
/*  76:    */       
/*  77: 74 */       Class.forName(properties.getProperty("jdbc.driver"));
/*  78: 75 */       String url = properties.getProperty("url");
/*  79: 76 */       String userName = properties.getProperty("userName");
/*  80: 77 */       String passWord = properties.getProperty("passWord");
/*  81: 78 */       conn = DriverManager.getConnection(url, userName, passWord);
/*  82: 79 */       conn.setAutoCommit(this.autoCommit.booleanValue());
/*  83: 80 */       this.conn = conn;
/*  84: 81 */       this.logger.info("數據庫連接成功！");
/*  85:    */     }
/*  86:    */     catch (Exception e)
/*  87:    */     {
/*  88: 83 */       this.logger.error("JDBC連線失敗:", e);
/*  89: 84 */       e.printStackTrace();
/*  90:    */     }
/*  91: 87 */     return conn;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void rollback()
/*  95:    */   {
/*  96: 91 */     if (this.conn != null) {
/*  97:    */       try
/*  98:    */       {
/*  99: 93 */         this.conn.rollback();
/* 100:    */         
/* 101: 95 */         this.logger.info("數據庫回朔成功！");
/* 102:    */       }
/* 103:    */       catch (Exception e)
/* 104:    */       {
/* 105: 97 */         this.logger.error(e);
/* 106: 98 */         e.printStackTrace();
/* 107:    */       }
/* 108:    */     }
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void setSavepoint()
/* 112:    */   {
/* 113:104 */     if (this.conn != null) {
/* 114:    */       try
/* 115:    */       {
/* 116:106 */         this.conn.setSavepoint();
/* 117:    */         
/* 118:108 */         this.logger.info("數據庫建立儲存點");
/* 119:    */       }
/* 120:    */       catch (Exception e)
/* 121:    */       {
/* 122:110 */         this.logger.error(e);
/* 123:111 */         e.printStackTrace();
/* 124:    */       }
/* 125:    */     }
/* 126:    */   }
/* 127:    */   
/* 128:    */   public void releaseConnection()
/* 129:    */   {
/* 130:122 */     if (this.conn != null) {
/* 131:    */       try
/* 132:    */       {
/* 133:124 */         this.conn.close();
/* 134:125 */         this.conn = null;
/* 135:    */         
/* 136:127 */         this.logger.info("數據庫關閉成功！");
/* 137:    */       }
/* 138:    */       catch (Exception e)
/* 139:    */       {
/* 140:129 */         this.logger.error(e);
/* 141:130 */         e.printStackTrace();
/* 142:    */       }
/* 143:    */     }
/* 144:    */   }
/* 145:    */   
/* 146:    */   public ResultSet getResultSet(String querySQL)
/* 147:    */     throws SQLException
/* 148:    */   {
/* 149:144 */     if (this.conn == null) {
/* 150:145 */       this.conn = getConnection();
/* 151:    */     }
/* 152:148 */     Statement stm = this.conn.createStatement();
/* 153:    */     
/* 154:    */ 
/* 155:151 */     ResultSet thisRST = stm.executeQuery(querySQL);
/* 156:    */     
/* 157:153 */     return thisRST;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public ResultSet getResultSet(String querySQL, Vector<Object> vector)
/* 161:    */     throws SQLException
/* 162:    */   {
/* 163:165 */     if (this.conn == null) {
/* 164:166 */       this.conn = getConnection();
/* 165:    */     }
/* 166:169 */     PreparedStatement ps = this.conn.prepareStatement(querySQL);
/* 167:170 */     Integer count = Integer.valueOf(1);
/* 168:171 */     for (Object object : vector)
/* 169:    */     {
/* 170:172 */       ps.setObject(count.intValue(), object);
/* 171:173 */       count = Integer.valueOf(count.intValue() + 1);
/* 172:    */     }
/* 173:177 */     ResultSet thisRST = ps.executeQuery();
/* 174:    */     
/* 175:179 */     return thisRST;
/* 176:    */   }
/* 177:    */   
/* 178:    */   public void executeSQL(String SQL)
/* 179:    */     throws SQLException
/* 180:    */   {
/* 181:190 */     if (this.conn == null) {
/* 182:191 */       this.conn = getConnection();
/* 183:    */     }
/* 184:193 */     Statement stm = this.conn.createStatement();
/* 185:    */     
/* 186:    */ 
/* 187:196 */     stm.executeUpdate(SQL);
/* 188:198 */     if (this.autoCommit.booleanValue()) {
/* 189:199 */       stm.close();
/* 190:    */     }
/* 191:    */   }
/* 192:    */   
/* 193:    */   public void executeSQL(String sql, Vector<Object> vector)
/* 194:    */     throws SQLException
/* 195:    */   {
/* 196:204 */     if (this.conn == null) {
/* 197:205 */       this.conn = getConnection();
/* 198:    */     }
/* 199:207 */     PreparedStatement ps = this.conn.prepareStatement(sql);
/* 200:208 */     Integer count = Integer.valueOf(1);
/* 201:209 */     for (Object object : vector)
/* 202:    */     {
/* 203:210 */       ps.setObject(count.intValue(), object);
/* 204:211 */       count = Integer.valueOf(count.intValue() + 1);
/* 205:    */     }
/* 206:214 */     ps.executeUpdate();
/* 207:216 */     if (this.autoCommit.booleanValue()) {
/* 208:217 */       ps.close();
/* 209:    */     }
/* 210:    */   }
/* 211:    */   
/* 212:    */   public Object uniqueResult(Class<?> entityClass, String querySQL)
/* 213:    */     throws SQLException
/* 214:    */   {
/* 215:223 */     ResultSet resultSet = getResultSet(querySQL);
/* 216:    */     try
/* 217:    */     {
/* 218:225 */       List<Object> list = reflectResult(entityClass, resultSet);
/* 219:227 */       if (this.autoCommit.booleanValue()) {
/* 220:228 */         resultSet.close();
/* 221:    */       }
/* 222:230 */       if (list.size() != 0) {
/* 223:231 */         return list.get(0);
/* 224:    */       }
/* 225:233 */       return null;
/* 226:    */     }
/* 227:    */     catch (Exception e)
/* 228:    */     {
/* 229:236 */       throw new SQLException(e);
/* 230:    */     }
/* 231:    */   }
/* 232:    */   
/* 233:    */   public Object uniqueResult(Class<?> entityClass, String querySQL, Vector<Object> vector)
/* 234:    */     throws SQLException
/* 235:    */   {
/* 236:242 */     ResultSet resultSet = getResultSet(querySQL, vector);
/* 237:    */     
/* 238:244 */     List<Object> list = reflectResult(entityClass, resultSet);
/* 239:246 */     if (this.autoCommit.booleanValue()) {
/* 240:247 */       resultSet.close();
/* 241:    */     }
/* 242:249 */     if (list.size() != 0) {
/* 243:250 */       return list.get(0);
/* 244:    */     }
/* 245:252 */     return null;
/* 246:    */   }
/* 247:    */   
/* 248:    */   public Object uniqueResult(Object entity, String tableName)
/* 249:    */     throws SQLException
/* 250:    */   {
/* 251:257 */     Map<String, Object> map = null;
/* 252:    */     try
/* 253:    */     {
/* 254:259 */       map = getJavaToDbColumnValueMap(entity, ColumnMode.Both, Boolean.valueOf(false), DMLType.Select);
/* 255:    */     }
/* 256:    */     catch (Exception e)
/* 257:    */     {
/* 258:261 */       e.printStackTrace();
/* 259:262 */       throw new SQLException(e);
/* 260:    */     }
/* 261:265 */     StringBuffer sql = new StringBuffer();
/* 262:266 */     Vector<Object> params = new Vector();
/* 263:267 */     sql.append(" SELECT TOP 1 * FROM " + tableName + " WHERE 1 = 1 ");
/* 264:268 */     for (String columnName : map.keySet())
/* 265:    */     {
/* 266:269 */       sql.append(" AND " + columnName + " = ? ");
/* 267:270 */       params.add(map.get(columnName));
/* 268:    */     }
/* 269:273 */     return uniqueResult(entity.getClass(), sql.toString(), params);
/* 270:    */   }
/* 271:    */   
/* 272:    */   public List listResult(Class<?> entityClass, String querySQL)
/* 273:    */     throws SQLException
/* 274:    */   {
/* 275:278 */     ResultSet resultSet = getResultSet(querySQL);
/* 276:    */     
/* 277:280 */     List listResult = reflectResult(entityClass, resultSet);
/* 278:282 */     if (this.autoCommit.booleanValue()) {
/* 279:283 */       resultSet.close();
/* 280:    */     }
/* 281:286 */     return listResult;
/* 282:    */   }
/* 283:    */   
/* 284:    */   public List listResult(Class<?> entityClass, String querySQL, Vector<Object> vector)
/* 285:    */     throws SQLException
/* 286:    */   {
/* 287:291 */     ResultSet resultSet = getResultSet(querySQL, vector);
/* 288:    */     
/* 289:293 */     List listResult = reflectResult(entityClass, resultSet);
/* 290:295 */     if (this.autoCommit.booleanValue()) {
/* 291:296 */       resultSet.close();
/* 292:    */     }
/* 293:299 */     return listResult;
/* 294:    */   }
/* 295:    */   
/* 296:    */   public List listResult(Object entity, String tableName)
/* 297:    */     throws SQLException
/* 298:    */   {
/* 299:304 */     Map<String, Object> map = null;
/* 300:    */     try
/* 301:    */     {
/* 302:306 */       map = getJavaToDbColumnValueMap(entity, ColumnMode.Both, Boolean.valueOf(false), DMLType.Select);
/* 303:    */     }
/* 304:    */     catch (Exception e)
/* 305:    */     {
/* 306:308 */       e.printStackTrace();
/* 307:309 */       throw new SQLException(e);
/* 308:    */     }
/* 309:312 */     StringBuffer sql = new StringBuffer();
/* 310:313 */     Vector<Object> params = new Vector();
/* 311:314 */     sql.append(" SELECT * FROM " + tableName + " WHERE 1 = 1 ");
/* 312:315 */     for (String columnName : map.keySet())
/* 313:    */     {
/* 314:316 */       sql.append(" AND " + columnName + " = ? ");
/* 315:317 */       params.add(map.get(columnName));
/* 316:    */     }
/* 317:320 */     return listResult(entity.getClass(), sql.toString(), params);
/* 318:    */   }
/* 319:    */   
/* 320:    */   public void update(Object entity, String tableName)
/* 321:    */     throws SQLException
/* 322:    */   {
/* 323:324 */     Map<String, Object> columnMap = null;
/* 324:325 */     Map<String, Object> primaryKeyMap = null;
/* 325:    */     try
/* 326:    */     {
/* 327:327 */       columnMap = getJavaToDbColumnValueMap(entity, ColumnMode.Column, Boolean.valueOf(true), DMLType.Update);
/* 328:328 */       primaryKeyMap = getJavaToDbColumnValueMap(entity, ColumnMode.PrimaryKey, Boolean.valueOf(false), DMLType.Update);
/* 329:    */     }
/* 330:    */     catch (Exception e)
/* 331:    */     {
/* 332:330 */       e.printStackTrace();
/* 333:331 */       throw new SQLException(e);
/* 334:    */     }
/* 335:334 */     if (columnMap.size() == 0) {
/* 336:335 */       throw new SQLException("can't update with empty value");
/* 337:    */     }
/* 338:336 */     if (primaryKeyMap.size() == 0) {
/* 339:337 */       throw new SQLException("can't update with empty primaryKey");
/* 340:    */     }
/* 341:340 */     StringBuffer sql = new StringBuffer();
/* 342:341 */     Vector<Object> params = new Vector();
/* 343:342 */     sql.append(" UPDATE " + tableName + " SET ");
/* 344:    */     
/* 345:344 */     int index = 0;
/* 346:345 */     for (String columnName : columnMap.keySet())
/* 347:    */     {
/* 348:346 */       if (index != 0) {
/* 349:347 */         sql.append(", ");
/* 350:    */       }
/* 351:349 */       sql.append(columnName + " = ? ");
/* 352:350 */       if ((columnMap.get(columnName) instanceof java.util.Date)) {
/* 353:351 */         params.add(new java.sql.Date(((java.util.Date)columnMap.get(columnName)).getTime()));
/* 354:    */       } else {
/* 355:353 */         params.add(columnMap.get(columnName));
/* 356:    */       }
/* 357:355 */       index++;
/* 358:    */     }
/* 359:358 */     sql.append(" WHERE ");
/* 360:359 */     index = 0;
/* 361:360 */     for (String primaryKeyName : primaryKeyMap.keySet())
/* 362:    */     {
/* 363:361 */       if (index != 0) {
/* 364:362 */         sql.append(" AND ");
/* 365:    */       }
/* 366:364 */       sql.append(primaryKeyName + " = ? ");
/* 367:365 */       if ((primaryKeyMap.get(primaryKeyName) instanceof java.util.Date)) {
/* 368:366 */         params.add(new java.sql.Date(((java.util.Date)primaryKeyMap.get(primaryKeyName)).getTime()));
/* 369:    */       } else {
/* 370:368 */         params.add(primaryKeyMap.get(primaryKeyName));
/* 371:    */       }
/* 372:370 */       index++;
/* 373:    */     }
/* 374:373 */     executeSQL(sql.toString(), params);
/* 375:    */   }
/* 376:    */   
/* 377:    */   public void insert(Object entity, String tableName)
/* 378:    */     throws SQLException
/* 379:    */   {
/* 380:377 */     Map<String, Object> map = null;
/* 381:    */     try
/* 382:    */     {
/* 383:379 */       map = getJavaToDbColumnValueMap(entity, ColumnMode.Both, Boolean.valueOf(true), DMLType.Insert);
/* 384:    */     }
/* 385:    */     catch (Exception e)
/* 386:    */     {
/* 387:381 */       e.printStackTrace();
/* 388:382 */       throw new SQLException(e);
/* 389:    */     }
/* 390:385 */     StringBuffer sqlColumn = new StringBuffer();
/* 391:386 */     StringBuffer sqlValue = new StringBuffer();
/* 392:387 */     Vector<Object> params = new Vector();
/* 393:    */     
/* 394:389 */     int index = 0;
/* 395:390 */     for (String columnName : map.keySet())
/* 396:    */     {
/* 397:391 */       if (index != 0)
/* 398:    */       {
/* 399:392 */         sqlColumn.append(", ");
/* 400:393 */         sqlValue.append(", ");
/* 401:    */       }
/* 402:396 */       sqlColumn.append(columnName);
/* 403:397 */       sqlValue.append(" ? ");
/* 404:399 */       if ((map.get(columnName) instanceof java.util.Date)) {
/* 405:400 */         params.add(new java.sql.Date(((java.util.Date)map.get(columnName)).getTime()));
/* 406:    */       } else {
/* 407:402 */         params.add(map.get(columnName));
/* 408:    */       }
/* 409:404 */       index++;
/* 410:    */     }
/* 411:406 */     String sql = " INSERT INTO " + tableName + "(" + sqlColumn.toString() + ")" + 
/* 412:407 */       " VALUES(" + sqlValue.toString() + ")";
/* 413:408 */     executeSQL(sql.toString(), params);
/* 414:    */   }
/* 415:    */   
/* 416:    */   public void delete(Object entity, String tableName)
/* 417:    */     throws SQLException
/* 418:    */   {
/* 419:412 */     Map<String, Object> map = null;
/* 420:    */     try
/* 421:    */     {
/* 422:414 */       map = getJavaToDbColumnValueMap(entity, ColumnMode.Both, Boolean.valueOf(false), DMLType.Delete);
/* 423:    */     }
/* 424:    */     catch (Exception e)
/* 425:    */     {
/* 426:416 */       e.printStackTrace();
/* 427:417 */       throw new SQLException(e);
/* 428:    */     }
/* 429:420 */     if (map.size() == 0) {
/* 430:421 */       throw new SQLException("can't delete with empty entity");
/* 431:    */     }
/* 432:424 */     StringBuffer sql = new StringBuffer();
/* 433:425 */     Vector<Object> params = new Vector();
/* 434:    */     
/* 435:427 */     sql.append(" DELETE FROM " + tableName + " WHERE ");
/* 436:428 */     int index = 0;
/* 437:429 */     for (String columnName : map.keySet())
/* 438:    */     {
/* 439:430 */       if (index != 0) {
/* 440:431 */         sql.append(" AND ");
/* 441:    */       }
/* 442:433 */       sql.append(columnName + " = ? ");
/* 443:434 */       if ((map.get(columnName) instanceof java.util.Date)) {
/* 444:435 */         params.add(new java.sql.Date(((java.util.Date)map.get(columnName)).getTime()));
/* 445:    */       } else {
/* 446:437 */         params.add(map.get(columnName));
/* 447:    */       }
/* 448:440 */       index++;
/* 449:    */     }
/* 450:442 */     executeSQL(sql.toString(), params);
/* 451:    */   }
/* 452:    */   
/* 453:    */   public String nvl(String s1, String s2)
/* 454:    */   {
/* 455:446 */     if ((s1 == null) || ("".equals(s1))) {
/* 456:447 */       return s2;
/* 457:    */     }
/* 458:449 */     return s1;
/* 459:    */   }
/* 460:    */   
/* 461:    */   private List reflectResult(Class<?> entityClass, ResultSet resultSet)
/* 462:    */     throws SQLException
/* 463:    */   {
/* 464:    */     try
/* 465:    */     {
/* 466:456 */       BeanInfo reflectBean = ReflectBean.getBeanInfo(entityClass);
/* 467:    */       
/* 468:458 */       Map<String, EntityMapping> entityMapping = new HashMap();
/* 469:459 */       entityMapping.putAll(reflectBean.getColumnDbToJava());
/* 470:460 */       entityMapping.putAll(reflectBean.getPrimaryKeyDbToJava());
/* 471:    */       
/* 472:    */ 
/* 473:463 */       Map<String, EntityMapping> entityIdMapping = new HashMap();
/* 474:464 */       Class<?> embeddedIdClass = reflectBean.getEmbeddedIdClass();
/* 475:465 */       if (embeddedIdClass != null)
/* 476:    */       {
/* 477:466 */         BeanInfo reflectBeanId = ReflectBean.getBeanInfo(embeddedIdClass);
/* 478:    */         
/* 479:468 */         entityIdMapping.putAll(reflectBeanId.getColumnDbToJava());
/* 480:469 */         entityIdMapping.putAll(reflectBeanId.getPrimaryKeyDbToJava());
/* 481:    */       }
/* 482:472 */       List<Object> result = new ArrayList();
/* 483:473 */       while (resultSet.next())
/* 484:    */       {
/* 485:474 */         Object entity = entityClass.newInstance();
/* 486:    */         
/* 487:476 */         Object entityId = null;
/* 488:477 */         if (embeddedIdClass != null)
/* 489:    */         {
/* 490:478 */           entityId = embeddedIdClass.newInstance();
/* 491:    */           
/* 492:480 */           String fieldName = reflectBean.getEmbeddedIdFieldName();
/* 493:481 */           String setIdMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
/* 494:    */           
/* 495:483 */           entityClass.getMethod(setIdMethodName, new Class[] { embeddedIdClass }).invoke(entity, new Object[] { entityId });
/* 496:    */         }
/* 497:486 */         ResultSetMetaData data = resultSet.getMetaData();
/* 498:487 */         for (int i = 1; i <= data.getColumnCount(); i++)
/* 499:    */         {
/* 500:488 */           String dbColumnName = data.getColumnName(i).toUpperCase();
/* 501:490 */           if (entityMapping.get(dbColumnName) != null) {
/* 502:491 */             setEntity(entity, (EntityMapping)entityMapping.get(dbColumnName), data, resultSet, Integer.valueOf(i));
/* 503:492 */           } else if (entityIdMapping.get(dbColumnName) != null) {
/* 504:493 */             setEntity(entityId, (EntityMapping)entityIdMapping.get(dbColumnName), data, resultSet, Integer.valueOf(i));
/* 505:    */           }
/* 506:    */         }
/* 507:496 */         result.add(entity);
/* 508:    */       }
/* 509:499 */       return result;
/* 510:    */     }
/* 511:    */     catch (Exception e)
/* 512:    */     {
/* 513:501 */       e.printStackTrace();
/* 514:502 */       throw new SQLException(e);
/* 515:    */     }
/* 516:    */   }
/* 517:    */   
/* 518:    */   private Map<String, Object> getJavaToDbColumnValueMap(Object entity, ColumnMode columnMode, Boolean isEmptyAble, DMLType dmlType)
/* 519:    */     throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
/* 520:    */   {
/* 521:507 */     Map<String, Object> map = new HashMap();
/* 522:    */     
/* 523:509 */     BeanInfo reflectBean = ReflectBean.getBeanInfo(entity.getClass());
/* 524:511 */     if ((ColumnMode.Column.equals(columnMode)) || (ColumnMode.Both.equals(columnMode)))
/* 525:    */     {
/* 526:512 */       Map<String, EntityMapping> entityMapping = reflectBean.getColumnJavaToDb();
/* 527:513 */       map.putAll(getEntityValueMap(entity, entityMapping, isEmptyAble, dmlType));
/* 528:    */     }
/* 529:515 */     if ((ColumnMode.PrimaryKey.equals(columnMode)) || (ColumnMode.Both.equals(columnMode)))
/* 530:    */     {
/* 531:516 */       Map<String, EntityMapping> entityIdMapping = reflectBean.getPrimaryKeyJavaToDb();
/* 532:517 */       map.putAll(getEntityValueMap(entity, entityIdMapping, isEmptyAble, dmlType));
/* 533:518 */       if (reflectBean.getEmbeddedIdClass() != null)
/* 534:    */       {
/* 535:519 */         String idName = reflectBean.getEmbeddedIdFieldName();
/* 536:520 */         String methodGetName = "get" + idName.substring(0, 1).toUpperCase() + idName.substring(1, idName.length());
/* 537:521 */         Object entityId = entity.getClass().getMethod(methodGetName, new Class[0]).invoke(entity, new Object[0]);
/* 538:    */         
/* 539:523 */         BeanInfo embeddedIdReflectBean = ReflectBean.getBeanInfo(reflectBean.getEmbeddedIdClass());
/* 540:524 */         Map<String, EntityMapping> embeddedIdMapping = embeddedIdReflectBean.getColumnJavaToDb();
/* 541:    */         
/* 542:526 */         map.putAll(getEntityValueMap(entityId, embeddedIdMapping, isEmptyAble, dmlType));
/* 543:    */       }
/* 544:    */     }
/* 545:530 */     return map;
/* 546:    */   }
/* 547:    */   
/* 548:    */   private Map<String, Object> getEntityValueMap(Object entity, Map<String, EntityMapping> entityMapping, Boolean isEmptyAble, DMLType dmlType)
/* 549:    */     throws SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException
/* 550:    */   {
/* 551:534 */     Map<String, Object> map = new HashMap();
/* 552:535 */     Class<?> entityClass = entity.getClass();
/* 553:537 */     for (String fieldName : entityMapping.keySet())
/* 554:    */     {
/* 555:539 */       String methodGetName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
/* 556:540 */       Method methodGet = null;
/* 557:    */       try
/* 558:    */       {
/* 559:542 */         methodGet = entityClass.getMethod(methodGetName, new Class[0]);
/* 560:    */       }
/* 561:    */       catch (NoSuchMethodException e)
/* 562:    */       {
/* 563:544 */         if (entityClass.getDeclaredField(fieldName).getType().equals(Boolean.class))
/* 564:    */         {
/* 565:545 */           methodGetName = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
/* 566:546 */           methodGet = entityClass.getMethod(methodGetName, new Class[0]);
/* 567:    */         }
/* 568:    */       }
/* 569:550 */       Object value = methodGet.invoke(entity, new Object[0]);
/* 570:551 */       EntityMapping mapping = (EntityMapping)entityMapping.get(fieldName);
/* 571:552 */       if (isEmptyAble.booleanValue())
/* 572:    */       {
/* 573:553 */         if ((!DMLType.Insert.equals(dmlType)) || (!mapping.getIsGeneratedValueIdentity().booleanValue())) {
/* 574:554 */           map.put(mapping.getDbColumnName(), value);
/* 575:    */         }
/* 576:    */       }
/* 577:557 */       else if ((value != null) && ((!DMLType.Insert.equals(dmlType)) || (!mapping.getIsGeneratedValueIdentity().booleanValue()))) {
/* 578:558 */         map.put(mapping.getDbColumnName(), value);
/* 579:    */       }
/* 580:    */     }
/* 581:563 */     return map;
/* 582:    */   }
/* 583:    */   
/* 584:    */   private void setEntity(Object entity, EntityMapping entityMapping, ResultSetMetaData data, ResultSet resultSet, Integer index)
/* 585:    */     throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
/* 586:    */   {
/* 587:568 */     Object value = null;
/* 588:569 */     switch (data.getColumnType(index.intValue()))
/* 589:    */     {
/* 590:    */     case 1: 
/* 591:571 */       value = resultSet.getString(index.intValue());
/* 592:572 */       if (value != null) {
/* 593:573 */         value = ((String)value).trim();
/* 594:    */       }
/* 595:575 */       break;
/* 596:    */     case -9: 
/* 597:    */     case -1: 
/* 598:    */     case 12: 
/* 599:579 */       value = resultSet.getString(index.intValue());
/* 600:580 */       break;
/* 601:    */     case -7: 
/* 602:582 */       value = Boolean.valueOf(resultSet.getBoolean(index.intValue()));
/* 603:583 */       break;
/* 604:    */     case 93: 
/* 605:585 */       value = resultSet.getDate(index.intValue());
/* 606:586 */       break;
/* 607:    */     case 4: 
/* 608:    */     case 5: 
/* 609:589 */       value = Integer.valueOf(resultSet.getInt(index.intValue()));
/* 610:590 */       break;
/* 611:    */     case 2: 
/* 612:592 */       BigDecimal bigDecimal = resultSet.getBigDecimal(index.intValue());
/* 613:593 */       value = Double.valueOf(bigDecimal.doubleValue());
/* 614:    */     }
/* 615:595 */     String javaColumnName = entityMapping.getJavaColumnName();
/* 616:596 */     String setMethodName = "set" + javaColumnName.substring(0, 1).toUpperCase() + javaColumnName.substring(1, javaColumnName.length());
/* 617:597 */     entity.getClass().getMethod(setMethodName, new Class[] { entityMapping.getJavaColumnClass() }).invoke(entity, new Object[] { value });
/* 618:    */   }
/* 619:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.core.dao.DatabaseDao
 * JD-Core Version:    0.7.0.1
 */