package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Upload implements Initializable {
    @FXML
    private Button back;
    @FXML
    private AnchorPane root;
    Navigation nav = new Navigation();
    public void Upload_back(){
        nav.navigateTo(root,nav.UPLOAD);
    }
    public void Upload(){


    }
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        FileChooser fileChooserApp = new FileChooser();
//        fileChooserApp.start(primaryStage);
//    }
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

                    }
                }
                workbook.close();
            } catch (IOException | BiffException e) {
                e.printStackTrace();
            }

            if (file != null) {
                for (String line : lines) {
                    System.out.println(line);
                }
            }

//            Scanner in = new Scanner(file);
//            String txt = "";
//
//            while(in.hasNext()){
//                txt += in.nextLine();
//            }
//
//            Label labe = new  Label(txt);
//            labe.setAlignment(Pos.TOP_LEFT);
//            labe.setFont(new Font(20));
//            Scene scene = new Scene(labe,600,400);
//            stage.setTitle("Expected Output");
//            stage.setScene(scene);
//            stage.show();
        }catch (Exception e){

        }

//        ArrayList<String> lines = new ArrayList<>();


//        for (String line : lines) {
//            System.out.println(line);
//        }

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

    }
}
