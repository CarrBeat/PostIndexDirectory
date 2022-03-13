package carrbeat.postindexdirectory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Admin extends Application {
    Stage stage = new Stage();

    public void start(Stage stage) throws Exception{
        if (Main.isAuthorized()){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 860, 555);
        stage.setTitle("Окно администратора");
        stage.setScene(scene);
        stage.show();
        } else { System.out.println("Запустите класс Main!");
                System.exit(1);
        }
    }
    
    void showAdminWindow() throws Exception{
        start(stage);
    }

}