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

import javafx.scene.control.Tooltip;

public class Main extends Application {

    private static boolean isAuthorize = false;
    public static ObservableList<String> localityList = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws IOException, NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
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

    public static void takeLocality() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        String url = "jdbc:mysql://127.0.0.1/postindexdirectory";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        try(Connection connection = DriverManager.getConnection(url, "root", "carrbeat")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM locality");
            while(resultSet.next()){
                localityList.add(resultSet.getString("locality"));
            }
        }
    }
}



