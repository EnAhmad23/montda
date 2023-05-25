package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateTA implements Initializable {
    @FXML
    public AnchorPane root;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private ComboBox teach;
    @FXML
    private TextField password;
    private String teaches[] = {"", ""};
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();


    public void nav_back(){
        nav.navigateTo(root, nav.TEACHING_FXML);
    }
    public void nav_update(){}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText("Student ID");
        id.setEditable(false);
        teach.getItems().addAll(teaches);
    }
}
