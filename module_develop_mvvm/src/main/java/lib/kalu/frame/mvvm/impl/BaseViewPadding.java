package lib.kalu.frame.mvvm.impl;

import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvvm.util.MvvmUtil;

@Keep
public interface BaseViewPadding extends BaseViewFindViewById {

    default void setPadding(@IdRes int id,
                            @NonNull int[] padding) {
        try {
            if (null == padding || padding.length != 4)
                throw new Exception("padding error: " + padding);
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            viewById.setPadding(padding[0], padding[1], padding[2], padding[3]);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewPadding => setPadding => " + e.getMessage());
        }
    }

    default void setPaddingLeft(@IdRes int id,
                                @DimenRes int resId) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            int offset = viewById.getResources().getDimensionPixelOffset(resId);
            int right = viewById.getPaddingRight();
            int top = viewById.getPaddingTop();
            int bottom = viewById.getPaddingBottom();
            viewById.setPadding(offset, top, right, bottom);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewPadding => setPaddingLeft => " + e.getMessage());
        }
    }

    default void setPaddingRight(@IdRes int id,
                                 @DimenRes int resId) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            int offset = viewById.getResources().getDimensionPixelOffset(resId);
            int left = viewById.getPaddingLeft();
            int top = viewById.getPaddingTop();
            int bottom = viewById.getPaddingBottom();
            viewById.setPadding(left, top, offset, bottom);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewPadding => setPaddingRight => " + e.getMessage());
        }
    }

    default void setPaddingTop(@IdRes int id,
                               @DimenRes int resId) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            int offset = viewById.getResources().getDimensionPixelOffset(resId);
            int left = viewById.getPaddingLeft();
            int right = viewById.getPaddingRight();
            int bottom = viewById.getPaddingBottom();
            viewById.setPadding(left, offset, right, bottom);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewPadding => setPaddingTop => " + e.getMessage());
        }
    }

    default void setPaddingBottom(@IdRes int id,
                                  @DimenRes int resId) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            int offset = viewById.getResources().getDimensionPixelOffset(resId);
            int left = viewById.getPaddingLeft();
            int right = viewById.getPaddingRight();
            int top = viewById.getPaddingTop();
            viewById.setPadding(left, top, right, offset);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewPadding => setPaddingBottom => " + e.getMessage());
        }
    }
}
