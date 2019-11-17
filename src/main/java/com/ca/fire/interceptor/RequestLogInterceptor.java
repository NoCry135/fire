package com.ca.fire.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public final class RequestLogInterceptor implements Interceptor {
    private Logger logger = LoggerFactory.getLogger(RequestLogInterceptor.class);

    private Map<String, String> mappingHostNameToSysCode = new HashMap<>();

    private final static String SYS_CODE = "fire";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String host = request.url().host();
        String path = request.url().encodedPath();
        String requestBody = "";
        if (!"GET".equals(request.method())) {
            requestBody = getRequestBody(request);
        }
        String targetSysName = getTargetSysCode(host);

        long t1 = System.currentTimeMillis();
        logger.info(chain.request().tag() + " {}-> {} 开始调用方法: {} param：{}", new String[]{SYS_CODE, targetSysName, path, requestBody});
        Response response = null;
        try {
            response = chain.proceed(chain.request());
            ResponseBody body = response.body();
            String resource = body.source().readUtf8();
            logger.info("Response: {} -> {} return :{}", new String[]{SYS_CODE, targetSysName, resource});
            long t2 = System.currentTimeMillis();
            long t = t2 - t1;
            logger.info("{}-> {} 调用方法: {} 耗时：{} ms", new String[]{SYS_CODE, targetSysName, path, String.valueOf(t)});
            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), resource)).build();
        } catch (Exception e) {
            logger.error("{}-> {} 调用方法: {} 异常：{} ", new String[]{SYS_CODE, targetSysName, path, e.getMessage()});
            logger.error("调用异常", e);
            return null;

        }
    }

    private String getRequestBody(Request request) throws IOException {
        final Request copy = request.newBuilder().build();
        final Buffer buffer = new Buffer();
        copy.body().writeTo(buffer);
        return buffer.readUtf8();
    }


    private String getTargetSysCode(String host) {
        host = Optional.of(host).orElse("error host");
        String[] hostArr = host.split("/");
        if (hostArr.length > 0) {
            return hostArr[hostArr.length - 1];
        }
        return mappingHostNameToSysCode.get(host);
    }
}
