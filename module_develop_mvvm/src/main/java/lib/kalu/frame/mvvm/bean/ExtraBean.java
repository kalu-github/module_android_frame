package lib.kalu.frame.mvvm.bean;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lib.kalu.frame.mvvm.interceptor.OkhttpImpl;

@Keep
public class ExtraBean implements Serializable {

    private boolean local;
    @SerializedName(OkhttpImpl.EXTRA)
    private String extra = null;

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
