package com.example.stafftask.Controllers;

import com.example.stafftask.MyArrayList;
import com.example.stafftask.MyLinkedList;
import com.example.stafftask.Employee;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.util.Duration;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable {

    @FXML
    TextField searchText;
    @FXML
    TableView<Employee> tableView;
    @FXML
    TableColumn<Employee,String> nameColumn,phoneColumn,nationalityColumn;
    @FXML
    TableColumn<Employee, Integer> IDColumn,currentColumn;
    @FXML
    ComboBox orderBox;
    @FXML
    VBox ArrayVBox,LinkedVBox;
    @FXML
    Label listSort;

    public static MyArrayList my_array = new MyArrayList();
    public static MyLinkedList my_linkedList = new MyLinkedList();
    public static boolean isThisArrayList = true;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        read("src/assets/Data.txt");
        read_to_linked_list("src/assets/Data.txt");
        IDColumn.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("department"));
        nationalityColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("salary"));
        currentColumn.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("dateofbirth"));


        orderBox.getItems().add("Selection Sort");
        orderBox.getItems().add("Bubble Sort");


        loadData(my_array);
        //translateAnimation(ArrayVBox,300,-27,0);
        translateAnimation(LinkedBtn, 300, 300, 0);
        translateAnimation(ArrayBtn, 300, -300, 0);



        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isEmpty())
                if(isThisArrayList)
                    loadData(my_array);
                else
                    loadData(my_linkedList);
        });

    }


    @FXML
    public void onArrayListClicked() throws IOException {

        if(!isThisArrayList) {
            //translateAnimation(ArrayVBox, 300, -27, 0);
            //translateAnimation(LinkedVBox, 300, 27, 0);
            fadeoutAndInAnimation(tableView,500);
        }

        //showInstruction("wwwwwwwwwwwwwwwwwwwwwwwwwwww");
        listSort.setText("ArrayList");
        isThisArrayList = true;
        loadData(my_array);

    }

    @FXML
    public void onLinkedListClicked(){

        if(isThisArrayList) {
            //translateAnimation(LinkedVBox, 300, -27, 0);
            //translateAnimation(ArrayVBox,300,27,0);
            fadeoutAndInAnimation(tableView,500);
        }
        listSort.setText("LinkedList");
        isThisArrayList = false;
        loadData(my_linkedList);

    }

    @FXML
    Button LinkedBtn,ArrayBtn,drawerBtn;
    private boolean isDrawer = false;
    @FXML
    public void DrawerBtnClicked(){
        if(!isDrawer) {
            translateAnimation(LinkedBtn, 300, -300, 0);
            translateAnimation(ArrayBtn, 300, 300, 0);
            isDrawer = true;
            drawerBtn.setText("Close");
        }else{
            translateAnimation(LinkedBtn, 300, 300, 0);
            translateAnimation(ArrayBtn, 300, -300, 0);
            isDrawer = false;
            drawerBtn.setText("Open");
        }

    }
    public void onSelectItem(){
        switch (orderBox.getSelectionModel().getSelectedIndex()){

            case 0:
                System.out.println("Selection Sort");
                my_array.selection_sort_for_id();
                my_linkedList.selectionSort();
                break;
            case 1:
                System.out.println("Bubble Sort");
                my_array.bubble_sort_for_id();
                my_linkedList.bubble_sort();
                break;

        }
        if(isThisArrayList)
            loadData(my_array);
        else
            loadData(my_linkedList);
    }
    public void translateAnimation(Node node,float time,float xValue,float yValue){
        TranslateTransition translate = new TranslateTransition();
        translate.setByX(xValue);
        translate.setByY(yValue);
        translate.setDuration(Duration.millis(time));
        //translate.setCycleCount(500);
        translate.setAutoReverse(false);
        translate.setNode(node);
        translate.play();
    }
    public void fadeoutAndInAnimation(Node node,float time){
        FadeTransition ft = new FadeTransition(Duration.millis(time), node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.play();
    }
    @FXML
    private void insertMemberClicked() throws IOException {

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/com/example/stafftask/modifyMember.fxml"));
        InsertDialogController controller = new InsertDialogController();
        loader2.setController(controller);
        Parent root = loader2.load();
        Parent content = root;
        Scene scene = new Scene(content);
        window = new Stage();
        window.setScene(scene);
        window.showAndWait();
        //window.show();

        if(isThisArrayList) {
            System.out.println("Insert in arrayList has done");
            loadData(my_array);
        }else{
            System.out.println("Insert in linkedList has done");
            loadData(my_linkedList);
        }

    }

    @FXML
    public void SaveDataClicked(){
        if(isThisArrayList)
            write(my_array,"src/assets/Data.txt");
        else
            write(my_linkedList,"src/assets/Data.txt");
    }
    @FXML
    public void loadDataClicked(){
        my_array.clear();
        my_linkedList.clear();
        read("src/assets/Data.txt");
        read_to_linked_list("src/assets/Data.txt");
        orderBox.getSelectionModel().select("none");

        if(isThisArrayList) {
            loadData(my_array);
        }
        else
            loadData(my_linkedList);

    }
    @FXML
    public void RemoveMemberClicked(){
        Employee staff = tableView.getSelectionModel().getSelectedItem();
        if(staff != null) {
            if(isThisArrayList) {
                my_array.delete(staff.name);
                loadData(my_array);
            }else {
                my_linkedList.remove(staff.name);
                loadData(my_linkedList);
            }
        }
    }
    public static Employee employee;
    public static Stage window;
    @FXML
    public void ModifymemberClicked() throws IOException {

        employee = tableView.getSelectionModel().getSelectedItem();
        if(employee != null) {

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/com/example/stafftask/modifyMember.fxml"));

            ModifyDialogController controller = new ModifyDialogController();
            loader2.setController(controller);

            Parent root = loader2.load();
            Parent content = root;
            Scene scene = new Scene(content);
            window = new Stage();
            window.setScene(scene);
            window.showAndWait();
            //window.show();
            if(isThisArrayList)
                loadData(my_array);
            else
                loadData(my_linkedList);


        }

    }

    @FXML
    public void LinearSearchClicked(){
        if (isThisArrayList && my_array.linear_search(searchText.getText()) != -1) {
            System.out.println("Linear Search in ArrayList has done");
            Employee staff = my_array.get(my_array.linear_search(searchText.getText()));
            tableView.getItems().clear();
            tableView.getItems().add(staff);
        }else{
            if(!isThisArrayList && my_linkedList.linear_search(searchText.getText()) != -1){
                System.out.println("Linear Search in LinkedLst has done");
                Employee staff = my_linkedList.get(my_linkedList.linear_search(searchText.getText()));
                tableView.getItems().clear();
                tableView.getItems().add(staff);
            }
        }

    }
    @FXML
    public void BinarySearchClicked(){

        if ( isThisArrayList && isNumeric(searchText.getText()) && my_array.binary_search(searchText.getText()) != -1) {
           // System.out.println("Binary Search in ArrayList has done");
            Employee staff = my_array.get(my_array.binary_search(searchText.getText()));
            tableView.getItems().clear();
            tableView.getItems().add(staff);
        }else {
            if(!isThisArrayList && isNumeric(searchText.getText()) && my_linkedList.binary_search(searchText.getText()) != null){
             //   System.out.println("Binary Search in LinkedLst has done");
                Employee c = my_linkedList.binary_search(searchText.getText()).value;
                Employee employee = new Employee(c.ID,c.name,c.department,c.salary,c.dateofbirth);
                tableView.getItems().clear();
                tableView.getItems().add(employee);
            }
        }


    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public static MyArrayList read(String path) {
        Employee.contract_ID = 0;

        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()) {
                String[] splited = reader.nextLine().split("-");

                //my_array.insert(new StaffEntry(splited[0], splited[1]));


                if(my_array.insert(new Employee(Integer.parseInt(splited[0]),splited[1],splited[2] ,Integer.valueOf(splited[3]),splited[4]))) {
                    System.out.println("ARRAY OVERFLOW");
                    //tableView.getItems().add(new StaffEntry(splited[0], splited[1]));
                    //tableView.getItems().add(my_array.displayInArray());

                    //break;
                }
            }


        } catch (Exception e) {
            System.out.println("ERROR Reading File"+e);
        }

        return my_array;
    }


    public static boolean write(MyArrayList array , String path) {
        try {
            File file = new File(path);
            FileWriter writer = new FileWriter(file);

            for(int index = 0; index < array.capacity(); index++)
                writer.write(array.get(index).line() + "\n");

            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("ERROR WRITING FILE");
            return false;
        }
    }



    public void loadData(MyArrayList my_array) {
        tableView.getItems().clear();
        for (int i = 0; i <= my_array.top; i++) {
            tableView.getItems().add(my_array.displayInArray()[i]);
        }
    }
    public void loadData(MyLinkedList my_linkedList) {
        tableView.getItems().clear();
        for (int i = 0; i < my_linkedList.filled; i++) {
            tableView.getItems().add(my_linkedList.get(i));
        }
    }


    public static MyLinkedList read_to_linked_list(String path) {
        Employee.contract_ID = 0;

        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()) {
                String[] splited = reader.nextLine().split("-");

                my_linkedList.insert(new Employee(Integer.parseInt(splited[0]),splited[1],splited[2] ,Integer.valueOf(splited[3]),splited[4]));
                System.out.println("LinkedList : Data Inserted");

            }

        } catch (Exception e) {
            System.out.println("ERROR Reading File");
        }

        return my_linkedList;
    }


    public static boolean write(MyLinkedList array , String path) {
        try {
            File file = new File(path);
            FileWriter writer = new FileWriter(file);

            for(int index = 0; index < array.capacity(); index++)
                writer.write(array.get(index).line() + "\n");

            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("ERROR WRITING FILE");
            return false;
        }
    }






}