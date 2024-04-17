package lib.kalu.frame.mvp.impl;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.Keep;
import androidx.annotation.RawRes;

import java.io.InputStream;

import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewRaw extends BaseViewFindViewById, BaseViewContext {

    default String openRawString(@RawRes int rawId) {
        try {
            byte[] bytes = openRawBytes(rawId);
            if (null == bytes)
                throw new Exception("error: null == bytes");
            String str = new String(bytes);
            if (str.length() == 0)
                throw new Exception("error: null str");
            return str;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRaw => openRawString => " + e.getMessage());
            return null;
        }
    }

    default byte[] openRawBytes(@RawRes int rawId) {
        try {
            InputStream inputStream = openRawInputStream(rawId);
            if (null == inputStream)
                throw new Exception("error: null == inputStream");
            byte[] bytes = new byte[inputStream.available()];
            if (null == bytes)
                throw new Exception("error: null == bytes");
            inputStream.read(bytes);
            return bytes;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRaw => openRawBytes => " + e.getMessage());
            return null;
        }
    }

    default InputStream openRawInputStream(@RawRes int rawId) {
        try {
            Context context = getContext();
            if (null == context)
                throw new Exception("error: null == context");
            Resources resources = context.getResources();
            if (null == resources)
                throw new Exception("error: null == resources");
            InputStream inputStream = resources.openRawResource(rawId);
            if (null == inputStream)
                throw new Exception("error: null == inputStream");
            return inputStream;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewRaw => openRawInputStream => " + e.getMessage());
            return null;
        }
    }
}
