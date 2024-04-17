package lib.kalu.frame.mvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import org.json.JSONObject;

import lib.kalu.frame.mvp.impl.BaseViewActivity;
import lib.kalu.frame.mvp.impl.BaseViewCache;
import lib.kalu.frame.mvp.impl.BaseViewClip;
import lib.kalu.frame.mvp.impl.BaseViewContext;
import lib.kalu.frame.mvp.impl.BaseViewDescendantFocusability;
import lib.kalu.frame.mvp.impl.BaseViewEnable;
import lib.kalu.frame.mvp.impl.BaseViewFindViewById;
import lib.kalu.frame.mvp.impl.BaseViewFocusable;
import lib.kalu.frame.mvp.impl.BaseViewFragment;
import lib.kalu.frame.mvp.impl.BaseViewFragmentManager;
import lib.kalu.frame.mvp.impl.BaseViewImageView;
import lib.kalu.frame.mvp.impl.BaseViewIntent;
import lib.kalu.frame.mvp.impl.BaseViewLoading;
import lib.kalu.frame.mvp.impl.BaseViewMargin;
import lib.kalu.frame.mvp.impl.BaseViewPadding;
import lib.kalu.frame.mvp.impl.BaseViewProcess;
import lib.kalu.frame.mvp.impl.BaseViewRaw;
import lib.kalu.frame.mvp.impl.BaseViewRecyclerView;
import lib.kalu.frame.mvp.impl.BaseViewResources;
import lib.kalu.frame.mvp.impl.BaseViewSelected;
import lib.kalu.frame.mvp.impl.BaseViewTag;
import lib.kalu.frame.mvp.impl.BaseViewTextView;
import lib.kalu.frame.mvp.impl.BaseViewToast;
import lib.kalu.frame.mvp.impl.BaseViewView;
import lib.kalu.frame.mvp.impl.BaseViewVisibility;
import lib.kalu.frame.mvp.impl.BaseViewWindow;

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
        BaseViewView,
        BaseViewTextView,
        BaseViewImageView,
        BaseViewClip,
        BaseViewMargin,
        BaseViewPadding,
        BaseViewRecyclerView,
        BaseViewLoading,
        BaseViewRaw,
        BaseViewCache {

    default <T> T getPresenter() {
        throw new IllegalArgumentException("not implements method getPresenter()");
    }

    default void onCall(@NonNull int code, @NonNull JSONObject object) {
    }

    default void initWindow() {
    }

    int initLayout();

    void initData();
}
