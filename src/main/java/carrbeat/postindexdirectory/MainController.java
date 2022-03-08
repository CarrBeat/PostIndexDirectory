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
    private Button reset;

    @FXML
    private ComboBox<String> houseNum;

    @FXML
    private ComboBox<String> locality;

    @FXML
    private Button logInButton;

    @FXML
    private TextField outputField;

    @FXML
    private TextField postIndexItem;

    @FXML
    private ComboBox<String> streetName;


    @FXML
    void initialize() throws NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Main.takeLocality();

        locality.setItems(Main.localityList);
        streetName.setItems(Main.streetsList);
        houseNum.setItems(Main.housesList);

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
            Main.streetsList.clear();
            String selectedLocality = locality.getValue();
            if (selectedLocality != null) {
                try {
                    Main.knowSelectedLocalityID(selectedLocality);
                } catch (SQLException | ClassNotFoundException | InvocationTargetException
                        | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        streetName.setOnAction(event -> {
            Main.housenum_idhouse_num.clear();
            Main.housesList.clear();
            String selectedStreet = streetName.getValue();
            if (selectedStreet != null){
                try {
                    Main.knowSelectedStreetID(selectedStreet);
                } catch (SQLException | ClassNotFoundException | InvocationTargetException
                        | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        houseNum.setOnAction(event -> {
            Main.housenum_idhouse_num.clear();
            String selectedHouseNum = houseNum.getValue();
            Main.knowPostIndex(selectedHouseNum);
            postIndexItem.setText(Main.postIndex);
        });

        reset.setOnAction(event -> {
            Main.resetAll();
            locality.setPromptText("Здесь укажите населённый пункт!");
            streetName.setPromptText("Сюда введите улицу!");
            houseNum.setPromptText("Сюда введите номер дома (строения/корпуса)");
            try {
                Main.takeLocality();
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                    | InstantiationException | IllegalAccessException | SQLException e) {
                e.printStackTrace();
            }
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