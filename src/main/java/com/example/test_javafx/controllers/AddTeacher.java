package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.TeacherAssistant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTeacher implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField id;
    @FXML
    private Label lable = new Label() ;
    @FXML
    private TextField name;
    @FXML
    private ComboBox teache;
//    @FXML
//    private PasswordField password =TextFields.createClearablePasswordField() ;
    DBModel dm = DBModel.getModel();
    Navigation nav = new Navigation();





    public void back_user() {
        nav.navigateTo(root, nav.USERS_FXML);
    }
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back_user();
        }
    }
    public void add_teacher(ActionEvent actionEvent) {

        String t_id = id.getText();
        String t_name = name.getText();
        String t_teache = (String) teache.getValue();
        lable.setText("");
        if (!t_id.isEmpty() && !t_name.isEmpty() &&!t_teache.isEmpty()){
            if (dm.addTeacher(t_id, t_name, t_teache) != 0) {
                lable.setText("TEACHER ADDED ");
                lable.setTextFill(Color.color(0, 1, 0));
            } else {
                lable.setTextFill(Color.color(1, 0, 0));
                lable.setText("TEACHER DIDN'T ADD");
            }
        }

        else
            lable.setText("INCOMPLETE INPUTS");

        id.clear();name.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        teache.getItems().addAll(dm.getCourseIDs().toArray(new String[dm.getCourseIDs().size()]));
    }


}
