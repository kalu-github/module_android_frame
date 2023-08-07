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
        BaseViewRecyclerView {

    default <T> T getPresenter() {
        throw new IllegalArgumentException("not implements method getPresenter()");
    }

    default void onCall(@NonNull int code, @NonNull JSONObject object) {
    }

    default void initWindow() {
    }

    default void showLoading() {
        try {
            if (initLoadingLayoutRes() == 0 || initLoadingIdRes() == 0)
                throw new Exception();
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception();
            View viewById = rootView.findViewById(initLoadingIdRes());
            if (null != viewById)
                throw new IllegalStateException();
            View inflate = LayoutInflater.from(getContext()).inflate(initLoadingLayoutRes(), null);
            inflate.setVisibility(View.GONE);
            rootView.addView(inflate, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            inflate.bringToFront();
            inflate.setVisibility(View.VISIBLE);
        } catch (IllegalStateException e) {
            ViewGroup rootView = getRootView();
            View viewById = rootView.findViewById(initLoadingIdRes());
            viewById.bringToFront();
            viewById.setVisibility(View.VISIBLE);
        } catch (Exception e) {
        }
    }

    default void hideLoading() {
        try {
            if (initLoadingLayoutRes() == 0 || initLoadingIdRes() == 0)
                throw new Exception();
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception();
            View viewById = rootView.findViewById(initLoadingIdRes());
            if (null == viewById)
                throw new Exception();
            viewById.setVisibility(View.GONE);
        } catch (Exception e) {
        }
    }

    @LayoutRes
    default int initLoadingLayoutRes() {
        return 0;
    }

    @IdRes
    default int initLoadingIdRes() {
        return 0;
    }

    int initLayout();

    void initData();

    default <T> T getViewModel() {
        throw new IllegalArgumentException("not implements method getViewModel()");
    }
}
