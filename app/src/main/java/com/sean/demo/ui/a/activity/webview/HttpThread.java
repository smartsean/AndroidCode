package com.sean.demo.ui.a.activity.webview;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sean on 2017/4/28.
 */

public class HttpThread extends Thread {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpThread(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            Log.i("SmartSean-Debug===>>>", "start<<<===end");
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            InputStream in = conn.getInputStream();
            FileOutputStream out = null;
            File downLoadFile;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                downLoadFile = Environment.getExternalStorageDirectory();

                File sdFile = new File(downLoadFile, "test.apk");
                out = new FileOutputStream(sdFile);
            }

            byte[] b = new byte[6 * 1024];
            int len;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }

            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            Log.i("SmartSean-Debug===>>>", "succece<<<===end");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
