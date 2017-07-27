package sample.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.AssossiativScorePlayer;
import sample.model.Game;
import sample.model.Playlist;
import sample.model.Song;

import java.time.LocalDate;
import java.util.Iterator;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by MADMAX on 17/07/2017.
 */
public class SongsController {
    private String currentSongId;
    private ObservableList songobs;
    private ObservableList songGameobs;
    private ObservableList assossobs;
    private ObservableList playlistobs;

    @FXML
    private TableView<Song> songTab;
    @FXML
    private TableColumn<Song, String> tableTitle;
    @FXML
    private TableColumn<Song, Integer> tableArtist;
    @FXML
    private TableColumn<Song, LocalDate> tableCreated;

    @FXML
    private TableView<Game> songGameTab;
    @FXML
    private TableColumn<Game, LocalDate> songGameCreated;
    @FXML
    private TableColumn<Game, Integer> songGamePlayed;

    @FXML
    private TableView<AssossiativScorePlayer> assossiativScorePlayerTableView;
    @FXML
    private TableColumn<AssossiativScorePlayer, String> assossiativScorePlayerStringTableColumn;
    @FXML
    private TableColumn<AssossiativScorePlayer, Integer> assossiativScorePlayerIntegerTableColumn;

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
        initTableSongs();
        initTableGame();
        initScoreGameTable();
        initTablePlay();
    }

    private void initTableGame() {
        this.songGameTab.setItems(songGameobs);
        this.songGameCreated.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreated_at()));
        this.songGamePlayed.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScore().length));
        this.songGameTab.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setScoreGameSongDetail(newValue));
    }

    private void initTablePlay() {
        this.playlistTabp.setItems(playlistobs);
        this.createdThep.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreated_at()));
        this.numberSongsp.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSongs().length)));
        this.titlePlaylistp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.creatorp.setCellValueFactory(cellData -> new SimpleStringProperty((Main.Users.get(cellData.getValue().getCreator()).getUserName())));
    }

    private void setScoreGameSongDetail(Game newValue) {
        assossobs.clear();
        if (newValue != null) {
            for (AssossiativScorePlayer id: newValue.getScore())
                assossobs.add(id);
        }
    }

    private void initTableSongs() {
        this.songTab.setItems(songobs);
        this.tableTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.tableArtist.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getArtist().length));
        this.tableCreated.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreated_at()));
        this.songTab.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setSongDetail(newValue));
    }

    private void initScoreGameTable() {
        this.assossiativScorePlayerTableView.setItems(assossobs);
        this.assossiativScorePlayerIntegerTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Main.Scores.get(cellData.getValue().getScore()).getScoreInGame()));
        this.assossiativScorePlayerStringTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Main.Users.get(cellData.getValue().getPlayer()).getUserName()));
    }

    private void initMap() {
        this.playlistobs = observableArrayList();
        this.songobs = observableArrayList();
        this.songGameobs = observableArrayList();
        this.assossobs = observableArrayList();
        this.songobs.addAll(Main.Songs.values());
    }

    private void initLabel() {

    }

    private void setSongDetail(Song newValue) {
        songGameobs.clear();
        playlistobs.clear();
        currentSongId = "";
        if (newValue != null) {
            currentSongId = newValue.get_id();
            Iterator i = Main.Games.keySet().iterator();
            while (i.hasNext()) {
                String id = i.next().toString();
                for (String idsong : Main.Games.get(id).getSongs()) {
                    if (idsong.equals(newValue.get_id()))
                        songGameobs.add(Main.Games.get(id));
                }
            }

            for (Playlist play : Main.Playlists.values()) {
                for (String id: play.getSongs()) {
                    if (id.equals(currentSongId)) {
                        playlistobs.add(play);
                    }
                }
            }
        }
    }
}
