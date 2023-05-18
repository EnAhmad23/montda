package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.LectureTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Students implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField id;
    @FXML
    private TextField s_name;
    @FXML
    private TableColumn<LectureTime,String> s_id;
    @FXML
    private TableColumn<LectureTime,String> name;
    @FXML
    private TableColumn<LectureTime,String> g;
    @FXML
    private TableColumn<LectureTime,String> l;
    @FXML
    private TableColumn<LectureTime,String> m;
    @FXML
    private TextField department;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TableView<LectureTime> table;
    @FXML
    private Button id_close_students;

    DBModel dm = DBModel.getModel();
    Navigation nav = new Navigation();
    public void addStudent (){
        nav.navigateTo(root,nav.Add_STUDENT_FXML);
    }
    public void UpdateStudent(){

    }
    public void deleteStudent(){

    }

    public void enterStudentID(){

    }

    public void enterStudentName(){

    }
    public void enterStudentDepartment(){

    }
    public void Update_back(){
        if (nav.getCurrentPath().equals(nav.MAIN_FXML))
        nav.navigateTo(root,nav.MAIN_FXML);
        else
            nav.navigateTo(root,nav.TEACHING_FXML);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        s_id =new TableColumn<>("Student ID");
//        s_id.setCellFactory(new PropertyValueFactory<>("id"));
//        name.setCellFactory(new PropertyValueFactory<LectureTime,String>("m"));
//        m.setCellFactory(new PropertyValueFactory<LectureTime,String>("cv"));
//        g.setCellFactory(new PropertyValueFactory<LectureTime,String>("id"));
//        l.setCellFactory(new PropertyValueFactory<LectureTime,String>("id"));

        ObservableList <LectureTime>ids = FXCollections.observableArrayList(dm.getStdId());
        table.setItems(ids);

    }
}
