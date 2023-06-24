package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    public TextField bookName;
    @FXML
    public TextField room;
    @FXML
    public TextField teacherName;
    @FXML
    public TextField courseSubject;
    @FXML
    public Label label;
    @FXML
    public Button back;

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();



    public void nav_update() {
        if (dm.updateCourse(course_id.getText(), teacherName.getText(), bookName.getText(), room.getText(), courseSubject.getText()) != 0) {
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
