package cn.shuaijunlan.java.basic.learning.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:57 AM 1/22/19.
 */
public class LoggerTest {
    static Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    static org.apache.log4j.Logger loggers = org.apache.log4j.Logger.getLogger(LoggerTest.class);
    public static void main(String[] args) {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.error("error");

        loggers.fatal("fatal");
        loggers.debug("debug");
        loggers.info("info");
        loggers.error("error");
    }
}
