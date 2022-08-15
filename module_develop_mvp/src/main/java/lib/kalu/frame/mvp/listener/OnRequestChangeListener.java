package lib.kalu.frame.mvp.listener;

import androidx.annotation.NonNull;

public interface OnRequestChangeListener<T> {

    default void onStart() {
    }

    default void onComplete() {
    }

    default void onError(@NonNull String error) {
    }

    void onNext(@NonNull T t);
}
