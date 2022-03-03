module carrbeat.postindexdirectory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    opens carrbeat.postindexdirectory to javafx.fxml;
    exports carrbeat.postindexdirectory;
}