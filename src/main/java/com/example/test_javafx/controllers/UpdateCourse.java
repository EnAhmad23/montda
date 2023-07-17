package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCourse implements Initializable {

    @FXML
    public AnchorPane root;
    @FXML
    public TextField course_id;
    @FXML
    public TextField name;
    @FXML
    public TextField room;
    @FXML
    public TextField majer;
    @FXML
    public ComboBox<Integer> hour;
    @FXML
    public ComboBox<Integer> minute;
    @FXML
    public TextField teacherID;

    @FXML
    public Label label;
    @FXML
    public Button back;

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();



    public void nav_update() {
        String cTime = String.format("%02d:%02d",hour.getValue(),minute.getValue());
        if (dm.updateCourse(course_id.getText(),  name.getText(),teacherID.getText(), room.getText(), majer.getText(),cTime) != 0) {
            label.setTextFill(Color.color(0, 1, 0));
            label.setText("COURSE UPDATE SUCCESSFULLY");
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("COURSE DIDN'T UPDATE");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        course_id.setText(Navigation.string);
        course_id.setEditable(false);

    }
}
