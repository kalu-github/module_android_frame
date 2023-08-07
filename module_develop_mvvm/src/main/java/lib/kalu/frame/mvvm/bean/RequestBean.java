package lib.kalu.frame.mvvm.bean;

import androidx.annotation.Keep;

@Keep
public abstract class RequestBean<T> extends ExtraBean {

    public abstract int getCode();

    public abstract T getData();

    public abstract boolean isNext();
}
