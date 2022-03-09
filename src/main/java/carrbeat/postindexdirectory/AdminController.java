package carrbeat.postindexdirectory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import javax.swing.table.TableColumn;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class AdminController {

    @FXML
    private Button houseButton;

    @FXML
    private Button houseHomeNumButton;

    @FXML
    private Button localityButton;

    @FXML
    private Button loginPasswordButton;

    @FXML
    private Button streetButton;

    @FXML
    private ListView<String> tableView;



    private final ObservableList<Admin.Person> adminDataTable = FXCollections.observableArrayList();

    public void initialize(){
        column
    }

    
}
