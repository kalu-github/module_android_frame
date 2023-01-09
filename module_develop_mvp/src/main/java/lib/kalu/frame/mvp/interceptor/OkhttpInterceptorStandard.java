package lib.kalu.frame.mvp.interceptor;

import androidx.annotation.NonNull;

import okhttp3.Connection;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * description: 默认不加密
 * created by kalu on 2021-07-31
 */
public class OkhttpInterceptorStandard implements OkhttpInterceptor {

    @Override
    public Request analysisRequest(@NonNull long requestTime, @NonNull Connection connection, @NonNull Request request) {

        // log
        logsRequest(requestTime, request);
        // log
        logsConnection(requestTime, connection);

        // 报文
        String text;
        RequestBody requestBody = request.body();
        Headers.Builder headersBuilder = request.headers().newBuilder();

        try {
            if (requestBody instanceof FormBody) {
                FormBody formBody = processRequest((FormBody) requestBody, headersBuilder);
                // log
                logsRequestBody(requestTime, (FormBody) requestBody);
                text = null;
                requestBody = formBody;
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                text = buffer.readUtf8();
                // log
                logsRequestBody(requestTime, text);
                text = processRequest(text, headersBuilder);
                // log
                logsRequestBody(requestTime, text);
            }
        } catch (Exception e) {
            text = null;
            // log
            String message = e.getMessage();
            if (null != message && message.length() > 0) {
                logsRequestBody(requestTime, message);
            } else {
                logsRequestBody(requestTime, "is not application/json;charset=utf-8");
            }
        }

        Headers newHeaders = headersBuilder.build();
        // log
        logsRequestHeaders(requestTime, newHeaders);

        return request.newBuilder()
                .method(request.method(), null == text ? requestBody : RequestBody.create(MediaType.parse(APPLICATION_JSON_CHARSET_UTF8), text))
                .headers(newHeaders)
                .build();
    }

    @Override
    public Response analysisResponse(@NonNull long requestTime, @NonNull String extra, @NonNull Request request, @NonNull Response response) {

        // log
        logsResponse(requestTime, response);

        ResponseBody body = response.body();
        Headers.Builder builder = response.headers().newBuilder();
        String text;

        try {
            byte[] bytes = body.bytes();
            text = new String(bytes, UTF_8);
            // log
            logsResponseBody(requestTime, text);
            text = processResponse(text, extra);
            // log
            logsResponseBody(requestTime, text);
        } catch (Exception e) {
            text = null;
            String message = e.getMessage();
            if (null != message && message.length() > 0) {
                logsResponseBody(requestTime, message);
            } else {
                logsResponseBody(requestTime, "is not application/json;charset=utf-8");
            }
        }

        Headers newHeaders = builder.build();
        // log
        logsResponseHeaders(requestTime, newHeaders);

        return response.newBuilder()
                .code(200)
                .request(request)
                .headers(newHeaders)
                .body(null == text ? body : ResponseBody.create(MediaType.parse(APPLICATION_JSON_CHARSET_UTF8), text))
                .build();
    }
}
