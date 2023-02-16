package lib.kalu.frame.mvp;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import org.json.JSONObject;

import lib.kalu.frame.mvp.impl.BaseViewFocus;
import lib.kalu.frame.mvp.impl.BaseViewContext;
import lib.kalu.frame.mvp.impl.BaseViewFindViewById;
import lib.kalu.frame.mvp.impl.BaseViewFragmentManager;
import lib.kalu.frame.mvp.impl.BaseViewImageView;
import lib.kalu.frame.mvp.impl.BaseViewIntent;
import lib.kalu.frame.mvp.impl.BaseViewRecyclerView;
import lib.kalu.frame.mvp.impl.BaseViewResources;
import lib.kalu.frame.mvp.impl.BaseViewTextView;
import lib.kalu.frame.mvp.impl.BaseViewView;
import lib.kalu.frame.mvp.impl.BaseViewViewGroup;
import lib.kalu.frame.mvp.impl.BaseViewWindow;

/**
 * @author zhanghang
 * @description: mvp => v
 * @date :2022-01-17
 */
@Keep
public interface BaseView extends BaseViewContext,
        BaseViewWindow,
        BaseViewFragmentManager,
        BaseViewFocus,
        BaseViewResources,
        BaseViewFindViewById,
        BaseViewIntent,
        BaseViewView,
        BaseViewViewGroup,
        BaseViewTextView,
        BaseViewImageView,
        BaseViewRecyclerView {

    default <T> T getPresenter() {
        throw new IllegalArgumentException("not implements method getPresenter()");
    }

    default void onCall(@NonNull int code, @NonNull JSONObject object) {
    }

    default void initWindow() {
    }

    default void showLoading() {
    }

    default void hideLoading() {
    }

    int initLayout();

    void initData();
}
