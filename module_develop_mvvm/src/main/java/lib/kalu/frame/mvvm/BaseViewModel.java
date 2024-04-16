package lib.kalu.frame.mvvm;

import android.app.Application;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import lib.kalu.frame.mvvm.util.MvvmUtil;

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
        try {
            M baseModel = getModel();
            if (null == baseModel)
                throw new Exception("baseModel error: null");
            baseModel.clearDisposable();
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewModel => onCleared => " + e.getMessage());
        }
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
        try {
            M baseModel = getModel();
            if (null == baseModel)
                throw new Exception("baseModel error: null");
            baseModel.regist(owner, key, observer);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewModel => regist => " + e.getMessage());
        }
    }
}
