package carrbeat.postindexdirectory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {

    @FXML
    private TableView<adminTable> adminTable;

    @FXML
    private TextField columnOne;

    @FXML
    private TextField columnThree;

    @FXML
    private TextField columnTwo;

    @FXML
    private TableColumn<?, ?> houseNumColumn;

    @FXML
    private TableColumn<adminTable, String> idAdminTable;

    @FXML
    private TableColumn<?, ?> idCommonColumn;

    @FXML
    private TableColumn<?, ?> idHouseNumColumn;

    @FXML
    private TableColumn<?, ?> idHouseNumTable;

    @FXML
    private TableColumn<localityTable, String> idLocalityColumn;

    @FXML
    private TableColumn<?, ?> idLocalityTable;

    @FXML
    private TableColumn<?, ?> idStreetColumn;

    @FXML
    private TextField idTable;

    @FXML
    private TableColumn<localityTable, String> localityColumn;

    @FXML
    private TableView<adminTable> localityTable;

    @FXML
    private TableView<?> localityTable1;

    @FXML
    private TableView<?> localityTable11;

    @FXML
    private TableView<?> localityTable12;

    @FXML
    private TableColumn<adminTable, String> loginColumn;

    @FXML
    private Button loginPasswordButton;

    @FXML
    private Button loginPasswordButton1;

    @FXML
    private Button loginPasswordButton11;

    @FXML
    private TableColumn<?, ?> nameStreetColumn;

    @FXML
    private TableColumn<adminTable, String> passwordColumn;

    @FXML
    private TableColumn<?, ?> postIndexColumn;

    @FXML
    private TextField tableNum;

    ObservableList<adminTable> adminTableData = FXCollections.observableArrayList();
    ObservableList<adminTable> localityTableData = FXCollections.observableArrayList();

    public void initialize()  {
        try {
            Connection connection = DBConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM admin");
            while (resultSet.next()) {
                adminTableData.add(new adminTable(resultSet.getString("idadmin"),
                        resultSet.getString("login"), resultSet.getString("password")));
            }
            idAdminTable.setCellValueFactory(new PropertyValueFactory<>("id"));
            loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            adminTable.setItems(adminTableData);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        try {
            Connection connection = DBConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM locality");
            while (resultSet.next()) {
                localityTableData.add(new localityTable(resultSet.getString("idlocality"),
                        resultSet.getString("locality")));
            }
            idLocalityTable.setCellValueFactory(new PropertyValueFactory<>("idlocality"));
            localityColumn.setCellValueFactory(new PropertyValueFactory<>("locality"));
            localityTable.setItems(localityTableData);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

}