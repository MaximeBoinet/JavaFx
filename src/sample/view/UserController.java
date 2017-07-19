package sample.view;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by MADMAX on 15/07/2017.
 */
public class UserController {
    private ObservableList<User> usersobs;
    private ObservableList<Rewards> userRewardsobs;
    private ObservableList<User> userfriendsobs;
    private ObservableList<Game> gamesobs;
    private ObservableList<Song> songobs;
    private ObservableList<Playlist> playobs;
    private ObservableList<Song> playsongsobs;
    private ObservableList<PieChart.Data> chartobs;

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
    private TableView<User> userTabFriend;
    @FXML
    private TableColumn<User, String> tablenameFriend;
    @FXML
    private TableColumn<User, Double> tableGoldFriend;
    @FXML
    private TableColumn<User, LocalDate> tableSinceFriend;
    @FXML
    private TableColumn<User, String> tableRankFriend;
    @FXML
    private TableColumn<User, Integer> tableglobScoreFriend;

    @FXML
    private TableView<Rewards> userTabReward;
    @FXML
    private TableColumn<Rewards, String> tableTitleReward;
    @FXML
    private TableColumn<Rewards, String> tableTypeReward;
    @FXML
    private TableColumn<Rewards, Integer> tablePriceReward;


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
    private Label totalUser;
    @FXML
    private Label totalReward;
    @FXML
    private Label totalFriend;

    @FXML
    public PieChart pieCh;


    @FXML
    private void initialize() {
        initLabel();
        initMap();
        initTable();
    }

    private void initLabel() {
        this.totalUser.setText(String.valueOf(Main.Users.keySet().size()));
        this.userName.setText("");
        this.totalGame.setText("");
        this.scoretot.setText("");
        this.userNamePLay.setText("");
        this.totalPlaylist.setText("");
        this.nomPlay.setText("");
        this.totalFriend.setText("");
        this.totalReward.setText("");
    }

    private void initTable() {
        initTableUser();
        initTableGame();
        initTableSong();
        initTablePlaylist();
        initTablePlaylistSong();
        initTableUserFriends();
        initTabmeUserRewards();
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
        this.songsPlayTitle.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue().getTitle())));
    }

    private void initTableUserFriends() {
        this.userTabFriend.setItems(userfriendsobs);
        this.tablenameFriend.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserName()));
        this.tableGoldFriend.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getGold().doubleValue()));
        this.tableSinceFriend.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getCreated_at()));
        this.tableRankFriend.setCellValueFactory(cellData -> new SimpleStringProperty(Main.Ranks.get(cellData.getValue().getRank()).getTitle()));
        this.tableglobScoreFriend.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getGlobalScore()));
    }

    private void initTabmeUserRewards() {
        this.userTabReward.setItems(userRewardsobs);
        this.tableTitleReward.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.tableTypeReward.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue().getType())));
        this.tablePriceReward.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getGoldToAcces()));
    }

    private void initMap() {
        this.usersobs = observableArrayList();
        this.gamesobs = observableArrayList();
        this.songobs = observableArrayList();
        this.playsongsobs = observableArrayList();
        this.playobs = observableArrayList();
        this.chartobs = observableArrayList();
        this.userfriendsobs = observableArrayList();
        this.userRewardsobs = observableArrayList();
        Iterator it = Main.mainApp.Users.keySet().iterator();
        HashMap<String, Integer> ranks = new HashMap<>();
        for (Object key: Main.Ranks.keySet()){
            ranks.put(Main.Ranks.get(key).getTitle(),0);
        }
        while (it.hasNext()) {
            String key = it.next().toString();
            String title = Main.Ranks.get(Main.Users.get(key).getRank()).getTitle();
            ranks.put(title, ranks.get(title) + 1);
            usersobs.add(Main.Users.get(key));
        }
        it = ranks.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            chartobs.add(new PieChart.Data(key, ranks.get(key)));
        }
        pieCh.setData(chartobs);
        pieCh.setLabelsVisible(true);
        pieCh.setLabelLineLength(10);
        pieCh.setTitle("Users ranks");
    }

    public void setUserDetail(User userDetail) {
        this.currentUserId = userDetail.get_id();
        this.scoretot.setText("");
        this.nomPlay.setText("");
        this.userNamePLay.setText("");
        this.totalPlaylist.setText("");
        this.totalFriend.setText("");
        this.totalReward.setText("");
        this.userName.setText(userDetail.getUserName());
        this.totalGame.setText(String.valueOf(userDetail.getGames().length));
        this.songobs.clear();
        this.playsongsobs.clear();
        this.userfriendsobs.clear();
        setGameUser();
        setPlayListUser();
        setFriendUser();
        setRewardUser();
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
        totalPlaylist.setText(String.valueOf(playobs.size()));
        userNamePLay.setText(Main.Users.get(this.currentUserId).getUserName());
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

    private void setRewardUser() {
        userRewardsobs.clear();
        for (String id: Main.Users.get(this.currentUserId).getRewards())
            userRewardsobs.add(Main.Rewards.get(id));
        totalReward.setText(String.valueOf(userRewardsobs.size()));
    }

    private void setFriendUser() {
        userfriendsobs.clear();
        for (String id: Main.Users.get(this.currentUserId).getFriends())
            userfriendsobs.add(Main.Users.get(id));
        totalFriend.setText(String.valueOf(userfriendsobs.size()));
    }

}
