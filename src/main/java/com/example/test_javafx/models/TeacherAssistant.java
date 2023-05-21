package com.example.test_javafx.models;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherAssistant implements Initializable {

    String id;
    String name;
    String teache;

    String advisor;

    public TeacherAssistant(String id, String name, String teache,String advisor) {
        this.id = id;
        this.name = name;
        this.teache = teache;
        this.advisor=advisor;
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

    public void setTeache(String teache) {
        this.teache = teache;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
