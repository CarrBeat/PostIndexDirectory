package carrbeat.postindexdirectory;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import java.util.TimeZone;

public class Admin extends Application {
    Stage stage = new Stage();
    private TableView<Person> tableView = new TableView();



    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 630, 480);
        stage.setTitle("Окно администратора");
        stage.setScene(scene);
        stage.show();
        Parent root = tableView;
    }

    public static void main(String[] args) { launch(args); }

    
    void showAdminWindow() throws Exception{
        start(stage);
    }

    public void buildData() throws ClassNotFoundException, SQLException {

        Connection dbConnection;
        //SQL Database connection params
        String dbHost = "localhost";
        String dbPort = "3306";
        String dbUser = "root";
        String dbPassword = "carrbeat";
        String dbName = "postindexdirectory";
        String dbTableName = "admin";
        String select = "SELECT * FROM admin";
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort +"/" + dbName+"?useLegacyDatetimeCode=false&amp&serverTimezone=" + TimeZone.getDefault().getID();
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Connecting to Database
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        //Extracting data from Databasee
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList dbData = FXCollections.observableArrayList(dataBaseArrayList(resultSet));

        //Giving readable names to columns
        for(int i=0 ; i<resultSet.getMetaData().getColumnCount(); i++) {
            TableColumn column = new TableColumn<>();
            switch (resultSet.getMetaData().getColumnName(i+1)) {
                case "id":
                    column.setText("ID");
                    break;
                case "login":
                    column.setText("Логин");
                    break;
                case "password":
                    column.setText("Пароль");
                    break;
                default: column.setText(resultSet.getMetaData().getColumnName(i+1)); //if column name in SQL Database is not found, then TableView column receive SQL Database current column name (not readable)
                    break;
            }
            column.setCellValueFactory(new PropertyValueFactory<>(resultSet.getMetaData().getColumnName(i+1))); //Setting cell property value to correct variable from Person class.
            tableView.getColumns().add(column);
        }

        //Filling up tableView with data
        tableView.setItems(dbData);
    }

    public class Person {

        IntegerProperty id = new SimpleIntegerProperty(); //variable names should be exactly as column names in SQL Database Table. In case if you want to use <int> type instead of <IntegerProperty>, then you need to use getter/setter procedures instead of xxxProperty() below
        StringProperty login = new SimpleStringProperty();
        BooleanProperty password = new SimpleBooleanProperty();

        public IntegerProperty idProperty() { //name should be exactly like this [IntegerProperty variable name (id) + (Property) = idProperty] (case sensitive)
            return id;
        }

        public StringProperty loginProperty() {
            return login;
        }

        public BooleanProperty passwordProperty() {
            return password;
        }

        public Person(int idValue, String nameValue, boolean marriedValue) {
            id.set(idValue);
            login.set(nameValue);
            password.set(marriedValue);
        }

        Person(){}
    }

    private ArrayList dataBaseArrayList(ResultSet resultSet) throws SQLException {
        ArrayList<Person> data =  new ArrayList<>();
        while (resultSet.next()) {
            Person person = new Person();
            person.id.set(resultSet.getInt("idadmin"));
            person.login.set(resultSet.getString("login"));
            person.password.set(resultSet.getBoolean("password"));
            data.add(person);
        }
        return data;
    }




}