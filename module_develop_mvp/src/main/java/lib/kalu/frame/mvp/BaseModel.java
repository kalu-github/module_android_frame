package lib.kalu.frame.mvp;

import android.util.Log;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import java.util.LinkedList;

import io.reactivex.disposables.Disposable;

/**
 * @author zhanghang
 * @description: mvp => m
 * @date :2022-01-17
 */
@Keep
public class BaseModel {

    @Keep
    public BaseModel() {
        init();
    }

    protected void init() {
        Log.d("BaseModel", "init =>");
    }

    /***********************/

    private LinkedList<Disposable> mDisposable = new LinkedList<>();

    protected final void addDisposable(@NonNull Disposable disposable) {
        if (null == disposable)
            return;
        mDisposable.add(disposable);
    }

    protected final void cleanDisposable() {
        while (true) {
            if (mDisposable.isEmpty())
                break;
            Disposable disposable = mDisposable.removeLast();
            disposable.dispose();
            disposable = null;
        }
    }
}