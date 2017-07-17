package sample.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.Game;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by MADMAX on 17/07/2017.
 */
public class GameController {
    private ObservableList<Game> gameobs;

    @FXML
    private TableView<Game> gameTab;

    @FXML
    private TableColumn<Game, LocalDate> created;



    @FXML
    private void initialize() {
        gameobs = FXCollections.observableArrayList();
        initMap();
        initTable();
    }

    private void initTable() {
        gameTab.setItems(gameobs);
        created.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreated_at()));
    }

    private void initMap() {
        Iterator it = Main.mainApp.Games.keySet().iterator();
        while (it.hasNext()) {
            gameobs.add(Main.mainApp.Games.get(it.next()));
        }
    }


}
