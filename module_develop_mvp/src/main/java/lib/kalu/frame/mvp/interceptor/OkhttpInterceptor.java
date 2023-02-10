package lib.kalu.frame.mvp.interceptor;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
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
        long requestTime = System.nanoTime();
        try {
            Connection connection = chain.connection();
            Request request = chain.request();

            // extra
            String v1 = getExtraValue(request);
            if (null != v1 && v1.length() > 0) {
                HttpUrl.Builder builder = getExtraBuilder(request);
                request = request.newBuilder().url(builder.build()).build();
            }

//            // params
//            String v2 = getParamValue(request);
//            if (null != v2 && v2.length() > 0) {
//                HttpUrl.Builder builder = getParamBuilder(request);
//                request = request.newBuilder().url(builder.build()).build();
//            }
//
//            // heads
//            Headers headers = getHeads(request);
//            if (null != headers) {
//                HttpUrl.Builder builder = request.url().newBuilder();
//                builder.removeAllQueryParameters(HEAD);
//                request = request.newBuilder().url(builder.build()).headers(headers).build();
//            }

            // format
//            request = formatRequest(request);

            Request newRequest = analysisRequest(requestTime, connection, request);
            Response response = chain.proceed(newRequest);
            if (response.code() != 200)
                throw new IllegalArgumentException("response code is " + response.code());
            Response newResponse = analysisResponse(requestTime, v1, newRequest, response);
            return newResponse;
        } catch (Exception e) {
            logs(e.getMessage(), e);
            HashMap<CharSequence, CharSequence> map = new HashMap<>();
            map.put(CODE, CODE_DEDAULT);
            map.put(MESSAGE, MESSAGE_DEFAULT);
            Request request = chain.request();
            Response response = createResponse(request, map);
            Response newResponse = analysisResponse(requestTime, null, request, response);
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
     * @param s
     * @param builder
     * @return
     */
    default String processRequest(@NonNull String s, @NonNull Headers.Builder builder) {
        try {
            boolean contains = s.contains(SESSION);
            if (contains)
                throw new Exception("not contains " + SESSION);
            JSONObject object = new JSONObject(s);
            String session = object.optString(SESSION, null);
            object.remove(SESSION);
            if (null != session && session.length() > 0) {
                builder.add(SESSION, session);
            }
            return s;
        } catch (Exception e) {
            logs(e.getMessage());
            return s;
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
            logs(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 处理加工 => 响应报文
     *
     * @param s
     * @param extra
     * @return
     */
    default String processResponse(@NonNull String s, @NonNull String extra) {
        try {
            JSONObject object = new JSONObject(s);
            if (null != extra && extra.length() > 0) {
                object.putOpt(EXTRA, extra);
            }
            return object.toString();
        } catch (Exception e) {
            logs(e.getMessage());
            return s;
        }
    }
}
