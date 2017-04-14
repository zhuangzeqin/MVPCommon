package com.eeepay.cn.mvp.demo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * 描述：presenter绑定到activity和View的绑定和解绑操作是每个Activity都会去做的，
 * 抽象一个父类来完成这个统一的操作
 * 作者：zhuangzeqin
 * 时间: 2017/3/20-16:47
 * 邮箱：zzq@eeepay.cn
 */
public abstract class BaseMvpActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {
    /**tag 标签**/
    protected String TAG;
    /**上下文对象**/
    protected Context mContext;
    /**通用的presenter**/
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /**tag 标签**/
        this.TAG = this.getClass().getSimpleName();
        /*** 上下文对象**/
        this.mContext = this;
        /** 初始化通用的presenter **/
        mPresenter = initPresenter();
        /** 添加加view的绑定**/
        if(mPresenter!=null)
            mPresenter.attach((V)this);
    }

    @Override
    protected void onDestroy() {
        /** view的销毁 **/
        if(mPresenter!=null)
        mPresenter.dettach();
        super.onDestroy();
    }
    /** 初始化通用的presenter **/
    protected abstract P initPresenter();
}
