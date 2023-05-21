package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StartTeacher implements Initializable {
    @FXML
    AnchorPane root;
    Navigation nav = new Navigation();
    public void back(ActionEvent actionEvent) {
        nav.navigateTo(root,nav.LOGIN);
    }

    public void students(ActionEvent actionEvent) {
    }

    public void lectures(ActionEvent actionEvent) {
    }

    public void attendance_page(ActionEvent actionEvent) {
    }

    public void report_statement_page(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
