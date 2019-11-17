//package com.ca.fire.jvmMonitor.profiler.util;
//
//public class CustomLogger {
//
//    public static final CustomLogger TpLogger;
//    public static final CustomLogger AliveLogger;
//    public static final CustomLogger BusinessLogger;
//    public static final CustomLogger BizLogger;
//    public static final CustomLogger JVMLogger;
//    public static final CustomLogger CommonLogger;
//    private Logger logger;
//
//    public CustomLogger(final Logger logger) {
//        this.logger = logger;
//    }
//
//    public void info(final String message) {
//        this.logger.info(message);
//    }
//
//    public Logger getLogger() {
//        return this.logger;
//    }
//
//    static {
//        TpLogger = new CustomLogger(CustomLogFactory.getLogger("tpLogger"));
//        AliveLogger = new CustomLogger(CustomLogFactory.getLogger("aliveLogger"));
//        BusinessLogger = new CustomLogger(CustomLogFactory.getLogger("businessLogger"));
//        BizLogger = new CustomLogger(CustomLogFactory.getLogger("bizLogger"));
//        JVMLogger = new CustomLogger(CustomLogFactory.getLogger("jvmLogger"));
//        CommonLogger = new CustomLogger(CustomLogFactory.getLogger("commonLogger"));
//    }
//}
