package com.eeepay.cn.mvp.demo.model;

import com.eeepay.cn.mvp.demo.bean.Contributors;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述：model 负责提供数据源
 * 作者：zhuangzeqin
 * 时间: 2017/3/20-17:22
 * 邮箱：zzq@eeepay.cn
 */

public class GetContributorsinfoModel extends BaseModel<List<Contributors>> {

    public void getContributorsinfo(String owner, String repo) {
        mGitServiceApi.contributorsBySimpleGetCall(owner, repo)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
        // 延迟 .delay(0, TimeUnit.SECONDS)
        //异步请求 同步请求则用 execute()方法
//        listCall.enqueue(this);

//        closeSubscribes();
    }
}
