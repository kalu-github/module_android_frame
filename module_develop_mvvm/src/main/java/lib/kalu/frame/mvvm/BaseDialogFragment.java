package lib.kalu.frame.mvvm;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.PixelFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Field;

import lib.kalu.frame.mvvm.util.MvpUtil;

public class BaseDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getActivity().getWindow().setFormat(PixelFormat.TRANSLUCENT);
        } catch (Exception e) {
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        try {
            super.setupDialog(dialog, style);
        } catch (Exception e) {
            MvpUtil.logE("BaseDialogFragment => setupDialog => " + e.getMessage());
        }
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        try {
            Class cls = DialogFragment.class;
            Field mDismissed = cls.getDeclaredField("mDismissed");
            mDismissed.setAccessible(true);
            mDismissed.set(this, false);
            Field mShownByMe = cls.getDeclaredField("mShownByMe");
            mShownByMe.setAccessible(true);
            mShownByMe.set(this, true);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(this, tag);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            super.show(manager, tag);
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        try {
            dismissAllowingStateLoss();
        } catch (Exception e) {
            super.onDismiss(dialog);
        }
    }

    @Override
    public void dismiss() {
        try {
            dismissAllowingStateLoss();
        } catch (Exception e) {
            super.dismiss();
        }
    }
}