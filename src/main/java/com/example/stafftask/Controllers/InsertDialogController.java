package com.example.stafftask.Controllers;


import com.example.stafftask.Employee;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class InsertDialogController implements Initializable {

    @FXML
    TextField nameTextField,SalaryTextField1;
    @FXML
    Label TitleText;
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

        comboBox.getSelectionModel().select(0);

        TitleText.setText("Insert Member");
        SalaryTextField1.setText("0");



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

        if(nameTextField.getText().isEmpty() || SalaryTextField1.getText().isEmpty() || datePicker.getValue() == null){
            System.out.println("You must fill all fields");
        }else{

            if(helloController.isThisArrayList) {
                //int n = helloController.my_array.top + 1;
                //Customer.contract_ID = n;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                Employee c = new Employee(Employee.contract_ID+1,nameTextField.getText(), comboBox.getSelectionModel().getSelectedItem().toString(),
                        Integer.parseInt(SalaryTextField1.getText()),formatter.format(datePicker.getValue()));
                helloController.my_array.insert(c);

            }else{
                //int n = helloController.my_linkedList.filled;
                //Customer.contract_ID = n;
                Employee c = new Employee(Employee.contract_ID+1,nameTextField.getText(), comboBox.getSelectionModel().getSelectedItem().toString(),
                        Integer.parseInt(SalaryTextField1.getText()),datePicker.getValue().toString());
                helloController.my_linkedList.insert(c);
            }

            helloController.window.hide();


        }



    }
    @FXML
    public void onRefuseClicked(){

        helloController.window.hide();
    }





}
