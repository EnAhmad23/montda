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

public class UpdateLecture implements Initializable {
    @FXML
    public AnchorPane root;

    @FXML
    private TextField lecture_id ;
    @FXML
    private TextField course_id;
    @FXML
    private TextField RoomNo;
    @FXML
    private TextField timeSlot;
    @FXML
    private TextField title;

    @FXML
    private Button back;
    @FXML
    private Button update_button;
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();


    public void nav_update(){
//        nav.navigateTo(root, nav.UPDATE_LECTURE);
    }
    public void nav_back(){
        nav.navigateTo(root, nav.LECTURES_TIMES_FXML);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lecture_id.setText(Navigation.string);
        lecture_id.setEditable(false);
    }
}
