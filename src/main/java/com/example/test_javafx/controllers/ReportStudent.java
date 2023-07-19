package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
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

    @FXML
    public TableView<StudentReport> table;
    @FXML
    public TableColumn<StudentReport, String> id;
    @FXML
    public TableColumn<StudentReport, String> course_id;
    @FXML
    public TableColumn<StudentReport, String> name;
    @FXML
    public TableColumn<StudentReport, String> attendancePear;
    ArrayList<StudentReport> stuReport;
    ArrayList<Student> students;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();
    DBModel dm = DBModel.getModel();
    Navigation nav = new Navigation();



    public void back() {
        nav.navigateTo(root, nav.REPORT_PAGE);
    }
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        students = dm.getStd();
       // stuReport = ;
        autoValues();
        table.setOnMouseClicked(MouseEvent-> {

                if (MouseEvent.getButton().equals(MouseButton.PRIMARY) && MouseEvent.getClickCount() == 2) {
                    StudentReport selectedReport = table.getSelectionModel().getSelectedItem();
                    if (selectedReport != null) {
                        s_id.setText(selectedReport.getId());

                }
            }
        });
        autoCompletionBinding = TextFields.bindAutoCompletion(s_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            s_id.setText(event.getCompletion().toString().split(",")[0]);
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });
    }

    public void action(){
        if (s_id.getText() != null && s_id.getText().length() == 9 && !(s_id.getText().equals("         "))){
            stuReport=dm.reportStudents(s_id.getText());
            view(stuReport);
        }
    }

    private void view(ArrayList<StudentReport> list) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        attendancePear.setCellValueFactory(new PropertyValueFactory<>("attendancePear"));
        ObservableList<StudentReport> ids = FXCollections.observableArrayList(list);
        table.setItems(ids);
    }

    public void autoComplete(){
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
            stringBuilder.append(s.getMonadMajor());
            list.add(stringBuilder.toString());
        }
    }

}
