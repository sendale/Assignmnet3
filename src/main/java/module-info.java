module com.example.assignmnet3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignmnet3 to javafx.fxml;
    exports com.example.assignmnet3;
}