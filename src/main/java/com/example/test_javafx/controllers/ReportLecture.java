package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.LectureTime;
import com.example.test_javafx.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportLecture implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button Search;
    @FXML
    private Button nav_back;

    @FXML
    private TextField l_id;

    public TableView<ReportStudent> table;
    @FXML
    public TableColumn<ReportStudent, String> id;

    @FXML
    public TableColumn<ReportStudent, String> lect_id;
    @FXML
    public TableColumn<ReportStudent, String> student_name;
    @FXML
    public TableColumn<ReportStudent, String> attendancePear;

    Navigation nav = new Navigation();
    DBModel dm=DBModel.getModel();
    ArrayList<LectureTime> lectures  ;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();

    public void SearchReportAttendance(){


    }
    public void back(){
        nav.navigateTo(root,nav.REPORT_PAGE);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lectures=dm.getLectures();
//        view();
        autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(l_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            l_id.setText(event.getCompletion().toString().substring(0,5));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });
    }
    public void autoComplete() {

        autoValues();
    }
    void autoValues(){
        list = new ArrayList<>();
        for (LectureTime s : lectures) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getLecture_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getCourse_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getTitle());
            list.add(stringBuilder.toString());
        }
    }
    public void update() {

    }
}
