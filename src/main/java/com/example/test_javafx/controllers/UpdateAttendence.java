package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateAttendence implements Initializable {
    @FXML
    public AnchorPane root;

    @FXML
    private TextField id ;
    @FXML
    private TextField name ;
    @FXML
    private TextField lecture_id ;
    @FXML
    private TextField course_id ;
    @FXML
    private TextField title ;
@FXML
private Label label;
    @FXML
    private Button back ;

    @FXML
    private Button update_button ;

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();


    public void nav_update(){
        if (dm.updateAttendence(id.getText(),name.getText(),lecture_id.getId(),course_id.getId(),title.getText())!=0){
            label.setTextFill(Color.color(1,0,0));
            label.setText("UPDATED SUCCESSFULLY");
        }else { label.setTextFill(Color.color(0,1,0));
            label.setText("ATTENDENCES DIDN'T UPDATE");}

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(Navigation.string.substring(0,9));
        lecture_id.setText(Navigation.string.substring(10));
        id.setEditable(false);
        lecture_id.setEditable(false);
        id.setEditable(false);
    }
}
