package com.example.test_javafx.controllers;
import com.example.test_javafx.models.Student;
import org.controlsfx.control.textfield.TextFields;
import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.TeacherAssistant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeachingAssistant implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<TeacherAssistant> table;
    @FXML
    public TableColumn<TeachingAssistant, String> name;
    @FXML
    public TableColumn<TeachingAssistant, String> id;
    @FXML
    public TableColumn<TeachingAssistant, String> teache;

    @FXML
    private TextField t_id ;
    @FXML
    private Button nav_add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button view;
    DBModel dm = DBModel.getModel();
    Navigation nav = new Navigation();

    public void add_teaching_assist() {
        nav.navigateTo(root, nav.ADD_TEACHER_FXML);
    }

    public void delete_teaching_assist() {

      if (nav.del_message("TEACHING ASSIST DELETED !","TEACHING ASSIST DIDN'T DELETE !",t_id.getText())==1)
          view();
      t_id.clear();


    }

    public void update_teaching_assist() {

        nav.navigateTo(root, nav.UPDATE_TA);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        view();
    }

    public void view() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        teache.setCellValueFactory(new PropertyValueFactory<>("teache"));
        ObservableList<TeacherAssistant> ob = FXCollections.observableArrayList(dm.getTeacherAssistant());
        table.setItems(ob);
    }


    public void back(ActionEvent actionEvent) {
    nav.navigateTo(root,nav.MAIN_FXML);
}
    public void viewUser(ActionEvent actionEvent) {
    }



    public void lectures(ActionEvent actionEvent) {
    }

    public void attendance_page(ActionEvent actionEvent) {
    }
    public void report_statement_page(){}


    public void search(ActionEvent actionEvent) {
    }
}
