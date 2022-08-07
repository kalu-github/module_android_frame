package lib.kalu.frame.mvp.model;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class ExtraModel implements Serializable {
    private String extra = null;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
