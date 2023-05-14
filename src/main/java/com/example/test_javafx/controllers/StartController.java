package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.postgresql.ds.PGSimpleDataSource;

import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    public void navSections() {
        nav.navigateTo(rootPane, nav.SECTIONs_FXML);
    }
    public void navUsers() {
        nav.navigateTo(rootPane, nav.Users_FXML);
    }

    public void navToLecturesTimes() {
        nav.navigateTo(rootPane, nav.LECTURES_TIMES_FXML);
    }

    public void navToStudent() {
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
