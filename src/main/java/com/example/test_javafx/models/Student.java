package com.example.test_javafx.models;

public class Student {
    private String id ;
    private String name ;
    private String gender ;
    private String stu_level ;
    private String majer ;

    public Student(String id, String name, String gender, String stuLevel, String majer) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        stu_level = stuLevel;
        this.majer = majer;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public String getMajer() {
        return majer;
    }

    public String getName() {
        return name;
    }

    public String getStu_level() {
        return stu_level;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMajer(String majer) {
        this.majer = majer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStu_level(String stu_level) {
        this.stu_level = stu_level;
    }

}
