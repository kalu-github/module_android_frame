package lib.kalu.frame.mvvm.impl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvvm.BaseView;
import lib.kalu.frame.mvvm.util.MvvmUtil;

@Keep
public interface BaseViewView extends BaseViewContext {

    default void setTextBackgroundDrawableRes(@IdRes int id, @DrawableRes int res) {
        try {
            View view = ((BaseView) this).findViewById(id);
            if (null == view)
                throw new Exception("view error: null");
            Context context = getContext();
            if (null == view)
                throw new Exception("context error: null");
            Resources resources = context.getResources();
            if (null == resources)
                throw new Exception("resources error: null");
            Drawable drawable = resources.getDrawable(res);
            if (null == drawable)
                throw new Exception("drawable error: null");
            view.setBackground(drawable);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewView => setTextBackgroundDrawableRes => " + e.getMessage());
        }
    }

    default void setBackgroundColorRes(@IdRes int id, @ColorRes int res) {
        try {
            View view = ((BaseView) this).findViewById(id);
            if (null == view)
                throw new Exception("view error: null");
            Context context = getContext();
            if (null == view)
                throw new Exception("context error: null");
            Resources resources = context.getResources();
            if (null == resources)
                throw new Exception("resources error: null");
            int color = resources.getColor(res);
            ColorDrawable drawable = new ColorDrawable(color);
            if (null == drawable)
                throw new Exception("drawable error: null");
            view.setBackground(drawable);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewView => setBackgroundColorRes => " + e.getMessage());
        }
    }

    default void setBackgroundColorInt(@IdRes int id, @ColorInt int color) {
        try {
            View view = ((BaseView) this).findViewById(id);
            if (null == view)
                throw new Exception("view error: null");
            ColorDrawable drawable = new ColorDrawable(color);
            if (null == drawable)
                throw new Exception("drawable error: null");
            view.setBackground(drawable);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewView => setBackgroundColorInt => " + e.getMessage());
        }
    }

    default void setBackgroundDrawable(@IdRes int id, @NonNull Drawable drawable) {
        try {
            if (null == drawable)
                throw new Exception("drawable error: null");
            View view = ((BaseView) this).findViewById(id);
            if (null == view)
                throw new Exception("view error: null");
            view.setBackground(drawable);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewView => setBackgroundDrawable => " + e.getMessage());
        }
    }
}
