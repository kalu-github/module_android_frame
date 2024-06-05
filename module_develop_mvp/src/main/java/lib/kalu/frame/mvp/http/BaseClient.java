
package lib.kalu.frame.mvp.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lib.kalu.frame.mvp.interceptor.OkhttpInterceptorStandard;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description: Http
 * create by kalu on 2018/10/23 10:57
 */
public abstract class BaseClient {

    private Retrofit mRetrofit;

    private final Interceptor mCustomInterceptor = initInterceptor();
    private final OkhttpInterceptorStandard mOkhttpInterceptorStandard = new OkhttpInterceptorStandard();
    private final Gson mGson = new GsonBuilder()//建造者模式设置不同的配置
            .serializeNulls()//序列化为null对象
            .setDateFormat("yyyy-MM-dd HH:mm:ss") //设置日期的格式
            .disableHtmlEscaping()//防止对网址乱码 忽略对特殊字符的转换
            .setLenient() // 使用JsonReader.setLenient(true)在第1行、第1列、路径$接受格式错误的JSON
            .create();
    private final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(null != mCustomInterceptor ? mCustomInterceptor : mOkhttpInterceptorStandard)
            .readTimeout(initReadTimeout(), TimeUnit.SECONDS)
            .writeTimeout(initWriteTimeout(), TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10, 60, TimeUnit.MINUTES))
            .retryOnConnectionFailure(true)
            .proxySelector(initProxy() ? ProxySelector.getDefault() : new ProxySelector() {
                @Override
                public List<Proxy> select(URI uri) {
                    return Collections.singletonList(Proxy.NO_PROXY);
                }

                @Override
                public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                }
            })
            .build();

    protected BaseClient() {
        // 最大并发请求数为
        mOkHttpClient.dispatcher().setMaxRequests(initMaxRequests());
        // 每个主机最大请求数
        mOkHttpClient.dispatcher().setMaxRequestsPerHost(initMaxRequestsPerHost());
        // 发起请求
        mRetrofit = new Retrofit.Builder()
                .baseUrl(initBaseUrl())
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    protected int initMaxRequests() {
        return 64;
    }

    protected int initMaxRequestsPerHost() {
        return 5;
    }

    protected int initReadTimeout() {
        return 10;
    }

    public int initWriteTimeout() {
        return 10;
    }

    protected Interceptor initInterceptor() {
        return null;
    }

    protected boolean initProxy() {
        return false;
    }

    private final HashMap<String, Object> mApiService = new HashMap<>();

    public final <T> T getApiService(Class<T> apiService) {
        String simpleName = apiService.getSimpleName();
        Object o = mApiService.get(mApiService);
        if (null == o) {
            o = mRetrofit.create(apiService);
            mApiService.put(simpleName, o);
        }

        return (T) o;
    }

    protected abstract String initBaseUrl();
}