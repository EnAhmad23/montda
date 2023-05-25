package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

//import static com.example.test_javafx.controllers.Students.t_id;

public class UpdateStudent implements Initializable {
    @FXML
    public AnchorPane root;

    @FXML
    private TextField id ;
    @FXML
    private TextField name;
    @FXML
    private TextField place;
    @FXML
    private TextField major;
    @FXML
    private ComboBox gender;
    private String genders[] = {"male", "female"};
    String idFromStudent;

    @FXML
    private Button back;
    @FXML
    private Button update_button;
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    public void nav_update(){
//                      t_id
        idFromStudent = id.getText();
        String stu_id = idFromStudent;
        String stu_name = name.getText();
        String stu_major = major.getText();
        String stu_place =  place.getText();
        String stu_gender = (String) gender.getValue();
        dm.UpdateStudent(idFromStudent, stu_name, stu_gender,  stu_major,stu_place);

//        if (dm.addStudent(stu_id, stu_name, stu_gender,  stu_major,stu_place) != 0) {
//            label.setTextFill(Color.color(0, 1, 0));
//            label.setText("Student added successfully");
//        } else {
//            label.setTextFill(Color.color(1, 0, 0));
//            label.setText("Student did'nt add");
//        }

    }
    public void nav_back(){
        nav.navigateTo(root, nav.STUDENTS_FXML);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(idFromStudent);
        id.setEditable(false);
        gender.getItems().addAll(genders);
    }
}
