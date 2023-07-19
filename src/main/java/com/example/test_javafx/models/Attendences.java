package com.example.test_javafx.models;

import java.sql.Date;

public class Attendences {

    private String student_id;

    private String course_name;
    private String student_name;
    private Date date;
    private String course_id ;

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public Attendences(String student_id, String student_name, String course_id , String course_name, Date date) {
        this.student_id = student_id;

        this.course_name = course_name;
        this.student_name = student_name;
        this.date = date;
        this.course_id=course_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }


}

