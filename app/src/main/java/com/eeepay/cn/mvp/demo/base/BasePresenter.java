package com.eeepay.cn.mvp.demo.base;

/**
 * 描述：通用的presenter来为我们添加view的绑定与销毁。
 * 作者：zhuangzeqin
 * 时间: 2017/3/20-16:41
 * 邮箱：zzq@eeepay.cn
 */
public abstract class BasePresenter<V> {
    /**
     * 通用view
     **/
    protected V mView;

    /**
     * 添加view的绑定方法
     **/
    public void attach(V mView) {
        this.mView = mView;
    }

    /**
     * 加入一个销毁view的方法
     **/
    public void dettach() {
        mView = null;
    }




}
