package com.example.test_javafx.controllers;

import com.example.test_javafx.models.DBModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
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
    private AutoCompletionBinding<Object> autoCompletionBinding;
    ArrayList<String> students = new ArrayList<>();
    DBModel dm = DBModel.getModel();
    private ArrayList<String> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        students = dm.getStdIds();
        autoValues();
        autoCompletionBinding = TextFields.bindAutoCompletion(id, list.toArray());
        autoCompletionBinding.setOnAutoCompleted(event -> id.setText(event.getCompletion().toString()));

    }

    public void autoComplete() {
        autoValues();
    }

    void autoValues() {
        list = new ArrayList<>();
        for (String s : students) {
            list.add(s);
        }
    }

    public void addTransportationBotton() {

        String stu_id = id.getText();
        double stu_value = !(value.getText().isEmpty()) ? Double.parseDouble(value.getText()) : -1;
        double hour = !(h_required.getText().isEmpty()) ? Double.parseDouble(h_required.getText()) : -1;
        double expenseText = !(expense.getText().isEmpty()) ? Double.parseDouble(expense.getText()) : -1;

        if (!stu_id.isEmpty()) {
            if ((dm.addTransportation(stu_id, stu_value, hour, expenseText) != 0)) {
                label.setTextFill(Color.color(0, 1, 0));
                label.setText("Student added successfully");

            } else {
                label.setTextFill(Color.color(1, 0, 0));
                label.setText("Student did'nt add");
            }
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
