package lib.kalu.frame.mvp.glide;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import lib.kalu.frame.mvp.util.MvpUtil;

public class OkhttpGlideUtil {

    protected final static RequestOptions mRequestOptionsZip = new RequestOptions()
            .dontAnimate()
            .dontTransform()
            .encodeQuality(40)
            .format(DecodeFormat.PREFER_RGB_565)
            .priority(Priority.LOW)
            .diskCacheStrategy(DiskCacheStrategy.DATA) //缓存原始图片数据
            .skipMemoryCache(true); //跳过内存缓存

    protected final static RequestOptions mRequestOptionsSample = new RequestOptions()
            .dontAnimate()
            .dontTransform()
            .encodeQuality(100)
            .format(DecodeFormat.PREFER_RGB_565)
            .priority(Priority.LOW)
            .diskCacheStrategy(DiskCacheStrategy.DATA) //缓存原始图片数据
            .skipMemoryCache(true); //跳过内存缓存

    public static void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

    public static void pauseRequests(Context context) {
        Glide.with(context).pauseAllRequests();
    }

    public static void clearAll(@NonNull Context context) {
        try {
            if (null == context)
                throw new Exception("context error: null");
            clearDisk(context);
            clearMemory(context);
        } catch (Exception e) {
            MvpUtil.logE("OkhttpGlideUtil => clearAll => " + e.getMessage());
        }
    }

    public static void clearDisk(final Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(context).clearDiskCache();
                }
            }).start();
        } else {
            Glide.get(context).clearDiskCache();
        }
    }

    public static void clearMemory(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Glide.get(context).clearMemory();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Glide.get(context).clearMemory();
                }
            });
        }
    }
}
