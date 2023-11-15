
package lib.kalu.frame.mvp.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import lib.kalu.frame.mvp.glide.OkhttpGlideUtil;

public final class GlideUtil extends OkhttpGlideUtil {

    public static void load(@NonNull ImageView imageView, String url) {
        check(imageView, url, 0, 40, true);
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
            MvpUtil.logE("GlideUtil => check => " + e.getMessage());
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
            MvpUtil.logE("GlideUtil => into => " + e.getMessage());
        }
    }
}