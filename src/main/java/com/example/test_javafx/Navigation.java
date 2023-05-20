package com.example.test_javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    public final String MAIN_FXML = "views/start.fxml";
    public final String ADD_SECTION_FXML = "views/addNewSection.fxml";
    public final String SECTIONs_FXML = "views/Sections.fxml";
    public final String LECTURES_TIMES_FXML = "views/lecturestimes.fxml";

    public final String Add_STUDENT_FXML = "views/addStudent.fxml";
    public final String STUDENTS_FXML = "views/Students.fxml";
    public final String Admin_FXML = "views/start.fxml";
    public final String Users_FXML = "views/users.fxml";
    public final String ADD_USER_FXML = "views/addUser.fxml";

    public final String ATTENDENCE_FXML = "views/attendence.fxml";
    public final String TEACHING_FXML = "views/teachingAssistant.fxml";
    public final String UPLOAD = "views/upload.fxml";
    public final String LOGIN = "views/login.fxml";
    public final String COURSES = "views/course.fxml";
    private String currentPath = "";



    public void navigateTo(Parent rootPane, String path) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            currentPath = path ;
//            rootPane.getScene().setRoot(root);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentPath() {
        return currentPath;
    }


}
