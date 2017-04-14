package com.eeepay.cn.mvp.demo.base.view;

import com.eeepay.cn.mvp.demo.base.IBaseView;
import com.eeepay.cn.mvp.demo.bean.Contributors;

import java.util.List;

/**
 * 描述：获取列表需要的显示操作
 * 作者：zhuangzeqin
 * 时间: 2017/3/20-17:44
 * 邮箱：zzq@eeepay.cn
 */
public interface IContributoView extends IBaseView {
    void setListItem(List<Contributors> data);
    void showMessage(String message);
}
