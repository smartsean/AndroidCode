package com.sean.lib.utils;


import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPInputStream;

/**
 * Created by sean on 2017/3/15.
 */
public class FormatUtil {

    private static final String TAG = "FORMAT_UTIL";

    public static final String dateFormateStr = "yyyy-mm-dd";

    public static String formatDate(String date) {

        DateFormat dateFormat = new SimpleDateFormat(dateFormateStr);
        try {
            return dateFormat.format(dateFormat.parse(date));
        } catch (ParseException e) {
            Log.e(TAG, ErrorLogUtil.getStackMsg(e));
            return "";
        }
    }

    public static final String timeFormateStr = "HH:mm:ss";

    public static String formatTime(String time) {

        return time.concat(":00");
    }

    public static Date formatStrTime(String str) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            Log.e(TAG, ErrorLogUtil.getStackMsg(e));
            return new Date("00:00");
        }
    }

    public static String yearConfig(float year) {

        int month = (int) (year * 12);
        String date = month / 12 + "年";
        if (month % 12 == 0) {
            return date;
        } else {
            return date + month % 12 + "个月";
        }
    }

    public static String monthSuffix(int month) {

        return month + "个月";
    }

    public static String hourConfig(float hour) {

        int minute = (int) (hour * 60);
        String time = minute / 60 + "小时";
        if (minute % 60 == 0) {
            return time;
        } else {
            return time + minute % 60 + "分钟";
        }
    }

    public static float float2(float f) {

        return BigDecimal.valueOf(f).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static String getUTF8XMLString(String xml) {
        // A StringBuffer Object
        StringBuffer sb = new StringBuffer();
        sb.append(xml);
        String xmString = "";
        String xmlUTF8 = "";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
            System.out.println("utf-8 编码：" + xmlUTF8);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, ErrorLogUtil.getStackMsg(e));
        }
        return xmlUTF8;
    }

    public static int getShort(byte[] data) {

        return (int) ((data[0] << 8) | data[1] & 0xFF);
    }

    public static String getRealString(byte[] data) {

        byte[] h = new byte[2];
        h[0] = (data)[0];
        h[1] = (data)[1];
        int head = getShort(h);
        boolean t = head == 0x1f8b;
        InputStream in;
        StringBuilder sb = new StringBuilder();
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            if (t) {
                in = new GZIPInputStream(bis);
            } else {
                in = bis;
            }
            BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
            for (String line = r.readLine(); line != null; line = r.readLine()) {
                sb.append(line);
            }
            in.close();
        } catch (Exception e) {
            Log.e(TAG, ErrorLogUtil.getStackMsg(e));
        }
        return sb.toString();
    }

    /**
     * 禁止输入空格
     *
     * @return
     */
    public static InputFilter getInputFilterForSpace() {
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                //返回null表示接收输入的字符,返回空字符串表示不接受输入的字符
                if (source.equals(" "))
                    return "";
                else
                    return null;
            }
        };
    }
}
