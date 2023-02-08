package lib.kalu.frame.mvp.interceptor;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lib.kalu.frame.mvp.util.MvpUtil;
import okhttp3.Connection;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

/**
 * description: 常量标记
 * created by kalu on 2021-07-31
 */
interface OkhttpImpl {

    String TAG = "module_okhttp";

    /**
     * text/html
     */
    String TEXT_HTML = "text/html";
    String UTF_8 = "UTF-8";

    /**
     * text/plain;
     */
    String TEXT_PLAIN = "text/plain";
    /**
     * text/plain;charset=UTF-8
     */
    String TEXT_PLAIN_UTF_8 = "text/plain;charset=UTF-8";
    /**
     * application/json
     */
    String APPLICATION_JSON = "application/json";
    /**
     * "application/x-www-form-urlencoded"
     */
    String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
    /**
     * application/json;charset=utf-8
     */
    String APPLICATION_JSON_CHARSET_UTF8 = "application/json;charset=utf-8";
    /**
     * "
     */
    String SLASH = "\"";
    /**
     * ":"
     */
    String COLON = "\":\"";
    /**
     * ,
     */
    String COMMA = ",";
    /**
     * {
     */
    String BRACES_LEFT = "{";
    /**
     * }
     */
    String BRACES_RIGHT = "}";
    /**
     * content
     */
    String CONTENT = "content";
    /**
     * session
     */
    String SESSION = "session";
    /**
     * key
     */
    String KEY = "key";
    /**
     * extra
     */
    String EXTRA = "extra";

//    /**
//     * heads
//     */
//    String HEAD = "head";
//    /**
//     * param
//     */
//    String PARAM = "param";

    /**
     * data
     */
    String DATA = "data";
    /**
     * code
     */
    String CODE = "code";
    /**
     * code
     */
    String CODE_DEDAULT = "200";
    /**
     * message
     */
    String MESSAGE = "status";
    /**
     * message
     */
    String MESSAGE_DEFAULT = "custom exception";

//    default Request formatRequest(@NonNull Request request) {
//        return request;
//    }
//
//    default String getParamValue(@NonNull Request request) {
//        try {
//            return request.url().queryParameter(PARAM);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    default HttpUrl.Builder getParamBuilder(@NonNull Request request) {
//        try {
//            String s = request.url().queryParameter(PARAM);
//            if (null == s || s.length() <= 0)
//                throw new Exception("param is empty");
//            JSONObject object = new JSONObject(s);
//            Iterator<String> keys = object.keys();
//            if (null == keys)
//                throw new Exception("param keys is empty");
//            HttpUrl.Builder newBuilder = request.url().newBuilder().removeAllQueryParameters(PARAM);
//            while (keys.hasNext()) {
//                String k = keys.next();
//                if (null == k)
//                    continue;
//                Object v = object.opt(k);
//                if (null == v)
//                    continue;
//                newBuilder.addQueryParameter(k, v.toString());
//            }
//            return newBuilder;
//        } catch (Exception e) {
//            return null;
//        }
//    }

    default String getExtraValue(@NonNull Request request) {
        try {
            return request.url().queryParameter(EXTRA);
        } catch (Exception e) {
            return null;
        }
    }

    default HttpUrl.Builder getExtraBuilder(@NonNull Request request) {
        try {
            return request.url().newBuilder().removeAllQueryParameters(EXTRA);
        } catch (Exception e) {
            return null;
        }
    }
//
//    default Headers getHeads(@NonNull Request request) {
//        try {
//            String s = request.url().queryParameter(HEAD);
//            if (null == s || s.length() <= 0)
//                throw new Exception("head is empty");
//            JSONObject object = new JSONObject(s);
//            Iterator<String> keys = object.keys();
//            if (null == keys)
//                throw new Exception("head keys empty");
//            Headers.Builder newBuilder = request.headers().newBuilder();
//            while (keys.hasNext()) {
//                String k = keys.next();
//                if (null == k)
//                    continue;
//                Object v = object.opt(k);
//                if (null == v)
//                    continue;
//                newBuilder.add(k, v.toString());
//            }
//            return newBuilder.build();
//        } catch (Exception e) {
//            return null;
//        }
//    }

    Request analysisRequest(
            @NonNull long requestTime,
            @NonNull Connection connection,
            @NonNull Request request);

    Response analysisResponse(
            @NonNull long requestTime,
            @NonNull String get,
            @NonNull Request request,
            @NonNull Response response);

    /********************************/

    /********************************/

    default boolean enableLogs() {
        return MvpUtil.isLogger();
    }

    /********************************/

