package com.sean.demo.ui.a.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;

import java.net.MalformedURLException;
import java.net.URL;
/**
 * @author  SmartSean
 */
public class AsyncTaskActivity extends BaseActivity {

    private Context context;
    private Button asyncTaskBtn;
    private NumberProgressBar asyncTaskPb;
    private TextView show;


    private DownloadFilesTask downloadFilesTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_async_task);
        context = this;
        setBackArrow();
        initView1();
    }

    private void initView1() {
        asyncTaskBtn = (Button) findViewById(R.id.async_task_btn);
        asyncTaskPb = (NumberProgressBar) findViewById(R.id.async_task_pb);
        show = (TextView) findViewById(R.id.show);
        asyncTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context).setMessage("确定下载文件吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    downloadFilesTask = new DownloadFilesTask();
                                    downloadFilesTask.execute(new URL("http://www.baidu.com"));
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).show();

            }
        });
    }


    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            show.setVisibility(View.GONE);
            asyncTaskPb.setVisibility(View.VISIBLE);
        }

        @Override
        protected Long doInBackground(URL... params) {
            int count = params.length;
            long totalSize = 0;
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                    totalSize += i;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isCancelled()) {
                    break;
                }
            }
            return totalSize;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            asyncTaskPb.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            asyncTaskPb.setVisibility(View.GONE);
            show.setVisibility(View.VISIBLE);
            show.setText("下载完成,文件大小：" + aLong);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (downloadFilesTask != downloadFilesTask && downloadFilesTask.getStatus() == AsyncTask.Status.RUNNING) {
            //cancel方法只是将AsyncTask标记为cancel状态，并不是真正的取消线程的执行
            downloadFilesTask.cancel(true);
        }
    }
}
