package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

import java.io.IOException;
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

    public void back() {
        nav.navigateTo(root, nav.TEACHING_FXML);
    }

    public void upload() throws IOException {
        nav.upSecen(nav.UPLOAD);
    }

    public void add() {
        if (t_id.getText() != null && lecture_ids.getValue() != null && t_id.getText().length() == 9) {
            if (dm.addAttendance(t_id.getText(), lecture_ids.getValue()) != 0)
                view(dm.getAttendence(lecture_ids.getValue()));
            t_id.clear();
        } else nav.error_message("STUDENT DIDN'T ADD");
    }

    public void updateAttendance() {
        if (t_id.getText().length()==9&&!t_id.getText().equals("         ")&&lecture_ids.getValue()!=null) {
            Navigation.string = t_id.getText() +" "+lecture_ids.getValue();
            nav.upSecen( nav.UPDATE_ATTENDENCE);
        }else nav.error_message("ENTER THE ID FOR STUDENT !!");
    }

    public void delete(ActionEvent actionEvent) {
        if (dm.deleteAttendence(t_id.getText(),lecture_ids.getValue())!=0) {
            nav.message("STUDENT DELETED");
            view(dm.getAttendence(lecture_ids.getValue()));
            t_id.clear();
        }else nav.error_message("STUDENT DIDN'T DELETE !!");
    }

    public void searchAttendance() {
        if(t_id.getText().length()==9&&!t_id.getText().equals("         ")&&lecture_ids.getValue()!=null) {
             view(dm.searchAttendence(t_id.getText(),lecture_ids.getValue()));
        }
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String id =Navigation.id;
        lecture_ids.getItems().addAll(dm.getTeacherLecIds(id).toArray(new String[dm.getTeacherLecIds(id).size()]));
        students = dm.getTeachStu(dm.getTeachCourseID(id));
        table.setOnMouseClicked(mouseEvent ->  {

                if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                    Attendences selectedAttendence = table.getSelectionModel().getSelectedItem();
                    if (selectedAttendence != null) {
                        t_id.setText(selectedAttendence.getStudent_id());
                    }
                }else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && mouseEvent.getClickCount() == 2){
                    Attendences selectedReport = table.getSelectionModel().getSelectedItem();
                    if (selectedReport != null) {
                        Navigation.string =(selectedReport.getStudent_id());
                        updateAttendance();
                    }
                }

        });
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
