package lib.kalu.frame.mvp;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import lib.kalu.frame.mvp.bean.RequestBean;
import lib.kalu.frame.mvp.listener.OnRequestChangeListener;
import lib.kalu.frame.mvp.transformer.ComposeSchedulers;

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
    public final void addDisposable(@NonNull Disposable disposable) {
        addDisposable(false, (Integer.MIN_VALUE - 100), disposable);
    }

    @Keep
    public final void addDisposable(@NonNull boolean remove, @NonNull Disposable disposable) {
        addDisposable(remove, (Integer.MIN_VALUE - 100), disposable);
    }

    @Keep
    public final void addDisposable(@NonNull boolean remove, @NonNull int key, @NonNull Disposable disposable) {
        BaseModel model = getModel();
        if (null == model)
            return;
        if (remove) {
            removeDisposable(key);
        }
        model.addDisposable(key, disposable);
    }

    @Keep
    public void cleanDisposable() {
        BaseModel model = getModel();
        if (null == model)
            return;
        model.cleanDisposable();
    }

    @Keep
    public void removeDisposable(@NonNull int key) {
        BaseModel model = getModel();
        if (null == model)
            return;
        model.removeDisposable(key);
    }

    protected final <T> void request(@NonNull Observable<? extends RequestBean<T>> observable, @NonNull OnRequestChangeListener<T> listener) {
        request(observable, listener, true);
    }

    protected final <T> void request(@NonNull Observable<? extends RequestBean<T>> observable, @NonNull OnRequestChangeListener<T> listener, @NonNull boolean loading) {

        addDisposable(observable
                .delay(40, TimeUnit.MILLISECONDS)
                .compose(ComposeSchedulers.io_main())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) {
                        if (loading) {
                            getView().showLoading();
                        }
                        listener.onStart();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        if (loading) {
                            getView().hideLoading();
                        }
                        listener.onError(null == throwable ? "not message" : throwable.getMessage());
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() {
                        if (loading) {
                            getView().hideLoading();
                        }
                        listener.onComplete();
                    }
                })
                .doOnNext(new Consumer<RequestBean<T>>() {
                    @Override
                    public void accept(RequestBean<T> model) {
                        if (loading) {
                            getView().hideLoading();
                        }
                        try {
                            boolean next = model.isNext();
                            if (next) {
                                listener.onNext(model.getData());
                            } else {
                                listener.onError("not succ");
                            }
                        } catch (Exception e) {
                            listener.onError(e.getMessage());
                        }
                    }
                })
                .subscribe()
        );
    }
}
