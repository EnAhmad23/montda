package com.example.test_javafx.models;

public class Course {

    String course_id;
    String book_name;
    String teacher_name;
    String room_number;
    String subject;

    public Course(String course_id, String book_name,  String teacher_name, String room_number,  String subject) {
        this.course_id = course_id;
        this.book_name = book_name;
        this.teacher_name = teacher_name;
        this.room_number = room_number;
        this.subject = subject;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
