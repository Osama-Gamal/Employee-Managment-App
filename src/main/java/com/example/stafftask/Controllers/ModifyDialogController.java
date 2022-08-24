package com.example.stafftask.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.ResourceBundle;

public class ModifyDialogController implements Initializable {

    @FXML
    TextField nameTextField,SalaryTextField1;
    @FXML
    AnchorPane AncorPane;
    @FXML
    DatePicker datePicker;
    @FXML
    ComboBox comboBox;
    public HelloController helloController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboBox.getItems().add("Department A");
        comboBox.getItems().add("Department B");
        comboBox.getItems().add("Department C");
        comboBox.getItems().add("Department D");



        nameTextField.setText(helloController.employee.getName());
        SalaryTextField1.setText(helloController.employee.getSalary()+"");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = helloController.employee.getDateofbirth();
        LocalDate localDate = LocalDate.parse(date, formatter);

        datePicker.setValue(localDate);

        comboBox.getSelectionModel().select(helloController.employee.department);



        SalaryTextField1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    SalaryTextField1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });




    }

    @FXML
    public void onAcceptClicked(){

        if(nameTextField.getText().isEmpty() || SalaryTextField1.getText().isEmpty() || datePicker.getValue().toString().isEmpty()){
                System.out.println("You must fill all fields");
        }else{
            helloController.employee.name = nameTextField.getText();
            helloController.employee.salary = Integer.parseInt(SalaryTextField1.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            helloController.employee.dateofbirth = formatter.format(datePicker.getValue());
            helloController.employee.department = comboBox.getSelectionModel().getSelectedItem().toString();
            helloController.window.hide();


        }



    }
    @FXML
    public void onRefuseClicked(){

        helloController.window.hide();
    }





}
