package com.eeepay.cn.mvp.demo.bean;

/**
 * 描述：课程实体bean
 * 作者：zhuangzeqin
 * 时间: 2017/4/11-15:29
 * 邮箱：zzq@eeepay.cn
 */
public class Course {
    String courseName;//课程名

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
