package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Students implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField id;
    @FXML
    private TextField s_name;
    @FXML
    private TextField department;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    Navigation nav = new Navigation();
    public void addStudent (){
        nav.navigateTo(root,nav.Add_STUDENT_FXML);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
