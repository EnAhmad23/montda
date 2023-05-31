package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddTakes implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button id_back;
    @FXML
    private Button add;
    @FXML
    private TextField id;
    @FXML
    private ComboBox<String> course_id;
    @FXML
    private Label label;
    ArrayList<String> students;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    public void back(ActionEvent actionEvent) {
        nav.navigateTo(root, nav.TAKES);
    }

    public void addTakes() {
        if (dm.addTake(id.getText(), course_id.getValue()) != 0) {
            label.setTextFill(Color.color(0, 1, 0));
            label.setText("TAKES ADDED SUCCESSFULLY");
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("TAKES DIDN'T ADD");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        course_id.getItems().addAll(dm.getCourseIDs().toArray(new String[dm.getCourseIDs().size()]));
        students = dm.getStdIds();
        autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            id.setText(event.getCompletion().toString().substring(0, 9));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });
    }

    public void autoComplete() {
        autoValues();

    }

    void autoValues() {
        list = new ArrayList<>();
        for (String s : students) {
            list.add(s);
        }
    }
}
