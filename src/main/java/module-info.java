module com.example.bank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bank to javafx.fxml;
    exports com.example.bank;
}