package lib.kalu.frame.mvvm.standard;

import android.app.Application;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import io.reactivex.disposables.Disposable;
import lib.kalu.frame.mvvm.BaseModel;
import lib.kalu.frame.mvvm.BaseView;
import lib.kalu.frame.mvvm.BaseViewModel;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
@Keep
public class StandardViewModel<V extends BaseView, M extends BaseModel> extends BaseViewModel {

    @Keep
    public StandardViewModel(@NonNull Application application, @NonNull V v, @NonNull M m) {
        super(application, v, m);
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
