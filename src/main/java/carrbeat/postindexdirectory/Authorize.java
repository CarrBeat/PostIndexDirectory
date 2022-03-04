package carrbeat.postindexdirectory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Authorize extends Application {
    Stage stage = new Stage();

    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("authorize.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 360);
        stage.setTitle("Окно авторизации");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static boolean authorization(String inLogin, String inPassword) throws Exception {
        String url = "jdbc:mysql://127.0.0.1/postindexdirectory";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        String login;
        String password;
        try(Connection connection = DriverManager.getConnection(url, "root", "carrbeat")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM admin");
            resultSet.next();
            login = resultSet.getString("login");
            password = resultSet.getString("password");
        }
        if (inLogin.equals(login) & inPassword.equals(password)) {
            Main.isAuthorized(true);
            return true;
        } else{
            return false;
        }
    }
    void showWindow() throws Exception{
        start(stage);
    }
}
