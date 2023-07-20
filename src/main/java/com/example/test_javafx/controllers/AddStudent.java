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

public class AddStudent implements Initializable {
    @FXML
    private Label label = new Label();
    @FXML
    private AnchorPane root;
    @FXML
    private TextField id;
    @FXML
    private TextField name;


    @FXML
    private TextField uni_major;
    @FXML
    private TextField montda_majer;
    @FXML
    private TextField place;

    @FXML
    private ComboBox level;

    @FXML
    private TextField path;
    @FXML
    private Button btn_add;

    @FXML
    private Button id_back_addStu;

    private String levels[] = {"1", "2","3","4","5"};
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    public void addStudntBotton()  {
        String stu_id = id.getText();
        String stu_name = name.getText();
        String m_major = montda_majer.getText();
        String u_major = montda_majer.getText();
        String stu_place =  place.getText();
        String stu_level = (String) level.getValue();
        String stu_path = path.getText();

        if ((dm.addStudent(stu_id, stu_name, stu_level,m_major,  u_major,stu_place,stu_path) != 0)&&checkFild()) {
            label.setTextFill(Color.color(0, 1, 0));
            label.setText("Student added successfully");

        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("Student didn't add");
        }
        id.clear();
        name.clear();
        montda_majer.clear();
        place.clear();
        path.clear();
        uni_major.clear();
    }


    public void back_addStu() {
        nav.navigateTo(root, nav.STUDENTS_FXML);
    }
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back_addStu();
        }
    }
    public boolean checkFild(){
       return ! (id.getText().isEmpty()&&
        name.getText().isEmpty()&&
        montda_majer.getText().isEmpty()&&place.getText().isEmpty()&&path.getText().isEmpty()&& uni_major.getText().isEmpty());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level.getItems().addAll(levels);
    }
}
