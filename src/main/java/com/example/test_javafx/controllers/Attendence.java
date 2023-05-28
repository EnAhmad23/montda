package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.LectureTime;
import com.example.test_javafx.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Attendence implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField t_id;
    @FXML
    private ComboBox lec_id_comboBox;

    public TableView<Student> table;
    @FXML  public TableColumn<LectureTime, String> stu_id;
    @FXML  public TableColumn<LectureTime, String> lec_id;

    @FXML  public TableColumn<LectureTime, String> title;

    @FXML  public TableColumn<LectureTime, String> room;
    @FXML  public TableColumn<LectureTime, String> attendance_percentage;


    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();
    public void addUser(){}

    public void back(){
        nav.navigateTo(root,nav.TEACHING_FXML);
    }

    public void updateAttendance(){
        nav.navigateTo(root, nav.UPDATE_ATTENDENCE);
    }

    public void delete(ActionEvent actionEvent) {
    }

    public void searchAttendance(){
//        if (t_id.getText().isEmpty()) {
//            view();
//        } else {
//            viewSearch();
//        }
    }
//    public void view() {
//        stu_id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        lec_id.setCellValueFactory(new PropertyValueFactory<>("Name"));
//        title.setCellValueFactory(new PropertyValueFactory<>("Gender"));
//        room.setCellValueFactory(new PropertyValueFactory<>("Majer"));
//        attendance_percentage.setCellValueFactory(new PropertyValueFactory<>("Place"));
//
//
//
//        ObservableList<Student> ids = FXCollections.observableArrayList(dm.getStd());
//        table.setItems(ids);
//    }
//
//
//    public void viewSearch() {
//        stu_id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        lec_id.setCellValueFactory(new PropertyValueFactory<>("Name"));
//        title.setCellValueFactory(new PropertyValueFactory<>("Gender"));
//        room.setCellValueFactory(new PropertyValueFactory<>("Place"));
//        attendance_percentage.setCellValueFactory(new PropertyValueFactory<>("Majer"));
//
//
//        ObservableList<Student> ids = FXCollections.observableArrayList(dm.searchStudent(t_id.getText()));
//        table.setItems(ids);
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
