package com.example.test_javafx.controllers;

import com.example.test_javafx.models.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import com.example.test_javafx.Navigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LecturesTimesController implements Initializable {
    @FXML
    public AnchorPane root;

    @FXML
    private Button add;

    @FXML
    private Button update;
    @FXML
    private Button delete;

    public TableView<LectureTime> table;
    @FXML
    public TableColumn<LectureTime, String> id;

    @FXML
    public TableColumn<LectureTime, String> course_id;
    @FXML
    public TableColumn<LectureTime, String> room;

    @FXML
    public TableColumn<LectureTime, String> title;

    @FXML
    public TextField t_id;
    @FXML
    public TextField instructorID;

    @FXML
    public ComboBox<String> year;
    @FXML
    public ComboBox<String> sem;
    @FXML
    public CheckBox student;
    @FXML
    public Button id_back_addStu;
    public Button view;
    DBModel db = DBModel.getModel();
    Navigation nav = new Navigation();
    ArrayList<LectureTime> lectures ;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lectures=db.getLectures();
        view();
        autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().substring(0, 5));
        });
    }

    public void back() {
        nav.navigateTo(root, Navigation.owner);
    }

//    public void addLectureTime(){
//
//    }

    @FXML
    public void onIDEnter() {
        if (!id.getText().equals("")) {
            if (student.isSelected()) {
                instructorID.setText(db.getStdName(id.getText()));
                room.setText(db.getStdDept(id.getText()));
                ObservableList<String> years = FXCollections.observableList(db.getStdYears(id.getText()));
                year.setItems(years);
            } else {
                instructorID.setText(db.getInstructorName(id.getText()));
                room.setText(db.getInstructorDept(id.getText()));
                ObservableList<String> years = FXCollections.observableList(db.getInstructorYears(id.getText()));
                year.setItems(years);
            }
        }
    }
    //

    public void upCombSems() {

        if (year.getValue() != null) {
            if (student.isSelected()) {
                ObservableList<String> sems = FXCollections.observableList(db.getStdSems(id.getText(),
                        Integer.parseInt(year.getValue())));
                sem.setItems(sems);
            } else {
                ObservableList<String> sems = FXCollections.observableList(db.getInstructorSems(id.getText(),
                        Integer.parseInt(year.getValue())));
                sem.setItems(sems);
            }
        } else sem.setItems(null);
    }

//    public void viewTimes() {
//        if (id.getText() != null && sem.getValue() != (null)
//                && year.getValue() != null) {
//            if (student.isSelected())
//                table.setItems(FXCollections.observableArrayList(
//                        db.getStdLectures(id.getText(), sem.getValue(),
//                                Integer.parseInt(year.getValue()))));
//            else
//                table.setItems(FXCollections.observableArrayList(
//                        db.getInstructorLectures(id.getText(), sem.getValue(),
//                                Integer.parseInt(year.getValue()))));
//        } else System.err.println("Error! fill fields");
//    }


    public void viewTimes() {
        if (t_id.getText().isEmpty()) {
            view();
        } else
            viewSearch();

//        if (id.getText() != null && sem.getValue() != (null)
//                && year.getValue() != null) {
//            if (student.isSelected())
//                table.setItems(FXCollections.observableArrayList(
//                        db.getStdLectures(id.getText(), sem.getValue(),
//                                Integer.parseInt(year.getValue()))));
//            else
//                table.setItems(FXCollections.observableArrayList(
//                        db.getInstructorLectures(id.getText(), sem.getValue(),
//                                Integer.parseInt(year.getValue()))));
//        } else System.err.println("Error! fill fields");

//        public void viewTimes(){


    }

    public void viewSearch() {
        id.setCellValueFactory(new PropertyValueFactory<>("lecture_id"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("Course_id"));
        room.setCellValueFactory(new PropertyValueFactory<>("Room_number"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        ObservableList<LectureTime> lectures = FXCollections.observableArrayList(db.searchLecture(t_id.getText()));
        table.setItems(lectures);
    }

    public void view() {
        id.setCellValueFactory(new PropertyValueFactory<>("lecture_id"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("Course_id"));
        room.setCellValueFactory(new PropertyValueFactory<>("Room_number"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        ObservableList<LectureTime> lectures = FXCollections.observableArrayList(db.getLectures());
        table.setItems(lectures);
    }

    public void studentId_lecture() {
    }

    public void studentName_lecture() {
    }

    public void studentDepartment_lecture() {
    }

    public void delete_button() {
        Stage stage = new Stage();
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label = new Label("LECTURE DELETED !");
        if (db.delete_lecture(t_id.getText()) != 0) {
            view();
            del(t_id.getText());
           nav.navigateTo(root,nav.LECTURES_TIMES_FXML);
            label.setTextFill(Color.color(0, 0, 0));
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("LECTURE DIDN'T DELETE !");
        }
        lectures=db.getLectures();
        t_id.clear();
        vBox.getChildren().add(label);
        stage.setScene(new Scene(vBox, 300, 100));
        stage.show();

    }

    void del(String s) {
        for (int i = 0; i < lectures.size(); i++) {
            if (lectures.get(i).getLecture_id().equals(s)) {
//                list.remove(i);
                System.out.println(lectures.remove(i));
            }
        }
    }

    public void update_button() {
        if (t_id.getText().length() == 5) {
            Navigation.string = t_id.getText();
            nav.navigateTo(root, nav.UPDATE_LECTURE);
        } else
            nav.error_message("ENTER THE ID FOR  THE LECTURE !!");
    }

    public void add_button() {
        nav.navigateTo(root, nav.ADD_LECTURES_FXML);

    }

    public void autoComplete() {

autoValues();
    }
    void autoValues(){
        list = new ArrayList<>();
        for (LectureTime s : lectures) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getLecture_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getCourse_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getRoom_number());
            stringBuilder.append(", ");
            stringBuilder.append(s.getTitle());
            list.add(stringBuilder.toString());
        }
    }
    public void SelectYear() {
    }
}
