package com.example.test_javafx.models;

public class Course {

    private String course_id;
    private String name;
    private String teacher_name;
    private String room_number;
    private String montdaMajor;
    private String time;
    private String hours;


    public Course(String course_id, String name, String room_number, String montdaMajor, String time, String hours) {
        this.course_id = course_id;
        this.name = name;
        this.room_number = room_number;
        this.montdaMajor = montdaMajor;
        this.time = time;
        this.hours = hours;
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

    public String getMontdaMajor() {
        return montdaMajor;
    }

    public void setMontdaMajor(String montdaMajor) {
        this.montdaMajor = montdaMajor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
