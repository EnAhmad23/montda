package com.example.test_javafx.controllers;

import com.example.test_javafx.models.LectureTime;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportStudent implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button Search;
    @FXML
    private Button nav_back;
    @FXML
    private TextField Student_ID;
    @FXML
    private TextField lecture_id;

    public TableView<ReportStudent> table;
    @FXML
    public TableColumn<ReportStudent, String> sti_id;

    @FXML
    public TableColumn<ReportStudent, String> course_id;
    @FXML
    public TableColumn<ReportStudent, String> lec_id;
    @FXML
    public TableColumn<ReportStudent, String> attendancePear;



    public void SearchReport(){}

    public void back(){}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
