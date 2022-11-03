package lib.kalu.frame.mvvm;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONObject;

import lib.kalu.frame.mvvm.impl.BaseViewFindViewById;
import lib.kalu.frame.mvvm.impl.BaseViewIntent;

/**
 * @author zhanghang
 * @description: mvvm => v
 * @date :2022-01-17
 */
@Keep
public interface BaseView extends BaseViewFindViewById, BaseViewIntent {

//    default <T> T getModel() {
//        throw new IllegalArgumentException("not implements method getModel()");
//    }

    //    default <T extends BaseModel> T initModel() {
//        throw new IllegalArgumentException("not implements method initModel()");
//    }
//
    default <T> T getViewModel() {
        throw new IllegalArgumentException("not implements method getViewModel()");
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
