/*   1:    */ package com.zurich.sds.ipa.module;
/*   2:    */ 
/*   3:    */ import com.zurich.sds.model.common.hibernate.entity.AppMTb;
/*   4:    */ import com.zurich.sds.model.common.hibernate.entity.CustDetailTb;
/*   5:    */ import com.zurich.sds.model.common.hibernate.entity.pk.CustDetailId;
/*   6:    */ import com.zurich.sds.model.ipa.hibernate.entity.AppIpaMTb;
/*   7:    */ 
/*   8:    */ public class IpaLabrMain
/*   9:    */ {
/*  10:    */   private AppMTb appMTb;
/*  11:    */   private AppIpaMTb appIpaMTb;
/*  12:    */   private CustDetailTb ipaCustI;
/*  13:    */   private CustDetailTb ipaCustA;
/*  14:    */   private CustDetailTb ipaCustP;
/*  15:    */   private String errorMessage;
/*  16:    */   
/*  17:    */   public IpaLabrMain()
/*  18:    */   {
/*  19: 28 */     this.appMTb = new AppMTb();
/*  20:    */     
/*  21: 30 */     this.appIpaMTb = new AppIpaMTb();
/*  22:    */     
/*  23: 32 */     this.ipaCustI = new CustDetailTb();
/*  24: 33 */     CustDetailId custDetailIdI = new CustDetailId();
/*  25: 34 */     custDetailIdI.setCustRoleCd("I");
/*  26: 35 */     this.ipaCustI.setId(custDetailIdI);
/*  27:    */     
/*  28: 37 */     this.ipaCustA = new CustDetailTb();
/*  29: 38 */     CustDetailId custDetailIdA = new CustDetailId();
/*  30: 39 */     custDetailIdA.setCustRoleCd("A");
/*  31: 40 */     this.ipaCustA.setId(custDetailIdA);
/*  32:    */     
/*  33: 42 */     this.ipaCustP = new CustDetailTb();
/*  34: 43 */     CustDetailId custDetailIdP = new CustDetailId();
/*  35: 44 */     custDetailIdP.setCustRoleCd("P");
/*  36: 45 */     this.ipaCustP.setId(custDetailIdP);
/*  37:    */   }
/*  38:    */   
/*  39:    */   public IpaLabrMain(String dataId, Integer dataIdVerno)
/*  40:    */   {
/*  41: 49 */     this.appMTb = new AppMTb();
/*  42: 50 */     this.appMTb.setDataId(dataId);
/*  43:    */     
/*  44: 52 */     this.appIpaMTb = new AppIpaMTb();
/*  45: 53 */     this.appIpaMTb.setDataId(dataId);
/*  46: 54 */     this.appIpaMTb.setDataIdVerno(dataIdVerno);
/*  47:    */     
/*  48: 56 */     this.ipaCustI = new CustDetailTb();
/*  49: 57 */     CustDetailId custDetailIdI = new CustDetailId();
/*  50: 58 */     custDetailIdI.setCustRoleCd("I");
/*  51: 59 */     custDetailIdI.setDataId(dataId);
/*  52: 60 */     custDetailIdI.setDataIdVerNo(dataIdVerno);
/*  53: 61 */     this.ipaCustI.setId(custDetailIdI);
/*  54:    */     
/*  55: 63 */     this.ipaCustA = new CustDetailTb();
/*  56: 64 */     CustDetailId custDetailIdA = new CustDetailId();
/*  57: 65 */     custDetailIdA.setCustRoleCd("A");
/*  58: 66 */     custDetailIdA.setDataId(dataId);
/*  59: 67 */     custDetailIdA.setDataIdVerNo(dataIdVerno);
/*  60: 68 */     this.ipaCustA.setId(custDetailIdA);
/*  61:    */     
/*  62: 70 */     this.ipaCustP = new CustDetailTb();
/*  63: 71 */     CustDetailId custDetailIdP = new CustDetailId();
/*  64: 72 */     custDetailIdP.setCustRoleCd("P");
/*  65: 73 */     custDetailIdP.setDataId(dataId);
/*  66: 74 */     custDetailIdP.setDataIdVerNo(dataIdVerno);
/*  67: 75 */     this.ipaCustP.setId(custDetailIdP);
/*  68:    */   }
/*  69:    */   
/*  70:    */   public AppMTb getAppMTb()
/*  71:    */   {
/*  72: 79 */     return this.appMTb;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void setAppMTb(AppMTb appMTb)
/*  76:    */   {
/*  77: 83 */     this.appMTb = appMTb;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public AppIpaMTb getAppIpaMTb()
/*  81:    */   {
/*  82: 87 */     return this.appIpaMTb;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void setAppIpaMTb(AppIpaMTb appIpaMTb)
/*  86:    */   {
/*  87: 91 */     this.appIpaMTb = appIpaMTb;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public CustDetailTb getIpaCustI()
/*  91:    */   {
/*  92: 95 */     return this.ipaCustI;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void setIpaCustI(CustDetailTb ipaCustI)
/*  96:    */   {
/*  97: 99 */     this.ipaCustI = ipaCustI;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public CustDetailTb getIpaCustA()
/* 101:    */   {
/* 102:103 */     return this.ipaCustA;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void setIpaCustA(CustDetailTb ipaCustA)
/* 106:    */   {
/* 107:107 */     this.ipaCustA = ipaCustA;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public CustDetailTb getIpaCustP()
/* 111:    */   {
/* 112:111 */     return this.ipaCustP;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void setIpaCustP(CustDetailTb ipaCustP)
/* 116:    */   {
/* 117:115 */     this.ipaCustP = ipaCustP;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public String getErrorMessage()
/* 121:    */   {
/* 122:119 */     return this.errorMessage;
/* 123:    */   }
/* 124:    */   
/* 125:    */   public void setErrorMessage(String errorMessage)
/* 126:    */   {
/* 127:123 */     this.errorMessage = errorMessage;
/* 128:    */   }
/* 129:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.ipa.module.IpaLabrMain
 * JD-Core Version:    0.7.0.1
 */