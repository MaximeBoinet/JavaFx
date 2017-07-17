package sample.view;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample.model.User;

import javax.swing.text.TabableView;
import java.util.Iterator;

/**
 * Created by MADMAX on 15/07/2017.
 */
public class UserController {
    private ObservableList<User> usersobs;

    @FXML
    private TableView<User> userTab;

    @FXML
    private TableColumn<User, String> tablename;

    @FXML
    private void initialize() {
        usersobs = FXCollections.observableArrayList();
        initMap();
        initTable();

    }

    private void initTable() {
        userTab.setItems(usersobs);
        tablename.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserName()));
    }

    private void initMap() {
        Iterator it = Main.mainApp.Users.keySet().iterator();
        while (it.hasNext()) {
            usersobs.add(Main.mainApp.Users.get(it.next()));
        }
    }
}
