package com.kalu.develop.frame;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

import lib.kalu.frame.mvp.glide.OkhttpGlideInterceptor;
import lib.kalu.frame.mvp.glide.OkhttpGlideProgressListener;

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

        test1();
//        test2();

        findViewById(R.id.image_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test0();
            }
        });
    }

    private void test0() {
        String url = "https://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-02-04%2F5e3962782e8b2.jpg&thumburl=https%3A%2F%2Fimg2.baidu.com%2Fit%2Fu%3D1814561676%2C2470063876%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG%3Fw%3D750%26h%3D500";
        String cacheAbsolutePath = GlideUtil.getCacheAbsolutePath(getApplicationContext(), url);
        Toast.makeText(getApplicationContext(), "=> " + cacheAbsolutePath, Toast.LENGTH_SHORT).show();
        if (null != cacheAbsolutePath && cacheAbsolutePath.length() > 0) {
            ImageView imageView2 = findViewById(R.id.image_src2);
            imageView2.setImageURI(Uri.parse(cacheAbsolutePath));
        }
    }

    private void test1() {
        String url = "https://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-02-04%2F5e3962782e8b2.jpg&thumburl=https%3A%2F%2Fimg2.baidu.com%2Fit%2Fu%3D1814561676%2C2470063876%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG%3Fw%3D750%26h%3D500";
        ImageView imageView = findViewById(R.id.image_src1);
        GlideUtil.load(imageView, url);
    }

    private void test2() {

        String url = "https://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-02-04%2F5e3962782e8b2.jpg&thumburl=https%3A%2F%2Fimg2.baidu.com%2Fit%2Fu%3D1814561676%2C2470063876%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG%3Fw%3D750%26h%3D500";
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
            public void onProgress(int progress) {
                Log.d("TAG", "onProgress: " + progress);
            }
        });
    }
}
