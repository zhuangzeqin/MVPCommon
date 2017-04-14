package com.eeepay.cn.mvp.demo.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描述：基于Fragmet的抽象的MVP
 * 作者：zhuangzeqin
 * 时间: 2017/3/21-9:31
 * 邮箱：zzq@eeepay.cn
 */
public abstract class BaseMvpFragment<V, P extends BasePresenter<V>> extends Fragment {
    /**
     * tag 标签
     **/
    protected String TAG;
    /**
     * 上下文对象
     **/
    protected Activity mContext;
    /**
     * 通用的presenter
     **/
    protected P mPresenter;

    /** Fragment当前状态是否可见 */
    protected boolean misVisible;

    @Override
    public void onAttach(Context context) {
        this.TAG = this.getClass().getSimpleName();
        this.mContext = ((Activity) context);
        /** 初始化通用的presenter **/
        mPresenter = initPresenter();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayoutId(), null);
        /** 添加加view的绑定**/
        mPresenter.attach((V) this);
        return view;
    }

    /**
     * 判断当前Fragment，再去请求数据或者进行其他操作
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            misVisible = true;
        } else {
            misVisible = false;
        }
    }

    @Override
    public void onDetach() {
        mPresenter.dettach();//销毁
        super.onDetach();
    }
    /**
     * 初始化布局layout
     **/
    protected abstract int initLayoutId();

    /**
     * 初始化通用的presenter
     **/
    protected abstract P initPresenter();
}
