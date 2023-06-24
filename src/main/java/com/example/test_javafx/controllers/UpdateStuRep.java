package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateStuRep implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button back;
    @FXML
    private Button update_button;
    @FXML
    private TextField id;
    @FXML
    private ComboBox<String> lec_id;

    @FXML
    private ComboBox<String> course_id;
    DBModel db =DBModel.getModel() ;
    Navigation nav = new Navigation();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(Navigation.string);
        id.setEditable(false);
         String s =id.getText();
        course_id.getItems().addAll(db.getStuCourseIDs(s).toArray(new String[db.getStuCourseIDs(s).size()]));
        lec_id.getItems().addAll(db.getStuLecIds(s).toArray(new String[db.getStuLecIds(s).size()]));
    }

    public void nav_update() {


    }


}
