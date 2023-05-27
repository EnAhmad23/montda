package com.example.test_javafx;

import com.example.test_javafx.models.DBModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/teachingAssistant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("University");
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/uni.jpg"))));
        stage.setScene(scene);
        stage.show();

//        try {
//            // Open the Excel file
//            Workbook workbook = Workbook.getWorkbook(new File("/Users/yaseenhasan/Desktop/Book1.xlsx"));
//
//            // Get the first sheet
//            Sheet sheet = workbook.getSheet(0);
//
//            // Iterate over the rows and columns
//            for (int row = 0; row < sheet.getRows(); row++) {
//                for (int col = 0; col < sheet.getColumns(); col++) {
//                    Cell cell = sheet.getCell(col, row);
//                    String contents = cell.getContents();
//                    System.out.print(contents + "\t");
//                }
//                System.out.println();
//            }
//
//            // Close the workbook
//            workbook.close();
//        } catch (IOException | BiffException e) {
//            e.printStackTrace();
//        }
    }
}
