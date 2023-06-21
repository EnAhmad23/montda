package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

public class Takes implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    public TableView<Take> table;
    @FXML
    public TableColumn<Take, String> stu_id;
    @FXML
    public TableColumn<Take, String> name;
    @FXML
    public TableColumn<Take, String> course;
    @FXML
    private TextField t_id;

    ArrayList<Take> students;
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        students = dm.getTakes();
        view(students);
        autoValues();
        table.setOnMouseClicked(MouseEvent-> {

            if (MouseEvent.getButton().equals(MouseButton.PRIMARY) && MouseEvent.getClickCount() == 2) {
                Take selectedReport = table.getSelectionModel().getSelectedItem();
                if (selectedReport != null) {
                    t_id.setText(selectedReport.getStu_id());

                }
            }
        });
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().substring(0, 9));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });

    }

    public void back() {
        nav.navigateTo(root, nav.Admin_FXML);
    }
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }
    public void search() {
        if(t_id.getText().length()==9&&!t_id.getText().equals("         ")) {
            view(dm.searchTakes(t_id.getText()));
        }else
            view(students);
    }

    public void delete() {
        if (dm.delete_take(t_id.getText())!=0) {
            nav.message("STUDENT DELETED");
            view(dm.getTakes());
        }
        else
            nav.error_message("STUDENT DIDN'T DELETE");
    }

    public void autoComplete() {
        autoValues();
    }

    void autoValues() {
        list = new ArrayList<>();
        for (Take s : students) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getStu_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getStu_name());
            stringBuilder.append(", ");
            stringBuilder.append(s.getCourse_id());
            list.add(stringBuilder.toString());
        }
    }


    public void view(ArrayList<Take> lectureTimes) {
        stu_id.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("stu_name"));
        course.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        ObservableList<Take> ids = FXCollections.observableArrayList(lectureTimes);
        table.setItems(ids);
    }

    public void add() {
        nav.navigateTo(root,nav.ADD_TAKES_FXML);

    }
}
