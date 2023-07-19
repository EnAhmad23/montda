package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.LectureTime;
import com.example.test_javafx.models.ReportLectures;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportLecture implements Initializable {
    @FXML
    private AnchorPane root;


    @FXML
    private TextField l_id;

    public TableView<ReportLectures> table;
    @FXML
    public TableColumn<ReportStudent, String> id;
    @FXML
    public TableColumn<ReportStudent, String> num;

    @FXML
    public TableColumn<ReportStudent, String> lec_id;
    @FXML
    public TableColumn<ReportStudent, String> title;
    @FXML
    public TableColumn<ReportStudent, String> attendancePear;

    Navigation nav = new Navigation();
    DBModel dm=DBModel.getModel();
    ArrayList<ReportLectures> lecture  ;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();

    public void SearchReportAttendance(){
        if(l_id.getText().length()==5&&!l_id.getText().equals("     ")) {
            view(dm.reportLectures(l_id.getText()));
        }else
            view(lecture);

    }
    public void back(){
        nav.navigateTo(root,nav.REPORT_PAGE);
    }
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lecture=dm.reportLectures();
        view(lecture);
        autoValues();
        table.setOnMouseClicked(MouseEvent-> {

                if (MouseEvent.getButton().equals(MouseButton.PRIMARY) && MouseEvent.getClickCount() == 2) {
                    ReportLectures selectedLecture = table.getSelectionModel().getSelectedItem();
                    if (selectedLecture != null) {
                        l_id.setText(selectedLecture.getLec_id());
                    }
                }

        });
        autoCompletionBinding = TextFields.bindAutoCompletion(l_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            l_id.setText(event.getCompletion().toString().split(",")[0]);
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });
    }

    private void view(ArrayList<ReportLectures> lectures) {
        lec_id.setCellValueFactory(new PropertyValueFactory<>("lec_id"));
        id.setCellValueFactory(new PropertyValueFactory<>("Course_id"));
        attendancePear.setCellValueFactory(new PropertyValueFactory<>("present"));
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        ObservableList<ReportLectures> lecture = FXCollections.observableArrayList(lectures);
        table.setItems(lecture);
    }

    public void autoComplete() {

        autoValues();
    }
    void autoValues(){
        list = new ArrayList<>();
        for (ReportLectures s : lecture) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getLec_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getCourse_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getTitle());
            stringBuilder.append(", ");
            stringBuilder.append(s.getNum());
            stringBuilder.append(", ");
            stringBuilder.append(s.getPresent());
            list.add(stringBuilder.toString());
        }
    }

}
