package sample.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.model.AssossiativScorePlayer;
import sample.model.Game;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
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
        System.out.print("Ok1");
        int cptplayed = Main.Games.values().size();
        int cptscore = 0;
        int totplayed = 0;
        int totscore = 0;
        for (Game g: Main.Games.values()) {
            totplayed += g.getScore().length;
            cptscore += g.getScore().length;
            for (AssossiativScorePlayer ass: g.getScore())
                totscore += Main.Scores.get(ass.getScore()).getScoreInGame();
        }
        int averagePlayed = totplayed/cptplayed;
        int averageScore = totscore/cptscore;
        averageplayed.setText(String.valueOf(averagePlayed));
        averagescore.setText(String.valueOf(averageScore));
        int[][] sbc = {{0,0,0},{0,0,0},{0,0,0}};
        for (Game g: Main.Games.values()) {
            for (AssossiativScorePlayer ass: g.getScore()) {
                int score = Main.Scores.get(ass.getScore()).getScoreInGame();
                if (g.getScore().length > averagePlayed) {
                    if ( score > averageScore)
                        sbc[2][2] ++;
                    else if (score < averageScore)
                        sbc[2][0] ++;
                    else
                        sbc[2][1] ++;
                } else if(g.getScore().length < averagePlayed) {
                    if ( score > averageScore)
                        sbc[0][2] ++;
                    else if (score < averageScore)
                        sbc[0][0] ++;
                    else
                        sbc[0][1] ++;
                } else {
                    if ( score > averageScore)
                        sbc[1][2] ++;
                    else if (score < averageScore)
                        sbc[1][0] ++;
                    else
                        sbc[1][1] ++;
                }
            }
        }
        initPieChartScore(sbc);
        initPieChartPlayed(sbc);
        initStackedBar(sbc, totplayed, totscore);
    }

    private void initStackedBar(int[][] sbc, int totplayed, int totscore) {
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Played");
        xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList("Played below av", "Played av", "Played above av")));
        yAxis.setLabel("Value %");
        averageplayscor.setTitle("Average played by average score");
        series1.setName("Score below av");
        series1.getData().add(new XYChart.Data<>("Played below av", sbc[0][0]* (100/totplayed)));
        series1.getData().add(new XYChart.Data<>("Played av", sbc[1][0]* (100/totplayed)));
        series1.getData().add(new XYChart.Data<>("Played above av", sbc[2][0]* (100/totplayed)));
        series2.setName("Score average");
        series2.getData().add(new XYChart.Data<>("Played below av", sbc[0][1]* (100/totplayed)));
        series2.getData().add(new XYChart.Data<>("Played av", sbc[1][1]* (100/totplayed)));
        series2.getData().add(new XYChart.Data<>("Played above av", sbc[2][1]* (100/totplayed)));
        series3.setName("Score above av");
        series3.getData().add(new XYChart.Data<>("Played below av", sbc[0][2]* (100/totplayed)));
        series3.getData().add(new XYChart.Data<>("Played av", sbc[1][2]* (100/totplayed)));
        series3.getData().add(new XYChart.Data<>("Played above av", sbc[2][2]* (100/totplayed)));
        averageplayscor.getData().addAll(series1,series2,series3);

    }

    private void initPieChartScore(int[][] sbc) {
        avscoreobs =
                FXCollections.observableArrayList(
                        new PieChart.Data("AboveAverage", sbc[0][2]+sbc[1][2]+sbc[2][2]),
                        new PieChart.Data("Average", sbc[0][1]+sbc[1][1]+sbc[2][1]),
                        new PieChart.Data("BelowAverage", sbc[0][0]+sbc[1][0]+sbc[2][0]));
        avscopie.setData(avscoreobs);
        avscopie.setTitle("Score average");
    }

    private void initPieChartPlayed(int[][] sbc) {
        avgames =
                FXCollections.observableArrayList(
                        new PieChart.Data("AboveAverage", sbc[2][0]+sbc[2][1]+sbc[2][2]),
                        new PieChart.Data("Average", sbc[1][0]+sbc[1][1]+sbc[1][2]),
                        new PieChart.Data("BelowAverage", sbc[0][0]+sbc[0][1]+sbc[0][2]));
        avplaypie.setData(avgames);
        avplaypie.setTitle("Played average");
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
