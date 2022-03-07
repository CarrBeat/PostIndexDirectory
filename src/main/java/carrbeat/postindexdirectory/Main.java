package carrbeat.postindexdirectory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Tooltip;

public class Main extends Application {

    private static boolean isAuthorize = false;
    public static ObservableList<String> localityList = FXCollections.observableArrayList();
    public static Map<String, Integer> localityMap = new HashMap<>();
    public static ObservableList<String> streetsList = FXCollections.observableArrayList();

    public static int selectedLocalityID;
    public static String tempLocality;
    public static int tempLocalityID;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 550);
        stage.setTitle("Справочник почтовой индексации");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static boolean isAuthorized() {
        return isAuthorize;
    }

    public static void isAuthorized(boolean check) { isAuthorize = true;
    }

    public static void takeLocality() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        String url = "jdbc:mysql://127.0.0.1/postindexdirectory";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        try (Connection connection = DriverManager.getConnection(url, "root", "carrbeat")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM locality");
            while (resultSet.next()) {
                localityList.add(resultSet.getString("locality"));
                tempLocality = resultSet.getString("locality");
                tempLocalityID = resultSet.getInt("idlocality");
                localityMap.put(tempLocality, tempLocalityID);
            }
        }
    }

    public static void variablesClear(){
        tempLocality = "";
        tempLocalityID = 0;
        streetsList.clear();
    }

        public static void knowSelectedLocalityID(String chosenLocality) throws SQLException, ClassNotFoundException,
                InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            selectedLocalityID = localityMap.get(chosenLocality); // получение id выбранного населённого пункта
            takeStreets();
        }

    public static void takeStreets() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
            String tempLocality;
            String url = "jdbc:mysql://127.0.0.1/postindexdirectory";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection connection = DriverManager.getConnection(url, "root", "carrbeat")){
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM street");
                while(resultSet.next()){
                    tempLocality = resultSet.getString("street_idlocality");
                    if(tempLocality.contains(String.valueOf(selectedLocalityID))){
                        streetsList.add(resultSet.getString("street name"));
                    }
                }
            }
    }

    public static void takeфStreets() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        String tempLocality;
        String url = "jdbc:mysql://127.0.0.1/postindexdirectory";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        try(Connection connection = DriverManager.getConnection(url, "root", "carrbeat")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM street");
            while(resultSet.next()){
                tempLocality = resultSet.getString("street_idlocality");
                if(tempLocality.contains(String.valueOf(selectedLocalityID))){
                    streetsList.add(resultSet.getString("street name"));
                }
            }
        }
    }
}