package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.*;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class Absent implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button search;
    @FXML
    private Button export;
    @FXML
    private Button back;
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
        table.setOnMouseClicked(MouseEvent-> {

                if (MouseEvent.getButton().equals(MouseButton.PRIMARY) && MouseEvent.getClickCount() == 2) {
                    Absents selectedAbsent = table.getSelectionModel().getSelectedItem();
                    if (selectedAbsent != null) {
                        t_id.setText(selectedAbsent.getStu_id());
                    }
                }

        });
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
    public void exportStudent() {
        try {
            Stage stage = new Stage();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File directory = directoryChooser.showDialog(stage);
            if (directory != null) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("File Name");
                dialog.setHeaderText("Enter the file name:");
                dialog.setContentText("File Name:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    String fileName = result.get();
                    String path = directory.getAbsolutePath() + "/" + fileName + ".xls";

                    try {
                        WritableWorkbook workbook = Workbook.createWorkbook(new File(path));
                        WritableSheet sheet = workbook.createSheet("Sheet1", 0);

                        // Write column headers
                        sheet.addCell(new Label(0, 0, "Student ID"));
                        sheet.addCell(new Label(1, 0, "Student Name"));
                        sheet.addCell(new Label(2, 0, "Phone Number"));
                        sheet.addCell(new Label(3, 0, "Course ID"));
                        sheet.addCell(new Label(4, 0, "Attendance Percentage"));

                        // Write table data
                        ObservableList<Absents> data = table.getItems();
                        for (int i = 0; i < data.size(); i++) {
                            Absents absent = data.get(i);
                            sheet.addCell(new Label(0, i + 1, absent.getStu_id()));
                            sheet.addCell(new Label(1, i + 1, absent.getStu_name()));
                            sheet.addCell(new Label(2, i + 1, absent.getStu_num()));
                            sheet.addCell(new Label(3, i + 1, absent.getCourse_id()));
                            sheet.addCell(new Label(4, i + 1, absent.getPercent()));
                        }

                        workbook.write();
                        workbook.close();
                        System.out.println("File created successfully at: " + path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No file name entered.");
                }
            }
        } catch (Exception e) {
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
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }
}
