package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateTran implements Initializable {
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
        id.setText(Navigation.string);
        id.setEditable(false);
    }

    public void update() {
//                      t_id


        double stu_value = !(value.getText().isEmpty()) ? Double.parseDouble(value.getText()) : -1;
        double hour = !(h_required.getText().isEmpty()) ? Double.parseDouble(h_required.getText()) : -1;
        double expenseText = !(expense.getText().isEmpty()) ? Double.parseDouble(expense.getText()) : -1;

        if (dm.updateTran(id.getText(), stu_value, hour, expenseText) != 0) {
            label.setTextFill(Color.color(0, 1, 0));
            label.setText("Student Update successfully");
        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("Student did'nt Update");
        }

    }

}
