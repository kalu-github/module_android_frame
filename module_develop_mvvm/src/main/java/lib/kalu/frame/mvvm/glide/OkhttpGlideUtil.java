package lib.kalu.frame.mvvm.glide;

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
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.SafeKeyGenerator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.EmptySignature;

import java.io.File;
import java.util.HashMap;

import lib.kalu.frame.mvvm.util.MvvmUtil;

public class OkhttpGlideUtil {

    private static final HashMap<String, RequestOptions> mRequestOptionMaps = new HashMap<>();

    public static final void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
        Glide.with(context).resumeRequestsRecursive();
    }

    public static final void pauseRequests(Context context) {
        Glide.with(context).pauseAllRequests();
        Glide.with(context).pauseAllRequestsRecursive();
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

    public static final String getCacheAbsolutePath(@NonNull Context context, @NonNull String url) {
        return getCacheAbsolutePath(context, null, url);
    }

    public static final String getCacheAbsolutePath(@NonNull Context context, @NonNull File directory, @NonNull String url) {
        try {
            if (null == context)
                throw new Exception("context error: null");
            if (null == url || url.length() == 0)
                throw new Exception("url error: " + url);
            if (null == directory) {
                File filesDir = context.getFilesDir();
                if (null == filesDir || !filesDir.exists()) {
                    filesDir.mkdirs();
                }
                String absolutePath = filesDir.getAbsolutePath();
                File filesGlide = new File(absolutePath, "glide");
                if (null == filesGlide || !filesGlide.exists()) {
                    filesGlide.mkdirs();
                }
                directory = filesGlide;
            }
            OkhttpGlideDataCacheKey dataCacheKey = new OkhttpGlideDataCacheKey(new GlideUrl(url), EmptySignature.obtain());
            SafeKeyGenerator safeKeyGenerator = new SafeKeyGenerator();
            String safeKey = safeKeyGenerator.getSafeKey(dataCacheKey);
            DiskLruCache diskLruCache = DiskLruCache.open(directory, 1, 1, Integer.MAX_VALUE);
            DiskLruCache.Value lruValue = diskLruCache.get(safeKey);
            if (lruValue == null)
                throw new Exception("lruValue error: null");
            File file = lruValue.getFile(0);
            if (null == file || !file.exists())
                throw new Exception("file error: null");
            String absolutePath = file.getAbsolutePath();
            if (null == absolutePath || absolutePath.length() == 0)
                throw new Exception("absolutePath error: " + absolutePath);
            return absolutePath;
        } catch (Exception e) {
            MvvmUtil.logE("OkhttpGlideUtil => getCacheAbsolutePath => " + e.getMessage());
            return null;
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

    private static void init(@NonNull ImageView imageView, @DrawableRes int defatltRes) {
        try {
            if (null == imageView)
                throw new Exception("imageView error: null");
            imageView.setImageDrawable(null);
            if (defatltRes == 0)
                throw new Exception("defatltRes error: " + defatltRes);
            Drawable drawable = imageView.getResources().getDrawable(defatltRes);
            if (null == drawable)
                throw new Exception("drawable error: null");
            imageView.setImageDrawable(drawable);
        } catch (Exception e) {
            MvvmUtil.logE("OkhttpGlideUtil => init => " + e.getMessage());
        }
    }

    private static void check(@NonNull ImageView imageView, String url, @DrawableRes int defatltRes, @IntRange(from = 10, to = 100) int encodeQuality, @NonNull boolean skipMemoryCache) {
        init(imageView, defatltRes);
        try {
            if (null == imageView)
                throw new Exception("imageView error: null");
            if (null == url || url.length() == 0)
                throw new Exception("url error: " + url);
            Context context = imageView.getContext();
            if (null == context)
                throw new Exception("context error: null");
            Context applicationContext = context.getApplicationContext();
            if (null == applicationContext)
                throw new Exception("applicationContext error: null");
            into(applicationContext, imageView, url, defatltRes, encodeQuality, skipMemoryCache);
        } catch (Exception e) {
            MvvmUtil.logE("OkhttpGlideUtil => check => " + e.getMessage());
        }
    }

    private static void into(@NonNull Context context, @NonNull ImageView imageView, String url, @DrawableRes int defatltRes, @IntRange(from = 10, to = 100) int encodeQuality, @NonNull boolean skipMemoryCache) {

        try {
            if (null == imageView)
                throw new Exception("imageView error: null");
            if (null == url || url.length() == 0)
                throw new Exception("url error: " + url);
            RequestOptions requestOptions = getRequestOptions(context, defatltRes, encodeQuality, skipMemoryCache);
            if (null == requestOptions)
                throw new Exception("requestOptions error: null");
//            // 圆角
//            float v = context.getResources().getDimension(R.dimen.dp_4);
//            GlideRoundTransform transform = new GlideRoundTransform(context, v);
//            options.transform(transform);
//            Glide.with(context).load(url).apply(requestOptions).into(imageView);
            Glide.with(context).asDrawable().load(url).apply(requestOptions).into(imageView);

        } catch (Exception e) {
            MvvmUtil.logE("OkhttpGlideUtil => into => " + e.getMessage());
        }
    }

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
                        .priority(Priority.IMMEDIATE)
                        .diskCacheStrategy(DiskCacheStrategy.DATA) //缓存原始图片数据
                        .skipMemoryCache(skipMemoryCache); //跳过内存缓存
                try {
                    if (defatltRes == 0)
                        throw new Exception();
                    Drawable drawable = context.getResources().getDrawable(defatltRes);
                    if (null != drawable) {
                        requestOptions.error(drawable).placeholder(drawable);
                    }
                } catch (Exception e) {
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
}
