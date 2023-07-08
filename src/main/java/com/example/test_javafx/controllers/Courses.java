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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
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
    ArrayList<Course> courses ;
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();

    public void addCourse() {

        try {
            nav.upSecen( nav.ADD_COURSE_FXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteCourse() {
        Stage stage = new Stage();
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label = new Label("COURSES DELETED !");
        if (dm.delete_Course(t_id.getText()) != 0) {
            view();
            del(t_id.getText());
//            if (autoCompletionBinding != null)
//                autoCompletionBinding.dispose();
            nav.navigateTo(root,nav.COURSES);
            label.setTextFill(Color.color(0, 0, 0));

        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("COURSES DIDN'T DELETE !");
        }
//        courses = dm.getCou();
        t_id.clear();
        vBox.getChildren().add(label);
        stage.setScene(new Scene(vBox, 300, 100));
        stage.show();

    }

    public void back() {
        nav.navigateTo(root, nav.Admin_FXML);

    }
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
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
        courses= dm.getCou();
        view();
        autoValues();
        table.setOnMouseClicked(mouseEvent ->  {

                if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                    Course selectedCourse = table.getSelectionModel().getSelectedItem();
                    if (selectedCourse != null) {
                        t_id.setText(selectedCourse.getCourse_id());
                    }
                }else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && mouseEvent.getClickCount() == 2){
                    Course selectedReport = table.getSelectionModel().getSelectedItem();
                    if (selectedReport != null) {
                        Navigation.string =(selectedReport.getCourse_id());
                        try {
                            updateCourse();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

        });
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().substring(0, 8));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });

    }

    public void updateCourse() throws IOException {

        if (t_id.getText().length() == 8&& (!t_id.getText().equals("        "))) {
            Navigation.string = t_id.getText();
            nav.upSecen( nav.UPDATE_COURSE);
        } else
            nav.error_message("ENTER THE ID FOR  THE COURSE !!");
    }

    public void autoComplete() {
        autoValues();


    }
    void autoValues(){
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
