package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TeachingAssistant implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button student;
    @FXML
    private Button lectures;
    @FXML
    private Button attendance;
    @FXML
    private Button reports_statements;

    Navigation nav = new Navigation();
    public void student_page(){
        nav.navigateTo(root,nav.STUDENTS_FXML);
    }
    public void lecture_page(){
        nav.navigateTo(root,nav.LECTURES_TIMES_FXML);
    }
    public void attendance_page(){
        nav.navigateTo(root,nav.ATTENDENCE_FXML);
    }
    public void backTA(){
        nav.navigateTo(root,nav.LOGIN);
    }

    public void report_statement_page(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
