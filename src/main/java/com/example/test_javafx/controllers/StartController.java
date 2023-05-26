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
    public Button nav_add_section;
    @FXML
    public Button users ;
    // nav_add_section,nav_lectures_times,nav_enroll_student
    @FXML
    public Button nav_lectures_times;
    @FXML
    public Button nav_student;
    @FXML
    public Button id_close_admin;
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(dm.getStdName("ahmad"));

    }
//    private void handleButtonAction (ActionEvent event) throws Exception {
//
//        Parent root = null;
//
//        if(event.getSource()==nav_student){
//            stage = (Stage) nav_student.getScene().getWindow();
//            root = FXMLLoader.load(getClass().getResource(nav.SECTIONs_FXML));
//        }
//
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
    public void navSections() throws IOException {
        nav.navigateTo(rootPane, nav.COURSES);
//        Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource(".../views/Sections.fxml")));
//        Stage stage = (Stage) nav_add_section.getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

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

    public void close_admin(){}
    public Connection connect() {
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName("localhost");
        source.setDatabaseName("UNI1");
        source.setUser("postgres");
        source.setPassword("bohboq20");
        source.setCurrentSchema("uni-space");
        Connection con = null;
        try {
            con = source.getConnection();

            DatabaseMetaData metadata = con.getMetaData();
            System.out.println("DBMS Name: " + metadata.getDatabaseProductName());
            System.out.println("DBMS Version: " + metadata.getDatabaseProductVersion());
            System.out.println("Logged User: " + metadata.getUserName());
            System.out.println("JDBC Driver: " + metadata.getDriverName());
            System.out.println("Driver Version: " + metadata.getDriverVersion());
            System.out.println("Server URL: " + metadata.getURL());
            System.out.println("Max connections: " + metadata.getMaxConnections());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con;  // will return Connection object

    }


}
