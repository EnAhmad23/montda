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

public class ReportAttendance implements Initializable {
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
    public TableColumn<ReportStudent, String> id_student;

    @FXML
    public TableColumn<ReportStudent, String> lect_id;
    @FXML
    public TableColumn<ReportStudent, String> lecture_name;
    @FXML
    public TableColumn<ReportStudent, String> attendancePear;




    public void SearchReportAttendance(){


    }
    public void back(){}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
