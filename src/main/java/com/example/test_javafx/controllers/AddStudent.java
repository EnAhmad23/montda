package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddStudent implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField major;
    @FXML
    private ComboBox level;
    @FXML
    private ComboBox gender;
    @FXML
    private Button btn_add;
    @FXML
    private Button nav_close;
    @FXML
    private Button id_back_addStu;
    Navigation nav = new Navigation();

    public void addStudntBotton() {
    }

    public void close_addStudent() {
    }

    public void back_addStu() {
        nav.navigateTo(root,nav.MAIN_FXML);
    }

    public void addSrudentLevel() {
    }

    public void addSrudentGender() {
    }

    public void addSrudentID() {
    }

    public void addSrudentName() {
    }

    public void addSrudentMajor() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
