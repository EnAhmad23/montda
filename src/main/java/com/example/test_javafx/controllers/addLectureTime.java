package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


import java.sql.SQLException;

public class addLectureTime {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField t_lecId;

    @FXML
    private TextField t_insId;
    @FXML
    private TextField t_roomNo;
    @FXML
    private TextField t_timeSlot;
    @FXML
    private Button btn_add;
    @FXML
    private Button id_back_addlecT;
    DBModel dm = DBModel.getModel();
    @FXML
    private TextField t_lecTitle;
    @FXML
    private TextField t_courseId;
    Navigation nav=new Navigation();
    private Label label;

    public void addLecture() throws SQLException {

        String lecId = t_lecId.getText();
        String courseId = t_courseId.getText();
        String roomNo = t_roomNo.getText();
        String timeSlot = t_timeSlot.getText();
        String lecTitle = t_lecTitle.getText();
//        dm.addLecture(lecId, courseId, timeSlot, roomNo, lecTitle);
         if (dm.addLecture(lecId,courseId,timeSlot,roomNo,lecTitle)!=0){
          label.setTextFill(Color.color(0,1,0));
          label.setText("Lecture added successfully");
         }else
            label.setText("Lecture did'nt add");
    }

    public void addLectureTime(){}

    public void back_lecT() {
        nav.navigateTo(root,nav.LECTURES_TIMES_FXML);
    }
}
