module com.example.prizefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.prizefx to javafx.fxml;
    exports com.example.prizefx;
}