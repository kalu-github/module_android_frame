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
import lib.kalu.frame.mvvm.util.MvpUtil;

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
            baseModel.cleanDisposable();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewModel => onCleared => " + e.getMessage());
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
            MvpUtil.logE("BaseViewModel => regist => " + e.getMessage());
        }
    }

//    @Keep
//    public final void addDisposable(@NonNull Disposable disposable) {
//        addDisposable(false, (Integer.MIN_VALUE - 100), disposable);
//    }
//
//    @Keep
//    public final void addDisposable(@NonNull boolean remove, @NonNull Disposable disposable) {
//        addDisposable(remove, (Integer.MIN_VALUE - 100), disposable);
//    }
//
//    @Keep
//    public final void addDisposable(@NonNull boolean remove, @NonNull int key, @NonNull Disposable disposable) {
//        try {
//            M baseModel = getModel();
//            if (null == baseModel)
//                throw new Exception("baseModel error: null");
//            if (remove) {
//                removeDisposable(key);
//            }
//            baseModel.addDisposable(key, disposable);
//        } catch (Exception e) {
//            MvpUtil.logE("BaseViewModel => addDisposable => " + e.getMessage());
//        }
//    }
//
//    @Keep
//    public void cleanDisposable() {
//        try {
//            M baseModel = getModel();
//            if (null == baseModel)
//                throw new Exception("baseModel error: null");
//            baseModel.cleanDisposable();
//        } catch (Exception e) {
//            MvpUtil.logE("BaseViewModel => cleanDisposable => " + e.getMessage());
//        }
//    }
//
//    @Keep
//    public void removeDisposable(@NonNull int key) {
//        try {
//            M baseModel = getModel();
//            if (null == baseModel)
//                throw new Exception("baseModel error: null");
//            baseModel.removeDisposable(key);
//        } catch (Exception e) {
//            MvpUtil.logE("BaseViewModel => removeDisposable => " + e.getMessage());
//        }
//    }
}
