package carrbeat.postindexdirectory;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private ComboBox<String> streetName;


    @FXML
    void initialize() throws NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Main.takeLocality();

        locality.setItems(Main.localityList);
        streetName.setItems(Main.streetsList);

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


        locality.setOnAction(event -> {
            Main.variablesClear();
            String selectedLocality = locality.getValue();
            try {
                Main.knowSelectedLocalityID(selectedLocality);
            } catch (SQLException | ClassNotFoundException | InvocationTargetException
                    | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });


        streetName.setOnAction(event -> {

        });

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