package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.TeacherAssistant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

import java.sql.PreparedStatement;

public class AddTeacherAssistant {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField id;
    @FXML
    private Label label;
    @FXML
    private TextField name;
    @FXML
    private TextField teache;
    @FXML
    private TextField password;
    DBModel dm = DBModel.getModel();
    Navigation nav = new Navigation();
    public void addTeacher(ActionEvent actionEvent){
        String t_id = id.getText();
        String t_name = name.getText();
        String t_teache = teache.getText();
        String t_password = password.getText();
//        label.setText("");
        if (!t_id.isEmpty()&&!t_name.isEmpty()&&!t_password.isEmpty())
            new TeacherAssistant(id.getText(),name.getText(),teache.getText(),password.getText());
        else
            label.setText("INCOMPLETE INPUTS");
        if(dm.addTeacher(t_id,t_name,t_teache,t_password)!=0){
//
            System.out.println("1");
        }else
//            label.setText("Teacher Assistant didn't add ");
            System.out.println("0");

    }
    public void back_to_start(){}
    public void userId(){}
    public void userDept(){}
    public void userName(){}
    public void userPass(){}
    public void back_user(){nav.navigateTo(root,nav.MAIN_FXML);}

    public void add_teacher(ActionEvent actionEvent) {
    }
}
