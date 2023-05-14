package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Upload implements Initializable {
    @FXML
    private Button back;
    @FXML
    private AnchorPane root;
    Navigation nav = new Navigation();
    public void Upload_back(){
        nav.navigateTo(root,nav.UPLOAD);
    }
    public void Upload(){

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
