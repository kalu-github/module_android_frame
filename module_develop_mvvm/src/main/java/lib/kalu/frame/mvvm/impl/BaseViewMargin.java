package lib.kalu.frame.mvvm.impl;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.DimenRes;
import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvvm.util.MvvmUtil;

@Keep
public interface BaseViewMargin extends BaseViewFindViewById {

    default void setMargin(@IdRes int id,
                           @NonNull int[] margin) {
        try {
            if (null == margin || margin.length != 4)
                throw new Exception("margin error: " + margin);
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
            if (null == layoutParams)
                throw new Exception("layoutParams error: null");
            if (!(layoutParams instanceof RelativeLayout.LayoutParams))
                throw new Exception("layoutParams error: not instanceof RelativeLayout.LayoutParams");
            ((RelativeLayout.LayoutParams) layoutParams).leftMargin = margin[0];
            ((RelativeLayout.LayoutParams) layoutParams).topMargin = margin[1];
            ((RelativeLayout.LayoutParams) layoutParams).rightMargin = margin[2];
            ((RelativeLayout.LayoutParams) layoutParams).bottomMargin = margin[3];
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewMargin => setMargin => " + e.getMessage());
        }
    }

    default void setMarginLeft(@IdRes int id,
                               @DimenRes int resId) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
            if (null == layoutParams)
                throw new Exception("layoutParams error: null");
            if (!(layoutParams instanceof RelativeLayout.LayoutParams))
                throw new Exception("layoutParams error: not instanceof RelativeLayout.LayoutParams");
            int offset = viewById.getResources().getDimensionPixelOffset(resId);
            ((RelativeLayout.LayoutParams) layoutParams).leftMargin = offset;
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewMargin => setMarginLeft => " + e.getMessage());
        }
    }

    default void setMargiRight(@IdRes int id,
                               @DimenRes int resId) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
            if (null == layoutParams)
                throw new Exception("layoutParams error: null");
            if (!(layoutParams instanceof RelativeLayout.LayoutParams))
                throw new Exception("layoutParams error: not instanceof RelativeLayout.LayoutParams");
            int offset = viewById.getResources().getDimensionPixelOffset(resId);
            ((RelativeLayout.LayoutParams) layoutParams).rightMargin = offset;
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewMargin => setMarginRight => " + e.getMessage());
        }
    }

    default void setMargiTop(@IdRes int id,
                             @DimenRes int resId) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
            if (null == layoutParams)
                throw new Exception("layoutParams error: null");
            if (!(layoutParams instanceof RelativeLayout.LayoutParams))
                throw new Exception("layoutParams error: not instanceof RelativeLayout.LayoutParams");
            int offset = viewById.getResources().getDimensionPixelOffset(resId);
            ((RelativeLayout.LayoutParams) layoutParams).topMargin = offset;
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewMargin => setMarginTop => " + e.getMessage());
        }
    }

    default void setMargiBottom(@IdRes int id,
                                @DimenRes int resId) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
            if (null == layoutParams)
                throw new Exception("layoutParams error: null");
            if (!(layoutParams instanceof RelativeLayout.LayoutParams))
                throw new Exception("layoutParams error: not instanceof RelativeLayout.LayoutParams");
            int offset = viewById.getResources().getDimensionPixelOffset(resId);
            ((RelativeLayout.LayoutParams) layoutParams).bottomMargin = offset;
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewMargin => setMarginBottom => " + e.getMessage());
        }
    }
}
