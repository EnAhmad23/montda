package com.example.test_javafx.models;

public class LectureTime {
    String lecture_id;
    String course_id;
    String room_number;



    String title;


    public LectureTime(String lecture_id, String course_id,  String room_number,  String title) {
        this.lecture_id = lecture_id;
        this.course_id = course_id;
        this.room_number = room_number;
        this.title = title;
    }
    public void setLecture_id(String lecture_id) {
        this.lecture_id = lecture_id;
    }


    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


}
