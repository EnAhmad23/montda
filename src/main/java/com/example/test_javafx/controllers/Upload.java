package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.LectureTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Upload implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Label label;
    @FXML
    private Button back;
    @FXML
    private ComboBox<String> lecture_ID;
    @FXML
    private ComboBox<String> course_ID;
    @FXML
    private TextField title;
    @FXML
    private TextField roomNumber;

    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    ArrayList<String> studentIDFromExcel = new ArrayList<>();

    public void Upload_back(){
        nav.navigateTo(root,nav.ATTENDENCE_FXML);
    }
    public void Upload() {
        String courseID =  course_ID.getValue();
        String lectureID =  lecture_ID.getValue();
        String lec_title = title.getText();
        String room_num = roomNumber.getText();
        try{
            for (int i =0 ;i< studentIDFromExcel.size();i++){
                if (dm.addAttendance(studentIDFromExcel.get(i),lecture_ID.getValue()) != 0) {
                    label.setTextFill(Color.color(0, 1, 0));
                    label.setText("SUCCESSFULLY ^_^");
                } else {
                    label.setTextFill(Color.color(1, 0, 0));
                    label.setText("DIDN'T ADD !!");
                }
            }
        }catch (Exception e){
            System.out.println("Error! ");
        }
    }
    public void chooseFile(){
        try{
            Stage stage = new Stage();
            FileChooser filechooser = new FileChooser();
            File file = filechooser.showOpenDialog(stage);
            ArrayList<String> lines = readFileContents(file);
            try {
                Workbook workbook = Workbook.getWorkbook(file);
                Sheet sheet = workbook.getSheet(0);

                for (int row = 0; row < sheet.getRows(); row++) {
                    for (int col = 0; col < sheet.getColumns(); col++) {
                        Cell cell = sheet.getCell(col, row);
                        String contents = ((Cell) cell).getContents();
                        lines.add(contents);
                        studentIDFromExcel.add(contents);
                    }
                }
                workbook.close();
            } catch (IOException | BiffException e) {
                e.printStackTrace();
            }

            // to print excel
            if (file != null) {
                for (String line : lines) {
                    System.out.println(line);
                }
            }
        }catch (Exception e){

        }
    }
    public ArrayList<String> readFileContents(File file) {
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lecture_ID.getItems().addAll(dm.getLecIds().toArray(new String[dm.getLecIds().size()]));
        course_ID.getItems().addAll(dm.getCourseIDs().toArray(new String[dm.getCourseIDs().size()]));
    }
}
