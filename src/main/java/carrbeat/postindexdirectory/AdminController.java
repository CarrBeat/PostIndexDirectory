package carrbeat.postindexdirectory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private TableColumn<adminTable, String> idAdminTable;
    @FXML
    private TableColumn<adminTable, String> loginColumn;
    @FXML
    private TableColumn<adminTable, String> passwordColumn;


    @FXML
    private TableView<localityTable> localityTable;
    @FXML
    private TableColumn<localityTable, String> idLocalityTable;
    @FXML
    private TableColumn<localityTable, String> localityColumn;


    @FXML
    private TableView<carrbeat.postindexdirectory.streetTable> streetTable; // вот тут вопросик
    @FXML
    private TableColumn<carrbeat.postindexdirectory.streetTable, String> idStreetTable;
    @FXML
    private TableColumn<carrbeat.postindexdirectory.streetTable, String> nameStreetColumn;
    @FXML
    private TableColumn<carrbeat.postindexdirectory.streetTable, String> idLocalityColumn;


    @FXML
    private TableView<?> commonTable;
    @FXML
    private TableColumn<?, ?> idCommonColumn;
    @FXML
    private TableColumn<?, ?> idStreetColumn;
    @FXML
    private TableColumn<?, ?> idHouseNumColumn;
    @FXML
    private TableColumn<?, ?> idLocalityColumnCom;


    @FXML
    private TableView<?> houseNumTable;
    @FXML
    private TableColumn<?, ?> idHouseNumTable;
    @FXML
    private TableColumn<?, ?> houseNumColumn;
    @FXML
    private TableColumn<?, ?> postIndexColumn;


    @FXML
    private TextField tableNum;
    @FXML
    private TextField idTable;
    @FXML
    private TextField columnOne;
    @FXML
    private TextField columnThree;
    @FXML
    private TextField columnTwo;


    @FXML
    private Button loginPasswordButton;
    @FXML
    private Button loginPasswordButton1;
    @FXML
    private Button loginPasswordButton11;


    ObservableList<adminTable> adminTableData = FXCollections.observableArrayList();
    ObservableList<localityTable> localityTableData = FXCollections.observableArrayList();
    ObservableList<streetTable> streetTableData = FXCollections.observableArrayList();
    ObservableList<commonTable> commonTableData = FXCollections.observableArrayList();

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

        try {
            Connection connection = DBConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM street");
            while (resultSet.next()) {
                streetTableData.add(new streetTable(resultSet.getString("idstreet"),
                        resultSet.getString("street name"),  resultSet.getString("street_idlocality")));
            }
            idStreetTable.setCellValueFactory(new PropertyValueFactory<>("idStreet"));
            nameStreetColumn.setCellValueFactory(new PropertyValueFactory<>("nameStreet"));
            idLocalityColumn.setCellValueFactory(new PropertyValueFactory<>("idLocalityCol"));
            streetTable.setItems(streetTableData);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }



    }

}
