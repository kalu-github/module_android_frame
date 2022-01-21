package lib.kalu.frame.mvp;

import android.app.Application;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.util.LinkedList;
import java.util.WeakHashMap;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
@Keep
public abstract class BaseViewModel<T> extends AndroidViewModel {

    private final WeakHashMap<String, BaseLiveData> mWHM = new WeakHashMap<>(1);

    @Keep
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    private final <T> BaseLiveData<T> create(@NonNull String key) {

        boolean contains = mWHM.containsKey(key);
        if (!contains) {
            BaseLiveData<T> tBaseLiveData = new BaseLiveData<>();
            mWHM.put(key, tBaseLiveData);
        }
        BaseLiveData liveData = mWHM.get(key);
        return liveData;
    }

    public final <T> void attach(@NonNull LifecycleOwner owner, @NonNull String key, @NonNull Observer<T> observer) {
        BaseLiveData<T> liveData = create(key);
        liveData.observe(owner, observer);
    }

    public final <T> void put(@NonNull String key, @NonNull T value) {
        BaseLiveData liveData = mWHM.get(key);
        liveData.postValue(value);
    }

    private final LinkedList<T> mT = new LinkedList<T>();

    @Override
    protected void onCleared() {
        super.onCleared();
        if (null == mT || mT.size() == 0)
            return;
        for (T t : mT) {
            onRelease(t);
        }
    }

    protected final void addDisposable(@NonNull T disposable) {
        if (null == disposable)
            return;
        mT.add(disposable);
    }

    protected abstract void onRelease(@NonNull T t);
}
