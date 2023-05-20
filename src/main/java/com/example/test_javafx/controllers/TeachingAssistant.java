package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.TeacherAssistant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TeachingAssistant implements Initializable {
    @FXML
    private TableView <TeacherAssistant> table ;
    @FXML
    public TableColumn <TeachingAssistant,String> name;
    @FXML
    public TableColumn <TeachingAssistant,String> id;
    @FXML
    public TableColumn <TeachingAssistant,String> teache;
    @FXML
    public TableColumn <TeachingAssistant,String> advisor;

    @FXML
    private AnchorPane root;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button view;
    DBModel dm =  DBModel.getModel();
    Navigation nav = new Navigation();
    public void addUser(){
        nav.navigateTo(root,nav.ADD_USER_FXML);
    }
    public void deleteUser(){
        nav.navigateTo(root,nav.LECTURES_TIMES_FXML);
    }
    public void updateUser(){
        nav.navigateTo(root,nav.ATTENDENCE_FXML);
    }


    public void report_statement_page(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        teache.setCellValueFactory(new PropertyValueFactory<>("teache"));
        advisor.setCellValueFactory(new PropertyValueFactory<>("advisor"));
        ObservableList<TeacherAssistant> ob= FXCollections.observableArrayList(dm.getTeacherAssistant());
        table.setItems(ob);

    }

    public void back(ActionEvent actionEvent) {
        nav.navigateTo(root,nav.MAIN_FXML);
    }

    public void viewUser(ActionEvent actionEvent) {
    }

    public void students(ActionEvent actionEvent) {
    }

    public void lectures(ActionEvent actionEvent) {
    }

    public void attendance_page(ActionEvent actionEvent) {
    }
}
