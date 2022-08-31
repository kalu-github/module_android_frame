package lib.kalu.frame.mvp.demo;

import lib.kalu.frame.mvp.http.BaseClient;

public class DemoClient extends BaseClient {

    private DemoClient() {
        super();
    }

    public final static DemoClient getDemoClient() {
        return DemoHolder.single;
    }

    public final DemoApi getDemoApi() {
        return getApiService(DemoApi.class);
    }

    private final static class DemoHolder {
        private final static DemoClient single = new DemoClient();
    }

    @Override
    protected String initApi() {
        return "";
    }
}
