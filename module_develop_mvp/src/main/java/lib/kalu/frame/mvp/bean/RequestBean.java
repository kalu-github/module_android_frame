package lib.kalu.frame.mvp.bean;

import androidx.annotation.Keep;

@Keep
public interface RequestBean<T> {

    int getCode();

    T getNext();

    boolean isNext();
}
