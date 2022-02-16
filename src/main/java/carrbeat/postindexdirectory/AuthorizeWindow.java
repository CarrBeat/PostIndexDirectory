package carrbeat.postindexdirectory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AuthorizeWindow extends Application {
    Stage stage = new Stage();

    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("authorize.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 360);
        stage.setTitle("Окно авторизации");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    void showWindow() throws Exception{
        start(stage);
    }


}
