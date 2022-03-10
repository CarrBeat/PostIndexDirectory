package carrbeat.postindexdirectory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/postindexdirectory",
                "root", "carrbeat");
        return connection;
    }
}
