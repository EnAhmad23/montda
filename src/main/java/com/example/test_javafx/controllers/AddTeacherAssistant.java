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
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class AddTeacherAssistant implements Initializable {
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
    @FXML
    private TextField password ;
    DBModel dm = DBModel.getModel();
    Navigation nav = new Navigation();



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
        nav.navigateTo(root, nav.USERS_FXML);
    }

    public void add_teacher(ActionEvent actionEvent) {

        String t_id = id.getText();
        String t_name = name.getText();
        String t_teache = (String) teache.getValue();
        String t_password = password.getText();
        lable.setText("");
        if (!t_id.isEmpty() && !t_name.isEmpty() && !t_password.isEmpty()&&!t_teache.isEmpty())
            new TeacherAssistant(t_id, t_name, t_teache).setPassword(t_password);
        else
            lable.setText("INCOMPLETE INPUTS");
        if (dm.addTeacher(t_id, t_name, t_teache, t_password) != 0) {
            lable.setTextFill(Color.color(0, 1, 0));
            lable.setText("Teacher Assistant added Successfully");
        } else {
            lable.setTextFill(Color.color(1, 0, 0));
            lable.setText("Teacher Assistant didn't add ");
        }
        id.clear();name.clear();password.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        if (!dm.availableCourse().is)

        teache.getItems().addAll(dm.availableCourse().toArray(new String[dm.availableCourse().size()]));
    }


}
