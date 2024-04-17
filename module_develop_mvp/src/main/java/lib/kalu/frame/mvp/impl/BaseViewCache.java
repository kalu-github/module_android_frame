package lib.kalu.frame.mvp.impl;

import android.content.Context;

import androidx.annotation.Keep;
import androidx.annotation.RawRes;

import lib.kalu.frame.mvp.util.CacheUtil;
import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewCache extends BaseViewContext {

    default boolean put(String key, String value) {
        try {
            if (null == key || key.length() == 0)
                throw new Exception("error: key null");
            if (null == value) {
                value = "";
            }
            Context context = getContext();
            boolean put = CacheUtil.put(context, key, value);
            if (!put)
                throw new Exception("error: put false");
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewCache => put => " + e.getMessage());
            return false;
        }
    }

    default String get(String key) {
        try {
            if (null == key || key.length() == 0)
                throw new Exception("error: key null");
            Context context = getContext();
            String s = CacheUtil.get(context, key);
            if (s.length() == 0)
                throw new Exception("error: s null");
            return s;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewCache => get => " + e.getMessage());
            return "";
        }
    }
}
