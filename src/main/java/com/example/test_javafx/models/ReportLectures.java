package com.example.test_javafx.models;

public class ReportLectures {
    private String lec_id;
    private String course_id;
    private String title;
    private double num;
    private double present;

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    ReportLectures(String lecId, String courseId, String title, double num, double present){
        lec_id = lecId;
        course_id = courseId;
        this.title = title;
        this.num = num;

        this.present = present;
    }
    public String getLec_id() {
        return lec_id;
    }

    public void setLec_id(String lec_id) {
        this.lec_id = lec_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPresent() {
        return present;
    }

    public void setPresent(double present) {
        this.present = present;
    }


}
