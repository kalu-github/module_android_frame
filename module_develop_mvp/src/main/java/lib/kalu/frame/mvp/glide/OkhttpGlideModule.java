package lib.kalu.frame.mvp.glide;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.InputStream;

import okhttp3.OkHttpClient;

public class OkhttpGlideModule extends AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        OkHttpClient okHttpClient = OkhttpGlideClient.getHttpClient();
        OkhttpGlideUrlLoader.Factory factory = new OkhttpGlideUrlLoader.Factory(okHttpClient);
        registry.replace(GlideUrl.class, InputStream.class, factory);
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        File cacheDir = context.getExternalCacheDir();
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        File glideDir = new File(cacheDir, "glide");
        if (!glideDir.exists()) {
            glideDir.mkdirs();
        }
        String diskCacheFolder = glideDir.getAbsolutePath();
        int diskSizeMB = initDiskSizeMB();
        builder.setDiskCache(new DiskLruCacheFactory(diskCacheFolder, diskSizeMB * 1024 * 1024));
        int memorySizeMB = initMemorySizeMB();
        builder.setMemoryCache(new LruResourceCache(memorySizeMB * 1024 * 1024));
        int bitmapSizeMB = initBitmapSizeMB();
        builder.setBitmapPool(new LruBitmapPool(bitmapSizeMB * 1024 * 1024));
        //设置读取不在缓存中资源的线程
        builder.setSourceExecutor(GlideExecutor.newSourceExecutor());
        //设置读取磁盘缓存中资源的线程
        builder.setDiskCacheExecutor(GlideExecutor.newDiskCacheExecutor());
        //设置日志级别
        builder.setLogLevel(Log.VERBOSE);
    }

    protected int initBitmapSizeMB() {
        return 40;
    }

    protected int initMemorySizeMB() {
        return 40;
    }

    protected int initDiskSizeMB() {
        return 200;
    }
}