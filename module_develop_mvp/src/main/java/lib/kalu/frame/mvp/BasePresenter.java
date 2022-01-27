package lib.kalu.frame.mvp;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author zhanghang
 * @description: mvp => p
 * @date :2022-01-17
 */
@Keep
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {

    private V mV;
    private M mM;

    @Keep
    public BasePresenter(@NonNull V v, @NonNull M m) {
        this.mV = v;
        this.mM = m;
    }

    @Keep
    protected void dispose() {
        BaseModel model = getModel();
        if (null == model)
            return;
        CompositeDisposable disposables = model.getDisposables();
        if (null == disposables || disposables.size() == 0)
            return;
        disposables.dispose();
    }

    @Keep
    protected final <T> void onCall(@NonNull String key, @NonNull T t) {

    }

    @Keep
    protected V getView() {
        if (null == mV)
            throw new IllegalArgumentException("BaseViewModel => getView => view is null");
        return this.mV;
    }

    @Keep
    protected M getModel() {
        if (null == mM)
            throw new IllegalArgumentException("BaseViewModel => getModel => model is null");
        return this.mM;
    }
}
