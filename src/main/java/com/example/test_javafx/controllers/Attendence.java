package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Attendence {
    @FXML
    private AnchorPane root;
    Navigation nav = new Navigation();
    public void addUser(){}
    public void enterUserID(){}
    public void enterUserName(){}
    public void enterUserDepartment(){}
    public void back(){
        nav.navigateTo(root,nav.TEACHING_FXML);
    }
    public void att_course(){}
    public void UpdateUser(){}


}
