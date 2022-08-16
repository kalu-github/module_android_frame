package lib.kalu.frame.mvp.bean;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class ExtraBean implements Serializable {

    private boolean local;
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
