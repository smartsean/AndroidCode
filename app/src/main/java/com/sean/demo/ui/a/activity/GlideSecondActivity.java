package com.sean.demo.ui.a.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.sean.demo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author smartsean
 */
public class GlideSecondActivity extends AppCompatActivity {
    private static final String TAG = "GlideSecondActivity";
    ImageView mImageView;
    String url = "http://p2oza5z62.bkt.clouddn.com/2018-04-11-Photo by Nick Wood -_o0P5q6_Gjc-.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_second);
        mImageView = findViewById(R.id.show);
        RequestOptions options = new RequestOptions()
                .transform(new MultiTransformation<Bitmap>(new CenterCrop(),new CircleCrop()));
        Glide.with(this)
                .load(url)
                .apply(options)
                .into(mImageView);
//        new MyAsyncTask().execute(url);
    }

    class MyAsyncTask extends AsyncTask<String, Void, File> {

        @Override
        protected File doInBackground(String... strings) {

            FutureTarget<File> target = Glide.with(GlideSecondActivity.this)
                    .asFile()
                    .load(strings[0])
                    .submit();

            try {
                return target.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(GlideSecondActivity.this, "Glide开始下载图片", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(File file) {
            super.onPostExecute(file);
            Log.d(TAG, "图片下载完成，地址为" + file.getAbsolutePath());
            Toast.makeText(GlideSecondActivity.this, "图片下载完成，地址为" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            try {
                FileInputStream inputStream = new FileInputStream(file);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mImageView.setImageBitmap(bitmap);
                inputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}


