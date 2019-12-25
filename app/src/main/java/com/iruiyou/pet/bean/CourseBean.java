package com.iruiyou.pet.bean;

/**
 * 作者：sgf
 * 课程
 */
public class CourseBean {


    private int CourseText;
    private int CourseIcon;

    public CourseBean(int courseText, int courseIcon) {
        CourseText = courseText;
        CourseIcon = courseIcon;
    }

    public int getCourseText() {
        return CourseText;
    }

    public void setCourseText(int courseText) {
        CourseText = courseText;
    }

    public int getCourseIcon() {
        return CourseIcon;
    }

    public void setCourseIcon(int courseIcon) {
        CourseIcon = courseIcon;
    }
}
