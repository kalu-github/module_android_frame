package lib.kalu.frame.mvvm;

import android.app.Application;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.WeakHashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author zhanghang
 * @description: mvvm => vm
 * @date :2022-01-17
 */
@Keep
public abstract class BaseViewModel<V extends BaseView, M extends BaseModel> extends AndroidViewModel {

    private V mV;
    private M mM;

    @Keep
    public BaseViewModel(@NonNull Application application, @NonNull V v, @NonNull M m) {
        super(application);
        this.mV = v;
        this.mM = m;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        BaseModel model = getModel();
        if (null == model)
            return;
        CompositeDisposable disposables = model.getDisposables();
        if (null == disposables || disposables.size() == 0)
            return;
        disposables.dispose();
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

    @Keep
    public final <T> void regist(@NonNull LifecycleOwner owner, @NonNull String key, @NonNull Observer<T> observer) {
        M model = getModel();
        if (null == model)
            return;
        model.regist(owner, key, observer);
    }
}
