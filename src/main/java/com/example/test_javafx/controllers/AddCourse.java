package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddCourse implements Initializable {

    @FXML
    public AnchorPane rootPane;
    @FXML
    private Label label;
    @FXML
    public TextField course_id;

    @FXML
    private TextField CourseSubject;
    @FXML
    private TextField bookName;
    @FXML
    private TextField teacherName;
    @FXML

    public TextField room;
    @FXML
    public Button button_to_back;

    @FXML
    public Button nav_back;
    DBModel db = DBModel.getModel();
    Navigation nav = new Navigation();

    DBModel dm = DBModel.getModel();



    public void back() {
        nav.navigateTo(rootPane, nav.COURSES);
    }
    public void addCourses() {
        String courseId = course_id.getText();
        String Teacher_name = teacherName.getText();
        String book = bookName.getText();
        String Room = room.getText();
        String Subject = CourseSubject.getText();
        if (dm.addCourse(courseId, Teacher_name, book, Room, Subject) != 0) {
            label.setTextFill(Color.color(0, 1, 0));
            label.setText("COURSE ADDED SUCCESSFULLY");//successfully
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("COURSE DIDN'T ADD");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }





}
