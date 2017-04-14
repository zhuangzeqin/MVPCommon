package com.eeepay.cn.mvp.demo.bean;

/**
 * 描述：学生类实体bean
 * 作者：zhuangzeqin
 * 时间: 2017/4/11-15:30
 * 邮箱：zzq@eeepay.cn
 */
public class Student {
    String name;//姓名

    String className;//班级姓名

    Course[] coursearray; //课程表 对象数组


    public Student(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public Course[] getCoursearray() {
        return coursearray;
    }

    public void setCoursearray(Course[] coursearray) {
        this.coursearray = coursearray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
