package com.eeepay.cn.mvp.demo.api;

import android.text.TextUtils;
import android.webkit.URLUtil;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述：Retrofit 请求封装；采用的是单例模式
 * 作者：zhuangzeqin
 * 时间: 2017/2/3 16:07
 * 邮箱：zhuangzeqin@szxys.cn
 */
public class RetrofitClient {
    /**
     * tag 标签
     **/
    private final String TAG = RetrofitClient.class.getSimpleName();
    /**
     * 请求的Baseurl
     **/
    private final static String BASEURL = GitServiceApi.BASEURL;
    /**
     * 默认的超时时间；10s
     **/
    private static final int DEFAULT_TIMEOUT = 10;
    private GitServiceApi mGitServiceApi = null;

    private RetrofitClient() {
    }

    /**
     * 匿名内部类容器法
     **/
    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 初始化操作
     */
    public void initBaseURL() {
        initBaseURL(null);
    }

    /**
     * 初始化操作，传入基础的url
     */
    public void initBaseURL(String baseUrl) {
        if (TextUtils.isEmpty(baseUrl) || !URLUtil.isNetworkUrl(baseUrl)) {
            baseUrl = BASEURL;//如果不符合的情况下；采用默认的base url
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .addNetworkInterceptor(interceptor)
                .addInterceptor(interceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(3, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里3个，和每个保持时间为15
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)//
                .addConverterFactory(GsonConverterFactory.create())//gson 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//rxjava 适配器
                .baseUrl(baseUrl)//设置baseurl
                .build();
        mGitServiceApi = retrofit.create(GitServiceApi.class);//创建api
    }

    /**
     * Log 日志拦截器
     */
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            try {
                StringReader reader = new StringReader(message);
                Properties properties = new Properties();
                properties.load(reader);
                properties.list(System.out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });

    /**
     * 向外提供获取api的实例
     *
     * @return
     */
    public GitServiceApi getGitHubApi() {
        return mGitServiceApi;
    }

}
