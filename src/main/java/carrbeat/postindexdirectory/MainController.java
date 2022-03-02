package carrbeat.postindexdirectory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button editDButton;

    @FXML
    private Button guide;

    @FXML
    private ComboBox<?> houseNum;

    @FXML
    private ComboBox<?> locality;

    @FXML
    private Button logInButton;
    

    @FXML
    private TextField outputField;

    @FXML
    private TextField postIndex;

    @FXML
    private ComboBox<?> streetName;



    @FXML
    void initialize() {
            logInButton.setOnAction(event -> {
                try {
                    if (!MainWindow.isAuthorized()){
                        openAuthorizeMethod();
                    } else{
                        outputField.setText("Ошибка - авторизация уже пройдена!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }
    public void openAuthorizeMethod() throws Exception{
        AuthorizeWindow authWindow = new AuthorizeWindow();
        authWindow.showWindow();
    }
}
