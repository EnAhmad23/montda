package com.example.test_javafx.controllers;

import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTransportarion implements Initializable {
    @FXML
    TextField id;
    @FXML
    TextField value;
    @FXML
    TextField h_required;

    @FXML
    TextField expense;
    @FXML
    Label label;
    DBModel dm = DBModel.getModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addTransportationBotton() {
        String stu_id = id.getText();
        double stu_value = Double.parseDouble(value.getText());
        double hour = Double.parseDouble(h_required.getText());
        double expenseText = Double.parseDouble(expense.getText());


        if (dm.addTransportation(stu_id, stu_value, hour, expenseText) != 0) {
            label.setTextFill(Color.color(0, 1, 0));
            label.setText("Student added successfully");

        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("Student did'nt add");
        }
        id.clear();
        value.clear();
        h_required.clear();
        expense.clear();

    }
}
