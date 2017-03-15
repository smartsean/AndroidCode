package com.sean.lib.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


import java.io.ByteArrayOutputStream;

/**
 * String转bitmap需要content信息
 * Created by sean on 2017/3/15.
 */

public class BitmapUtil {

    /**
     * 图片转成string
     *
     * @param bitmap
     * @return
     */
    public static String convertIconToString(Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(appicon, Base64.DEFAULT);

    }

    //使用需要修改

//    /**
//     * string转成bitmap
//     *
//     * @param st
//     */
//    public static Bitmap convertStringToIcon(String st) {
//
//        Bitmap bitmap;
//        try {
//            byte[] bitmapArray;
//            bitmapArray = Base64.decode(st, Base64.DEFAULT);
//            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
//        } catch (Exception e) {
//            bitmap = BitmapFactory.decodeResource(AppManagerApplication.getInstance().getApplicationContext()
//                    .getResources(), R.drawable.imageloader_ic_error);
//        }
//        return bitmap;
//    }
}