package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    private final String url = "jdbc:postgresql://localhost/UNI1";
    private final String adminUser = "admin";
    private final String adminPassword = "admin";
    @FXML
    private AnchorPane root;
    @FXML
    private TextField userID;
    @FXML
    private TextField password;
    @FXML
    private Button login;

    @FXML
    private Label label;
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    public void checkAdmin() {
        if (userID.getText().equals(adminUser) && password.getText().equals(adminPassword))
            nav.navigateTo(root, nav.MAIN_FXML);
        else if (dm.login(userID.getText(),password.getText())) {
            nav.navigateTo(root,nav.TEACHING_FXML);
            Navigation.id=userID.getText();
        } else label.setText(" WRONG USER NAME OR PASSWORD ");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }



    public void handleKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            checkAdmin();
        }
    }
}
