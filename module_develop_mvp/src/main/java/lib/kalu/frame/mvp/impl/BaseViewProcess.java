
package lib.kalu.frame.mvp.impl;

import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import lib.kalu.frame.mvp.util.MvpUtil;
import lib.kalu.frame.mvp.util.WrapperUtil;

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
