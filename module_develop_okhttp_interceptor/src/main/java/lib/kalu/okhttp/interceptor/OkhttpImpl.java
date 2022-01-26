package lib.kalu.okhttp.interceptor;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;

import lib.kalu.okhttp.interceptor.util.OkhttpUtil;
import okhttp3.Connection;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

/**
 * description: 常量标记
 * created by kalu on 2021-07-31
 */
interface OkhttpImpl {

    /**
     * 无需加密
     */
    String HttpAes = "xAes:1";
    /**
     * 需要缓存
     */
    String HttpCached = "xCached:1";
    /**
     * 需要登录公共参数
     */
    String HttpLogin = "xLogin:1";
    /**
     * 下载文件
     */
    String HttpDownload = "xDownload:1";
    /**
     * 流文件
     */
    String HttpMupdf = "xStream:1";

    /**
     * text/html
     */
    String TEXT_HTML = "text/html";
    /**
     * text/plain;
     */
    String TEXT_PLAIN = "text/plain";
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
    String TEMP_URL_QUERY_PARAMETER = "tempUrlQueryParameter";
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
        return OkhttpUtil.isPrint();
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
            OkhttpUtil.logE(message, throwable);
        }
    }

    /********************************/
}
