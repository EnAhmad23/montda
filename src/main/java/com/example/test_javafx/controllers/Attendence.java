package com.example.test_javafx.controllers;

import com.example.test_javafx.DataBus;
import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Attendences;
import com.example.test_javafx.models.DBModel;
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
        nav.navigateTo(root, nav.MAIN_FXML);
    }

    public void upload() throws IOException {
        nav.upSecen(nav.UPLOAD);
    }

    public void add() {
        String studentId = t_id.getText();
        String selectedCourse = course_ids.getValue();

        if (dm.checkStudentID(studentId) && selectedCourse != null) {
            if (dm.isTakes(studentId, selectedCourse)) {
                double currentDayHours = dm.getStu_Atten_houres_day(studentId, Date.valueOf(LocalDate.now()));
                double requiredHoursDaily = dm.gethours_required_daily(studentId);
                double courseHours = dm.getCourseHoures(selectedCourse);
                System.err.println("1");
                if (currentDayHours < requiredHoursDaily && currentDayHours + courseHours >= requiredHoursDaily) {
                    System.err.println(dm.updateDays_of_attendance(studentId, Date.valueOf(LocalDate.now())));
                    System.err.println('0');
                }

                if (dm.addAttendance(studentId, selectedCourse) != 0) {
                    LocalDate localDate = LocalDate.now();
                    view(dm.getAttendence(selectedCourse));
//                    System.err.println(dm.updateDays_of_attendance(studentId, Date.valueOf(localDate)));
                }
            } else {
                nav.error_message("STUDENT DIDN'T TAKES THIS COURSE");
            }

            t_id.clear();
        } else {
            nav.error_message("STUDENT DIDN'T ADD");
        }
    }


    public void updateAttendance() {
        String input =(!t_id.getText().isEmpty())? t_id.getText():" ";
        String id = (input.contains(","))?input.split(",")[0]:input;
        if (dm.checkStudentID(id)&& course_ids.getValue() != null) {
            DataBus.data.add(id);
            DataBus.data.add(course_ids.getValue());
            nav.upSecen(nav.UPDATE_ATTENDENCE);
        } else nav.error_message("ENTER THE ID FOR STUDENT !!");
    }

    public void delete(ActionEvent actionEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String studentId = t_id.getText();
        String courseId = course_ids.getValue();

        if (isInputValid(studentId, courseId)) {
            LocalDate date = LocalDate.parse(studentId.split(",")[2], formatter);

            double studentAttendanceHours = dm.getStu_Atten_houres_day(studentId, Date.valueOf(date));
            double courseHours = dm.getCourseHoures(courseId);
            double requiredHours = dm.gethours_required_daily(studentId);

            if (studentAttendanceHours > requiredHours && studentAttendanceHours - courseHours < requiredHours) {
                dm.updateDays_delete(studentId, Date.valueOf(date));
            }

            if (dm.deleteAttendence(studentId, courseId, Date.valueOf(date)) != 0) {
                nav.message("STUDENT DELETED");
                view(dm.getAttendence(courseId));
                t_id.clear();
            } else {
                nav.error_message("STUDENT DIDN'T DELETE !!");
            }
        } else {
            nav.error_message("ENTER THE ID FOR STUDENT !!");
        }
    }

    private boolean isInputValid(String studentId, String courseId) {
        return dm.checkStudentID(studentId) && courseId != null;
    }


    public void searchAttendance() {
        if (dm.checkStudentID(t_id.getText()) && course_ids.getValue() != null) {
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
                    t_id.clear();
                    t_id.setText(selectedAttendence.getStudent_id()+","+selectedAttendence.getCourse_id()+","+selectedAttendence.getDate().toString());
                }
            } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && mouseEvent.getClickCount() == 2) {
                Attendences selectedReport = table.getSelectionModel().getSelectedItem();
                if (selectedReport != null) {
                    DataBus.data.add(selectedReport.getStudent_id());
                    DataBus.data.add(selectedReport.getCourse_id());
                    updateAttendance();
                }
            }

        });
        course_ids.setOnAction(e -> {
            if (course_ids.getValue() != null) {
//                attendences = dm.getAttendence(lecture_ids.getValue());

                view(dm.getAttendence(course_ids.getValue()));

            } else {
                nav.error_message("SELECT COURSE ID");
            }
        });
        autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> t_id.setText(event.getCompletion().toString().split(",")[0]));

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
