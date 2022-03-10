package carrbeat.postindexdirectory;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import java.util.TimeZone;

public class Admin extends Application {
    Stage stage = new Stage();

    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 760, 480);
        stage.setTitle("Окно администратора");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
    
    void showAdminWindow() throws Exception{
        start(stage);
    }

}