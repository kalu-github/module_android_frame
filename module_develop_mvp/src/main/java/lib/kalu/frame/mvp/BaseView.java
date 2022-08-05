package lib.kalu.frame.mvp;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import org.json.JSONObject;

import lib.kalu.frame.mvp.impl.BaseViewFindViewById;
import lib.kalu.frame.mvp.impl.BaseViewIntent;

/**
 * @author zhanghang
 * @description: mvp => v
 * @date :2022-01-17
 */
@Keep
public interface BaseView extends BaseViewFindViewById, BaseViewIntent {

    default <T> T getPresenter() {
        throw new IllegalArgumentException("not implements method getPresenter()");
    }

    default <T extends BasePresenter> T initPresenter() {
        throw new IllegalArgumentException("not implements method initPresenter()");
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
