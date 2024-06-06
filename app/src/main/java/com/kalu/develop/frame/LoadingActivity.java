package com.kalu.develop.frame;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import lib.kalu.frame.mvp.glide.OkhttpGlideProgressListener;
import lib.kalu.frame.mvp.util.MvpUtil;

/**
 * @author zhanghang
 * @description: 启动页
 * @date :2022-01-17
 */
public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        MvpUtil.setLogger(true);

        String url = "https://pic4.zhimg.com/80/v2-362dbf74dd1f8339071a41c5c8bc468f_1440w.webp";
        findViewById(R.id.button_src1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test1(url);
            }
        });
        findViewById(R.id.button_src2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test2(url);
            }
        });
        findViewById(R.id.button_src3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test3(url);
            }
        });
    }

    private void test1(@NonNull String url) {
        ImageView imageView = findViewById(R.id.image_src1);
        GlideUtil.load(imageView, url, new OkhttpGlideProgressListener() {
            @Override
            public void onStart() {
                Log.d("TAG", "onStart: ");
            }

            @Override
            public void onComplete() {
                Log.d("TAG", "onComplete: ");
            }

            @Override
            public void onError(@NonNull Exception e) {
                Log.d("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onProgress(int progress) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = findViewById(R.id.text_src1);
                        textView.setText(progress + "%");
                    }
                });
                Log.d("TAG", "onProgress: " + progress);
            }
        });
    }

    private void test2(@NonNull String url) {
        ImageView imageView = findViewById(R.id.image_src2);
        GlideUtil.load(imageView, url);
    }

    private void test3(@NonNull String url) {
        String cacheAbsolutePath = GlideUtil.getPath(getApplicationContext(), url);
        Toast.makeText(getApplicationContext(), "=> " + cacheAbsolutePath, Toast.LENGTH_SHORT).show();
        if (null != cacheAbsolutePath && cacheAbsolutePath.length() > 0) {
            ImageView imageView2 = findViewById(R.id.image_src3);
            imageView2.setImageURI(Uri.parse(cacheAbsolutePath));
        }
    }
}
