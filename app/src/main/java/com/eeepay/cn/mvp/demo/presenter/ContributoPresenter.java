package com.eeepay.cn.mvp.demo.presenter;

import com.eeepay.cn.mvp.demo.base.BasePresenter;
import com.eeepay.cn.mvp.demo.base.view.IContributoView;
import com.eeepay.cn.mvp.demo.bean.Contributors;
import com.eeepay.cn.mvp.demo.model.BaseModel;
import com.eeepay.cn.mvp.demo.model.GetContributorsinfoModel;

import java.util.List;

/**
 * 描述：p层负责调度m层；反馈给view 层
 * 作者：zhuangzeqin
 * 时间: 2017/3/20-17:46
 * 邮箱：zzq@eeepay.cn
 */
public class ContributoPresenter extends BasePresenter<IContributoView> {

    private GetContributorsinfoModel model ;//model

    public ContributoPresenter() {
        //初始化操作..........
        model = new GetContributorsinfoModel();
    }

    public void getContributoInfo(String owner, String repo) {
        if (mView != null)
            mView.showLoading();//显示进度框
        model.setResultCallBack(resultCallBack);
        model.getContributorsinfo(owner, repo);
    }

    /**
     * @param position
     */
    public void onItemClick(String position) {
        if (mView != null)
            mView.showMessage("点击了item:" + position);
    }

    /**
     * 接口的监听回调实现
     */
    private final BaseModel.ResultCallBack resultCallBack = new BaseModel.ResultCallBack<List<Contributors>>() {
        @Override
        public void onSucess(List<Contributors> response) {
            if (mView != null) {
                mView.hideLoading();//隐藏进度框
                mView.setListItem(response);
            }
        }

        @Override
        public void onFailure(String message) {
            if (mView != null) {
                mView.hideLoading();//隐藏进度框
                mView.showError(message);//提示错误信息
            }
        }
    };

    @Override
    public void dettach() {
        model.closeSubscribes();//取消订阅
        super.dettach();
    }
}
