module com.example.stafftask {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.stafftask to javafx.fxml;
    exports com.example.stafftask;
    exports com.example.stafftask.Controllers;
    opens com.example.stafftask.Controllers to javafx.fxml;
}