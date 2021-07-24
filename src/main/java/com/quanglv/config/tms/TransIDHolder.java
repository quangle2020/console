//package com.quanglv.config.tms;
//
//import java.io.Serializable;
//
///**
// * TMS transaction id holder for multi thread
// */
//public class TransIDHolder implements Serializable {
//    private static final long serialVersionUID = -3981793918484672181L;
//    private static final ThreadLocal<String> transID = ThreadLocal.withInitial(() -> null);
//
//    private TransIDHolder() {
//        //private constructor, do nothing
//    }
//
//    public static void setTransID (String transactionID) {
//        transID.set(transactionID);
//    }
//
//    public static String getTransID () {
//        return transID.get();
//    }
//
//    public static void removeTransID() {
//        transID.remove();
//    }
//
//}
