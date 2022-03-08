package carrbeat.postindexdirectory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main extends Application {

    private static boolean isAuthorize = false;
    public static ObservableList<String> localityList = FXCollections.observableArrayList();
    public static Map<String, Integer> localityMap = new HashMap<>();
    public static ObservableList<String> streetsList = FXCollections.observableArrayList();
    public static ObservableList<Integer> housenum_idhouse_num = FXCollections.observableArrayList();
    public static ObservableList<String> housesList = FXCollections.observableArrayList();
    public static ObservableList<String> indexesList = FXCollections.observableArrayList();
    public static ObservableList<String> idStreet_houseNum = FXCollections.observableArrayList();
    public static ObservableList<String> searchedAdressesList = FXCollections.observableArrayList();
    public static Map<String, String> house_postIndex = new HashMap<>();
    public static Map<Integer, String> idHouse_house = new HashMap<>();
    public static Map<Integer, String> idStreet_street = new HashMap<>();
    public static int selectedLocalityID;
    public static int selectedStreetID;
    public static String postIndex;
    public static int searchedLocalityID;
    public static String searchedLocality;
    public static int searchedStreet;

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
                localityMap.put(resultSet.getString("locality"), resultSet.getInt("idlocality"));
            }
        }
    }

        public static void knowSelectedLocalityID(String chosenLocality) throws SQLException, ClassNotFoundException,
                InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            selectedLocalityID = localityMap.get(chosenLocality); // получение id выбранного населённого пункта
            takeStreets();
        }

    public static void knowSelectedStreetID(String chosenStreet) throws SQLException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(localityMap.get(chosenStreet) != null){
            selectedStreetID = localityMap.get(chosenStreet); // получение id выбранной улицы
            processHouseNum();
        }
    }

    public static void knowPostIndexes() throws SQLException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String url = "jdbc:mysql://127.0.0.1/postindexdirectory";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        try(Connection connection = DriverManager.getConnection(url, "root", "carrbeat")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM house_num");
            while(resultSet.next()){
                if (!indexesList.contains(resultSet.getString("post_index"))){
                    indexesList.add(resultSet.getString("post_index"));
                }
            }
        }
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
                        localityMap.put(resultSet.getString("street name"), resultSet.getInt("idstreet"));
                    }
                }
                processHouseNum();
            }
    }

    public static void processHouseNum() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        String url = "jdbc:mysql://127.0.0.1/postindexdirectory";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        try(Connection connection = DriverManager.getConnection(url, "root", "carrbeat")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM street_housenum");
            while(resultSet.next()){
                if (resultSet.getInt("street_idstreet") == selectedStreetID & resultSet.getInt("localityid") == selectedLocalityID) {
                    housenum_idhouse_num.add(resultSet.getInt("house num_idhouse num"));
                }
            }
        }
        takeHouseNum();
    }

    public static void takeHouseNum() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        String url = "jdbc:mysql://127.0.0.1/postindexdirectory";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        try(Connection connection = DriverManager.getConnection(url, "root", "carrbeat")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM house_num");
            while(resultSet.next()){
                if(housenum_idhouse_num.contains(resultSet.getInt("idhousenum"))){
                    housesList.add(resultSet.getString("num_house"));
                    house_postIndex.put(resultSet.getString("num_house"),
                            resultSet.getString("post_index"));
                }
            }
        }
    }

    public static void knowPostIndex(String selectedNumHouse){
        postIndex = house_postIndex.get(selectedNumHouse);
    }

    public static void resetAll(){
        localityList.clear();
        streetsList.clear();
        housenum_idhouse_num.clear();
        housesList.clear();
        house_postIndex.clear();
        selectedStreetID = 0;
        selectedLocalityID = 0;
        postIndex = "";
    }

    public static void searchAdresses(String selectedIndex) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        String url = "jdbc:mysql://127.0.0.1/postindexdirectory";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        try(Connection connection = DriverManager.getConnection(url, "root", "carrbeat")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM house_num");
            while(resultSet.next()){
                if (Objects.equals(selectedIndex, resultSet.getString("post_index"))){
                    idHouse_house.put(resultSet.getInt("idhousenum"), resultSet.getString("num_house"));
                    System.out.println(idHouse_house);
                }
            }
        }


        try(Connection connection = DriverManager.getConnection(url, "root", "carrbeat")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM street");
            while(resultSet.next()){
                idStreet_street.put(resultSet.getInt("idstreet"), resultSet.getString("street name"));
                System.out.println(idStreet_street);
            }
        }

        try(Connection connection = DriverManager.getConnection(url, "root", "carrbeat")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM street_housenum");
            while(resultSet.next()){
                if (idHouse_house.containsKey(resultSet.getInt("house num_idhouse num"))){
                    searchedLocalityID = resultSet.getInt("localityid");
                    idStreet_houseNum.add(idStreet_street.get(resultSet.getInt("street_idstreet")) + "-" + idHouse_house.get(resultSet.getInt("house num_idhouse num")));
                    System.out.println(idStreet_houseNum);
                }
            }
        }
    }

}