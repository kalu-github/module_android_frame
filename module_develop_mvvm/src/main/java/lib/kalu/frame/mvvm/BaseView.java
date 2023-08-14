package lib.kalu.frame.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import org.json.JSONObject;

import lib.kalu.frame.mvvm.impl.BaseViewActivity;
import lib.kalu.frame.mvvm.impl.BaseViewClip;
import lib.kalu.frame.mvvm.impl.BaseViewContext;
import lib.kalu.frame.mvvm.impl.BaseViewDescendantFocusability;
import lib.kalu.frame.mvvm.impl.BaseViewEnable;
import lib.kalu.frame.mvvm.impl.BaseViewFindViewById;
import lib.kalu.frame.mvvm.impl.BaseViewFocusable;
import lib.kalu.frame.mvvm.impl.BaseViewFragment;
import lib.kalu.frame.mvvm.impl.BaseViewFragmentManager;
import lib.kalu.frame.mvvm.impl.BaseViewImageView;
import lib.kalu.frame.mvvm.impl.BaseViewIntent;
import lib.kalu.frame.mvvm.impl.BaseViewLoading;
import lib.kalu.frame.mvvm.impl.BaseViewMargin;
import lib.kalu.frame.mvvm.impl.BaseViewPadding;
import lib.kalu.frame.mvvm.impl.BaseViewProcess;
import lib.kalu.frame.mvvm.impl.BaseViewRecyclerView;
import lib.kalu.frame.mvvm.impl.BaseViewResources;
import lib.kalu.frame.mvvm.impl.BaseViewSelected;
import lib.kalu.frame.mvvm.impl.BaseViewTag;
import lib.kalu.frame.mvvm.impl.BaseViewTextView;
import lib.kalu.frame.mvvm.impl.BaseViewToast;
import lib.kalu.frame.mvvm.impl.BaseViewVisibility;
import lib.kalu.frame.mvvm.impl.BaseViewWindow;

/**
 * @author zhanghang
 * @description: mvp => v
 * @date :2022-01-17
 */
@Keep
public interface BaseView extends BaseViewContext,
        BaseViewFragment,
        BaseViewActivity,
        BaseViewProcess,
        BaseViewToast,
        BaseViewWindow,
        BaseViewFragmentManager,
        BaseViewResources,
        BaseViewFindViewById,
        BaseViewIntent,
        BaseViewTag,
        BaseViewEnable,
        BaseViewSelected,
        BaseViewFocusable,
        BaseViewVisibility,
        BaseViewDescendantFocusability,
        BaseViewTextView,
        BaseViewImageView,
        BaseViewClip,
        BaseViewMargin,
        BaseViewPadding,
        BaseViewRecyclerView,
        BaseViewLoading {

    default <T> T getPresenter() {
        throw new IllegalArgumentException("not implements method getPresenter()");
    }

    default void onCall(@NonNull int code, @NonNull JSONObject object) {
    }

    default void initWindow() {
    }

    int initLayout();

    void initData();

    default <T> T getViewModel() {
        throw new IllegalArgumentException("not implements method getViewModel()");
    }
}
