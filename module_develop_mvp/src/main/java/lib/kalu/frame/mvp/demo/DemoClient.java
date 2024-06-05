package lib.kalu.frame.mvp.demo;

import lib.kalu.frame.mvp.http.BaseClient;
import okhttp3.Interceptor;

public class DemoClient extends BaseClient {
//    public final DemoApi getDemoApi() {
//        return getApiService(DemoApi.class);
//    }

    @Override
    protected String initBaseUrl() {
        return null;
    }

    protected static BaseClient getHttpClient() {
        return new DemoClient();
    }
}
