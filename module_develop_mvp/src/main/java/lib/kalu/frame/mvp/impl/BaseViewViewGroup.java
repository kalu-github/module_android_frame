package lib.kalu.frame.mvp.impl;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;

import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewViewGroup extends BaseViewFindViewById {

    default int getChildCount(@IdRes int id) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("error: viewById null");
            if (!(viewById instanceof ViewGroup))
                throw new Exception("error: viewById not instanceof ViewGroup");
            return ((ViewGroup) viewById).getChildCount();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewView => getChildCount => " + e.getMessage());
            return 0;
        }
    }
}
