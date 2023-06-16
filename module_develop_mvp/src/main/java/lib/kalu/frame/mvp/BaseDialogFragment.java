package lib.kalu.frame.mvp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import lib.kalu.frame.mvp.util.MvpUtil;

public class BaseDialogFragment extends DialogFragment {

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        try {
            super.setupDialog(dialog, style);
        }catch (Exception e){
            MvpUtil.logE("BaseDialogFragment => setupDialog => " + e.getMessage());
        }
    }
}
