package org.maddev.threadingPoc.util;

public class UrlUtils {

    public static String getId(String url) {
        String[] splitted = url.split("/");
        return splitted[splitted.length-1];
    }

}
