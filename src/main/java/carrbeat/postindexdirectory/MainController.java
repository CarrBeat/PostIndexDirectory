package carrbeat.postindexdirectory;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.text.Normalizer;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

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
    private ComboBox<String> locality;

    @FXML
    private Button logInButton;

    @FXML
    private TextField outputField;

    @FXML
    private TextField postIndex;

    @FXML
    private ComboBox<?> streetName;

    @FXML
    void initialize() throws NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Main.takeLocality();
        locality.setItems(Main.localityList);
        new ComboBoxAutoComplete<>(locality);
        logInButton.setOnAction(event -> {
            try {
                if (!Main.isAuthorized()) {
                    openAuthorizeMethod();
                    outputField.setText("");
                } else {
                    outputField.setText("Ошибка - авторизация уже пройдена!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        editDButton.setOnAction(event -> {
            try {
                if (Main.isAuthorized()) {
                    openAdminMethod();
                    outputField.setText("");
                } else {
                    outputField.setText("Ошибка - авторизация не пройдена!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        new ComboBoxAutoComplete<>(locality);
    }

    public void openAuthorizeMethod() throws Exception{
        Authorize authWindow = new Authorize();
        authWindow.showWindow();
    }

    public void openAdminMethod() throws Exception{
        Admin adminWindow = new Admin();
        adminWindow.showAdminWindow();
    }

}