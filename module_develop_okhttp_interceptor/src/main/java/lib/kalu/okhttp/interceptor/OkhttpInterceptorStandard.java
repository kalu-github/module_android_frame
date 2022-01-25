package lib.kalu.okhttp.interceptor;

import androidx.annotation.NonNull;

import java.nio.charset.StandardCharsets;

import okhttp3.Connection;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
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
        String contentType;
        Headers.Builder headersBuilder = request.headers().newBuilder();
        try {
            contentType = requestBody.contentType().toString();
        } catch (Exception e) {
            contentType = null;
        }

        // json-body
        if (null != contentType && contentType.length() > 0 && contentType.startsWith(APPLICATION_JSON)) {
            try {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                text = buffer.readUtf8();
                // log
                logsRequestBody(requestTime, text);
                text = processRequest(text, headersBuilder);
                // log
                logsRequestBody(requestTime, text);
            } catch (Exception e) {
                text = null;
                // log
                logsRequestBody(requestTime, e.getMessage());
            }
        }
        // form-body
        else if (null != contentType && contentType.length() > 0 && contentType.startsWith(APPLICATION_X_WWW_FORM_URLENCODED)) {
            FormBody formBody = processRequest((FormBody) requestBody, headersBuilder);
            // log
            logsRequestBody(requestTime, (FormBody) requestBody);
            text = null;
            requestBody = formBody;
        }
        // type-other
        else {
            text = null;
            // log
            logsRequestBody(requestTime, "is not application/json;charset=utf-8");
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
    public Response analysisResponse(@NonNull long requestTime, @NonNull Request request, @NonNull Response response) {

        // log
        logsResponse(requestTime, response);

        ResponseBody body = response.body();
        String contentType = body.contentType().toString();
        Headers.Builder builder = response.headers().newBuilder();
        String text = null;

        // json-body
        if (null != contentType && contentType.length() > 0 && contentType.startsWith(APPLICATION_JSON)) {
            try {
                byte[] bytes = body.bytes();
                text = new String(bytes, StandardCharsets.UTF_8);
            } catch (Exception e) {
            }
            // log
            logsResponseBody(requestTime, text);

            text = processResponse(text, request);
            // log
            logsResponseBody(requestTime, text);
        }
        // type-other
        else {
            text = null;
            logsResponseBody(requestTime, "is not application/json;charset=utf-8");
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

    @Override
    public boolean enableLogs() {
        return true;
    }
}
