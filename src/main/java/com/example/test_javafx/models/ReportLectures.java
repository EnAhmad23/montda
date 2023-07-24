package com.example.test_javafx.models;

public class ReportLectures {
    private String name;
    private String course_id;
    private String majer;
    private double num;
    private double present;

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    ReportLectures(String name, String courseId, String majer, double num, double present){
        this.name = name;
        course_id = courseId;
        this.majer = majer;
        this.num = num;

        this.present = present;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getMajer() {
        return majer;
    }

    public void setMajer(String majer) {
        this.majer = majer;
    }

    public double getPresent() {
        return present;
    }

    public void setPresent(double present) {
        this.present = present;
    }


}
