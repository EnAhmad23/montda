package com.example.test_javafx.controllers;

import com.example.test_javafx.models.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class absent {


    @FXML
    private Button search;
    @FXML
    private Button export;
    @FXML
    private Button update;
    @FXML
    private TextField t_id;

    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn<absent, String> id;
    @FXML
    private TableColumn<absent, String> name;
    @FXML
    private TableColumn<absent, String> gender;
    @FXML
    private TableColumn<absent, String> major;
    @FXML
    private TableColumn<absent, String> place;
    @FXML
    private TableColumn<absent, String> phone_num;
    @FXML
    private TableColumn<absent, String> course_id;
    @FXML
    private TableColumn<absent, String> attPercentage;



    public void exportStudent(){}

    public void UpdateStudent(){}

    public void Back(){}

    public void searchStudent(){}

    public void autoComplete() {
    }

    public void updateStudent() {
    }
}
