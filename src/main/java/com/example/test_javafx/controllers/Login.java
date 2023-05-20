package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    private final String url = "jdbc:postgresql://localhost/UNI1";
    private final String user = "admin";
    private final String password = "admin";
    @FXML
    private AnchorPane root;
    @FXML
    private TextField userName ;
    @FXML
    private TextField passWord;
    @FXML
    private Button login;
    @FXML
    private Button id_close_login;
    @FXML
    private Label label;
    Navigation nav = new Navigation();

    public void checkAdmin() {
        if (userName.getText().equals(user) && passWord.getText().equals(password))
            nav.navigateTo(root, nav.TEACHING_FXML);
        else label.setText(" WRONG USER NAME OR PASSWORD ");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void Username_Login() {}
    public void Password_Login() {}
    public void close_login() {}
}
