package lib.kalu.frame.mvp.test;

import java.util.List;

import io.reactivex.Observable;
import lib.kalu.frame.mvp.bean.RequestBean;
import lib.kalu.frame.mvp.interceptor.OkhttpInterceptorStandard;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TestApi {

    @GET("/testApi1")
    Observable<Object> testApi1(@Query(OkhttpInterceptorStandard.EXTRA) String value);

    @FormUrlEncoded
    @POST("/testApi1/?" + OkhttpInterceptorStandard.EXTRA + "={xxx}")
    Observable<Object> testApi2(@Path("xxx") String movieId, @Body RequestBody request);
}
