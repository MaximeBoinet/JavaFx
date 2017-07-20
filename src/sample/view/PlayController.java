package sample.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.Playlist;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by MADMAX on 19/07/2017.
 */
public class PlayController {
    private ObservableList<Playlist> playlistsobs;

    @FXML
    private TableView<Playlist> playlistTab;
    @FXML
    private TableColumn<Playlist, LocalDate> createdThe;
    @FXML
    private TableColumn<Playlist, String> numberSongs;
    @FXML
    private TableColumn<Playlist, String> titlePlaylist;
    @FXML
    private TableColumn<Playlist, String> creator;

    @FXML
    public void initialize() {
        initLabel();
        initMap();
        initTable();
    }

    private void initTable() {
        initTablePlaylist();
    }

    private void initMap() {
        this.playlistsobs = observableArrayList();
        this.playlistsobs.addAll(Main.Playlists.values());
    }

    private void initLabel() {

    }

    private void initTablePlaylist() {
        this.playlistTab.setItems(playlistsobs);
        this.createdThe.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreated_at()));
        this.numberSongs.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSongs().length)));
        this.titlePlaylist.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.creator.setCellValueFactory(cellData -> new SimpleStringProperty((Main.Users.get(cellData.getValue().getCreator()).getUserName())));
        this.playlistTab.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setPlayListDetail(newValue));
    }

    private void setPlayListDetail(Playlist newValue) {

    }
}
