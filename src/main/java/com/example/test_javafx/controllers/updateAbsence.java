package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class updateAbsence {


    private TextField student_id;
    @FXML
    private TextField Name;
    @FXML
    private TextField phoneNum;
    @FXML
    private TextField courseId;
    @FXML
    private Button update_button;
    @FXML
    private Button back;
    @FXML
    private ComboBox gender;
    private String genders[] = {"male", "female"};

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();


}
