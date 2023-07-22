package com.example.test_javafx.controllers;

import com.example.test_javafx.DataBus;
import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.Transport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Transportation implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField t_id;
    @FXML
    private DatePicker months;

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
    ArrayList<Transport> transports;ArrayList<Transport> auto;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> dataBus = new ArrayList<>();

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    public void back() {
        nav.navigateTo(root, nav.MAIN_FXML);
    }



    public void add() {
        nav.upSecen(nav.Add_TRANSPORTATION_FXML);
    }

    public void updateAttendance() {
        if (dm.checkStudentID(t_id.getText())) {
            DataBus.data.add(t_id.getText());
            nav.upSecen( nav.UPDATE_TARN);
        }else nav.error_message("ENTER THE ID FOR STUDENT !!");
    }

    public void delete(ActionEvent actionEvent) {
        if (dm.deleteTran(t_id.getText()) != 0) {
            nav.message("STUDENT DELETED");
            view(dm.getTransport(months.getValue()));
            t_id.clear();
        } else nav.error_message("STUDENT DIDN'T DELETE !!");
    }

    public void searchAttendance() {
        if(dm.checkStudentID(t_id.getText())&&months.getValue()!=null) {
            view(dm.getTransport(months.getValue()));
        }
    }

    public void view(ArrayList<Transport> transports) {
        stu_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        value_day.setCellValueFactory(new PropertyValueFactory<>("value_day"));
        h_required.setCellValueFactory(new PropertyValueFactory<>("h_required"));
        expense.setCellValueFactory(new PropertyValueFactory<>("expense"));
        num_att.setCellValueFactory(new PropertyValueFactory<>("num_att"));
        tra_month.setCellValueFactory(new PropertyValueFactory<>("tra_month"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ObservableList<Transport> ids = FXCollections.observableArrayList(transports);
        table.setItems(ids);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String id = Navigation.id;
        auto=dm.getTransport();
        autoValues();
//        months.getItems().addAll(dm.getTeacherLecIds(id).toArray(new String[dm.getTeacherLecIds(id).size()]));
//        if (months.getValue() != null) {
//            transports = dm.getTransport((months.getValue()));
//            autoValues();
//        }
//       view(transports);
        table.setOnMouseClicked(mouseEvent -> {

            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                Transport selectedAttendence = table.getSelectionModel().getSelectedItem();
                if (selectedAttendence != null) {
                    t_id.setText(selectedAttendence.getId());
                }
            } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && mouseEvent.getClickCount() == 2) {
                Transport selectedReport = table.getSelectionModel().getSelectedItem();
                if (selectedReport != null) {
                    Navigation.string = (selectedReport.getId());
                    dataBus.add(selectedReport.getId());
                    dataBus.add(String.valueOf(selectedReport.getValue_day()));
                    dataBus.add(String.valueOf(selectedReport.getH_required()));
                    dataBus.add(String.valueOf(selectedReport.getExpense()));
                    DataBus.data = dataBus;
                    updateAttendance();
                }
            }

        });
        months.setOnAction(e -> {
            if (months.getValue() != null) {
                LocalDate selectedDate = months.getValue();
                int year = selectedDate.getYear();
                int month = selectedDate.getMonthValue();
                transports = dm.getTransport(selectedDate);

                view(transports);
                // Create a java.sql.Date object from year and month

                // Insert the java.sql.Date object into the database

            }
        });
        autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().split(",")[0]);
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });

    }

    public void autoComplete() {
        autoValues();
    }

    void autoValues() {
        list = new ArrayList<>();
        for (Transport s : auto) {
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

//    public void handleKeyPress(KeyEvent keyEvent) {
//        if (keyEvent.getCode() == KeyCode.ENTER) {
//            add();
//        }
//    }
public void  refresh() {
    initialize(null,null);
}
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }
}
