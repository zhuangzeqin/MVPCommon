package com.eeepay.cn.mvp.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.eeepay.cn.mvp.demo.base.BaseMvpActivity;
import com.eeepay.cn.mvp.demo.base.view.IContributoView;
import com.eeepay.cn.mvp.demo.bean.Contributors;
import com.eeepay.cn.mvp.demo.bean.Course;
import com.eeepay.cn.mvp.demo.bean.Student;
import com.eeepay.cn.mvp.demo.presenter.ContributoPresenter;
import com.eeepay.cn.mvp.demo.ui.fragment.MainFragment;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 描述：mvp 通过的架构
 * 作者：zhuangzeqin
 * 时间: 2017/3/20-16:33
 * 邮箱：zzq@eeepay.cn
 */
public class MainActivity extends BaseMvpActivity<IContributoView, ContributoPresenter> implements IContributoView, AdapterView.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
//    @Bind(R.id.mvp_loading)
//    ProgressBar mvpLoading;
//    @Bind(R.id.mvp_listview)
//    ListView mvpListview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        //开始获取数据
//        mPresenter.getContributoInfo("square", "retrofit");23

        setContentView(R.layout.mainlayout);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("owner", "square");
        bundle.putString("repo", "retrofit");//参数设置
        fragmentTransaction.replace(R.id.rl_layout, MainFragment.getInstance(bundle));
        fragmentTransaction.commitAllowingStateLoss();

//        norRxJava();
    }

    private void norRxJava() {
//        //第一步创建被观察者
//        Observable observable =  Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("on");
//                subscriber.onNext("off");
//                subscriber.onNext("on");
//                subscriber.onCompleted();
//            }
//        });

        //第二步；创建观察者
//        Observer observer = new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                //被观察者的onCompleted();事件会走到这里
//                Log.d(TAG, "结束观察");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                //出现错误会调用这个方法
//            }
//
//            @Override
//            public void onNext(String s) {
//                //被观察者传过的onNext 事件
//                Log.d(TAG, s);
//            }
//        };
        //订阅
//        observable.subscribe(observer);

        //1 偷懒的写法
//        Observable.just("on","off","off","of","on",null).
//                filter(new Func1<String, Boolean>() {//对事件进行过滤的操作；如果等于null 过滤掉
//                    @Override
//                    public Boolean call(String s) {
//                        return s!=null;
//                    }
//                }).subscribe(observer);
        //2偷懒的写法
//        String[] strs = {"on","off","off","of","on"};
//        Observable.from(strs).filter(new Func1<String, Boolean>() {
//            @Override
//            public Boolean call(String s) {
//                return s!=null;
//            }
//        }).subscribe(observer);

        // ------------------------操作符；切换线程的基本用法-----------------------
//        Observable observable = (Observable) Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                Log.d(TAG,"当前线程:"+Thread.currentThread().getName());
//                subscriber.onNext("on");
//                subscriber.onNext("off");
//                subscriber.onNext("on");
//                subscriber.onNext(null);
//                subscriber.onNext("abc");
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.newThread())
//                .filter(new Func1<String, Boolean>() {//先过滤掉一层null
//            @Override
//            public Boolean call(String s) {
//                return s != null;
//            }
//        }).observeOn(Schedulers.io())
//                .map(new Func1<String, Integer>() {//类型转换<原始输入类型，要转换的类型>
//            @Override
//            public Integer call(String str) {
//               Log.d(TAG,"当前线程:"+Thread.currentThread().getName());
//                if (str.equals("on"))
//                    return 1;
//                else if (str.equals("off"))
//                    return 2;
//                else if (str.equals("abc"))
//                    return 3;
//                else if (str==null)
//                    return 0;
//                else
//                    return -1;
//            }
//        }).observeOn(AndroidSchedulers.mainThread());//指定观察者为主线程
//
//        observable.subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//                Log.d(TAG,"当前线程:"+Thread.currentThread().getName());
//                Log.d(TAG, "结束观察");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                //出现错误会调用这个方法
//                Log.d(TAG, e.getLocalizedMessage());
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "NO:"+integer);
//            }
//        });

        //初始化数据
        Course [] CourseArray = {new Course("语文"),new Course("数学"),new Course("物理"),new Course("化学"),new Course("生物")};
        Student [] students = new Student[5];
        for (int i=0;i<5;i++)
        {

            Student student = new Student("zzq" + i, "className" + i);
            student.setCoursearray(CourseArray);
            students[i] =student;
        }

        Observer observer = new Subscriber<Course>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {
                Log.d(TAG,"当前线程:"+Thread.currentThread().getName());
                Log.d(TAG, "结束观察");
            }

            @Override
            public void onError(Throwable e) {
                //出现错误会调用这个方法
//                Log.d(TAG, e.getLocalizedMessage());
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onNext(Course course) {
                Log.d(TAG, "NO:"+course.getCourseName());
            }

        };
        Observable.from(students).subscribeOn(Schedulers.newThread()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                TextView textView = new TextView(mContext);
                textView.setText("正在加载中......");
                Log.d(TAG,"正在加载中......");
                setContentView(textView);
            }
        }).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                Log.d(TAG,"当前线程:"+Thread.currentThread().getName());
                //将每个Observable产生的事件里的信息再包装成新的Observable传递出来，
                return Observable.from(student.getCoursearray());
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);


    }

    @Override
    protected ContributoPresenter initPresenter() {
//        return new ContributoPresenter();
        return null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //mPresenter.onItemClick((String) parent.getAdapter().getItem(position));
    }

    @Override
    public void showLoading() {
//        mvpLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
//        mvpLoading.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void setListItem(List<Contributors> data) {
//        List<String> stringList = new ArrayList<>();
//        for (Contributors contributors : data) {
//            stringList.add(contributors.getLogin());
//        }
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringList);
//        mvpListview.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
//        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
