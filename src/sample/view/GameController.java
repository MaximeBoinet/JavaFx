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
    private String currentGameId;

    @FXML
    private TableView<Game> gameTab;
    @FXML
    private TableColumn<Game, LocalDate> created;
    @FXML
    private TableColumn<Game, Integer> played;
    @FXML
    private TableColumn<Game, Integer> songs;


    @FXML
    private void initialize() {
        initMap();
        initTable();
    }

    private void initTable() {
        gameTab.setItems(gameobs);
        created.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreated_at()));
        played.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScore().length));
        songs.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSongs().length));

    }

    private void initMap() {
        gameobs = FXCollections.observableArrayList();
        gameobs.addAll(Main.Games.values());
    }


}
