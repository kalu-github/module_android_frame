package lib.kalu.frame.mvvm;

import android.util.Log;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import io.reactivex.disposables.Disposable;
import lib.kalu.frame.mvvm.util.MvpUtil;

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
        Log.d("BaseModel", "init =>");
    }

    /*****************/

    private final Map<String, BaseLiveData> mMap = new HashMap<>(1);

    private final <T> BaseLiveData<T> create(@NonNull String key) {
        boolean contains = mMap.containsKey(key);
        if (!contains) {
            BaseLiveData<T> tBaseLiveData = new BaseLiveData<>();
            mMap.put(key, tBaseLiveData);
        }
        BaseLiveData liveData = mMap.get(key);
        return liveData;
    }

    protected final <T> void regist(@NonNull LifecycleOwner owner, @NonNull String key, @NonNull Observer<T> observer) {
        BaseLiveData<T> liveData = create(key);
        liveData.observe(owner, observer);
    }

    protected final <T> void update(@NonNull String key, @NonNull T value) {
        BaseLiveData liveData = mMap.get(key);
        liveData.postValue(value);
    }

    /***********************/
    private final HashMap<Integer, List<Disposable>> mDisposables = new HashMap<>();

    protected final void addDisposable(@NonNull int k, @NonNull Disposable disposable) {
        try {
            if (null == disposable)
                throw new Exception("disposable error: null");
            boolean containsKey = mDisposables.containsKey(k);
            if (!containsKey) {
                LinkedList<Disposable> disposables = new LinkedList<>();
                mDisposables.put(k, disposables);
            }
            List<Disposable> disposables = mDisposables.get(k);
            disposables.add(disposable);
        } catch (Exception e) {
            MvpUtil.logE("BaseModel => addDisposable => " + e.getMessage());
        }
    }

    protected final void cleanDisposable() {
        try {
            Set<Map.Entry<Integer, List<Disposable>>> entrySet = mDisposables.entrySet();
            if (null == entrySet || entrySet.size() == 0)
                throw new Exception("entrySet error: " + entrySet);
            for (Map.Entry<Integer, List<Disposable>> entry : entrySet) {
                if (null == entry)
                    continue;
                Integer key = entry.getKey();
                List<Disposable> value = entry.getValue();
                if (null == value || value.size() == 0)
                    continue;
                for (Disposable disposable : value) {
                    if (null == disposable)
                        continue;
                    disposable.dispose();
                }
            }
            mDisposables.clear();
        } catch (Exception e) {
            MvpUtil.logE("BaseModel => cleanDisposable => " + e.getMessage());
        }
    }

    protected final void removeDisposable(@NonNull int k) {
        try {
            Set<Map.Entry<Integer, List<Disposable>>> entrySet = mDisposables.entrySet();
            if (null == entrySet || entrySet.size() == 0)
                throw new Exception("entrySet error: " + entrySet);
            for (Map.Entry<Integer, List<Disposable>> entry : entrySet) {
                if (null == entry)
                    continue;
                int key = entry.getKey();
                if (key != k)
                    continue;
                List<Disposable> value = entry.getValue();
                if (null == value || value.size() == 0)
                    continue;
                for (Disposable disposable : value) {
                    if (null == disposable)
                        continue;
                    disposable.dispose();
                }
            }
            mDisposables.remove(k);
        } catch (Exception e) {
            MvpUtil.logE("BaseModel => removeDisposable => " + e.getMessage());
        }
    }
}