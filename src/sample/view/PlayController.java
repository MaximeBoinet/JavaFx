package sample.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.Playlist;
import sample.model.Song;

import java.time.LocalDate;
import java.util.Iterator;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by MADMAX on 19/07/2017.
 */
public class PlayController {
    private ObservableList<Playlist> playlistsobs;
    private ObservableList<Song> playlistSongsobs;
    private String currentIdPlaylist;

    @FXML
    private TableView<Song> playlistSong;
    @FXML
    private TableColumn<Song, LocalDate> createdSongP;
    @FXML
    private TableColumn<Song, String> titleSongP;
    @FXML
    private TableColumn<Song, String> artistSongP;

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
    private Label allsongs;

    @FXML
    public void initialize() {
        initLabel();
        initMap();
        initTable();
    }

    private void initTable() {
        initTablePlaylist();
        initTableSong();
    }

    private void initMap() {
        this.playlistsobs = observableArrayList();
        this.playlistSongsobs = observableArrayList();
        this.playlistsobs.addAll(Main.Playlists.values());
    }

    private void initLabel() {
        allsongs.setText("");
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

    private void initTableSong() {
        this.playlistSong.setItems(this.playlistSongsobs);
        this.createdSongP.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreated_at()));
        this.titleSongP.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.artistSongP.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue().getArtist().length >= 1? Main.Artist.get(cellData.getValue().getArtist()[0]).getTitle() : "NA")));
    }

    private void setPlayListDetail(Playlist newValue) {
        playlistSongsobs.clear();
        allsongs.setText("");
        if (newValue != null) {
            this.currentIdPlaylist = newValue.get_id();
            for (String id: Main.Playlists.get(currentIdPlaylist).getSongs())
                playlistSongsobs.add(Main.Songs.get(id));
            allsongs.setText(String.valueOf(playlistSongsobs.size()));
        }
    }
}
