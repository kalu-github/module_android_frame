package lib.kalu.frame.mvp.glide;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lib.kalu.frame.mvp.util.MvpUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class OkhttpGlideInterceptor implements Interceptor {

    protected static final String HEADER_PROGRESS = "progress";
    protected static final String HEADER_PROGRESS_OK = "1";
    protected static final String HEADER_PROGRESS_NO = "0";

    private static final Map<String, OkhttpGlideProgressListener> LISTENER_MAP = new HashMap<>();

    public static OkhttpGlideProgressListener getListener(@NonNull String url) {
        if (null == url || url.length() == 0)
            return null;
        return LISTENER_MAP.get(url);
    }

    public static void putListener(@NonNull String url, @NonNull OkhttpGlideProgressListener listener) {
        if (null == listener)
            return;
        if (null == url || url.length() == 0)
            return;
        LISTENER_MAP.put(url, listener);
    }

    public static void removeListener(@NonNull String url) {
        if (null == url || url.length() == 0)
            return;
        LISTENER_MAP.remove(url);
    }

    public static void removeListener(@NonNull OkhttpGlideProgressListener listener) {
        if (null == listener)
            return;
        Set<Map.Entry<String, OkhttpGlideProgressListener>> entrySet = LISTENER_MAP.entrySet();
        if (null == entrySet)
            return;
        for (Map.Entry<String, OkhttpGlideProgressListener> entry : entrySet) {
            if (null == entry)
                continue;
            OkhttpGlideProgressListener value = entry.getValue();
            if (null == value)
                continue;
            if (value == listener) {
                String key = entry.getKey();
                LISTENER_MAP.remove(key);
                break;
            }
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            Request request = chain.request();
            String header = request.header(HEADER_PROGRESS);
            if (null == header || header.equals(HEADER_PROGRESS_NO)) {
                return chain.proceed(request);
            } else {
                String url = request.url().toString();
                Response response = chain.proceed(request);
                Response newResponse = response.newBuilder().body(new OkhttpGlideProgressResponseBody(url, response.body())).build();
                return newResponse;
            }
        } catch (Exception e) {
            MvpUtil.logE("OkhttpGlideInterceptor => intercept => " + e.getMessage());
            throw e;
        }
    }
}
