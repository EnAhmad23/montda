package com.example.test_javafx.models;

public class Absents {
    private  String stu_id;
    private  String stu_name;

    private  String course_id;
    private  String percent;

    public Absents(String stuId, String stuName, String courseId, String percent) {
        stu_id = stuId;
        stu_name = stuName;
        course_id = courseId;
        this.percent = percent;
    }
    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

   

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }



}
