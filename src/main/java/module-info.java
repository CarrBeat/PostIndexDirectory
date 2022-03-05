module carrbeat.postindexdirectory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;
    requires org.apache.commons.lang3;
    requires junit;
    opens carrbeat.postindexdirectory to javafx.fxml;
    exports carrbeat.postindexdirectory;
}