package carrbeat.postindexdirectory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 550);
        stage.setTitle("Справочник почтовой индексации");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static boolean authorization(String inLogin, String inPassword) {
        String login = null;
        String password = null;
        try {
            File file = new File("src\\data.txt");
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);
            String line = bufferedReader.readLine();
            if (line != null) {
                login = line;
                line = bufferedReader.readLine();
                password = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inLogin.equals(login) & inPassword.equals(password)) {
            System.out.println("Авторизация пройдена! ");
            return(true);
        } else{
            return false;
        }

    }
}
