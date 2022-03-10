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
    private TextField IDtable;

    @FXML
    private Button addString;

    @FXML
    private TableView<adminTable> adminTable;

    @FXML
    private TextField column1;

    @FXML
    private TextField column2;

    @FXML
    private TextField column3;

    @FXML
    private TableView<?> commonTable;

    @FXML
    private TableColumn<?, ?> houseNumColumn;

    @FXML
    private TableView<?> houseNumTable;

    @FXML
    private TableColumn<adminTable, String> idColumnTable1;

    @FXML
    private TableColumn<?, ?> idColumnTable2;

    @FXML
    private TableColumn<?, ?> idColumnTable3;

    @FXML
    private TableColumn<?, ?> idColumnTable4;

    @FXML
    private TableColumn<?, ?> idColumnTable5;

    @FXML
    private TableColumn<?, ?> idHouseNumColumn;

    @FXML
    private TableColumn<?, ?> idLocalityColumn;

    @FXML
    private TableColumn<?, ?> idStreetColumn;

    @FXML
    private TableColumn<?, ?> localityColumn;

    @FXML
    private TableView<?> localityTable;

    @FXML
    private TableColumn<adminTable, String> loginColumn;

    @FXML
    private TableColumn<?, ?> nameStreetColumn;

    @FXML
    private TableColumn<adminTable, String> passwordColumn;

    @FXML
    private TableColumn<?, ?> postIndexColumn;

    @FXML
    private Button removeString;

    @FXML
    private Button saveChanges;

    @FXML
    private TableView<?> streetTable;

    @FXML
    private TextField tableNum;

    ObservableList<adminTable> adminTableData = FXCollections.observableArrayList();


    public void initialize() throws SQLException {
        Connection connection = DBConnection.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM admin");
        while (resultSet.next()){
            adminTableData.add(new adminTable(resultSet.getString("id"),
                    resultSet.getString("login"), resultSet.getString("password")));
        }

        idColumnTable1.setCellValueFactory(new PropertyValueFactory<>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        adminTable.setItems(adminTableData);
    }

}