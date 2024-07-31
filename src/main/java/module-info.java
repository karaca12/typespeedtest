module com.karacamehmet.typespeed {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.karacamehmet.typespeed to javafx.fxml;
    exports com.karacamehmet.typespeed;
}