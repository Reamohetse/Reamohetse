module com.example.assignmenttwo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.assignmenttwo to javafx.fxml;
    exports com.example.assignmenttwo;
}