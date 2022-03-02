package carrbeat.postindexdirectory;

import java.sql.*;

public class DatabaseHandler {
    protected String dbHost = "127.0.0.1";
    protected String dbPort = "3306";
    protected String dbUser = "root";
    protected String dbPass = "carrbeat";
    protected String dbName = "postindexdirectory";

    public static final String USER_TABLE = "admin";
    public static final String USERS_ID = "idadmin";
    public static final String USERS_LOGIN= "login";
    public static final String USERS_PASSWORD= "password";





    Connection dbConnection;

    public static ResultSet getUser(User user) {
        String select = "SELECT * FROM " + USER_TABLE + " WHERE " + USERS_LOGIN + " =? AND " +
                USERS_PASSWORD + "=?";
        ResultSet resSet = null;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin() );
            prSt.setString(2, user.getPassword() );
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return resSet;
    }

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return  dbConnection;

    }

}
