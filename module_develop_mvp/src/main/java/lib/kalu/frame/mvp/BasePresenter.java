package lib.kalu.frame.mvp;

import android.util.Log;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
@Keep
public abstract class BasePresenter<K extends BaseView, M extends BaseViewModel> {

    private K mBaseView;
    private M mBaseViewModel;

    @Keep
    public BasePresenter(@NonNull K k, @NonNull M m) {
        this.mBaseView = k;
        this.mBaseViewModel = m;
        init();
    }

    protected K getBaseView() {
        if (null != mBaseView) {
            return mBaseView;
        } else {
            throw new IllegalArgumentException("mBaseView is null");
        }
    }

    protected M getBaseViewModel() {
        if (null != mBaseViewModel) {
            return mBaseViewModel;
        } else {
            throw new IllegalArgumentException("mBaseViewModel is null");
        }
    }

    private final void init() {
        Log.d("BasePresenter", "init => mBaseView = " + mBaseView + ", mBaseViewModel = " + mBaseViewModel);
    }
}