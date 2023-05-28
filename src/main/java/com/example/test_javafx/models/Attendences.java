package com.example.test_javafx.models;

public class Attendences {

    String student_id;

    String lecture_id;
    String student_name;
    String title;
    String course_id ;

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public Attendences(String student_id, String student_name , String lecture_id, String course_id, String title) {
        this.student_id = student_id;

        this.lecture_id = lecture_id;
        this.student_name = student_name;
        this.title = title;
        this.course_id=course_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    

    public String getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(String lecture_id) {
        this.lecture_id = lecture_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

