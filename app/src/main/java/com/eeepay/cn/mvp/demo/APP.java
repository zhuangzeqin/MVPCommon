package com.eeepay.cn.mvp.demo;

import android.app.Application;

import com.eeepay.cn.mvp.demo.api.RetrofitClient;


/**
 * 描述：全局的Application
 * 作者：zhuangzeqin
 * 时间: 2017/3/14-17:24
 * 邮箱：zzq@eeepay.cn
 */
public class APP extends Application {
    private static APP mInstance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //初始化一下
        RetrofitClient.getInstance().initBaseURL();
    }
    /**
     * 获取APP的实例
     * @return
     */
    public static APP getApplicationInstance() {
        if (mInstance != null && mInstance instanceof APP) {
            return mInstance;
        } else {
            return mInstance;
        }
    }
}
