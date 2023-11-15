package lib.kalu.frame.mvvm.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;

import lib.kalu.frame.mvvm.util.MvvmUtil;

public class OkhttpGlideUtil {

    private static final HashMap<String, RequestOptions> mRequestOptionMaps = new HashMap<>();

    @SuppressLint("UseCompatLoadingForDrawables")
    protected static final RequestOptions getRequestOptions(@NonNull Context context, @DrawableRes int defatltRes, @IntRange(from = 10, to = 100) int encodeQuality, @NonNull boolean skipMemoryCache) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(defatltRes);
            builder.append(encodeQuality);
            builder.append(skipMemoryCache);
            String key = builder.toString();
            boolean containsKey = mRequestOptionMaps.containsKey(key);
            if (!containsKey) {
                RequestOptions requestOptions = new RequestOptions()
                        .dontAnimate()
                        .dontTransform()
                        .encodeQuality(encodeQuality)
                        .format(DecodeFormat.PREFER_RGB_565)
                        .priority(Priority.LOW)
                        .diskCacheStrategy(DiskCacheStrategy.DATA) //缓存原始图片数据
                        .skipMemoryCache(skipMemoryCache); //跳过内存缓存
                Drawable drawable = context.getResources().getDrawable(defatltRes);
                if (null != drawable) {
                    requestOptions.error(drawable).placeholder(drawable);
                }
                mRequestOptionMaps.put(key, requestOptions);
            }
            RequestOptions requestOptions = mRequestOptionMaps.get(key);
            if (null == requestOptions)
                throw new Exception("requestOptions error: null");
            return requestOptions;
        } catch (Exception e) {
            MvvmUtil.logE("OkhttpGlideUtil => getRequestOptions => " + e.getMessage());
            return null;
        }
    }

    public static final void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

    public static final void pauseRequests(Context context) {
        Glide.with(context).pauseAllRequests();
    }

    public static final void clearAll(@NonNull Context context) {
        try {
            if (null == context)
                throw new Exception("context error: null");
            clearDisk(context);
            clearMemory(context);
        } catch (Exception e) {
            MvvmUtil.logE("OkhttpGlideUtil => clearAll => " + e.getMessage());
        }
    }

    public static final void clearDisk(final Context context) {
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

    public static final void clearMemory(Context context) {
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
