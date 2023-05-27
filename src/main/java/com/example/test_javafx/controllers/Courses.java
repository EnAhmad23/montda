package com.example.test_javafx.controllers;


import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import com.example.test_javafx.models.DBModel;
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
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Courses implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn<Courses, String> course_id;
    ;
    @FXML
    private TableColumn<Courses, String> book_name;
    @FXML
    private TableColumn<Courses, String> teacher_name;
    @FXML
    private TableColumn<Courses, String> room_number;
    @FXML
    private TableColumn<Courses, String> subject;
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
    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();

    public void addCourse() {
        nav.navigateTo(root, nav.ADD_COURSE_FXML);

    }

    public void deleteCourse() {
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label = new Label("COURSES DELETED !");
        if (dm.delete_Course(t_id.getText()) != 0) {

            view();
            del(t_id.getText());
            if (autoCompletionBinding != null)
                autoCompletionBinding.dispose();
            label.setTextFill(Color.color(0, 0, 0));
//            courses = dm.getCou();

        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("COURSES DIDN'T DELETE !");
        }
        courses = dm.getCou();
        t_id.clear();
        root.getChildren().add(label);
        stage.setScene(new Scene(root, 300, 100));
        stage.show();

    }

    public void back() {
        nav.navigateTo(root, nav.Admin_FXML);

    }

    public void view() {
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

        if (t_id.getText().length() == 8) {
            Navigation.string = t_id.getText();
            nav.navigateTo(root, nav.UPDATE_COURSE);
        } else
            nav.error_message("ENTER THE ID FOR  THE COURSE !!");
    }

    public void autoComplete() {
        list = new ArrayList<>();
        for (Course s : courses) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getCourse_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getBook_name());
            stringBuilder.append(", ");
            stringBuilder.append(s.getTeacher_name());
            stringBuilder.append(", ");
            stringBuilder.append(s.getRoom_number());
            stringBuilder.append(", ");
            stringBuilder.append(s.getSubject());
            list.add(stringBuilder.toString());
        }
        if (autoCompletionBinding != null)
            autoCompletionBinding.dispose();
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().substring(0, 8));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });


    }

    void del(String s) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourse_id().equals(s)) {
//                list.remove(i);
                System.out.println(courses.remove(i));
            }
        }
    }

    public void searchCourse(ActionEvent actionEvent) {
        if (t_id.getText().isEmpty()) {
            view();
        } else
            viewSearch();
    }

    public void viewSearch() {
        course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        book_name.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        teacher_name.setCellValueFactory(new PropertyValueFactory<>("teacher_name"));
        room_number.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));

        ObservableList<Course> course = FXCollections.observableArrayList(dm.searchCourse(t_id.getText()));
        table.setItems(course);
    }

}
