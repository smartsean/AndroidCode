package com.sean.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * sp工具类，使用的时候修改SP_NAME
 * Created by sean on 2017/3/15.
 */

public class SharePrefUtil {
    private final static String SP_NAME = "doone";
    private static SharedPreferences mSp;

    public static void saveBoolean(Context context, String key, boolean value) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        mSp.edit().putBoolean(key, value).commit();
    }

    public static void saveString(Context context, String key, String value) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        mSp.edit().putString(key, value).commit();
    }

    public static void saveLong(Context context, String key, long value) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        mSp.edit().putLong(key, value).commit();
    }

    public static void saveInt(Context context, String key, int value) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        mSp.edit().putInt(key, value).commit();
    }

    public static void saveFloat(Context context, String key, float value) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        mSp.edit().putFloat(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        return mSp.getString(key, defValue);
    }

    public static int getInt(Context context, String key, int defValue) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        return mSp.getInt(key, defValue);
    }

    public static long getLong(Context context, String key, long defValue) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        return mSp.getLong(key, defValue);
    }

    public static float getFloat(Context context, String key, float defValue) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        return mSp.getFloat(key, defValue);
    }

    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        return mSp.getBoolean(key, defValue);
    }

    public static void saveObj(Context context, String key, Object object) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            String objBase64 = new String(Base64.encode(baos.toByteArray(),
                    Base64.DEFAULT));
            mSp.edit().putString(key, objBase64).commit();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getObj(Context context, String key) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        String objBase64 = mSp.getString(key, null);
        if (TextUtils.isEmpty(objBase64))
            return null;
        byte[] base64Bytes = Base64.decode(objBase64.getBytes(),
                Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        ObjectInputStream ois;
        Object obj = null;
        try {
            ois = new ObjectInputStream(bais);
            obj = (Object) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 删除对应缓存
     *
     * @param context
     * @param key
     * @return
     */
    public static void removeKey(Context context, String key) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        mSp.edit().remove(key).commit();
    }

    public static String removeKey(Context context, String key, String defValue) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        mSp.edit().remove(key).commit();
        return mSp.getString(key, defValue);
    }

    /**
     * 清空缓存
     *
     * @param context
     */
    public static void clear(Context context) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        mSp.edit().clear().commit();
    }

    /**
     * 清空缓存
     *
     * @param context
     */
    public static void clear(Context context, String... keys) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_NAME, 0);
        Map<String, ?> map = mSp.getAll();
        if (null != map && map.size() > 0) {
            Set<String> keySet = map.keySet();
            if (keys != null) {
                keySet.removeAll(Arrays.asList(keys));
            }
            if (null != keySet && keySet.size() > 0) {
                for (String key : keySet) {
                    removeKey(context, key);
                }
            }
        }
    }
}

