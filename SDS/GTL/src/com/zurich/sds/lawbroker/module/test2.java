/*   1:    */ package com.zurich.sds.lawbroker.module;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.List;
/*   5:    */ 
/*   6:    */ public class test2
/*   7:    */ {
/*   8:    */   private List<Integer> deleted(List<Integer> a, List<Integer> b)
/*   9:    */   {
/*  10:118 */     List<Integer> result = new ArrayList(a);
/*  11:    */     
/*  12:120 */     result.removeAll(b);
/*  13:    */     
/*  14:122 */     return result;
/*  15:    */   }
/*  16:    */   
/*  17:    */   private List<Integer> retained(List<Integer> a, List<Integer> b)
/*  18:    */   {
/*  19:126 */     List<Integer> result = new ArrayList(a);
/*  20:127 */     result.retainAll(b);
/*  21:    */     
/*  22:129 */     return result;
/*  23:    */   }
/*  24:    */   
/*  25:    */   private List<Integer> added(List<Integer> a, List<Integer> b)
/*  26:    */   {
/*  27:133 */     return deleted(b, a);
/*  28:    */   }
/*  29:    */ }


/* Location:           D:\00_Louie\Zoomin\SFTP_BatchJob\SDS\GTL\SDS_JOB.jar
 * Qualified Name:     com.zurich.sds.lawbroker.module.test2
 * JD-Core Version:    0.7.0.1
 */