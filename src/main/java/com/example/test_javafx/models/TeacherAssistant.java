package com.example.test_javafx.models;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherAssistant  {

    String id;
    String name;
    String teache;
    String password;


    public TeacherAssistant(String id, String name, String teache) {
        this.id = id;
        this.name = name;
        this.teache = teache;
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

    public String getTeache() {
        return teache;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setTeache(String teache) {
        this.teache = teache;
    }

}
