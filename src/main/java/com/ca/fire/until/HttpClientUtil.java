package com.ca.fire.until;


import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static final String url = "http://localhost";

    public static Object getResult(String address, Map<String, Object> map) {
        String result;
        try {

            HttpClient httpClient = new HttpClient();
            PostMethod postMethod = new PostMethod(url);

            String requestJson = JSON.toJSONString(map);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            postMethod.setRequestEntity(new StringRequestEntity(requestJson, "application/json", "UTF-8"));
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                result = postMethod.getResponseBodyAsString();
                logger.error("HttpClientUtil getResult,result:" + result);
            } else if (String.valueOf(statusCode).startsWith("4")) {
                throw new RuntimeException(String.format("请求错误:" + String.valueOf(statusCode)));
            } else if (String.valueOf(statusCode).startsWith("5")) {
                throw new RuntimeException(String.format("目的服务器错误:" + String.valueOf(statusCode) + postMethod.getResponseBodyAsString()));
            } else {
                throw new RuntimeException(String.format("调用异常:" + String.valueOf(statusCode)));
            }
            return result;
        } catch (Exception e) {
            logger.error("HttpClientUtil getResult error", e);
            throw new RuntimeException(e);
        }
    }


}
