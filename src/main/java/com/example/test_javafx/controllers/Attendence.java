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
import javafx.scene.control.ComboBox;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Attendence implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField t_id;
    @FXML
    private ComboBox<String> course_ids;

    public TableView<Attendences> table;
    @FXML
    public TableColumn<Attendences, String> stu_id;
    @FXML
    public TableColumn<Attendences, String> course_name;

    @FXML
    public TableColumn<Attendences, Date> date;

    @FXML
    public TableColumn<Attendences, String> course;
    @FXML
    public TableColumn<Attendences, String> name;
    ArrayList<Student> students;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    public void back() {
        nav.navigateTo(root, nav.TEACHING_FXML);
    }

    public void upload() throws IOException {
        nav.upSecen(nav.UPLOAD);
    }

    public void add() {
        if (dm.checkStudentID(t_id.getText()) && course_ids.getValue() != null ) {
            if (dm.addAttendance(t_id.getText(), course_ids.getValue()) != 0)
                view(dm.getAttendence());
            t_id.clear();
        } else nav.error_message("STUDENT DIDN'T ADD");
    }

    public void updateAttendance() {
        if (t_id.getText().length() == 9 && !t_id.getText().equals("         ") && course_ids.getValue() != null) {
            Navigation.string = t_id.getText() + " " + course_ids.getValue();
            nav.upSecen(nav.UPDATE_ATTENDENCE);
        } else nav.error_message("ENTER THE ID FOR STUDENT !!");
    }

    public void delete(ActionEvent actionEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String [] s=t_id.getText().split(",");
        LocalDate date = LocalDate.parse(s[2], formatter);
        if (dm.deleteAttendence(s[0], course_ids.getValue(), Date.valueOf(date)) != 0) {
            nav.message("STUDENT DELETED");
            view(dm.getAttendence());
            t_id.clear();
        } else nav.error_message("STUDENT DIDN'T DELETE !!");
    }

    public void searchAttendance() {
        if (t_id.getText().length() == 9 && !t_id.getText().equals("         ") && course_ids.getValue() != null) {
            view(dm.searchAttendence(t_id.getText(), course_ids.getValue()));
        }
    }

    public void view(ArrayList<Attendences> lectureTimes) {
        stu_id.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        course.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        course_name.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        ObservableList<Attendences> ids = FXCollections.observableArrayList(lectureTimes);
        table.setItems(ids);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String id = Navigation.id;
        course_ids.getItems().addAll(dm.getCourseIDs().toArray(new String[dm.getCourseIDs().size()]));
        students = dm.getStd();
        table.setOnMouseClicked(mouseEvent -> {

            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                Attendences selectedAttendence = table.getSelectionModel().getSelectedItem();
                if (selectedAttendence != null) {
                    t_id.setText(selectedAttendence.getStudent_id());
                }
            } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && mouseEvent.getClickCount() == 2) {
                Attendences selectedReport = table.getSelectionModel().getSelectedItem();
                if (selectedReport != null) {
                    Navigation.string = (selectedReport.getStudent_id());
                    updateAttendance();
                }
            }

        });
        course_ids.setOnAction(e -> {
            if (course_ids.getValue() != null) {
//                attendences = dm.getAttendence(lecture_ids.getValue());

                view(dm.getAttendence());

            } else {
                nav.error_message("SELECT LECTURE ID");
            }
        });
        autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().split(",")[0]);
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
            stringBuilder.append(s.getMonadMajor());
            list.add(stringBuilder.toString());
        }
    }

    public void handleKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            add();
        }
    }

    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }

}
