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
    private TableView<commonTable> commonTable;
    @FXML
    private TableColumn<commonTable, String> idCommonColumn;
    @FXML
    private TableColumn<commonTable, String> idStreetColumn;
    @FXML
    private TableColumn<commonTable, String> idHouseNumColumn;
    @FXML
    private TableColumn<commonTable, String> idLocalityColumnCom;

    @FXML
    private TableView<houseNumTable> houseNumTable;
    @FXML
    private TableColumn<houseNumTable, String> idHouseNumTable;
    @FXML
    private TableColumn<houseNumTable, String> houseNumColumn;
    @FXML
    private TableColumn<houseNumTable, String> postIndexColumn;

    ObservableList<adminTable> adminTableData = FXCollections.observableArrayList();
    ObservableList<localityTable> localityTableData = FXCollections.observableArrayList();
    ObservableList<streetTable> streetTableData = FXCollections.observableArrayList();
    ObservableList<commonTable> commonTableData = FXCollections.observableArrayList();
    ObservableList<houseNumTable> houseNumTableData = FXCollections.observableArrayList();

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
                        resultSet.getString("street name"),
                        resultSet.getString("street_idlocality")));
            }
            idStreetTable.setCellValueFactory(new PropertyValueFactory<>("idStreet"));
            nameStreetColumn.setCellValueFactory(new PropertyValueFactory<>("nameStreet"));
            idLocalityColumn.setCellValueFactory(new PropertyValueFactory<>("idLocalityCol"));
            streetTable.setItems(streetTableData);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        try {
            Connection connection = DBConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM street_housenum");
            while (resultSet.next()) {
                commonTableData.add(new commonTable(resultSet.getString("idstreet-house num"),
                        resultSet.getString("street_idstreet"),
                        resultSet.getString("house num_idhouse num"),
                        resultSet.getString("localityid")));
            }
            idCommonColumn.setCellValueFactory(new PropertyValueFactory<>("IdCommon"));
            idStreetColumn.setCellValueFactory(new PropertyValueFactory<>("StreetId"));
            idHouseNumColumn.setCellValueFactory(new PropertyValueFactory<>("HouseNumID"));
            idLocalityColumnCom.setCellValueFactory(new PropertyValueFactory<>("LocalityID"));
            commonTable.setItems(commonTableData);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        try {
            Connection connection = DBConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM house_num");
            while (resultSet.next()) {
                houseNumTableData.add(new houseNumTable(resultSet.getString("idhousenum"),
                        resultSet.getString("num_house"),  resultSet.getString("post_index")));
            }
            idHouseNumTable.setCellValueFactory(new PropertyValueFactory<>("IdhouseNum"));
            houseNumColumn.setCellValueFactory(new PropertyValueFactory<>("HouseNum"));
            postIndexColumn.setCellValueFactory(new PropertyValueFactory<>("PostIndex"));
            houseNumTable.setItems(houseNumTableData);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
