package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class Teach implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    public TableView<Teacher> table;
    @FXML
    public TableColumn<Teacher, String> id;
    @FXML
    public TableColumn<Teacher, String> name;
    @FXML
    public TableColumn<Teacher, String> course_id;
    @FXML
    public TableColumn<Teacher, String> course_name;
    @FXML
    private TextField t_id;

    ArrayList<Teacher> teachers;
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teachers = dm.getTeach();
        view(teachers);
        autoValues();
        table.setOnMouseClicked(MouseEvent -> {

            if (MouseEvent.getButton().equals(MouseButton.PRIMARY) && MouseEvent.getClickCount() == 2) {
                Teacher selectedReport = table.getSelectionModel().getSelectedItem();
                if (selectedReport != null) {
                    t_id.setText(selectedReport.getTea_id());

                }
            }
        });
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().split(",")[0]);
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
        if (dm.checkTeacherID(t_id.getText())) {
            view(dm.searchTeach(t_id.getText()));
        } else
            view(teachers);
    }

    public void refresh() {
        initialize(null, null);
    }

    public void delete() {
        if (dm.delete_take(t_id.getText()) != 0) {
            nav.message("STUDENT DELETED");
            view(dm.getTeach());
        } else
            nav.error_message("STUDENT DIDN'T DELETE");
    }

    public void autoComplete() {
        autoValues();
    }

    void autoValues() {
        list = new ArrayList<>();
        for (Teacher s : teachers) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getTea_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getTea_name());
            stringBuilder.append(", ");
            stringBuilder.append(s.getCourse_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getCourse_name());
            list.add(stringBuilder.toString());
        }
    }


    public void view(ArrayList<Teacher> lectureTimes) {
        id.setCellValueFactory(new PropertyValueFactory<>("tea_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("tea_name"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        course_name.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        ObservableList<Teacher> ids = FXCollections.observableArrayList(lectureTimes);
        table.setItems(ids);
    }

    public void add() {
        nav.upSecen(nav.ADD_TEACHER_FXML);

    }
}
