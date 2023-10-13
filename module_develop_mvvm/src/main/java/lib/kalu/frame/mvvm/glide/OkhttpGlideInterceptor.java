package lib.kalu.frame.mvvm.glide;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

final class OkhttpGlideInterceptor implements Interceptor {

    private final String TAG = "module_glide";

    @Override
    public Response intercept(Chain chain) throws IOException {
        long timeMillis = System.currentTimeMillis();
        try {
            Request request = chain.request();
            String url = request.url().toString();
            logE("request[" + timeMillis + "] => url = " + url);
            Response response = chain.proceed(request);
            logE("response[" + timeMillis + "] => result = ok");
            return response;
        } catch (Exception e) {
            logE("response[" + timeMillis + "] => result = " + e.getMessage());
            throw e;
        }
    }

    private void logE(@NonNull String s) {
        Log.e(TAG, s);
    }
}
