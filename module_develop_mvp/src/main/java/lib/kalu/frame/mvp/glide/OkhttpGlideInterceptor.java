package lib.kalu.frame.mvp.glide;

import okhttp3.Interceptor;
import okhttp3.Response;

final class OkhttpGlideInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) {
        try {
            return chain.proceed(chain.request());
        } catch (Exception e) {
            return null;
        }
    }
}
