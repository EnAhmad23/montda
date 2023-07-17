package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Reports {

    @FXML
    private AnchorPane root;
    public Button id_back_stuOP;
    public Button studentReport;
    public Button lectureReports;
    Navigation nav = new Navigation();


    public void back_stuOP() {
        nav.navigateTo(root, nav.MAIN_FXML);
    }
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back_stuOP();
        }
    }

    public void navToStudentReports() {
        nav.navigateTo(root,nav.REPORT_STUDENT);
    }
    public void navToAbsence() {
        nav.navigateTo(root,nav.absence_FXML);
    }

    public void navToLectureReports() {
        nav.navigateTo(root,nav.REPORT_LECTURE);
    }
}
