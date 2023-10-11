package lib.kalu.frame.mvvm.glide;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

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
        File filesDir = context.getFilesDir();
        if (null == filesDir || !filesDir.exists()) {
            filesDir.mkdirs();
        }
        String absolutePath = filesDir.getAbsolutePath();
        File filesGlide = new File(absolutePath, "glide");
        if (null == filesGlide || !filesGlide.exists()) {
            filesGlide.mkdir();
        }
        String diskCacheFolder = filesGlide.getAbsolutePath();
        DiskLruCacheFactory diskLruCacheFactory = new DiskLruCacheFactory(diskCacheFolder, 200 * 1024 * 1024);
        builder.setDiskCache(diskLruCacheFactory);
        int size = initMemorySizeMB() * 1024 * 1024;
        builder.setMemoryCache(new LruResourceCache(size));
    }

    protected int initMemorySizeMB() {
        return 100;
    }
}