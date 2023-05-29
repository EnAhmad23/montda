package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.event.ActionEvent;
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

public class UpdateLecRep implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button back;
    @FXML
    private Button update_button;
    @FXML
    private TextField lec_id;
    @FXML
    private TextField title;

    @FXML
    private ComboBox<String> course_id;
    @FXML
    private Label label;
    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lec_id.setText(Navigation.string);
        lec_id.setEditable(false);
        course_id.getItems().addAll(db.getCourseIDs().toArray(new String[db.getCourseIDs().size()]));

    }

    public void nav_back() {
        nav.navigateTo(root, nav.REPORT_LECTURE);
    }




}
