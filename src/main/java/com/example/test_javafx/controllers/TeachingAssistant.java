package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TeachingAssistant implements Initializable {
    @FXML
    public TableColumn <TeachingAssistant,String> name;
    @FXML
    public TableColumn <TeachingAssistant,String> id;

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
    public void addUser(){
        nav.navigateTo(root,nav.STUDENTS_FXML);
    }
    public void deleteUser(){
        nav.navigateTo(root,nav.LECTURES_TIMES_FXML);
    }
    public void updateUser(){
        nav.navigateTo(root,nav.ATTENDENCE_FXML);
    }


    public void report_statement_page(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>(""));

    }

    public void back(ActionEvent actionEvent) {
        nav.navigateTo(root,nav.LOGIN);
    }
}
