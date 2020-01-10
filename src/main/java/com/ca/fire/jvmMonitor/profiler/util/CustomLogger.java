package com.ca.fire.jvmMonitor.profiler.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger {

    public static final CustomLogger TpLogger;
    public static final CustomLogger AliveLogger;
    public static final CustomLogger BusinessLogger;
    public static final CustomLogger BizLogger;
    public static final CustomLogger JVMLogger;
    public static final CustomLogger CommonLogger;
    private Logger logger;


    public CustomLogger(final Logger logger) {
        this.logger = logger;
    }

    public void info(final String message) {
        this.logger.info(message);
    }

    public Logger getLogger() {
        return this.logger;
    }

    static {
        TpLogger = new CustomLogger(LoggerFactory.getLogger("tpLogger"));
        AliveLogger = new CustomLogger(LoggerFactory.getLogger("aliveLogger"));
        BusinessLogger = new CustomLogger(LoggerFactory.getLogger("businessLogger"));
        BizLogger = new CustomLogger(LoggerFactory.getLogger("bizLogger"));
        JVMLogger = new CustomLogger(LoggerFactory.getLogger("jvmLogger"));
        CommonLogger = new CustomLogger(LoggerFactory.getLogger("commonLogger"));
    }
}
