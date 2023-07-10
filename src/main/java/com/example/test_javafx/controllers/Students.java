package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Students implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField t_id;
    //    @FXML
//    private TextField s_name;
    @FXML
    private TableColumn<Student, String> id;
    @FXML
    private TableColumn<Student, String> name;
    @FXML
    private TableColumn<Student, String> place;
    @FXML
    private TableColumn<Student, String> major;
    @FXML
    private TableColumn<Student, String> phone_num;
    //    @FXML
    //    private TextField department;

    @FXML
    private TableView<Student> table;

    public static String string = "";
    DBModel dm = DBModel.getModel();
    Navigation nav = new Navigation();
    ArrayList<Student> students;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();

    public void addStudent() {
        nav.upSecen( nav.Add_STUDENT_FXML);
    }

    public void updateStudent() throws IOException {
        if (t_id.getText().length() == 9 && (!t_id.getText().equals("         "))) {
            Navigation.string = t_id.getText();
            nav.upSecen(nav.UPDATE_STUDENT);
//            nav.navigateTo(root,nav.UPDATE_STUDENT);
        } else
            nav.error_message("ENTER THE ID FOR THE STUDENT !!");
    }

    public void deleteStudent() {
        Stage stage = new Stage();
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label = new Label("STUDENT DELETED !");
        if (dm.delete_Student(t_id.getText()) != 0) {
            view(dm.getStd());
            del(t_id.getText());
            nav.navigateTo(root, nav.STUDENTS_FXML);
            label.setTextFill(Color.color(0, 0, 0));
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("STUDENT DIDN'T DELETE !");
        }
        t_id.clear();
        vBox.getChildren().add(label);
        stage.setScene(new Scene(vBox, 300, 100));
        stage.show();
//
//        stage.setScene(new Scene());

    }

    void del(String s) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(s)) {
                students.remove(i);
            }
        }
    }



    public void view(ArrayList<Student> arrayList) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        major.setCellValueFactory(new PropertyValueFactory<>("Majer"));
        place.setCellValueFactory(new PropertyValueFactory<>("Place"));
        phone_num.setCellValueFactory(new PropertyValueFactory<>("phone_num"));


        ObservableList<Student> ids = FXCollections.observableArrayList(arrayList);
        table.setItems(ids);
    }
    public void update() {

        nav.navigateTo(root, Navigation.owner);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        students = dm.getStd();
        view(students);
        autoValues();

        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().substring(0, 9));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });
    }

    public void select(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
            Student selectedReport = table.getSelectionModel().getSelectedItem();
            if (selectedReport != null) {
                t_id.setText(selectedReport.getId());

            }
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && mouseEvent.getClickCount() == 2){
                Student selectedReport2 = table.getSelectionModel().getSelectedItem();
                if (selectedReport2 != null) {
                    Navigation.string =(selectedReport2.getId());
                    try {
                        updateStudent();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
//                    update();
                }
            }
        }

    }

    public void autoComplete() {
        autoValues();

    }

    void autoValues() {
        list = new ArrayList<>();
        for (Student s : students) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getId());
            stringBuilder.append(", ");
            stringBuilder.append(s.getName());
            stringBuilder.append(", ");
            stringBuilder.append(", ");
            stringBuilder.append(s.getMajer());
            stringBuilder.append(", ");
            stringBuilder.append(s.getPlace());
            stringBuilder.append(", ");
            stringBuilder.append(s.getPhone_num());
            list.add(stringBuilder.toString());
        }
    }

    public void searchStudent() {
        if (t_id.getText().isEmpty()) {
            view(dm.getStd());
        } else {
            view(dm.searchStudent(t_id.getText()));
        }
    }

    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }

    public void back() {
        nav.navigateTo(root,Navigation.owner);
    }
}
