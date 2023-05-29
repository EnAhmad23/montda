package com.example.test_javafx.models;

public class StudentReport {

    private String id;
    private String course_id;
    private String lec_id;
    private double attendancePear;

    StudentReport(String id, String course_id, String lec_id, double attendancePear){
        this.id = id;
        this.course_id = course_id;
        this.lec_id = lec_id;
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

    public String getLec_id() {
        return lec_id;
    }

    public void setLec_id(String lec_id) {
        this.lec_id = lec_id;
    }

    public double getAttendancePear() {
        return attendancePear;
    }

    public void setAttendancePear(double attendancePear) {
        this.attendancePear = attendancePear;
    }
}
