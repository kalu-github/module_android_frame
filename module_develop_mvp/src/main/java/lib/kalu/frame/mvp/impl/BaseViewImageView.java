package lib.kalu.frame.mvp.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import java.nio.ByteBuffer;

import lib.kalu.frame.mvp.BaseView;
import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewImageView {

    default void setImageByteBuffer(@IdRes int id, @NonNull ByteBuffer byteBuffer) {
        try {
            if (null == byteBuffer)
                throw new Exception("error: byteBuffer null");
            ImageView imageView = ((BaseView) this).findViewById(id);
            if(null == imageView)
                throw new Exception("error: imageView null");
            setImageByteBuffer(imageView, byteBuffer);
        } catch (Exception e) {
            MvpUtil.logE("setImageByteBuffer => " + e.getMessage());
        }
    }

    default void setImageByteBuffer(@NonNull ImageView imageView, ByteBuffer byteBuffer) {
        try {
            if (null == byteBuffer)
                throw new Exception("error: byteBuffer null");
            if (null == imageView)
                throw new Exception("error: imageView null");
            byteBuffer.rewind();
            int remaining = byteBuffer.remaining();
            byte[] byteArray = new byte[remaining];
            byteBuffer.get(byteArray);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            MvpUtil.logE("setImageByteBuffer => " + e.getMessage());
        }
    }


    default void setImageFile(@IdRes int id, @NonNull String path) {
        try {
            ImageView imageView = ((BaseView) this).findViewById(id);
            setImageFile(imageView, path);
        } catch (Exception e) {
            MvpUtil.logE("setImageFile => " + e.getMessage());
        }
    }

    default void setImageFile(@NonNull ImageView imageView, @NonNull String path) {
        try {
            if (null == imageView)
                throw new Exception("imageView is null");
            imageView.setImageURI(Uri.parse(path));
        } catch (Exception e) {
            MvpUtil.logE("setImageFile => " + e.getMessage());
        }
    }

    default void setImageResource(@NonNull ImageView imageView, @DrawableRes int drawable) {
        try {
            if (null == imageView)
                throw new Exception("imageView is null");
            imageView.setImageResource(drawable);
        } catch (Exception e) {
            MvpUtil.logE("setImageResource => " + e.getMessage());
        }
    }

    default void setImageResource(@IdRes int id, @DrawableRes int drawable) {
        try {
            ImageView imageView = ((BaseView) this).findViewById(id);
            setImageResource(imageView, drawable);
        } catch (Exception e) {
            MvpUtil.logE("setImageResource => " + e.getMessage());
        }
    }

    default void setImageResource(@NonNull View view, @IdRes int id, @DrawableRes int drawable) {
        try {
            if (null == view)
                throw new Exception("view is null");
            ImageView imageView = view.findViewById(id);
            setImageResource(imageView, drawable);
        } catch (Exception e) {
            MvpUtil.logE("setImageResource => " + e.getMessage());
        }
    }

    default void setImageDrawable(@IdRes int id, @NonNull Drawable drawable) {
        try {
            ImageView imageView = ((BaseView) this).findViewById(id);
            imageView.setImageDrawable(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setImageBitmap(@IdRes int id, @NonNull Bitmap bitmap) {
        try {
            ImageView imageView = ((BaseView) this).findViewById(id);
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setImageBackgroundColor(@IdRes int id, @ColorInt int color) {
        try {
            ImageView imageView = ((BaseView) this).findViewById(id);
            imageView.setBackgroundColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setImageBackgroundColorRes(@IdRes int id, @ColorRes int res) {
        try {
            Context context = ((BaseView) this).getContext();
            int color = context.getResources().getColor(res);
            ImageView imageView = ((BaseView) this).findViewById(id);
            imageView.setBackgroundColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setImageBackgroundResource(@IdRes int id, @DrawableRes int drawable) {
        try {
            ImageView imageView = ((BaseView) this).findViewById(id);
            imageView.setBackgroundResource(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setImageBackground(@IdRes int id, @NonNull Drawable drawable) {
        try {
            ImageView imageView = ((BaseView) this).findViewById(id);
            imageView.setBackgroundDrawable(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setImageScaleType(@IdRes int id, @NonNull ImageView.ScaleType scaleType) {
        try {
            ImageView imageView = ((BaseView) this).findViewById(id);
            imageView.setScaleType(scaleType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setImageAlpha(@IdRes int id, @NonNull int alpha) {
        try {
            ImageView imageView = ((BaseView) this).findViewById(id);
            imageView.setImageAlpha(alpha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
