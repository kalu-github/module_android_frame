package lib.kalu.frame.mvp.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

@Keep
public interface BaseViewIntent {

    default boolean getBooleanExtra(@NonNull String name, @NonNull boolean defaults) {

        try {

            // activiy
            if (this instanceof Activity) {
                Activity activity = (Activity) this;
                return getBooleanExtra(activity, name, defaults);
            }
            // fragment
            else if (this instanceof Fragment) {
                Fragment fragment = (Fragment) this;
                return getBooleanExtra(fragment, name, defaults);
            }
            // defaults
            else {
                return defaults;
            }
        } catch (Exception e) {
            return defaults;
        }
    }

    default boolean getBooleanExtra(@NonNull Activity activity, @NonNull String name, @NonNull boolean defaults) {

        try {
            Intent intent = activity.getIntent();
            return intent.getBooleanExtra(name, defaults);
        } catch (Exception e) {
            return defaults;
        }
    }

    default boolean getBooleanExtra(@NonNull Fragment fragment, @NonNull String name, @NonNull boolean defaults) {

        try {
            Bundle bundle = fragment.getArguments();
            return bundle.getBoolean(name, defaults);
        } catch (Exception e) {
            return defaults;
        }
    }

    default void putBooleanExtra(@NonNull String name, @NonNull boolean value) {

        try {

            // activiy
            if (this instanceof Activity) {
                Activity activity = (Activity) this;
                putBooleanExtra(activity, name, value);
            }
            // fragment
            else if (this instanceof Fragment) {
                Fragment fragment = (Fragment) this;
                putBooleanExtra(fragment, name, value);
            }
        } catch (Exception e) {
        }
    }

    default void putBooleanExtra(@NonNull Activity activity, @NonNull String name, @NonNull boolean value) {

        try {
            Intent intent = activity.getIntent();
            intent.putExtra(name, value);
        } catch (Exception e) {
        }
    }

    default void putBooleanExtra(@NonNull Fragment fragment, @NonNull String name, @NonNull boolean value) {

        try {
            Bundle bundle = fragment.getArguments();
            bundle.putBoolean(name, value);
        } catch (Exception e) {
        }
    }

    default int getIntExtra(@NonNull String name, @NonNull int defaultValue) {

        try {

            // activiy
            if (this instanceof Activity) {
                Activity activity = (Activity) this;
                return getIntExtra(activity, name, defaultValue);
            }
            // fragment
            else if (this instanceof Fragment) {
                Fragment fragment = (Fragment) this;
                return getIntExtra(fragment, name, defaultValue);
            }
            // defaults
            else {
                return defaultValue;
            }
        } catch (Exception e) {
            return defaultValue;
        }
    }

    default int getIntExtra(@NonNull Activity activity, @NonNull String name, @NonNull int defaultValue) {

        try {
            Intent intent = activity.getIntent();
            return intent.getIntExtra(name, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    default int getIntExtra(@NonNull Fragment fragment, @NonNull String name, @NonNull int defaultValue) {

        try {
            Bundle bundle = fragment.getArguments();
            return bundle.getInt(name, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    default void putIntExtra(@NonNull String name, @NonNull int value) {

        try {

            // activiy
            if (this instanceof Activity) {
                Activity activity = (Activity) this;
                putIntExtra(activity, name, value);
            }
            // fragment
            else if (this instanceof Fragment) {
                Fragment fragment = (Fragment) this;
                putIntExtra(fragment, name, value);
            }
        } catch (Exception e) {
        }
    }

    default void putIntExtra(@NonNull Activity activity, @NonNull String name, @NonNull int value) {

        try {
            Intent intent = activity.getIntent();
            intent.putExtra(name, value);
        } catch (Exception e) {
        }
    }

    default void putIntExtra(@NonNull Fragment fragment, @NonNull String name, @NonNull int value) {

        try {
            Bundle bundle = fragment.getArguments();
            bundle.putInt(name, value);
        } catch (Exception e) {
        }
    }

    default String getStringExtra(@NonNull String name) {

        try {

            // activiy
            if (this instanceof Activity) {
                Activity activity = (Activity) this;
                return getStringExtra(activity, name);
            }
            // fragment
            else if (this instanceof Fragment) {
                Fragment fragment = (Fragment) this;
                return getStringExtra(fragment, name);
            }
            // defaults
            else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    default String getStringExtra(@NonNull Activity activity, @NonNull String name) {

        try {
            Intent intent = activity.getIntent();
            return intent.getStringExtra(name);
        } catch (Exception e) {
            return null;
        }
    }

    default String getStringExtra(@NonNull Fragment fragment, @NonNull String name) {

        try {
            Bundle bundle = fragment.getArguments();
            return bundle.getString(name, null);
        } catch (Exception e) {
            return null;
        }
    }

    default void putStringExtra(@NonNull String name, @NonNull String value) {

        try {

            // activiy
            if (this instanceof Activity) {
                Activity activity = (Activity) this;
                putStringExtra(activity, name, value);
            }
            // fragment
            else if (this instanceof Fragment) {
                Fragment fragment = (Fragment) this;
                putStringExtra(fragment, name, value);
            }
        } catch (Exception e) {
        }
    }

    default void putStringExtra(@NonNull Activity activity, @NonNull String name, @NonNull String value) {

        try {
            Intent intent = activity.getIntent();
            intent.putExtra(name, value);
        } catch (Exception e) {
        }
    }

    default void putStringExtra(@NonNull Fragment fragment, @NonNull String name, @NonNull String value) {

        try {
            Bundle bundle = fragment.getArguments();
            bundle.putString(name, value);
        } catch (Exception e) {
        }
    }

    default long getLongExtra(@NonNull String name, @NonNull long defaultValue) {

        try {

            // activiy
            if (this instanceof Activity) {
                Activity activity = (Activity) this;
                return getLongExtra(activity, name, defaultValue);
            }
            // fragment
            else if (this instanceof Fragment) {
                Fragment fragment = (Fragment) this;
                return getLongExtra(fragment, name, defaultValue);
            }
            // defaults
            else {
                return defaultValue;
            }
        } catch (Exception e) {
            return defaultValue;
        }
    }

    default long getLongExtra(@NonNull Activity activity, @NonNull String name, @NonNull long defaultValue) {

        try {
            Intent intent = activity.getIntent();
            return intent.getLongExtra(name, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    default long getLongExtra(@NonNull Fragment fragment, @NonNull String name, @NonNull long defaultValue) {

        try {
            Bundle bundle = fragment.getArguments();
            return bundle.getLong(name, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    default void putLongExtra(@NonNull String name, @NonNull long value) {

        try {

            // activiy
            if (this instanceof Activity) {
                Activity activity = (Activity) this;
                putLongExtra(activity, name, value);
            }
            // fragment
            else if (this instanceof Fragment) {
                Fragment fragment = (Fragment) this;
                putLongExtra(fragment, name, value);
            }
        } catch (Exception e) {
        }
    }

    default void putLongExtra(@NonNull Activity activity, @NonNull String name, @NonNull long value) {

        try {
            Intent intent = activity.getIntent();
            intent.putExtra(name, value);
        } catch (Exception e) {
        }
    }

    default void putLongExtra(@NonNull Fragment fragment, @NonNull String name, @NonNull long value) {

        try {
            Bundle bundle = fragment.getArguments();
            bundle.putLong(name, value);
        } catch (Exception e) {
        }
    }
}
