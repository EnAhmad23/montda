package com.example.test_javafx.controllers;


import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Courses implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn <Courses,String> course_id;;
    @FXML
    private TableColumn <Courses,String> book_name;
    @FXML
    private TableColumn <Courses,String> teacher_name;
    @FXML
    private TableColumn <Courses,String> room_number;
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
    private TextField t_id;
    @FXML
    private TextField field_to_name;
    @FXML
    private TextField field_to_department;
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();
    public void addCourse(){
        nav.navigateTo(root,nav.ADD_COURSE_FXML);

    }
    public void deleteCourse(){
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label =new Label("STUDENT DELETED !");
        if ( dm.delete_Course(t_id.getText())!=0) {
            view();
            label.setTextFill(Color.color(0,0,0));
        }else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("STUDENT DIDN'T DELETE !");
        }
        t_id.clear();
        root.getChildren().add(label);
        stage.setScene(new Scene(root, 300, 100));
        stage.show();

    }
    public void updateCourses(){
//        nav.navigateTo(root,nav.ADD_USER_FXML);

    }

    public void back(){
        nav.navigateTo(root,nav.Admin_FXML);

    }

    public void view (){
        course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        teacher_name.setCellValueFactory(new PropertyValueFactory<>("teacher_name"));
        room_number.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));

        ObservableList<Course> course = FXCollections.observableArrayList(dm.getCou());
        table.setItems(course);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        view();

    }

    public void updateCourse(ActionEvent actionEvent) {
    }

    public void searchCourse(ActionEvent actionEvent) {
    }
}
