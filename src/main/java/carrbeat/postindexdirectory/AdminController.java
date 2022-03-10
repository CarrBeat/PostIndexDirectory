package carrbeat.postindexdirectory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
public class AdminController {


    @FXML
    private TextField IDtable;

    @FXML
    private Button addString;

    @FXML
    private TableView<?> adminTable;

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
    private TableColumn<?, ?> idColumnTable1;

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
    private TableColumn<?, ?> loginColumn;

    @FXML
    private TableColumn<?, ?> nameStreetColumn;

    @FXML
    private TableColumn<?, ?> passwordColumn;

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


    public void initialize(){

    }
    
}