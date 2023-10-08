package lib.kalu.frame.mvvm.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

import okhttp3.OkHttpClient;

@GlideModule
public class OkhttpGlideModule extends AppGlideModule {

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        OkHttpClient okHttpClient = OkhttpGlideClient.getHttpClient();
        OkhttpUrlLoader.Factory factory = new OkhttpUrlLoader.Factory(okHttpClient);
        registry.replace(GlideUrl.class, InputStream.class, factory);
    }
}