package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Absent implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button search;
    @FXML
    private Button export;
    @FXML
    private Button update;
    @FXML
    private TextField t_id;

    @FXML
    private TableView<Absents> table;
    @FXML
    private TableColumn<Absents, String> id;
    @FXML
    private TableColumn<Absents, String> name;

    @FXML
    private TableColumn<Absents, String> phone_num;
    @FXML
    private TableColumn<Absents, String> course_id;
    @FXML
    private TableColumn<Absents, String> attPercentage;
    ArrayList<Absents> absents;
    //    ArrayList<Course> courses = dm.getCou();
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> list = new ArrayList<>();
    Navigation nav = new Navigation();
    DBModel dm =DBModel.getModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        id.getItems().addAll(dm.getLecIds().toArray(new String[dm.getLecIds().size()]));
        absents = dm.getAbsents();
        view(absents);
        autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(t_id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            t_id.setText(event.getCompletion().toString().substring(0, 9));
//            TextFields.bindAutoCompletion(t_id, list.toArray());
        });

    }

    public void view(ArrayList<Absents> lectureTimes) {
        id.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("stu_name"));
        phone_num.setCellValueFactory(new PropertyValueFactory<>("stu_num"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        attPercentage.setCellValueFactory(new PropertyValueFactory<>("percent"));
        ObservableList<Absents> ids = FXCollections.observableArrayList(lectureTimes);
        table.setItems(ids);
    }
    public void autoComplete() {


        autoValues();


    }

    void autoValues() {
        list = new ArrayList<>();
        for (Absents s : absents) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getStu_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getStu_name());
            stringBuilder.append(", ");
            stringBuilder.append(s.getStu_num());
            stringBuilder.append(", ");
            stringBuilder.append(s.getCourse_id());
            stringBuilder.append(", ");
            stringBuilder.append(s.getPercent());
            list.add(stringBuilder.toString());
        }
    }



    public void exportStudent() throws IOException {
        try {
            Stage stage = new Stage();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File directory = directoryChooser.showDialog(stage);
            if (directory != null) {
                try {
                    String path = directory.getAbsolutePath() + "/file.xls";
                    System.out.println(path);
                    WritableWorkbook workbook = Workbook.createWorkbook(new File(path));
                    WritableSheet sheet = workbook.createSheet("Sheet1", 0);
                    Label label = new Label(0,0,"123");
                    sheet.addCell((WritableCell) label);
                    workbook.write();
                    workbook.close();
                    System.out.println("File created successfully at: "+ path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }


    public void searchStudent() {
        if(t_id.getText().length()==9&&!t_id.getText().equals("         ")) {
            view(dm.searchAbsents(t_id.getText()));
        }else
            view(absents);
    }



    public void updateStudent() {
    }

    public void back() {
        nav.navigateTo(root, nav.REPORT_PAGE);
    }


}
