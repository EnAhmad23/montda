package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Sections implements Initializable {
    @FXML
    private AnchorPane root ;
    @FXML
    private TextField id ;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button id_close_section;
    Navigation nav = new Navigation();
    public void addSection (){
        nav.navigateTo(root,nav.ADD_SECTION_FXML);
    }
    public void updateSection (){

    }
    public void deleteSection(){}
    public void textToSecionID(){}
    public void close_section(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
