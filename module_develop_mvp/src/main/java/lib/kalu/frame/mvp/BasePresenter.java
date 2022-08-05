package lib.kalu.frame.mvp;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author zhanghang
 * @description: mvp => p
 * @date :2022-01-17
 */
@Keep
public class BasePresenter<V extends BaseView> {

    private V mV;
    private BaseModel mM;

    @Keep
    public BasePresenter(@NonNull V v) {
        this.mV = v;
        this.mM = new BaseModel();
    }

    @Keep
    protected V getView() {
        if (null == mV)
            throw new IllegalArgumentException("BaseViewModel => getView => view is null");
        return this.mV;
    }

    @Keep
    protected BaseModel getModel() {
        if (null == mM)
            throw new IllegalArgumentException("BaseViewModel => getModel => model is null");
        return this.mM;
    }

    @Keep
    protected final void addDisposable(@NonNull Disposable disposable) {
        BaseModel model = getModel();
        if (null == model)
            return;
        model.addDisposable(disposable);
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
}
