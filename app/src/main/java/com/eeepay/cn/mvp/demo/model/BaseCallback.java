package com.eeepay.cn.mvp.demo.model;

import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * 描述：抽象的callBack
 * 作者：zhuangzeqin
 * 时间: 2017/4/10-17:27
 * 邮箱：zzq@eeepay.cn
 */
public abstract class BaseCallback<T> implements Callback<T> {
    private static final String TAG = BaseCallback.class.getSimpleName();

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        int statusCode = response.raw().code();//状态码
        Log.d(TAG, "状态码:" + statusCode);
        Log.d(TAG, "body:" + response.body().toString());
        if (HTTP_OK == statusCode)//HTTP Status-Code 200: OK.
            onSucess(response);
        else //失败响应
            onFailure(call, new RuntimeException("response error,detail = " + response.raw().toString()));
    }

    /**
     * 网络问题会走该回调
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof SocketTimeoutException) {
            //
        } else if (t instanceof ConnectException) {
            //
        } else if (t instanceof RuntimeException) {
            //
        }
        onFailure(t.getMessage());
    }

    /**
     * 成功将结果回调出去
     **/
    public abstract void onSucess(Response<T> response);

    /**
     * 失败时将结错误信息回调出去
     **/
    public abstract void onFailure(String message);
}
