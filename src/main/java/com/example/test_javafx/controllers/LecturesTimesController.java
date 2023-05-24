package com.example.test_javafx.controllers;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.LectureTime;
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

import java.net.URL;
import java.util.ResourceBundle;

public class LecturesTimesController implements Initializable {
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
    public TableColumn<LectureTime, String> time_slot;
    @FXML
    public TableColumn<LectureTime, String> title;
    @FXML
    public AnchorPane root;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        view();
    }

    public void back() {
        nav.navigateTo(root, nav.MAIN_FXML);
    }

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

    public void viewTimes() {
        if (id.getText() != null && sem.getValue() != (null)
                && year.getValue() != null) {
            if (student.isSelected())
                table.setItems(FXCollections.observableArrayList(
                        db.getStdLectures(id.getText(), sem.getValue(),
                                Integer.parseInt(year.getValue()))));
            else
                table.setItems(FXCollections.observableArrayList(
                        db.getInstructorLectures(id.getText(), sem.getValue(),
                                Integer.parseInt(year.getValue()))));
        } else System.err.println("Error! fill fields");
    }


    public void studentId_lecture(){}
    public void studentName_lecture(){}
    public void studentDepartment_lecture(){}
    public void view(){
        id.setCellValueFactory(new PropertyValueFactory<>("lecture_id"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("Course_id"));
        room.setCellValueFactory(new PropertyValueFactory<>("Room_number"));
        time_slot.setCellValueFactory(new PropertyValueFactory<>("Time_slot"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        ObservableList<LectureTime> lectures = FXCollections.observableArrayList(db.getLectures());
        table.setItems(lectures);
    }

    public void delete_button(){
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label =new Label("LECTURE DELETED !");
        if (db.delete_lecture(t_id.getText())!=0) {
            view();
            label.setTextFill(Color.color(0,0,0));
        }else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("LECTURE DIDN'T DELETE !");
        }
        t_id.clear();
        root.getChildren().add(label);
        stage.setScene(new Scene(root, 300, 100));
        stage.show();

    }
    public void update_button(){

    }
    public void add_button(){
        nav.navigateTo(root,nav.ADD_LECTURES_FXML);

    }

    public void SelectYear(){}
}
