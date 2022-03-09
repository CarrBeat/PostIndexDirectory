package carrbeat.postindexdirectory;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {

    @FXML
    private Button searchByIndex;

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
    private ListView<String> tableView;

    @FXML
    void initialize() throws NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Main.takeLocality();
        Main.knowPostIndexes();

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
                    outputField.setText("");
                    outputField.setPromptText("Сюда выводится населённый пункт, а также ошибки");
                    Main.knowSelectedLocalityID(selectedLocality);
                } catch (SQLException | ClassNotFoundException | InvocationTargetException
                        | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        streetName.setOnAction(event -> {
            Main.houseNum_idHouseNum.clear();
            Main.housesList.clear();
            String selectedStreet = streetName.getValue();
            if (selectedStreet != null){
                try {
                    outputField.setPromptText("Сюда выводится населённый пункт, а также ошибки");
                    Main.knowSelectedStreetID(selectedStreet);
                } catch (SQLException | ClassNotFoundException | InvocationTargetException
                        | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        houseNum.setOnAction(event -> {
            Main.houseNum_idHouseNum.clear();
            String selectedHouseNum = houseNum.getValue();
            Main.knowPostIndex(selectedHouseNum);
            postIndexItem.setText(Main.postIndex);
            Main.street_houseNum.clear();
            outputField.setPromptText("Сюда выводится населённый пункт, а также ошибки");
        });

        reset.setOnAction(event -> {
            Main.resetAll();
            outputField.setText("");
            outputField.setPromptText("Сюда выводится населённый пункт, а также ошибки");
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

            searchByIndex.setOnAction(event -> {
                if(postIndexItem.getText().length() == 6 & Main.indexesList.contains(postIndexItem.getText())){
                    try {
                        Main.street_houseNum.clear();
                        Main.searchAdresses(postIndexItem.getText());
                    } catch (SQLException | IllegalAccessException | InstantiationException
                            | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    outputField.setText(Main.searchedLocality + ":");
                    tableView.setItems(Main.street_houseNum);
                } else {
                    outputField.setText("Неверный индекс!");
                    postIndexItem.setText("");
                    postIndexItem.setPromptText("000000");
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