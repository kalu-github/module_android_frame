
package lib.kalu.frame.mvp.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lib.kalu.frame.mvp.interceptor.OkhttpInterceptorStandard;
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

    protected BaseClient() {

        OkHttpClient.Builder mOkHttpBuilder = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .callTimeout(3000, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(new OkhttpInterceptorStandard());

        // 禁止代理抓包
        mOkHttpBuilder.proxySelector(new ProxySelector() {
            @Override
            public List<Proxy> select(URI uri) {
                return Collections.singletonList(Proxy.NO_PROXY);
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
            }
        });

        OkHttpClient mOkHttpClient = mOkHttpBuilder.build();
        // 最大并发请求数为
        mOkHttpClient.dispatcher().setMaxRequests(10);
        // 每个主机最大请求数
        mOkHttpClient.dispatcher().setMaxRequestsPerHost(10);

        Gson gson = new GsonBuilder()//建造者模式设置不同的配置
                .serializeNulls()//序列化为null对象
                .setDateFormat("yyyy-MM-dd HH:mm:ss") //设置日期的格式
                .disableHtmlEscaping()//防止对网址乱码 忽略对特殊字符的转换
                .setLenient() // 使用JsonReader.setLenient(true)在第1行、第1列、路径$接受格式错误的JSON
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(initApi())
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    protected final <T> T getApiService(Class<T> apiService) {
        return mRetrofit.create(apiService);
    }

    protected abstract String initApi();
}