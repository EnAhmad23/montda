package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class addLectureTime implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField id_lecId;

    @FXML
    private TextField t_title;
    @FXML
    private TextField t_roomNo;



    @FXML
    private ComboBox<String> t_courseId;
    Navigation nav = new Navigation();
    @FXML
    private Label label = new Label();
    DBModel dm = DBModel.getModel();

    public void addLecture() throws SQLException {

        String lecId = id_lecId.getText();
        String courseId = t_courseId.getValue();
        String roomNo = !(t_roomNo.getText()).isEmpty()?t_roomNo.getText():dm.getCourseRoom(courseId) ;

        String lecTitle = t_title.getText();
//        dm.addLecture(lecId, courseId, timeSlot, roomNo, lecTitle);
        if (dm.addLecture(lecId, courseId, roomNo, lecTitle) != 0) {
            label.setTextFill(Color.color(0, 1, 0));
            label.setText("Lecture added successfully");
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("Lecture did'nt add");
        }
        id_lecId.clear();
        t_roomNo.clear();
        t_title.clear();
    }


    public void back_lecT() {
        nav.navigateTo(root, nav.LECTURES_TIMES_FXML);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        t_courseId.getItems().addAll(dm.getCourseIDs().toArray(new String[dm.getCourseIDs().size()]));
    }
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back_lecT();
        }
    }
}
