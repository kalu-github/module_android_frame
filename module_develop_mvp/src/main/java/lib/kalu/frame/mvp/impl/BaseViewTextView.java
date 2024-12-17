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

    default void appendTextInput(@NonNull View view, @IdRes int id, @NonNull CharSequence value) {
        try {
            TextView textView = view.findViewById(id);
            appendTextInput(textView, value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => appendTextInput => Exception => " + e.getMessage());
        }
    }

    default void appendTextInput(@IdRes int id, @NonNull CharSequence value) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            appendTextInput(textView, value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => appendTextInput => Exception => " + e.getMessage());
        }
    }

    default void appendTextInput(@NonNull TextView textView, @NonNull CharSequence value) {
        try {
            if (null == textView)
                throw new Exception("error: textView null");
            if (null == value)
                throw new Exception("error: value null");
            if (value.length() == 0)
                throw new Exception("error: value.length() == 0");
            String data = textView.getText().toString();
            textView.setText(data + value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => appendTextInput => Exception => " + e.getMessage());
        }
    }

    default void backspaceTextInput(@NonNull View view, @IdRes int id) {
        try {
            TextView textView = view.findViewById(id);
            backspaceTextInput(textView);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => backspaceTextInput => Exception => " + e.getMessage());
        }
    }

    default void backspaceTextInput(@IdRes int id) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            backspaceTextInput(textView);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => backspaceTextInput => Exception => " + e.getMessage());
        }
    }

    default void backspaceTextInput(@NonNull TextView textView) {
        try {
            if (null == textView)
                throw new Exception("error: textView null");
            CharSequence text = textView.getText();
            if (null == text)
                throw new Exception("error: text null");
            int length = text.length();
            if (length == 0)
                throw new Exception("error: text.length() == 0");
            if (length == 1) {
                textView.setText("");
            } else {
                CharSequence charSequence = text.subSequence(0, --length);
                textView.setText(charSequence);
            }
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => backspaceTextInput => Exception => " + e.getMessage());
        }
    }

    default void clearTextInput(@NonNull View view, @IdRes int id) {
        try {
            TextView textView = view.findViewById(id);
            clearTextInput(textView);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => clearTextInput => Exception => " + e.getMessage());
        }
    }

    default void clearTextInput(@IdRes int id) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            clearTextInput(textView);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => clearTextInput => Exception => " + e.getMessage());
        }
    }

    default void clearTextInput(@NonNull TextView textView) {
        try {
            if (null == textView)
                throw new Exception("error: textView null");
            textView.setText("");
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => clearTextInput => Exception => " + e.getMessage());
        }
    }

    default boolean isTextEmpty(@NonNull View view, @IdRes int id) {
        try {
            TextView textView = view.findViewById(id);
            return isTextEmpty(textView);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => isTextEmpty => Exception => " + e.getMessage());
            return false;
        }
    }

    default boolean isTextEmpty(@IdRes int id) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            return isTextEmpty(textView);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => isTextEmpty => Exception => " + e.getMessage());
            return false;
        }
    }

    default boolean isTextEmpty(@NonNull TextView textView) {
        try {
            if (null == textView)
                throw new Exception("error: textView null");
            CharSequence text = textView.getText();
            return null == text || text.length() == 0;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => isTextEmpty => Exception => " + e.getMessage());
            return false;
        }
    }

    default String getTextInput(@NonNull View view, @IdRes int id) {
        try {
            TextView textView = view.findViewById(id);
            return getTextInput(textView);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => getTextInput => Exception => " + e.getMessage());
            return null;
        }
    }

    default String getTextInput(@IdRes int id) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            return getTextInput(textView);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => getTextInput => Exception => " + e.getMessage());
            return null;
        }
    }

    default String getTextInput(@NonNull TextView textView) {
        try {
            if (null == textView)
                throw new Exception("error: textView null");
            CharSequence text = textView.getText();
            if (null == text)
                throw new Exception("warning: text null");
            return String.valueOf(text);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => getTextInput => Exception => " + e.getMessage());
            return "";
        }
    }

    default void setTextInput(@NonNull View view, @IdRes int id, @StringRes int res) {
        try {
            TextView textView = view.findViewById(id);
            String value = textView.getResources().getString(res);
            setTextInput(textView, value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextInput => Exception => " + e.getMessage());
        }
    }

    default void setTextInput(@NonNull View view, @IdRes int id, @NonNull CharSequence value) {
        try {
            TextView textView = view.findViewById(id);
            setTextInput(textView, value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextInput => Exception => " + e.getMessage());
        }
    }

    default void setTextInput(@IdRes int id, @NonNull CharSequence value) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            setTextInput(textView, value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextInput => Exception => " + e.getMessage());
        }
    }

    default void setTextInput(@IdRes int id, @StringRes int res) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            String value = textView.getResources().getString(res);
            setTextInput(textView, value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextInput => Exception => " + e.getMessage());
        }
    }

    default void setTextInput(@NonNull TextView textView, @NonNull CharSequence value) {
        try {
            if (null == textView)
                throw new Exception("error: textView null");
            if (null == value)
                throw new Exception("error: value null");
            if (value.length() == 0)
                throw new Exception("error: value.length() == 0");
            textView.setText(value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextInput => Exception => " + e.getMessage());
        }
    }

    default void setTextHint(@NonNull View view, @IdRes int id, @StringRes int res) {
        try {
            TextView textView = view.findViewById(id);
            String value = textView.getResources().getString(res);
            setTextHint(textView, value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextHint => Exception => " + e.getMessage());
        }
    }

    default void setTextHint(@NonNull View view, @IdRes int id, @NonNull CharSequence value) {
        try {
            TextView textView = view.findViewById(id);
            setTextHint(textView, value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextHint => Exception => " + e.getMessage());
        }
    }

    default void setTextHint(@IdRes int id, @NonNull CharSequence value) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            setTextHint(textView, value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextHint => Exception => " + e.getMessage());
        }
    }

    default void setTextHint(@IdRes int id, @StringRes int res) {
        try {
            TextView textView = ((BaseView) this).findViewById(id);
            String value = textView.getResources().getString(res);
            setTextHint(textView, value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextHint => Exception => " + e.getMessage());
        }
    }

    default void setTextHint(@NonNull TextView textView, @NonNull CharSequence value) {
        try {
            if (null == textView)
                throw new Exception("error: textView null");
            if (null == value)
                throw new Exception("error: value null");
            if (value.length() == 0)
                throw new Exception("error: value.length() == 0");
            textView.setHint(value);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTextView => setTextHint => Exception => " + e.getMessage());
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
