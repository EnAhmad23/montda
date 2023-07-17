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
import java.util.ResourceBundle;

public class UpdateTA implements Initializable {
    @FXML
    public AnchorPane root;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private ComboBox teach;
    @FXML
    private TextField password;
    @FXML
    private Button back;
    @FXML
    private Label label;

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();




    public void nav_update() {
        if (dm.UpdateTeacher_Assist(id.getText(), name.getText(), teach.getValue().toString(), password.getText()) != 0) {
            label.setTextFill(Color.color(0, 1, 0));
            label.setText("Teach UPDATE SUCCESSFULLY");
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("Teach DIDN'T UPDATE");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(Navigation.string);
        id.setEditable(false);
        teach.getItems().addAll(dm.availableCourse().toArray(new String[dm.availableCourse().size()]));
    }
}
