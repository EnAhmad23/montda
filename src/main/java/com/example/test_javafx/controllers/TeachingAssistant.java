package com.example.test_javafx.controllers;
import com.example.test_javafx.models.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import com.example.test_javafx.Navigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    ArrayList<TeacherAssistant>teachers;

    private AutoCompletionBinding<Object> autoCompletionBinding;
   private ArrayList<String> list = new ArrayList<>();

    public void add_teaching_assist() {
        nav.navigateTo(root, nav.ADD_TEACHER_FXML);
    }

    public void delete_teaching_assist() {

      if (nav.del_message("TEACHING ASSIST DELETED !","TEACHING ASSIST DIDN'T DELETE !",t_id.getText())==1) {
          view();
          del(t_id.getText());
         nav.navigateTo(root,nav.USERS_FXML);
      }
      t_id.clear();


    }
    void del(String s) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(s)) {
//                list.remove(i);
                System.out.println(teachers.remove(i));
            }
        }
    }
    public void update_teaching_assist() {
        if (t_id.getText().length()==9&& (!t_id.getText().equals("         "))) {
            Navigation.string = t_id.getText();
            nav.upSecen(nav.UPDATE_TA);
        }else
            nav.error_message("ENTER THE ID FOR  THE COURSE !!");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       teachers =dm.getTeacherAssistant();
        view();
        autoValues();
        table.setOnMouseClicked(MouseEvent-> {

            if (MouseEvent.getButton().equals(MouseButton.PRIMARY) && MouseEvent.getClickCount() == 2) {
                TeacherAssistant selectedReport = table.getSelectionModel().getSelectedItem();
                if (selectedReport != null) {
                    t_id.setText(selectedReport.getId());

                }
            }else if (MouseEvent.getButton().equals(MouseButton.SECONDARY) && MouseEvent.getClickCount() == 2){
                TeacherAssistant selectedReport = table.getSelectionModel().getSelectedItem();
                if (selectedReport != null) {
                    Navigation.string =(selectedReport.getId());
                    update_teaching_assist();
                }
            }
        });
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().substring(0,9));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });
    }
    public void autoComplete(){
        autoValues();

    }
    void autoValues(){
        list = new ArrayList<>();
        for (TeacherAssistant s : teachers) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getId());
            stringBuilder.append(", ");
            stringBuilder.append(s.getName());
            stringBuilder.append(", ");
            stringBuilder.append(s.getTeach());
            list.add(stringBuilder.toString());
        }
    }

    public void view() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        teache.setCellValueFactory(new PropertyValueFactory<>("teach"));
        ObservableList<TeacherAssistant> ob = FXCollections.observableArrayList(dm.getTeacherAssistant());
        table.setItems(ob);
    }

    public void viewSearch(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        teache.setCellValueFactory(new PropertyValueFactory<>("teach"));
        ObservableList<TeacherAssistant> ob = FXCollections.observableArrayList(dm.searchTeacher(id.getText()));
        table.setItems(ob);
    }


    public void back() {
    nav.navigateTo(root,nav.MAIN_FXML);
}

    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }



    public void students(){}



    public void searchTA(){
        if (t_id.getText().isEmpty()) {
            view();
        } else {
            viewSearch();
        }
    }
}
