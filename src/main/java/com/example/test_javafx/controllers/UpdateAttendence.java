package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateAttendence implements Initializable {
    @FXML
    public AnchorPane root;

    @FXML
    private TextField id ;
    @FXML
    private TextField teacher_name ;
    @FXML
    private TextField lecture_id ;
    @FXML
    private TextField room ;
    @FXML
    private TextField time ;

    @FXML
    private Button back ;

    @FXML
    private Button update_button ;

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();


    public void nav_update(){
//        nav.navigateTo(root, nav.UPDATE_ATTENDENCE);
    }


    public void nav_back(){
        nav.navigateTo(root, nav.ATTENDENCE_FXML);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setText("Student ID");
        id.setEditable(false);
    }
}
