package com.sean.lib.utils;

/**
 * 双击判断类
 * Created by sean on 2017/3/15.
 */

public class ClickUtil {

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {

        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}