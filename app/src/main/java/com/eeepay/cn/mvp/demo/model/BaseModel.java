package com.eeepay.cn.mvp.demo.model;

import android.support.annotation.NonNull;

import com.eeepay.cn.mvp.demo.api.GitServiceApi;
import com.eeepay.cn.mvp.demo.api.RetrofitClient;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述：抽象的model层
 * 作者：zhuangzeqin
 * 时间: 2017/4/12-10:59
 * 邮箱：zzq@eeepay.cn
 */
public abstract class BaseModel<T> extends BaseSubscriber<T> {
    protected GitServiceApi mGitServiceApi;//提供获取API的接口实现类
    protected ResultCallBack mResultCallBack;//接口的回调

    /**
     * 外部调用者必先实现的改监听接口
     **/
    public BaseModel() {
        mGitServiceApi = RetrofitClient.getInstance().getGitHubApi();
    }

    /**
     * 外部调用者必实现的改监听接口
     **/
    public void setResultCallBack(@NonNull final ResultCallBack<T> resultCallBack) {
        this.mResultCallBack = resultCallBack;//设置监听回调接口
    }

    @Override
    public void onSucess(T response) {
        if (mResultCallBack != null) {
            mResultCallBack.onSucess(response);
        }
    }

    @Override
    public void onFailure(String message) {
        if (mResultCallBack != null) {
            mResultCallBack.onFailure(message);
        }
    }

    /**
     * Transformer实际上就是Func1<Observable , Observable
     * @param <T>
     * @return
     */
    protected <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io()).
                        unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 将结果返回给外部调用者使用
     **/
    public interface ResultCallBack<T> {
        void onSucess(T response);

        void onFailure(String message);
    }
}
