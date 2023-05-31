package com.example.test_javafx.models;

public class StudentReport {

    private String id;
    private String course_id;
    private String name;
    private double attendancePear;

    StudentReport(String id, String name ,String course_id, double attendancePear){
        this.id = id;
        this.course_id = course_id;
        this.name = name;
        this.attendancePear = attendancePear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAttendancePear() {
        return attendancePear;
    }

    public void setAttendancePear(double attendancePear) {
        this.attendancePear = attendancePear;
    }
}
