package com.example.test_javafx.controllers;


import com.example.test_javafx.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Users implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private Button nav_add;
    @FXML
    private Button nav_update;
    @FXML
    private Button nav_delete;
    @FXML
    private Button id_close_user;
    @FXML
    private TextField field_to_id;
    @FXML
    private TextField field_to_name;
    @FXML
    private TextField field_to_department;
    Navigation nav = new Navigation();

    public void addUser() {
        nav.navigateTo(root, nav.ADD_USER_FXML);
    }

    public void updateUser() {

    }

    public void deleteUser() {

    }


    public void close_user() {
        nav.navigateTo(root,nav.Admin_FXML);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
