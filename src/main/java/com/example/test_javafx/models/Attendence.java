package com.example.test_javafx.models;

public class Attendence {

    String student_id;
    String teacher_name;
    String lecture_id;
    String room_number;
    String time_slot;
    String course_id ;

    public Attendence(String student_id, String teacher_name, String lecture_id, String room_number, String time_slot, String course_id) {
        this.student_id = student_id;
        this.teacher_name = teacher_name;
        this.lecture_id = lecture_id;
        this.room_number = room_number;
        this.time_slot = time_slot;
        this.course_id=course_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(String lecture_id) {
        this.lecture_id = lecture_id;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

}

