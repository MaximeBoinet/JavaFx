package sample.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.AssossiativScorePlayer;
import sample.model.Game;

import java.time.LocalDate;
import java.util.Iterator;

import static javafx.collections.FXCollections.*;

/**
 * Created by MADMAX on 17/07/2017.
 */
public class GameController {
    private ObservableList<PieChart.Data> avscoreobs;
    private ObservableList<PieChart.Data> avgames;
    private ObservableList<Game> gameobs;
    private ObservableList<AssossiativScorePlayer> assossobs;
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
    private TableView<AssossiativScorePlayer> gameScoreTab;
    @FXML
    private TableColumn<AssossiativScorePlayer, Integer> scoreg;
    @FXML
    private TableColumn<AssossiativScorePlayer, String> userg;

    @FXML
    private Label totalscore;
    @FXML
    private Label totalgame;
    @FXML
    private Label totalplayed;
    @FXML
    private Label averagescore;
    @FXML
    private Label averageplayed;


    @FXML
    private PieChart avscopie;
    @FXML
    private PieChart avplaypie;

    @FXML
    private StackedBarChart averageplayscor;

    @FXML
    private void initialize() {
        initMap();
        initTable();
    }

    private void initTable() {
        initTableGame();
        initTableAssos();
        initLabel();
        initData();
    }

    private void initData() {
        int cptplayed = Main.Games.values().size();
        int cptscore = 0;
        int totplayed = 0;
        int totscore = 0;
        for (Game g: Main.Games.values()) {
            totplayed += g.getScore().length;
            cptscore += g.getScore().length;
            for (AssossiativScorePlayer ass: g.getScore()) {
                totscore += Main.Scores.get(ass.getScore()).getScoreInGame();
            }
        }
        int averagePlayed = totplayed/cptplayed;
        int averageScore = totscore/cptscore;
        int abovePlayed = 0;
        int intoAveragePlayed = 0;
        int belowPlayed = 0;
        int aboveScore = 0;
        int intoAverageScore = 0;
        int belowScore = 0;


    }

    private void initLabel() {
        totalscore.setText("");
        totalgame.setText(String.valueOf(Main.Games.keySet().size()));
        Iterator it = Main.Games.keySet().iterator();
        int tot = 0;
        while (it.hasNext())
            tot += Main.Games.get(it.next()).getScore().length;
        totalplayed.setText(String.valueOf(tot));
    }

    private void initTableAssos() {
        gameScoreTab.setItems(assossobs);
        scoreg.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Main.Scores.get(cellData.getValue().getScore()).getScoreInGame()));
        userg.setCellValueFactory(cellData -> new SimpleStringProperty(Main.Users.get(cellData.getValue().getPlayer()).getUserName()));
    }

    private void initTableGame() {
        gameTab.setItems(gameobs);
        created.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreated_at()));
        played.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScore().length));
        songs.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSongs().length));
        gameTab.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setAssoss(newValue));
    }

    private void setAssoss(Game newValue) {
        this.totalscore.setText("");
        this.assossobs.clear();
        if (newValue != null) {
            this.totalscore.setText(String.valueOf(newValue.getScore().length));
            for (AssossiativScorePlayer assoss : newValue.getScore())
                assossobs.add(assoss);
        }
    }

    private void initMap() {
        this.assossobs = observableArrayList();
        this.gameobs = observableArrayList();
        this.gameobs.addAll(Main.Games.values());
    }


}
