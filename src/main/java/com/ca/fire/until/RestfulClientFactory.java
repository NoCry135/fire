package com.ca.fire.until;


import com.ca.fire.interceptor.RequestLogInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RestfulClientFactory {

    private static final int readTimeout = 15;
    private static final int writeTimeout = 15;
    private static final int connectTimeout = 15;


    public <T> T createClient(Class<T> t, String host) {
        return createRetrofit(host).create(t);
    }

    private Retrofit createRetrofit(String host) {

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .addInterceptor(new RequestLogInterceptor())
//                    .addInterceptor(new LoggingInterceptor())
//                    .cookieJar(new CookieManager())
//                    .authenticator(new AuthenticatorManager())
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(host)
                .client(mOkHttpClient)
                .build();

        return mRetrofit;
    }
}
