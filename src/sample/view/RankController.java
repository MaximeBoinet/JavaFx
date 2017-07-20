package sample.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.model.Rank;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by MADMAX on 20/07/2017.
 */
public class RankController {
    private String currentRanksId;
    private ObservableList<Rank> ranksobs;

    @FXML
    private TableView<Rank> tabRank;
    @FXML
    private TableColumn<Rank, String> tableTitleRank;
    @FXML
    private TableColumn<Rank, LocalDate> tableCreatedRank;
    @FXML
    private TableColumn<Rank, Integer> tableRankRank;
    @FXML
    private TableColumn<Rank, Integer> tableScoreToAccesRank;

    @FXML
    private Button deleter;
    @FXML
    private Button updater;
    @FXML
    private Button creater;

    @FXML
    private TextField titlercr;
    @FXML
    private TextField scorercr;

    @FXML
    private TextField titlerup;
    @FXML
    private TextField scorerup;

    @FXML
    private void initialize() {
        initLabel();
        initMap();
        initTable();
    }

    private void initTable() {
        this.tabRank.setItems(ranksobs);
        this.tableTitleRank.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.tableCreatedRank.setCellValueFactory(cellDate -> new SimpleObjectProperty<>(cellDate.getValue().getCreated_at()));
        this.tableRankRank.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNb()));
        this.tableScoreToAccesRank.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScoreToAccess()));
        this.tabRank.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setRanksDetail(newValue));
    }

    private void initMap() {
        this.ranksobs = observableArrayList();
        this.ranksobs.addAll(Main.Ranks.values());
    }

    private void initLabel() {

    }

    public void setRanksDetail(Rank ranksDetail) {
        if (ranksDetail != null) {
            this.titlerup.setText(ranksDetail.getTitle());
            this.scorerup.setText(String.valueOf(ranksDetail.getScoreToAccess()));
            this.currentRanksId = ranksDetail.get_id();
        } else {
            this.titlerup.setText("");
            this.scorerup.setText("");
            this.currentRanksId = null;
        }
    }

    @FXML
    private void handleCreateClicked() {
        if (titlercr.getText().isEmpty())
            titlercr.setStyle("-fx-border-color: red;");
        else if (scorercr.getText().isEmpty())
            scorercr.setStyle("-fx-border-color: red;");
        else {
            titlercr.setStyle("");
            scorercr.setStyle("");
            Rank ra = Rank.postNewRank(titlercr.getText(), Integer.parseInt(scorercr.getText()));
            if ((ra ) != null) {
                ra.setCreated_at();
                this.ranksobs.add(ra);
                Main.Ranks.put(ra.get_id(), ra);
            }

        }
    }

    @FXML
    private void handleUpdateClicked() {
        if (titlerup.getText().isEmpty())
            titlerup.setStyle("-fx-border-color: red;");
        else if (scorerup.getText().isEmpty())
            scorerup.setStyle("-fx-border-color: red;");
        else {
            titlerup.setStyle("");
            scorerup.setStyle("");
            if (Rank.updateRank(Main.Ranks.get(currentRanksId), titlerup.getText(), Integer.parseInt(scorerup.getText()))) {
                this.tabRank.refresh();
            }
        }
    }

    @FXML
    private void handleDeleteClicked() {
        if (currentRanksId != null) {
            if (Rank.deleteRank(currentRanksId)) {
                this.ranksobs.remove(Main.Ranks.remove(currentRanksId));
            }
        }
    }
}
