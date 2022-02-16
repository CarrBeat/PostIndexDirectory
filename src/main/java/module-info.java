module carrbeat.postindexdirectory {
    requires javafx.controls;
    requires javafx.fxml;


    opens carrbeat.postindexdirectory to javafx.fxml;
    exports carrbeat.postindexdirectory;
}