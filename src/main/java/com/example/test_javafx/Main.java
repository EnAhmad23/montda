package com.example.test_javafx;


import com.example.test_javafx.models.DBModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        DBModel dm=DBModel.getModel();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Montda");
//        stage.setOnCloseRequest(event -> {
//            try {
//                if( dm.backupDatabase("backups/")==0)
//                    System.out.println("Backup Successfully");
//                else System.out.println("0");
//            } catch (IOException e) {
//                System.err.println(e.getMessage());
//                throw new RuntimeException(e);
//            } catch (InterruptedException e) {
//                System.err.println(e.getMessage());
//                throw new RuntimeException(e);
//            }
//        });
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/uni.jpg"))));
        stage.setScene(scene);
        stage.show();
    }
}
