package com.sean.lib.utils;

/**
 * Created by sean on 2017/3/15.
 */

public class ErrorLogUtil {

    public static String getStackMsg(Exception e) {

        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[ i ];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }

    public static String getStackMsg(Throwable e) {

        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[ i ];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }
}