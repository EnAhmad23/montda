package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.TeacherAssistant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class AddTeacherAssistant implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField id;
    @FXML
    private Label label = new Label();
    @FXML
    private TextField name;
    @FXML
    private ComboBox teache;
    @FXML
    private TextField password;
    DBModel dm = DBModel.getModel();
    Navigation nav = new Navigation();

    public void addTeacher(ActionEvent actionEvent) {
        String t_id = id.getText();
        String t_name = name.getText();
        String t_teache = (String) teache.getValue();
        String t_password = password.getText();
        label.setText("");
        if (!t_id.isEmpty() && !t_name.isEmpty() && !t_password.isEmpty())
            new TeacherAssistant(t_id, t_name, t_teache).setPassword(t_password);
        else
            label.setText("INCOMPLETE INPUTS");
        if (dm.addTeacher(t_id, t_name, t_teache, t_password) != 0) {
            label.setTextFill(Color.color(0, 0, 0));
            label.setText("Teacher Assistant added Successfully");
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("Teacher Assistant didn't add ");
        }

    }

    public void back_to_start() {
    }

    public void userId() {
    }

    public void userDept() {
    }

    public void userName() {
    }

    public void userPass() {
    }

    public void back_user() {
        nav.navigateTo(root, nav.MAIN_FXML);
    }

    public void add_teacher(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void ids(ActionEvent actionEvent) {
        if (!id.getText().isEmpty())
            teache.getItems().addAll(dm.availableCourse(id.getText()));
        else
            teache.getItems().addAll(dm.getCou());
    }
}