    default void logsConnection(@NonNull long requestTime, @NonNull Connection connection) {

        if (enableLogs() && null != connection) {
            try {
                logs("request[" + requestTime + "] => port = " + connection.protocol());
            } catch (Exception e) {
                logs("request[" + requestTime + "] => port = null");
            }
            try {
                logs("request[" + requestTime + "] => route = " + connection.route());
            } catch (Exception e) {
                logs("request[" + requestTime + "] => route = null");
            }
            try {
                logs("request[" + requestTime + "] => socket = " + connection.socket());
            } catch (Exception e) {
                logs("request[" + requestTime + "] => socket = null");
            }
        }
    }

    default void logsRequest(@NonNull long requestTime, @NonNull Request request) {

        if (enableLogs() && null != request) {
            logs("************************************************************************");
            try {
                logs("request[" + requestTime + "] => url = " + request.url().toString());
            } catch (Exception e) {
                logs("request[" + requestTime + "] => url = null");
            }
            try {
                logs("request[" + requestTime + "] => host = " + request.url().host());
            } catch (Exception e) {
                logs("request[" + requestTime + "] => host = null");
            }
            try {
                logs("request[" + requestTime + "] => method = " + request.method());
            } catch (Exception e) {
                logs("request[" + requestTime + "] => method = null");
            }
            try {
                logs("request[" + requestTime + "] => contentType = " + request.body().contentType());
            } catch (Exception e) {
                logs("request[" + requestTime + "] => contentType = null");
            }
            try {
                logs("request[" + requestTime + "] => contentLength = " + request.body().contentLength());
            } catch (Exception e) {
                logs("request[" + requestTime + "] => contentLength = null");
            }
        }
    }

    default void logsResponse(@NonNull long requestTime, @NonNull Response response) {
        if (enableLogs() && null != response) {
            logs("------------------------------------------------------------------------");
            try {
                long responseTime = System.nanoTime();
                long time = Math.abs(responseTime - requestTime) / 1000000;
                logs("response[" + requestTime + "] => time = " + time + "ms");
            } catch (Exception e) {
                logs("response[" + requestTime + "] => time = null");
            }
            try {
                logs("response[" + requestTime + "] => code = " + response.code());
            } catch (Exception e) {
                logs("response[" + requestTime + "] => code = null");
            }
            try {
                logs("response[" + requestTime + "] => message = " + response.message());
            } catch (Exception e) {
                logs("response[" + requestTime + "] => message = null");
            }
            try {
                logs("response[" + requestTime + "] => contentType = " + response.body().contentType());
            } catch (Exception e) {
                logs("response[" + requestTime + "] => contentType = null");
            }
            try {
                logs("response[" + requestTime + "] => contentLength = " + response.body().contentLength());
            } catch (Exception e) {
                logs("response[" + requestTime + "] => contentLength = null");
            }
        }
    }

    default void logsRequestHeaders(@NonNull long requestTime, @NonNull Headers headers) {

        if (enableLogs() && null != headers) {
            Map<String, List<String>> map = headers.toMultimap();
            String head = String.valueOf(map);
            logs("request[" + requestTime + "] => head = " + head);
        }
    }

    default void logsResponseHeaders(@NonNull long requestTime, @NonNull Headers headers) {

        if (enableLogs() && null != headers) {
            Map<String, List<String>> map = headers.toMultimap();
            String head = String.valueOf(map);
            logs("response[" + requestTime + "] => head = " + head);
            logs("************************************************************************");
        }
    }

    default void logsResponseBody(@NonNull long requestTime, @NonNull String body) {
        if (enableLogs() && null != body) {
            logs("response[" + requestTime + "] => body = " + body);
        }
    }

    default void logsRequestBody(@NonNull long requestTime, @NonNull String body) {
        if (enableLogs() && null != body) {
            logs("request[" + requestTime + "] => body = " + body);
        }
    }

    default void logsRequestBody(@NonNull long requestTime, @NonNull FormBody formBody) {
        if (enableLogs() && null != formBody) {
            try {
                Buffer buffer = new Buffer();
                formBody.writeTo(buffer);
                String readUtf8 = buffer.readUtf8();
                logs("request[" + requestTime + "] => body = " + readUtf8);
            } catch (Exception e) {
                logs("request[" + requestTime + "] => body = " + e.getMessage());
            }
        }
    }

    default void logs(String message) {

        if (enableLogs()) {
            logs(message, null);
        }
    }

    default void logs(String message, Throwable throwable) {

        if (enableLogs()) {
            MvpUtil.logE(TAG, message, throwable);
        }
    }


    /********************************/
}
