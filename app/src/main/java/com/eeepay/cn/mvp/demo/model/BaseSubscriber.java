package com.eeepay.cn.mvp.demo.model;

import android.util.Log;

import com.eeepay.cn.mvp.demo.APP;
import com.eeepay.cn.mvp.demo.exception.ExceptionHandle;
import com.eeepay.cn.mvp.demo.util.NetworkUtil;

import rx.Subscriber;

/**
 * 描述：抽象的订阅者Subscriber
 * 作者：zhuangzeqin
 * 时间: 2017/4/11-17:48
 * 邮箱：zzq@eeepay.cn
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {
    private static final String TAG = BaseSubscriber.class.getSimpleName();//TAG 标签

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"当前线程:"+Thread.currentThread().getName());
        //上层调用时只关心成功和失败即可无需关心网络情况
        if (!NetworkUtil.isNetworkAvailable(APP.getApplicationInstance())) {
            onFailure("当前网络不可用，请检查网络情况");
            onCompleted();
            return;
        }
    }

    @Override
    public void onCompleted() {
        Log.d(TAG,"当前线程:"+Thread.currentThread().getName());
        Log.i(TAG, "执行完毕");
    }

    /**
     * 如果想对Error错误统一处理，也可以在BaseSubscriber处理onError(), 然后在callback上层，具体看自己项目情况。
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        Log.d(TAG,"当前线程:"+Thread.currentThread().getName());
        ExceptionHandle.ResponeThrowable responeThrowable = ExceptionHandle.handleException(e);
        Log.i(TAG, "出现错误" + responeThrowable.getMessage());
//        onFailure(responeThrowable.message);
    }

    @Override
    public void onNext(T t) {
        Log.d(TAG,"当前线程:"+Thread.currentThread().getName());
        if (t != null) {
            Log.i(TAG, "成功回调:" + t.toString());
            onSucess(t);
        } else {
            Log.i(TAG, "暂时无法获取数据；请稍后重试");
            onFailure("暂时无法获取数据；请稍后重试");
        }
    }

    /**
     * 取消订阅
     * 一般我们在视图消亡后，无需RxJava再执行，可以直接取消订阅
     */
    public void closeSubscribes() {
        //取消订阅
        if (!isUnsubscribed()) {
            Log.d(TAG,"取消订阅");
            unsubscribe();
        }
//        observable.unsubscribeOn(Schedulers.io());
    }

    /**
     * 成功将结果回调出去
     **/
    public abstract void onSucess(T response);

    /**
     * 失败时将结错误信息回调出去
     **/
    public abstract void onFailure(String message);


}
