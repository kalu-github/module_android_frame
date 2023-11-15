package lib.kalu.frame.mvp.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;

import lib.kalu.frame.mvp.util.MvpUtil;

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

                if (defatltRes != 0) {
                    try {
                        Drawable drawable = context.getResources().getDrawable(defatltRes);
                        if (null != drawable) {
                            requestOptions.error(drawable).placeholder(drawable);
                        }
                    } catch (Exception e) {
                    }
                }
                mRequestOptionMaps.put(key, requestOptions);
            }
            RequestOptions requestOptions = mRequestOptionMaps.get(key);
            if (null == requestOptions)
                throw new Exception("requestOptions error: null");
            return requestOptions;
        } catch (Exception e) {
            MvpUtil.logE("OkhttpGlideUtil => getRequestOptions => " + e.getMessage());
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
            MvpUtil.logE("OkhttpGlideUtil => clearAll => " + e.getMessage());
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

    public static void load(@NonNull ImageView imageView, String url) {
        check(imageView, url, 0, 40, true);
    }

    public static void load(@NonNull ImageView imageView, String url, @NonNull boolean skipMemoryCache) {
        check(imageView, url, 0, 40, skipMemoryCache);
    }

    public static void load(@NonNull ImageView imageView, String url, @DrawableRes int defatltRes) {
        check(imageView, url, defatltRes, 40, true);
    }

    public static void load(@NonNull ImageView imageView, String url, @DrawableRes int defatltRes, @IntRange(from = 10, to = 100) int encodeQuality) {
        check(imageView, url, defatltRes, encodeQuality, true);
    }

    public static void load(@NonNull ImageView imageView, String url, @DrawableRes int defatltRes, @IntRange(from = 10, to = 100) int encodeQuality, @NonNull boolean skipMemoryCache) {
        check(imageView, url, defatltRes, encodeQuality, skipMemoryCache);
    }

    private static void check(@NonNull ImageView imageView, String url, @DrawableRes int defatltRes, @IntRange(from = 10, to = 100) int encodeQuality, @NonNull boolean skipMemoryCache) {
        try {
            if (null == imageView)
                throw new Exception("imageView error: null");
            imageView.setImageDrawable(null);
            if (null == url || url.length() == 0)
                throw new Exception("url error: " + url);
            Context context = imageView.getContext();
            if (null == context)
                throw new Exception("context error: null");
            Context applicationContext = context.getApplicationContext();
            if (null == applicationContext)
                throw new Exception("applicationContext error: null");
            into(context, imageView, url, defatltRes, encodeQuality, skipMemoryCache);
        } catch (Exception e) {
            MvpUtil.logE("OkhttpGlideUtil => check => " + e.getMessage());
        }
    }

    private static void into(@NonNull Context context, @NonNull ImageView imageView, String url, @DrawableRes int defatltRes, @IntRange(from = 10, to = 100) int encodeQuality, @NonNull boolean skipMemoryCache) {

        try {
            RequestOptions requestOptions = getRequestOptions(context, defatltRes, encodeQuality, skipMemoryCache);
            if (null == requestOptions)
                throw new Exception("requestOptions error: null");
//            // 圆角
//            float v = context.getResources().getDimension(R.dimen.dp_4);
//            GlideRoundTransform transform = new GlideRoundTransform(context, v);
//            options.transform(transform);
            Glide.with(context).load(url.trim()).apply(requestOptions).into(imageView);
        } catch (Exception e) {
            MvpUtil.logE("OkhttpGlideUtil => into => " + e.getMessage());
        }
    }
}
