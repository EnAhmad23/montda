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

public class UpdateLecture implements Initializable {
    @FXML
    public AnchorPane root;

    @FXML
    private TextField lecture_id ;
    @FXML
    private ComboBox<String> courses;
    @FXML
    private TextField roomNo;

    @FXML
    private TextField title;

    @FXML
    private Button back;
    @FXML
    private Button update_button;
    @FXML
     private Label label;
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();


    public void nav_update(){
        if (dm.UpdateLecture(lecture_id.getText(), courses.getValue(), roomNo.getText(), title.getText()) != 0) {
        label.setTextFill(Color.color(0, 1, 0));
        label.setText("LECTURE UPDATE SUCCESSFULLY");
    } else {
        label.setTextFill(Color.color(1, 0, 0));
        label.setText("LECTURE DIDN'T UPDATE");
    }
    }
    public void nav_back(){
        nav.navigateTo(root, nav.LECTURES_TIMES_FXML);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lecture_id.setText(Navigation.string);
        lecture_id.setEditable(false);
        courses.getItems().addAll(dm.getCourseIDs().toArray(new String[dm.getCourseIDs().size()]));
    }
}
