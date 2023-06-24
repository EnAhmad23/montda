package com.example.test_javafx;

import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    public final String MAIN_FXML = "views/start.fxml";
    public final String absence_FXML = "views/Absent.fxml";
    public final String ADD_COURSE_FXML = "views/addNewSection.fxml";
    public final String ADD_TEACHER_FXML = "views/addTeacher.fxml";
    public final String LECTURES_TIMES_FXML = "views/lecturestimes.fxml";
    public final String ADD_LECTURES_FXML = "views/addLectureTime.fxml";
    public final String ADD_TAKES_FXML = "views/addTakes.fxml";

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
    public final String TAKES = "views/Takes.fxml";

    public final String UPDATE_ATTENDENCE = "views/UpdateAttendence.fxml";
    public final String UPDATE_COURSE = "views/UpdateCourse.fxml";
    public final String UPDATE_LECTURE = "views/UpdateLecture.fxml";
    public final String UPDATE_STUDENT = "views/updateStudent.fxml";


    public final String UPDATE_TA = "views/UpdateTA.fxml";
    public final String REPORT_PAGE = "views/reports.fxml";
    public final String REPORT_LECTURE = "views/reportLecture.fxml";
    public final String REPORT_STUDENT = "views/reportStudent.fxml";

    public static String string = "";
    public static String id = "";
    DBModel dm = DBModel.getModel();
    public static String owner;


    public void navigateTo(Parent rootPane, String path) {
        try {
            Stage stage =new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
//            currentPath = path ;
//            rootPane.getScene().setRoot(root);
            stage = (Stage) rootPane.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                try {
                   if( dm.backupDatabase("backups/")==0)
                       System.out.println("Backup Successfully");
                   else System.out.println("0");
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            });
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void upSecen( String path) throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("University");
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/uni.jpg"))));
        stage.setScene(scene);
        stage.show();


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
    public int message(String string) {
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label = new Label();
        label.setTextFill(Color.color(0, 0, 0));
        label.setText(string);
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
