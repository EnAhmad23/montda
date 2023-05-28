package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Attendences;
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
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Attendence implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField t_id;
    @FXML
    private ComboBox<String> lecture_ids;

    public TableView<Attendences> table;
    @FXML
    public TableColumn<LectureTime, String> stu_id;
    @FXML
    public TableColumn<LectureTime, String> lec_id;

    @FXML
    public TableColumn<LectureTime, String> title;

    @FXML
    public TableColumn<LectureTime, String> course;
    @FXML
    public TableColumn<LectureTime, String> name;
    ArrayList<Student> students;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    public void addUser() {
    }

    public void back() {
        nav.navigateTo(root, nav.TEACHING_FXML);
    }

    public void updateAttendance() {
        if (t_id.getText().length()==9&&!t_id.getText().equals("         ")&&lecture_ids.getValue()!=null) {
            Navigation.string = t_id.getText() +" "+lecture_ids.getValue();
            nav.navigateTo(root, nav.UPDATE_ATTENDENCE);
        }else nav.error_message("ENTER THE ID FOR STUDENT !!");
    }

    public void delete(ActionEvent actionEvent) {
        if (dm.deleteAttendence(t_id.getText())!=0) {
            nav.message("STUDENT DELETED");
            view(dm.getAttendence(lecture_ids.getValue()));
            t_id.clear();
        }else nav.error_message("STUDENT DIDN'T DELETE !!");
    }

    public void searchAttendance() {
        if(t_id.getText().length()==9&&!t_id.getText().equals("         ")&&lecture_ids.getValue()!=null) {
             view(dm.searchAttendence(t_id.getText(),lecture_ids.getValue()));
        }
//        if (t_id.getText().isEmpty()) {
//            view();
//        } else {
//            viewSearch();
//        }
    }

    public void view(ArrayList<Attendences> lectureTimes) {
        stu_id.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        lec_id.setCellValueFactory(new PropertyValueFactory<>("lecture_id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        course.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        ObservableList<Attendences> ids = FXCollections.observableArrayList(lectureTimes);
        table.setItems(ids);
    }
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
        lecture_ids.getItems().addAll(dm.getLecIds().toArray(new String[dm.getLecIds().size()]));
        students = dm.getStd();
        lecture_ids.setOnAction(e -> {
            if (lecture_ids.getValue() != null) {
//                attendences = dm.getAttendence(lecture_ids.getValue());

                view(dm.getAttendence(lecture_ids.getValue()));

            } else {
                nav.error_message("SELECT LECTURE ID");
            }
        });
        autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().substring(0, 9));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });

    }

    public void autoComplete() {


        autoValues();


    }

    void autoValues() {
        list = new ArrayList<>();
        for (Student s : students) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getId());
            stringBuilder.append(", ");
            stringBuilder.append(s.getName());
            stringBuilder.append(", ");
            stringBuilder.append(s.getPhone_num());
            list.add(stringBuilder.toString());
        }
    }
//    void autoValues(){
//        list = new ArrayList<>();
//        for (Attendences s : attendences) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(s.getStudent_id());
//            stringBuilder.append(", ");
//            stringBuilder.append(s.getStudent_name());
//            stringBuilder.append(", ");
//            stringBuilder.append(s.getLecture_id());
//            stringBuilder.append(", ");
//            stringBuilder.append(s.getCourse_id());
//            stringBuilder.append(", ");
//            stringBuilder.append(s.getTitle());
//            list.add(stringBuilder.toString());
//        }
//    }

    public void add() {
        if (t_id.getText() != null && lecture_ids.getValue() != null && t_id.getText().length() == 9) {
            if (dm.addAttendence(t_id.getText(), lecture_ids.getValue()) != 0)
                view(dm.getAttendence(lecture_ids.getValue()));
            t_id.clear();
        } else nav.error_message("STUDENT DIDN'T ADD");
    }
}
