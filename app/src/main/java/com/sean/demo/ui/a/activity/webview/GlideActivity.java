package com.sean.demo.ui.a.activity.webview;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;
import com.sean.demo.ui.MainActivity;
import com.sean.demo.ui.a.activity.GlideSecondActivity;

import java.io.File;
import java.lang.annotation.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author smartsean
 */
public class GlideActivity extends BaseActivity {


    @BindView(R.id.test_img1)
    ImageView mImageView;

    private String url = "http://p2oza5z62.bkt.clouddn.com/2018-04-11-Photo by Nick Wood -_o0P5q6_Gjc-.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_glide);
        ButterKnife.bind(this);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GlideActivity.this, GlideSecondActivity.class));
            }
        });
//        String s = null;
//        s = "";
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.a)
//                .error(R.drawable.d)
//                .fallback(R.drawable.e)
//                .override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL);
//        Glide.with(this).load(url).thumbnail(0.5f).apply(options).into(mImageView);
//        String highQualityImageUrl = "...";
//        String lowQualityImageUrl = "...";
//        String url = "正常要加载的地址";
//        String errorUrl  ="失败要加载的地址";
//        Glide.with(this)
//                .load(url)
//                .error(Glide.with(this).load(errorUrl))
//                .into(mImageView);

//        Glide.with(this).load(url).into(new SimpleTarget<Drawable>() {
//            @Override
//            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                mImageView.setImageDrawable(resource);
//            }
//        });

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.a)
                .error(R.drawable.d)
                .fallback(R.drawable.e)
                .override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL);
//        Glide.with(this).load(url).thumbnail(0.5f).apply(options).into(mImageView);


        Glide.with(this)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, com.bumptech.glide.request.target.Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(GlideActivity.this, "加载失败，下个页面从网络取", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, com.bumptech.glide.request.target.Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Toast.makeText(GlideActivity.this, "加载成功，下个页面从缓存中取", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }).preload();


    }
//    String url = "http://p2oza5z62.bkt.clouddn.com/2018-04-11-Photo by Nick Wood -_o0P5q6_Gjc-.jpg";
}
