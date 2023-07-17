package com.example.test_javafx.models;

public class TeacherAssistant  {

    private String id;
    private String name;
    private String teach;



    public TeacherAssistant(String id, String name, String teach) {
        this.id = id;
        this.name = name;
        this.teach = teach;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeach() {
        return teach;
    }



    public void setTeach(String teach) {
        this.teach = teach;
    }

}
