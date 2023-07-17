package com.example.test_javafx.models;

public class Teacher {
    private String tea_id;
    private String tea_name;
    private String course_id;
    private String course_name;

    public Teacher(String teaId, String teaName, String courseId, String courseName) {
        tea_id = teaId;
        tea_name = teaName;
        course_id = courseId;
        course_name = courseName;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTea_name() {
        return tea_name;
    }

    public void setTea_name(String tea_name) {
        this.tea_name = tea_name;
    }

    public String getTea_id() {
        return tea_id;
    }

    public void setTea_id(String tea_id) {
        this.tea_id = tea_id;
    }
}
