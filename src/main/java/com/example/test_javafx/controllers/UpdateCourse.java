package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCourse implements Initializable {

    @FXML
    public AnchorPane root;
    @FXML
    public TextField course_id;
    @FXML
    public TextField BookName;
    @FXML
    public TextField room;
    @FXML
    public TextField CourseSubject;
    @FXML
    public Label notification;
    @FXML
    public Button back;

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();
    public void nav_back(){
        nav.navigateTo(root, nav.COURSES);
    }
    public void nav_update(){}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        course_id.setText(Navigation.string);
        course_id.setEditable(false);
    }
}
