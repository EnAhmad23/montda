package com.example.test_javafx.models;

public class LectureTime {
    String lecture_id;
    String course_id;
    String room_number;
    String time_slot;
    String course_title;

    public LectureTime(String lecture_id, String course_id,  String room_number, String time_slot,  String title) {
        this.lecture_id = lecture_id;
        this.course_id = course_id;
        this.room_number = room_number;
        this.time_slot = time_slot;
        this.course_title = title;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getLecture_id() {
        return lecture_id;
    }

//    public void setlecture_id(String lecture_id) {
//        this.lecture_id = lecture_id;
//    }

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
