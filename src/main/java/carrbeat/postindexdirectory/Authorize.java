package carrbeat.postindexdirectory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

    public static boolean authorization(String inLogin, String inPassword) {
        String url = "jdbc:mysql://localhost/postindexdirectory";
        Object loginField = null;
        String username = loginField.getText();
        String




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
