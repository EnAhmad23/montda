package com.example.test_javafx.controllers;

import com.example.test_javafx.models.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import com.example.test_javafx.Navigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
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

public class LecturesTimesController implements Initializable {
    @FXML
    public AnchorPane root;


    public TableView<LectureTime> table;
    @FXML
    public TableColumn<LectureTime, String> id;

    @FXML
    public TableColumn<LectureTime, String> course_id;
    @FXML
    public TableColumn<LectureTime, String> room;

    @FXML
    public TableColumn<LectureTime, String> title;

    @FXML
    public TextField t_id;

    @FXML
    public ComboBox<String> courses;
    @FXML
    public Button id_back_addStu;
    public Button view;
    DBModel db = DBModel.getModel();
    Navigation nav = new Navigation();
    ArrayList<LectureTime> lectures;

    private AutoCompletionBinding<Object> autoCompletionBinding;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courses.getItems().addAll(db.getCourseIDs().toArray(new String[db.getCourseIDs().size()]));
        lectures = db.getLectures();
        view(lectures);
        autoValues();
        table.setOnMouseClicked(MouseEvent-> {

            if (MouseEvent.getButton().equals(MouseButton.PRIMARY) && MouseEvent.getClickCount() == 2) {
                LectureTime selectedReport = table.getSelectionModel().getSelectedItem();
                if (selectedReport != null) {
                    t_id.setText(selectedReport.getLecture_id());

                }
            }else if (MouseEvent.getButton().equals(MouseButton.SECONDARY) && MouseEvent.getClickCount() == 2){
                LectureTime selectedReport = table.getSelectionModel().getSelectedItem();
                if (selectedReport != null) {
                    Navigation.string =(selectedReport.getLecture_id());
                    update_button();
                }
            }
        });
        courses.setOnAction(e -> {
            if (courses.getValue() != null) {

                view(db.getLecFromCou(courses.getValue()));

            } else {
                nav.error_message("SELECT LECTURE ID");
            }
        });
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().substring(0, 5));
        });
    }

    public void back() {
        nav.navigateTo(root, Navigation.owner);
    }

    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }
    public void viewTimes() {
        if (t_id.getText().isEmpty() && courses.getValue() != null) {
            view(db.getLecFromCou(courses.getValue()));
            courses.setValue(null);
        } else if (!t_id.getText().isEmpty())
            view(db.searchLecture(t_id.getText()));
        else
            view(lectures);



    }


    public void view(ArrayList<LectureTime> lectureTimes) {
        id.setCellValueFactory(new PropertyValueFactory<>("lecture_id"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("Course_id"));
        room.setCellValueFactory(new PropertyValueFactory<>("Room_number"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        ObservableList<LectureTime> lectures = FXCollections.observableArrayList(lectureTimes);
        table.setItems(lectures);
    }




    public void delete_button() {
        Stage stage = new Stage();
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label = new Label("LECTURE DELETED !");
        if (db.delete_lecture(t_id.getText()) != 0) {
            view(db.getLectures());
            del(t_id.getText());
            nav.navigateTo(root, nav.LECTURES_TIMES_FXML);
            label.setTextFill(Color.color(0, 0, 0));
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("LECTURE DIDN'T DELETE !");
        }
        lectures = db.getLectures();
        t_id.clear();
        vBox.getChildren().add(label);
        stage.setScene(new Scene(vBox, 300, 100));
        stage.show();

    }

    void del(String s) {
        for (int i = 0; i < lectures.size(); i++) {
            if (lectures.get(i).getLecture_id().equals(s)) {
//                list.remove(i);
                System.out.println(lectures.remove(i));
            }
        }
    }

    public void update_button() {
        if (t_id.getText().length() == 5 && (!t_id.getText().equals("     "))) {
            Navigation.string = t_id.getText();
            nav.upSecen( nav.UPDATE_LECTURE);
        } else
            nav.error_message("ENTER THE ID FOR  THE LECTURE !!");
    }

    public void add_button() {
        nav.navigateTo(root, nav.ADD_LECTURES_FXML);

    }

    public void autoComplete() {

        autoValues();
    }

    void autoValues() {
        list = new ArrayList<>();
        for (LectureTime s : lectures) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getLecture_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getCourse_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getRoom_number());
            stringBuilder.append(", ");
            stringBuilder.append(s.getTitle());
            list.add(stringBuilder.toString());
        }
    }


}
