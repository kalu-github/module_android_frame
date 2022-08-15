package lib.kalu.frame.mvp.model;

import androidx.annotation.Keep;

@Keep
public interface RequestModel<T> {

    int getCode();

    T getNext();

    boolean isNext();
}
