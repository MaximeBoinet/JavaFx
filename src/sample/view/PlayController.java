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
import java.util.Iterator;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by MADMAX on 19/07/2017.
 */
public class PlayController {
    private ObservableList<Playlist> playlistsobs;
    private String currentIdPlaylist;

    @FXML
    private TableView<Playlist> playlistTabp;
    @FXML
    private TableColumn<Playlist, LocalDate> createdThep;
    @FXML
    private TableColumn<Playlist, String> numberSongsp;
    @FXML
    private TableColumn<Playlist, String> titlePlaylistp;
    @FXML
    private TableColumn<Playlist, String> creatorp;

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
        this.playlistTabp.setItems(playlistsobs);
        this.createdThep.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreated_at()));
        this.numberSongsp.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSongs().length)));
        this.titlePlaylistp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.creatorp.setCellValueFactory(cellData -> new SimpleStringProperty((Main.Users.get(cellData.getValue().getCreator()).getUserName())));
        this.playlistTabp.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setPlayListDetail(newValue));
    }

    private void setPlayListDetail(Playlist newValue) {
        this.currentIdPlaylist = newValue.get_id();
        Iterator i = Main.Songs.keySet().iterator();
    }
}
