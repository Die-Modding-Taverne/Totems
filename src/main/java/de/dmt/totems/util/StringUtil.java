package de.dmt.totems.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StringUtil {

    public static String fromException(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);

        return stringWriter.toString();
    }
}
