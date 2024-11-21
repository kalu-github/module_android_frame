package lib.kalu.frame.mvp.impl;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import lib.kalu.frame.mvp.BaseView;

@Keep
public interface BaseViewTextView {

    default String getText(@NonNull View view, @IdRes int id) {
        try {
            TextView textView = view.findViewById(id);
            return textView.getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    default void setText(@NonNull View view, @IdRes int id, @StringRes int res) {
        try {
            TextView textView = view.findViewById(id);
            textView.setText(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setText(@NonNull View view, @IdRes int id, @NonNull CharSequence str) {
        try {
            TextView textView = view.findViewById(id);
            textView.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setText(@IdRes int id, @NonNull CharSequence str) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            textView.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setText(@IdRes int id, @StringRes int res) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            textView.setText(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setTextColor(@IdRes int id, @ColorInt int color) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            textView.setTextColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setTextColorRes(@IdRes int id, @ColorRes int res) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            Context context = ((BaseView) this).getContext();
            int color = context.getResources().getColor(res);
            textView.setTextColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void setTextSize(@IdRes int id, @DimenRes int dimen) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            textView.setTextSize(dimen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
