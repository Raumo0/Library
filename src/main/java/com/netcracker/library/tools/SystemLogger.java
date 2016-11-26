package com.netcracker.library.tools;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by raumo0 on 25.11.16.
 */
public class SystemLogger {
    private Logger logger;

    private SystemLogger(){}

    public static SystemLogger getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void logError(Class sender, String message){
        logger = LogManager.getLogger(sender);
        logger.error(message);
    }

    public void logError(Class sender, String message, Exception e){
        logger = LogManager.getLogger(sender);
        logger.error(message, e);
    }

    private static class SingletonHolder{
        private static final SystemLogger INSTANCE = new SystemLogger();
    }
}
