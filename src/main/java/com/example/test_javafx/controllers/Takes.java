package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Attendences;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.LectureTime;
import com.example.test_javafx.models.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Takes implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    public TableView<Attendences> table;
    @FXML
    public TableColumn<Takes, String> stu_id;
    @FXML
    public TableColumn<Takes, String> name;
    @FXML
    public TableColumn<Takes, String> course;
    ArrayList<Student> students;
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();
    public void back(){}
    public void search(){}
    public void delete(){}
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
            list.add(stringBuilder.toString());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
