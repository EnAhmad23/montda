package com.example.test_javafx.controllers;

import com.example.test_javafx.DataBus;
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

//import static com.example.test_javafx.controllers.Students.t_id;

public class UpdateStudent implements Initializable {
    @FXML
    public AnchorPane root;

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField place;
    @FXML
    private TextField montda_majer;
    @FXML
    private TextField uni_major;
    @FXML
    private TextField path;
    @FXML
    private ComboBox level;

    @FXML
    private TextField phone_number;
    @FXML
    private Label label;
    private String genders[] = {"1", "2","3","4","5"};
    String idFromStudent;


    @FXML
    private Button back;
    @FXML
    private Button update_button;
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    public void nav_update() {
//                      t_id

        String stu_name = name.getText();
        String stu_level = (String) level.getValue();
        String stu_place = place.getText();
        String stu_montda_majer = montda_majer.getText();
        String stu_uni_major = uni_major.getText();
        String stu_path = path.getText();

        if (dm.UpdateStudent(id.getText(), stu_name, stu_level, stu_place, stu_montda_majer, stu_uni_major,stu_path) != 0) {
            label.setTextFill(Color.color(0, 1, 0));
            label.setText("Student Update successfully");
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("Student did'nt Update");
        }

    }

//    public void nav_back() {
//        nav.navigateTo(root, nav.STUDENTS_FXML);
//    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setText(DataBus.data.get(0));
        name.setText((DataBus.data.size()>1)?DataBus.data.get(1):"");
        place.setText((DataBus.data.size()>2)?DataBus.data.get(2):"");
        montda_majer.setText((DataBus.data.size()>3)?DataBus.data.get(3):"");
        uni_major.setText((DataBus.data.size()>4)?DataBus.data.get(4):"");
        path.setText((DataBus.data.size()>5)?DataBus.data.get(5):"");
        id.setEditable(false);
        level.getItems().addAll(genders);
    }
}
