module com.example.berichtsheft {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.berichtsheft to javafx.fxml;
    exports com.example.berichtsheft;
}