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
import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewTextView {

    default void appendText(@NonNull View view, @IdRes int id, @NonNull CharSequence str) {
        try {
            TextView textView = view.findViewById(id);
            String string = textView.getText().toString();
            textView.setText(string + str);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => appendText => Exception => " + e.getMessage());
        }
    }

    default void appendText(@IdRes int id, @NonNull CharSequence str) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            String string = textView.getText().toString();
            textView.setText(string + str);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => appendText => Exception => " + e.getMessage());
        }
    }

    default void backspaceText(@NonNull View view, @IdRes int id) {
        try {
            TextView textView = view.findViewById(id);
            String string = textView.getText().toString();
            int length = string.length();
            if (length == 0)
                throw new Exception("warning: length == 0");
            if (length == 1) {
                textView.setText("");
            } else {
                String substring = string.substring(0, --length);
                textView.setText(substring);
            }
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => backspaceText => Exception => " + e.getMessage());
        }
    }

    default void backspaceText(@IdRes int id) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            String string = textView.getText().toString();
            int length = string.length();
            if (length == 0)
                throw new Exception("warning: length == 0");
            if (length == 1) {
                textView.setText("");
            } else {
                String substring = string.substring(0, --length);
                textView.setText(substring);
            }
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => backspaceText => Exception => " + e.getMessage());
        }
    }

    default void clearText(@NonNull View view, @IdRes int id) {
        try {
            TextView textView = view.findViewById(id);
            textView.setText("");
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => clearText => Exception => " + e.getMessage());
        }
    }

    default void clearText(@IdRes int id) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            textView.setText("");
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => clearText => Exception => " + e.getMessage());
        }
    }

    default boolean isTextEmpty(@NonNull View view, @IdRes int id) {
        try {
            TextView textView = view.findViewById(id);
            CharSequence text = textView.getText();
            return text.length() == 0;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => isTextEmpty => Exception => " + e.getMessage());
            return true;
        }
    }

    default boolean isTextEmpty(@IdRes int id) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            CharSequence text = textView.getText();
            return text.length() == 0;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => isTextEmpty => Exception => " + e.getMessage());
            return true;
        }
    }

    default String getText(@NonNull View view, @IdRes int id) {
        try {
            TextView textView = view.findViewById(id);
            return textView.getText().toString();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => getText => Exception => " + e.getMessage());
            return null;
        }
    }

    default void setText(@NonNull View view, @IdRes int id, @StringRes int res) {
        try {
            TextView textView = view.findViewById(id);
            textView.setText(res);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setText => Exception => " + e.getMessage());
        }
    }

    default void setText(@NonNull View view, @IdRes int id, @NonNull CharSequence str) {
        try {
            TextView textView = view.findViewById(id);
            textView.setText(str);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setText => Exception => " + e.getMessage());
        }
    }

    default void setText(@IdRes int id, @NonNull CharSequence str) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            textView.setText(str);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setText => Exception => " + e.getMessage());
        }
    }

    default void setText(@IdRes int id, @StringRes int res) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            textView.setText(res);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setText => Exception => " + e.getMessage());
        }
    }

    default void setTextColor(@IdRes int id, @ColorInt int color) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            textView.setTextColor(color);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextColor => Exception => " + e.getMessage());
        }
    }

    default void setTextColorRes(@IdRes int id, @ColorRes int res) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            Context context = ((BaseView) this).getContext();
            int color = context.getResources().getColor(res);
            textView.setTextColor(color);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextColorRes => Exception => " + e.getMessage());
        }
    }

    default void setTextSize(@IdRes int id, @DimenRes int dimen) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            textView.setTextSize(dimen);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextSize => Exception => " + e.getMessage());
        }
    }
}
