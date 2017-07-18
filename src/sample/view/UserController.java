package sample.view;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.*;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by MADMAX on 15/07/2017.
 */
public class UserController {
    private ObservableList<User> usersobs;
    private ObservableList<Game> gamesobs;
    private ObservableList<Song> songobs;
    private ObservableList<Playlist> playobs;
    private ObservableList<Song> playsongsobs;

    private String currentUserId;
    private String currentUserGameId;
    private String currentUserPlaylistId;

    @FXML
    private TableView<User> userTab;
    @FXML
    private TableColumn<User, String> tablename;
    @FXML
    private TableColumn<User, Double> tableGold;
    @FXML
    private TableColumn<User, LocalDate> tableSince;
    @FXML
    private TableColumn<User, String> tableRank;
    @FXML
    private TableColumn<User, Integer> tableglobScore;

    @FXML
    private TableView<Game> userGameTab;
    @FXML
    private TableColumn<Game, LocalDate> playedOn;
    @FXML
    private TableColumn<Game, String> finished;
    @FXML
    private TableColumn<Game, Integer> songNumber;

    @FXML
    private TableView<Song> userGameScoreTab;
    @FXML
    private TableColumn<Song, String> songTitle;

    @FXML
    private TableView<Playlist> userPlaylistTab;
    @FXML
    private TableColumn<Playlist, LocalDate> createdThe;
    @FXML
    private TableColumn<Playlist, String> numberPlay;
    @FXML
    private TableColumn<Playlist, String> titlePlaylist;
    @FXML
    private TableColumn<Playlist, String> isPublic;

    @FXML
    private TableView<Song> userPlaylistSongsTab;
    @FXML
    private TableColumn<Song, String> songsPlayTitle;

    @FXML
    private Label userName;
    @FXML
    private Label userNamePLay;
    @FXML
    private Label totalGame;
    @FXML
    private Label totalPlaylist;
    @FXML
    private Label scoretot;
    @FXML
    private Label nomPlay;



    @FXML
    private void initialize() {
        initLabel();
        initMap();
        initTable();
    }

    private void initLabel() {
        this.userName.setText("");
        this.totalGame.setText("");
        this.scoretot.setText("");
        this.userNamePLay.setText("");
        this.totalPlaylist.setText("");
        this.nomPlay.setText("");
    }

    private void initTable() {
        initTableUser();
        initTableGame();
        initTableSong();
        initTablePlaylist();
        initTablePlaylistSong();
    }

    private void initTableUser() {
        this.userTab.setItems(usersobs);
        this.tablename.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserName()));
        this.tableGold.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getGold().doubleValue()));
        this.tableSince.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getCreated_at()));
        this.tableRank.setCellValueFactory(cellData -> new SimpleStringProperty(Main.Ranks.get(cellData.getValue().getRank()).getTitle()));
        this.tableglobScore.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getGlobalScore()));
        this.userTab.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setUserDetail(newValue));
    }

    private void initTableGame() {
        this.userGameTab.setItems(gamesobs);
        this.playedOn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Main.Scores.get(cellData.getValue().getScoreUser(currentUserId)).getCreated_at()));
        this.finished.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue().isFinished() ? "Yes" : "No")));
        this.songNumber.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSongs().length));
        this.userGameTab.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setUserGameScore(newValue));
    }

    private void initTableSong() {
        this.userGameScoreTab.setItems(songobs);
        this.songTitle.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTitle()));
    }

    private void initTablePlaylist() {
        this.userPlaylistTab.setItems(playobs);
        this.createdThe.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreated_at()));
        this.numberPlay.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSongs().length)));
        this.titlePlaylist.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.isPublic.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue().getPublic() ? "Yes" : "No")));
        this.userPlaylistTab.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setPlayListUserSongs(newValue));
    }

    private void initTablePlaylistSong() {
        this.userPlaylistSongsTab.setItems(playsongsobs);
    }

    private void initMap() {
        this.usersobs = FXCollections.observableArrayList();
        this.gamesobs = FXCollections.observableArrayList();
        this.songobs = FXCollections.observableArrayList();
        this.playsongsobs = FXCollections.observableArrayList();
        this.playobs = FXCollections.observableArrayList();
        Iterator it = Main.mainApp.Users.keySet().iterator();
        while (it.hasNext()) {
            usersobs.add(Main.mainApp.Users.get(it.next()));
        }
    }

    public void setUserDetail(User userDetail) {
        this.currentUserId = userDetail.get_id();
        this.scoretot.setText("");
        userName.setText(userDetail.getUserName());
        totalGame.setText(String.valueOf(userDetail.getGames().length));
        songobs.clear();
        setGameUser();
        setPlayListUser();
    }

    public void setGameUser() {
        gamesobs.clear();
        for (String id: Main.Users.get(this.currentUserId).getGames())
            gamesobs.add(Main.Games.get(id));
    }

    public void setUserGameScore(Game userGame) {
        if (userGame != null) {
            this.currentUserGameId = userGame.get_id();
            scoretot.setText(String.valueOf(Main.Scores.get(Main.Games.get(currentUserGameId).getScoreUser(currentUserId)).getScoreInGame()));
            songobs.clear();
            for (String id : Main.Games.get(currentUserGameId).getSongs())
                songobs.add(Main.Songs.get(id));
        }
    }

    public void setPlayListUser() {
        playobs.clear();
        for (String id: Main.Users.get(this.currentUserId).getPlaylists())
            playobs.add(Main.Playlists.get(id));
    }

    public void setPlayListUserSongs(Playlist play) {
        if (play != null) {
            this.currentUserPlaylistId = play.get_id();
            nomPlay.setText(play.getTitle());
            playsongsobs.clear();
            for (String id: Main.Playlists.get(play.get_id()).getSongs())
                playsongsobs.add(Main.Songs.get(id));
        }
    }
}
