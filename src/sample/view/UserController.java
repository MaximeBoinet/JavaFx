package sample.view;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.Game;
import sample.model.User;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by MADMAX on 15/07/2017.
 */
public class UserController {
    private ObservableList<User> usersobs;
    private ObservableList<Game> gamesobs;

    private String currentUserId;
    private String currentUserGameId;

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
    private TableView<Game> userGameTab;
    @FXML
    private TableColumn<Game, LocalDate> playedOn;
    @FXML
    private TableColumn<Game, String> finished;
    @FXML
    private TableColumn<Game, Integer> songNumber;

    @FXML
    private TableView<Game> userGameScoreTab;

    @FXML
    private Label userName;
    @FXML
    private Label totalGame;


    @FXML
    private void initialize() {
        usersobs = FXCollections.observableArrayList();
        gamesobs = FXCollections.observableArrayList();
        initMap();
        initTable();
        userName.setText("");
        totalGame.setText("");
    }

    private void initTable() {
        initTableUser();
        initTableGame();
    }

    private void initTableUser() {
        this.userTab.setItems(usersobs);
        this.tablename.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserName()));
        this.tableGold.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getGold().doubleValue()));
        this.tableSince.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getCreated_at()));
        this.tableRank.setCellValueFactory(cellData -> new SimpleStringProperty(Main.Ranks.get(cellData.getValue().getRank()).getTitle()));
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

    private void initMap() {
        Iterator it = Main.mainApp.Users.keySet().iterator();
        while (it.hasNext()) {
            usersobs.add(Main.mainApp.Users.get(it.next()));
        }
    }

    public void setUserDetail(User userDetail) {
        this.currentUserId = userDetail.get_id();
        userName.setText(userDetail.getUserName());
        totalGame.setText(String.valueOf(userDetail.getGames().length));
        setGameUser();
    }

    public void setGameUser() {
        gamesobs.clear();

        for (String id: Main.Users.get(this.currentUserId).getGames()) {
            gamesobs.add(Main.Games.get(id));
        }
    }

    public void setUserGameScore(Game userGame) {
        this.currentUserGameId = userGame.toString();
    }
}
