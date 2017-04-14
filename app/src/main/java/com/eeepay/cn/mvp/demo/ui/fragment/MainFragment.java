package com.eeepay.cn.mvp.demo.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eeepay.cn.mvp.demo.R;
import com.eeepay.cn.mvp.demo.base.BaseMvpFragment;
import com.eeepay.cn.mvp.demo.base.view.IContributoView;
import com.eeepay.cn.mvp.demo.bean.Contributors;
import com.eeepay.cn.mvp.demo.presenter.ContributoPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;

/**
 * 描述：一个获取数据的列表Frament
 * 作者：zhuangzeqin
 * 时间: 2017/4/10-17:28
 * 邮箱：zzq@eeepay.cn
 */
public class MainFragment extends BaseMvpFragment<IContributoView, ContributoPresenter> implements IContributoView {
    @BindView(R.id.mvp_loading)
    ProgressBar pb;
    @BindView(R.id.mvp_listview)
    ListView mvpListView;
    Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        Bundle arguments = getArguments();//获取参数
        String owner = arguments.getString("owner", null);
        String repo = arguments.getString("repo", null);
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        //开始获取数据
        unbinder = ButterKnife.bind(this, rootView);
        mPresenter.getContributoInfo(owner, repo);
        return rootView;
    }

    /**
     * 参数设置
     *
     * @param bundle
     * @return
     */
    public static MainFragment getInstance(Bundle bundle) {
        MainFragment mainFragment = new MainFragment();
        if (bundle != null)//设置参数
            mainFragment.setArguments(bundle);
        return mainFragment;
    }

    /**
     * 无参数
     *
     * @return
     */
    public static MainFragment getInstance() {
        return getInstance(null);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @OnItemClick(R.id.mvp_listview)
    public void onItemClick(int position) {
        String str = (String) mvpListView.getAdapter().getItem(position);
        mPresenter.onItemClick(str);
    }

    @Override
    protected ContributoPresenter initPresenter() {
        return new ContributoPresenter();
    }


    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setListItem(List<Contributors> data) {
        List<String> stringList = new ArrayList<>();
        for (Contributors contributors : data) {
            stringList.add(contributors.getLogin());
        }
        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, stringList);
        mvpListView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.dettach();
    }
}
