package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Transportation implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField t_id;
    @FXML
    private ComboBox<String> months;

    public TableView<Transport> table;
    @FXML
    public TableColumn<Transport, String> stu_id;
    @FXML
    public TableColumn<Transport, String> value_day;

    @FXML
    public TableColumn<Transport, String> h_required;

    @FXML
    public TableColumn<Transport, String> expense;
    @FXML
    public TableColumn<Transport, String> tra_month;
    @FXML
    public TableColumn<Transport, String> num_att;
    @FXML
    public TableColumn<Transport, String> name;
    ArrayList<Transport> transports;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    public void back() {
        nav.navigateTo(root, nav.MAIN_FXML);
    }

    public void upload() throws IOException {
        nav.upSecen(nav.UPLOAD);
    }

    public void add() {
        if (t_id.getText() != null && months.getValue() != null && t_id.getText().length() == 9) {
            if (dm.addAttendance(t_id.getText(), months.getValue()) != 0)
          ///      view(dm.getAttendence(months.getValue()));
            t_id.clear();
        } else nav.error_message("STUDENT DIDN'T ADD");
    }

    public void updateAttendance() {
        if (t_id.getText().length()==9&&!t_id.getText().equals("         ")&&months.getValue()!=null) {
            Navigation.string = t_id.getText() +" "+months.getValue();
            nav.upSecen( nav.UPDATE_ATTENDENCE);
        }else nav.error_message("ENTER THE ID FOR STUDENT !!");
    }

    public void delete(ActionEvent actionEvent) {
        if (dm.deleteAttendence(t_id.getText(),months.getValue())!=0) {
            nav.message("STUDENT DELETED");
         ///   view(dm.getAttendence(months.getValue()));
            t_id.clear();
        }else nav.error_message("STUDENT DIDN'T DELETE !!");
    }

    public void searchAttendance() {
        if(t_id.getText().length()==9&&!t_id.getText().equals("         ")&&months.getValue()!=null) {
          ///  view(dm.searchAttendence(t_id.getText(),months.getValue()));
        }
    }

    public void view(ArrayList<Transport> lectureTimes) {
        stu_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        value_day.setCellValueFactory(new PropertyValueFactory<>("value_day"));
        h_required.setCellValueFactory(new PropertyValueFactory<>("h_required"));
        expense.setCellValueFactory(new PropertyValueFactory<>("expense"));
        num_att.setCellValueFactory(new PropertyValueFactory<>("num_att"));
        tra_month.setCellValueFactory(new PropertyValueFactory<>("tra_month"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ObservableList<Transport> ids = FXCollections.observableArrayList(lectureTimes);
        table.setItems(ids);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String id =Navigation.id;
     ///   months.getItems().addAll(dm.getTeacherLecIds(id).toArray(new String[dm.getTeacherLecIds(id).size()]));
     ///   transports = dm.getTeachStu(dm.getTeachCourseID(id));
        table.setOnMouseClicked(mouseEvent ->  {

            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
            ///    Attendences selectedAttendence = table.getSelectionModel().getSelectedItem();
//          /      if (selectedAttendence != null) {
//         /           t_id.setText(selectedAttendence.getStudent_id());
//          /      }
            }else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && mouseEvent.getClickCount() == 2){
//        /        Attendences selectedReport = table.getSelectionModel().getSelectedItem();
//             /   if (selectedReport != null) {
//             /       Navigation.string =(selectedReport.getStudent_id());
//               /     updateAttendance();
//             /   }
            }

        });
        months.setOnAction(e -> {
            if (months.getValue() != null) {
//                attendences = dm.getAttendence(lecture_ids.getValue());

//            /    view(dm.getAttendence(months.getValue()));

            } else {
                nav.error_message("SELECT LECTURE ID");
            }
        });
        ///autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().substring(0, 9));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });

    }

    public void autoComplete() {
        autoValues();
    }

    void autoValues() {
        list = new ArrayList<>();
        for (Transport s : transports) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getId());
            stringBuilder.append(", ");
            stringBuilder.append(s.getName());
            stringBuilder.append(", ");
            stringBuilder.append(s.getValue_day());
            stringBuilder.append(", ");
            stringBuilder.append(s.getH_required());
            stringBuilder.append(", ");
            stringBuilder.append(s.getExpense());
            stringBuilder.append(", ");
            stringBuilder.append(s.getTra_month());
            stringBuilder.append(", ");
            stringBuilder.append(s.getNum_att());
            list.add(stringBuilder.toString());
        }
    }

    public void handleKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            add();
        }
    }
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }
}