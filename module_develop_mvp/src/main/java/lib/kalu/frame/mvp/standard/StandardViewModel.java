package lib.kalu.frame.mvp.standard;

import android.app.Application;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvp.BaseModel;
import lib.kalu.frame.mvp.BasePresenter;
import lib.kalu.frame.mvp.BaseView;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
@Keep
public class StandardViewModel<V extends BaseView, M extends BaseModel> extends BasePresenter {

    @Keep
    public StandardViewModel(@NonNull BaseView baseView, @NonNull BaseModel baseModel) {
        super(baseView, baseModel);
    }

    @Override
    protected M getModel() {
        return (M) super.getModel();
    }

    @Override
    protected V getView() {
        return (V) super.getView();
    }
}
