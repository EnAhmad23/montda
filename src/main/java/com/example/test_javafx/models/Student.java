package com.example.test_javafx.models;

public class Student {
    private String id ;
    private String name ;

    private String place ;
    private String majer ;
    private String phone_num ;


public Student(){}
    public Student(String id, String name,  String majer, String place, String phoneNum) {
        this.id = id;
        this.name = name;
        this.majer = majer;
        this.place = place;
        phone_num = phoneNum;
    }
    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPhone_num() {
        return phone_num;
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

    public String getPlace() {
        return place;
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

    public void setPlace(String place) {
        this.place = place;
    }

}
