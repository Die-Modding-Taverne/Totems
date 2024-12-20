package de.dmt.totems.util;
import de.dmt.totems.Totems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    private static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = LoggerFactory.getLogger(Totems.MOD_ID);
        }

        return logger;
    }

    private static String format(String message, Object... values) {
        return String.format(message, values);
    }

    public static void info(String message) {
        getLogger().info(message);
    }

    public static void infof(String message, Object... values) {
        info(format(message, values));
    }

    public static void warn(String message) {
        getLogger().warn(message);
    }

    public static void warnf(String message, Object... values) {
        warn(format(message, values));
    }

    public static void error(String message) {
        getLogger().error(message);
    }


    public static void errorf(String message, Object... values) {
        error(format(message, values));
    }
}
