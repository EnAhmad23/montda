package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.LectureTime;
import com.example.test_javafx.models.Student;
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
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportStudent implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button Search;
    @FXML
    private Button nav_back;
    @FXML
    private TextField s_id;


    public TableView<ReportStudent> table;
    @FXML
    public TableColumn<ReportStudent, String> id;

    @FXML
    public TableColumn<ReportStudent, String> course_id;
    @FXML
    public TableColumn<ReportStudent, String> lec_id;
    @FXML
    public TableColumn<ReportStudent, String> attendancePear;
    ArrayList<Student> students;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();
    DBModel dm = DBModel.getModel();
    Navigation nav = new Navigation();

    public void SearchReport() {

    }

    public void back() {
        nav.navigateTo(root, nav.REPORT_PAGE);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        students=dm.getStd();
//        view();
        autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(s_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            s_id.setText(event.getCompletion().toString().substring(0,9));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });
    }

    private void view() {
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        course_id.setCellValueFactory(new PropertyValueFactory<>("Name"));
//        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
//        major.setCellValueFactory(new PropertyValueFactory<>("Majer"));
//        place.setCellValueFactory(new PropertyValueFactory<>("Place"));
//        phone_num.setCellValueFactory(new PropertyValueFactory<>("phone_num"));
//
//
//        ObservableList<Student> ids = FXCollections.observableArrayList(dm.getStd());
//        table.setItems(ids);
    }

    public void autoComplete(){
        autoValues();

    }
    void autoValues(){
        list = new ArrayList<>();
        for (Student s : students) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getId());
            stringBuilder.append(", ");
            stringBuilder.append(s.getName());
            stringBuilder.append(", ");
            stringBuilder.append(s.getGender());
            stringBuilder.append(", ");
            stringBuilder.append(s.getMajer());
            stringBuilder.append(", ");
            stringBuilder.append(s.getPlace());
            stringBuilder.append(", ");
            stringBuilder.append(s.getPhone_num());
            list.add(stringBuilder.toString());
        }
    }
}
