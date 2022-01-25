package lib.kalu.okhttp.interceptor;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Connection;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * description: 网络拦截器
 * created by kalu on 2021-07-31
 */
interface OkhttpInterceptor extends Interceptor, OkhttpImpl {

    /**
     * 拦截器入口
     *
     * @param chain
     * @return
     */
    @NonNull
    @Override
    default Response intercept(@NonNull Chain chain) {
        String extra = "";
        long requestTime = System.nanoTime();
        try {
            Connection connection = chain.connection();
            Request request = chain.request();

            extra = request.url().queryParameter(EXTRA);
            if (null != extra && extra.length() > 0) {
                HttpUrl.Builder builder = request.url().newBuilder();
                builder.removeAllQueryParameters(EXTRA);
                request = request.newBuilder().url(builder.build()).build();
            }

            Request newRequest = analysisRequest(requestTime, connection, request);
            Response response = chain.proceed(newRequest);
            Response newResponse = analysisResponse(requestTime, extra, newRequest, response);
            return newResponse;
        } catch (Exception e) {
            HashMap<CharSequence, CharSequence> map = new HashMap<>();
            map.put(CODE, CODE_DEDAULT);
            map.put(MESSAGE, MESSAGE_DEFAULT);
            Request request = chain.request();
            Response response = createResponse(request, map);
            Response newResponse = analysisResponse(requestTime, extra, request, response);
            return newResponse;
        }
    }

    /**
     * 默认响应报文
     *
     * @param request
     * @param map
     * @return
     */
    default Response createResponse(@NonNull Request request, @NonNull Map<CharSequence, CharSequence> map) {

        StringBuilder builder = new StringBuilder();
        builder.append(BRACES_LEFT);

        if (null != map && map.size() > 0) {
            Iterator<Map.Entry<CharSequence, CharSequence>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<CharSequence, CharSequence> next = iterator.next();
                CharSequence key = next.getKey();
                if (null == key || key.length() == 0)
                    continue;
                CharSequence value = next.getValue();
                builder.append(SLASH);
                builder.append(key);
                builder.append(COLON);
                builder.append(value);
                builder.append(SLASH);
                if (!iterator.hasNext())
                    continue;
                builder.append(COMMA);
            }
        }
        builder.append(BRACES_RIGHT);

        String text = builder.toString();
        ResponseBody responseBody = ResponseBody.create(MediaType.parse(APPLICATION_JSON_CHARSET_UTF8), text);

        Response response = new Response.Builder()
                .code(200)
                .protocol(Protocol.HTTP_1_1)
                .message(MESSAGE_DEFAULT)
                .request(request)
                .body(responseBody)
                .build();

        return response;
    }

    /**
     * 处理加工 => 请求报文
     *
     * @param text
     * @param headersBuilder
     * @return
     */
    default String processRequest(@NonNull String text, @NonNull Headers.Builder headersBuilder) {
        try {
            JSONObject object = new JSONObject(text);
            String session = object.optString(SESSION, null);
            object.remove(SESSION);
            headersBuilder.add(SESSION, session);
            return object.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 处理加工 => 请求报文
     *
     * @param formBody
     * @param headersBuilder
     * @return
     */
    default FormBody processRequest(@NonNull FormBody formBody, @NonNull Headers.Builder headersBuilder) {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for (int i = 0; i < formBody.size(); i++) {
                String key = formBody.encodedName(i);
                if (null == key || key.length() == 0)
                    continue;
                String value = formBody.encodedValue(i);
                if (SESSION.equals(key)) {
                    headersBuilder.add(key, value);
                } else {
                    builder.add(key, value);
                }
            }
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 处理加工 => 响应报文
     *
     * @param text
     * @param request
     * @return
     */
    default String processResponse(@NonNull String text, @NonNull String get, @NonNull Request request) {
        try {
            JSONObject object = new JSONObject(text);
            if (null != get && get.length() > 0) {
                object.putOpt(EXTRA, get);
            } else {
                String extra = request.headers().get(EXTRA);
                if (null != extra && extra.length() > 0) {
                    object.putOpt(EXTRA, extra);
                }
            }
            return object.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
