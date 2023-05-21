package com.example.test_javafx.controllers;


import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import com.example.test_javafx.models.DBModel;
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
import java.util.ResourceBundle;

public class Courses implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn <Courses,String> id;
    @FXML
    private TableColumn <Courses,String> bookName;
    @FXML
    private TableColumn <Courses,String> teacherName;
    @FXML
    private TableColumn <Courses,String> room;
    @FXML
    private TableColumn <Courses,String> subject;
    @FXML
    private Button nav_add;
    @FXML
    private Button nav_update;
    @FXML
    private Button nav_delete;
    @FXML
    private Button id_close_user;
    @FXML
    private TextField field_to_id;
    @FXML
    private TextField field_to_name;
    @FXML
    private TextField field_to_department;
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();
    public void addCourse(){
        nav.navigateTo(root,nav.ADD_COURSE_FXML);

    }
    public void deleteCourses(){
//        nav.navigateTo(root,nav.ADD_USER_FXML);

    }
    public void updateCourses(){
//        nav.navigateTo(root,nav.ADD_USER_FXML);

    }

    public void back(){
        nav.navigateTo(root,nav.Admin_FXML);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        bookName.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        teacherName.setCellValueFactory(new PropertyValueFactory<>("teacher_name"));
        room.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        ObservableList<Course> course = FXCollections.observableArrayList(dm.getCou());
        table.setItems(course);

    }
}
