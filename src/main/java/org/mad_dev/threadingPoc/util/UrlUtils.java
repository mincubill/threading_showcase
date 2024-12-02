package org.mad_dev.threadingPoc.util;

public class UrlUtils {

    public static String getId(String url) {
        String[] splitted = url.split("/");
        return splitted[splitted.length-1];
    }

}
