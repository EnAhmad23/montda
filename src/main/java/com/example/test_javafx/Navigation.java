package com.example.test_javafx;

import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    public final String MAIN_FXML = "views/start.fxml";
    public final String ADD_COURSE_FXML = "views/addNewSection.fxml";
    public final String ADD_TEACHER_FXML = "views/addTeacher.fxml";
    public final String LECTURES_TIMES_FXML = "views/lecturestimes.fxml";
    public final String ADD_LECTURES_FXML = "views/addLectureTime.fxml";

    public final String Add_STUDENT_FXML = "views/addStudent.fxml";
    public final String STUDENTS_FXML = "views/Students.fxml";
    public final String Admin_FXML = "views/start.fxml";
    public final String USERS_FXML = "views/users.fxml";
    public final String ADD_USER_FXML = "views/addUser.fxml";

    public final String ATTENDENCE_FXML = "views/attendence.fxml";
    public final String TEACHING_FXML = "views/teachingAssistant.fxml";
    public final String UPLOAD = "views/upload.fxml";
    public final String LOGIN = "views/login.fxml";
    public final String COURSES = "views/course.fxml";

    public final String UPDATE_ATTENDENCE = "views/UpdateAttendence.fxml";
    public final String UPDATE_COURSE = "views/UpdateCourse.fxml";
    public final String UPDATE_LECTURE = "views/UpdateLecture.fxml";
    public final String UPDATE_STUDENT = "views/updateStudent.fxml";
    public final String UPDATE_TA = "views/UpdateTA.fxml";
    public final String REPORT_ATTENDANCE = "views/ReportAttendance.fxml";
    public final String REPORT_STUDENT = "views/ReportStudent.fxml";

    public static String string = "";
    DBModel dm = DBModel.getModel();


    public void navigateTo(Parent rootPane, String path) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
//            currentPath = path ;
//            rootPane.getScene().setRoot(root);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int del_message(String done, String error, String id) {
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label = new Label(done);
        if (dm.delete_teacher_assistant(id) != 0) {
            label.setTextFill(Color.color(0, 0, 0));
            root.getChildren().add(label);
            stage.setScene(new Scene(root, 300, 100));
            stage.show();
            return 1;

        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText(error);

        }

        root.getChildren().add(label);
        stage.setScene(new Scene(root, 300, 100));
        stage.show();
        return 0;
    }

    public int error_message(String error) {
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label = new Label();
        label.setTextFill(Color.color(1, 0, 0));
        label.setText(error);
        root.getChildren().add(label);
        stage.setScene(new Scene(root, 300, 100));
        stage.show();
        return 0;
    }


    public String getCurrentPath() {
//        return currentPath;
        return null;
    }


}
