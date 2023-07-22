package com.example.test_javafx.models;

public class Student {
    private String id ;
    private String name ;

    private String place ;
    private String level;
    private String monadMajor;
    private String uniMajor;
    private String path;


public Student(){}
    public Student(String id, String name, String path,String level, String uniMajor, String monadMajor , String place) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.place = place;
        this.monadMajor = monadMajor;
        this.uniMajor = uniMajor;
        this.path = path;
    }
    public void setMonadMajor(String monadMajor) {
        this.monadMajor = monadMajor;
    }

    public String getMonadMajor() {
        return monadMajor;
    }


    public String getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }



    public void setLevel(String level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUniMajor() {
        return uniMajor;
    }

    public void setUniMajor(String uniMajor) {
        this.uniMajor = uniMajor;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
