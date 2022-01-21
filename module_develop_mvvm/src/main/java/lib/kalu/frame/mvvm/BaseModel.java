package lib.kalu.frame.mvvm;

import android.util.Log;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.disposables.Disposable;

/**
 * @author zhanghang
 * @description: mvvm => m
 * @date :2022-01-17
 */
@Keep
public abstract class BaseModel {

    @Keep
    public BaseModel() {
        init();
    }

    private final void init() {
        Log.d("BasePresenter", "init =>");
    }

    /*****************/

    private final Map<String, BaseLiveData> mWHM = new HashMap<>(1);

    private final <T> BaseLiveData<T> create(@NonNull String key) {
        boolean contains = mWHM.containsKey(key);
        if (!contains) {
            BaseLiveData<T> tBaseLiveData = new BaseLiveData<>();
            mWHM.put(key, tBaseLiveData);
        }
        BaseLiveData liveData = mWHM.get(key);
        return liveData;
    }

    protected final <T> void regist(@NonNull LifecycleOwner owner, @NonNull String key, @NonNull Observer<T> observer) {
        BaseLiveData<T> liveData = create(key);
        liveData.observe(owner, observer);
    }

    protected final <T> void update(@NonNull String key, @NonNull T value) {
        BaseLiveData liveData = mWHM.get(key);
        liveData.postValue(value);
    }

    /***********************/

    private final LinkedList<Disposable> mT = new LinkedList<Disposable>();

    protected final LinkedList<Disposable> getDisposables() {
        return mT;
    }

    protected final void addDisposable(@NonNull Disposable disposable) {
        if (null == disposable)
            return;
        mT.add(disposable);
    }
}