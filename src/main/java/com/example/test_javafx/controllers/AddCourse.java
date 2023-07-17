package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCourse implements Initializable {

    @FXML
    public AnchorPane rootPane;
    @FXML
    private Label label;
    @FXML
    public TextField course_id;

    @FXML
    private TextField name;
    @FXML
    private TextField majer;
    @FXML

    public TextField room;
    @FXML
    public Button button_to_back;
    @FXML
    public ComboBox<Integer> hour;
    @FXML
    public ComboBox<Integer> minute;

    @FXML
    public Button nav_back;
    DBModel db = DBModel.getModel();
    Navigation nav = new Navigation();

    DBModel dm = DBModel.getModel();




    public void addCourses() {
        String courseId = course_id.getText();
        String c_name = name.getText();
        String room = this.room.getText();
        String montda_majer = majer.getText();
        String cTime = String.format("%02d:%02d",hour.getValue(),minute.getValue());
        if (dm.addCourse(courseId,  c_name, room, montda_majer,cTime) != 0) {
            label.setTextFill(Color.color(0, 1, 0));
            label.setText("COURSE ADDED SUCCESSFULLY");//successfully
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("COURSE DIDN'T ADD");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 1; i <= 12; i++) {
            hour.getItems().add(i);
        }
        for (int i = 0; i <= 60; i++) {
            minute.getItems().add(i);
        }
    }







}
