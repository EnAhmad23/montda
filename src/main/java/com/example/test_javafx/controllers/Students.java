package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.*;

public class Students implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private  TextField t_id;
    //    @FXML
//    private TextField s_name;
    @FXML
    private TableColumn<Student, String> id;
    @FXML
    private TableColumn<Student, String> name;
    @FXML
    private TableColumn<Student, String> gender;
    @FXML
    private TableColumn<Student, String> place;
    @FXML
    private TableColumn<Student, String> major;
    @FXML
    private TableColumn<Student, String> phone_num;
    //    @FXML
//    private TextField department;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button back;
    @FXML
    private TableView<Student> table;
    @FXML
    private Button id_close_students;
    public static String string = "";
    DBModel dm = DBModel.getModel();
    Navigation nav = new Navigation();



    public void addStudent() {
        nav.navigateTo(root, nav.Add_STUDENT_FXML);
    }

    public void UpdateStudent() {
//        try {
//            Stage stage = new Stage();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(nav.UPDATE_STUDENT));
//            Parent rootP = loader.load();
//
//
////            rootPane.getScene().setRoot(root);
////            Stage stage = (Stage) rootPane.getScene().getWindow();
//            Scene scene = new Scene(rootP);
//            stage.setScene(scene);
//            stage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        string = t_id.getText();
//        System.out.println(string);
        Navigation.string =t_id.getText();
        nav.navigateTo(root, nav.UPDATE_STUDENT);

    }

    public void deleteStudent() {
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label = new Label("STUDENT DELETED !");
        if (dm.delete_Student(t_id.getText()) != 0) {
            view();
            label.setTextFill(Color.color(0, 0, 0));
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("STUDENT DIDN'T DELETE !");
        }
        root.getChildren().add(label);
        stage.setScene(new Scene(root, 300, 100));
        stage.show();
//
//        stage.setScene(new Scene());

    }

    public void enterStudentID() {

    }

    public void enterStudentName() {

    }

    public void enterStudentDepartment() {

    }

    public void Update_back() {
//        if (nav.getCurrentPath().equals(nav.MAIN_FXML))
//            nav.navigateTo(root, nav.MAIN_FXML);
//        else
//            nav.navigateTo(root, nav.TEACHING_FXML);
        nav.navigateTo(root, nav.MAIN_FXML);
    }

    public void view() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        major.setCellValueFactory(new PropertyValueFactory<>("Majer"));
        place.setCellValueFactory(new PropertyValueFactory<>("Place"));
        phone_num.setCellValueFactory(new PropertyValueFactory<>("phone_num"));


        ObservableList<Student> ids = FXCollections.observableArrayList(dm.getStd());
        table.setItems(ids);
    }


    public void viewSearch() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        place.setCellValueFactory(new PropertyValueFactory<>("Place"));
        major.setCellValueFactory(new PropertyValueFactory<>("Majer"));
        phone_num.setCellValueFactory(new PropertyValueFactory<>("phone_num"));

        ObservableList<Student> ids = FXCollections.observableArrayList(dm.searchStudent(t_id.getText()));
        table.setItems(ids);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        view();
    }

    public void autoComplete(){
        ArrayList<String> list = new ArrayList<>();
        for (Student s : dm.getStd()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getId());
            stringBuilder.append(", ");
            stringBuilder.append(s.getName());
            stringBuilder.append(", ");
            stringBuilder.append(s.getGender());
            stringBuilder.append(", ");
            stringBuilder.append(s.getMajer());
            stringBuilder.append(", ");
            stringBuilder.append(s.getPlace());
            stringBuilder.append(", ");
            stringBuilder.append(s.getPhone_num());
            list.add(stringBuilder.toString());
        }
        TextFields.bindAutoCompletion(t_id, list.toArray()).setOnAutoCompleted(event ->t_id.setText(event.getCompletion().toString().substring(0,9)));
    }
    public void searchStudent() {
        if (t_id.getText().isEmpty()) {
            view();
        } else {
            viewSearch();
        }
    }
}
