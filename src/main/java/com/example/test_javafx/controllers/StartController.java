package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartController implements Initializable {


    @FXML
    public AnchorPane rootPane;




    @FXML
    public Button nav_back;

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    public void navCourses() throws IOException {
        nav.navigateTo(rootPane, nav.COURSES);


    }
    public void navUsers() {
        nav.navigateTo(rootPane, nav.USERS_FXML);
    }

    public void navToLecturesTimes() {
        Navigation.owner =(nav.MAIN_FXML);
        nav.navigateTo(rootPane, nav.LECTURES_TIMES_FXML);
    }

    public void navToStudent() {
        Navigation.owner =(nav.MAIN_FXML);
        nav.navigateTo(rootPane, nav.STUDENTS_FXML);
    }

    public void takes(){
        nav.navigateTo(rootPane, nav.TAKES);
    }

    public void back(){nav.navigateTo(rootPane, nav.LOGIN);}
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }




}
