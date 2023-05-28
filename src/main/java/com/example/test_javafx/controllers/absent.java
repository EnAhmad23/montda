package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class absent implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button search;
    @FXML
    private Button export;
    @FXML
    private Button update;
    @FXML
    private TextField t_id;

    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn<absent, String> id;
    @FXML
    private TableColumn<absent, String> name;
    @FXML
    private TableColumn<absent, String> gender;
    @FXML
    private TableColumn<absent, String> major;
    @FXML
    private TableColumn<absent, String> place;
    @FXML
    private TableColumn<absent, String> phone_num;
    @FXML
    private TableColumn<absent, String> course_id;
    @FXML
    private TableColumn<absent, String> attPercentage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    Navigation nav = new Navigation();

    public void exportStudent() {
    }




    public void searchStudent() {
    }

    public void autoComplete() {
    }

    public void updateStudent() {
    }

    public void back() {
        nav.navigateTo(root, nav.REPORT_PAGE);
    }


}
