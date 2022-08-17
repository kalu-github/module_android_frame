package lib.kalu.frame.mvp.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import lib.kalu.frame.mvp.BaseView;

@Keep
public interface BaseViewImageView {

    default void setImageResource(@IdRes int id, @DrawableRes int drawable) {
        try {
            ImageView imageView = ((BaseView) this).findViewById(id);
            imageView.setImageResource(drawable);
        } catch (Exception e) {
            e.printStackTrace();
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
            imageView.setBackground(drawable);
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
