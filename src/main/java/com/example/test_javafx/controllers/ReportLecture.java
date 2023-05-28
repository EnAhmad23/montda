package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.LectureTime;
import com.example.test_javafx.models.ReportLectures;
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

public class ReportLecture implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button Search;
    @FXML
    private Button nav_back;

    @FXML
    private TextField l_id;

    public TableView<ReportLectures> table;
    @FXML
    public TableColumn<ReportStudent, String> id;

    @FXML
    public TableColumn<ReportStudent, String> lec_id;
    @FXML
    public TableColumn<ReportStudent, String> title;
    @FXML
    public TableColumn<ReportStudent, String> attendancePear;

    Navigation nav = new Navigation();
    DBModel dm=DBModel.getModel();
    ArrayList<ReportLectures> lecture  ;
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
        lecture=dm.reportLectures();
        view(lecture);
        autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(l_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            l_id.setText(event.getCompletion().toString().substring(0,5));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });
    }

    private void view(ArrayList<ReportLectures> lectures) {
        lec_id.setCellValueFactory(new PropertyValueFactory<>("lec_id"));
        id.setCellValueFactory(new PropertyValueFactory<>("Course_id"));
        attendancePear.setCellValueFactory(new PropertyValueFactory<>("present"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        ObservableList<ReportLectures> lecture = FXCollections.observableArrayList(lectures);
        table.setItems(lecture);
    }

    public void autoComplete() {

        autoValues();
    }
    void autoValues(){
        list = new ArrayList<>();
        for (ReportLectures s : lecture) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getLec_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getCourse_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getTitle());
            stringBuilder.append(", ");
            stringBuilder.append(s.getPresent());
            list.add(stringBuilder.toString());
        }
    }
    public void update() {

    }
}
