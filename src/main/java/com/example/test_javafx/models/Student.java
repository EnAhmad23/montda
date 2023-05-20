package com.example.test_javafx.models;

public class Student {

    String student_id;
    String student_name;
    String gender;
    String level;
    String major;

    public Student(String student_id, String student_name, String gender, String level, String major) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.gender = gender;
        this.level = level;
        this.major = major;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMajort() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
