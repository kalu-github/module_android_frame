
package lib.kalu.frame.mvvm.impl;

import android.os.Process;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvvm.util.MvpUtil;

@Keep
public interface BaseViewProcess {

    default void killProcess() {
        killProcess(false);
    }

    default void killProcess(@NonNull boolean android) {
        try {
            if (android) {
                Process.killProcess(Process.myPid());
            } else {
                System.exit(0);
            }
        } catch (Exception e) {
            MvpUtil.logE("BaseViewProcess => killProcess => " + e.getMessage());
        }
    }
}
