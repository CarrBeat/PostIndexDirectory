package carrbeat.postindexdirectory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthorizeController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authorizeButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        authorizeButton.setOnAction(event -> {
            String Login = loginField.getText();
            String Password = passwordField.getText();
            if (!Login.isEmpty() | !Password.isEmpty()){
                if (Authorize.authorization(Login, Password)) {
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                }
                loginField.setText("");
                passwordField.setText("");
            } else{
                loginField.setPromptText("Логин неверный!");
                passwordField.setPromptText("Пароль неверный!");
            }
        });
    }
}