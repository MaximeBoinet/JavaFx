package sample.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.model.Rewards;
import sample.model.User;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by MADMAX on 19/07/2017.
 */
public class RewardController {
    private ObservableList<Rewards> rewardsobs;

    private String currentRewardId;

    @FXML
    private TableView<Rewards> rewardTab;
    @FXML
    private TableColumn<Rewards, String> tableTitle;
    @FXML
    private TableColumn<Rewards, String> tableType;
    @FXML
    private TableColumn<Rewards, Double> tableGold;
    @FXML
    private TableColumn<Rewards, LocalDate> tableCreated;

    @FXML
    private TextField titleup;
    @FXML
    private TextField typeup;
    @FXML
    private TextField goldup;
    @FXML
    private TextArea descup;

    @FXML
    private TextField titlecr;
    @FXML
    private TextField typecr;
    @FXML
    private TextField goldcr;
    @FXML
    private TextArea desccr;

    @FXML
    private Button create;
    @FXML
    private Button update;
    @FXML
    private Button delete;

    @FXML
    private Label totre;
    @FXML
    private Label totbou;

    @FXML
    private void initialize() {
        update.setDisable(true);
        delete.setDisable(true);
        initLabel();
        initMap();
        initTable();
    }

    private void initLabel() {
        this.totre.setText("");
        int tot = 0;
        for (User us: Main.Users.values()) {
            tot += us.getRewards().length;
        }
        this.totbou.setText(String.valueOf(tot));
    }

    private void initMap() {
        this.rewardsobs = observableArrayList();
        rewardsobs.addAll(Main.Rewards.values());
    }

    private void initTable() {
        initTableReward();
    }

    private void initTableReward() {
        this.rewardTab.setItems(rewardsobs);
        this.tableTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        this.tableType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        this.tableGold.setCellValueFactory(cellData -> new SimpleObjectProperty<>((double) cellData.getValue().getGoldToAcces()));
        this.tableCreated.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreated_at()));
        this.rewardTab.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setRewardDetail(newValue));
    }

    private void setRewardDetail(Rewards reward) {
        if (reward != null) {
            this.update.setDisable(false);
            this.delete.setDisable(false);
            this.currentRewardId = reward.get_id();
            this.descup.setText(reward.getDescription() != null ? reward.getDescription() : "");
            this.titleup.setText(reward.getTitle());
            this.goldup.setText(String.valueOf(reward.getGoldToAcces()));
            this.typeup.setText(reward.getType());
            this.totre.setText(String.valueOf(rewardsobs.size()));
        } else {
            this.currentRewardId = null;
            this.delete.setDisable(true);
            this.update.setDisable(true);
            this.descup.setText("");
            this.titleup.setText("");
            this.goldup.setText("");
            this.typeup.setText("");
        }
    }

    @FXML
    private void handleCreateClicked() {
        if (titlecr.getText().isEmpty())
            titlecr.setStyle("-fx-border-color: red;");
        else if (typecr.getText().isEmpty())
            typecr.setStyle("-fx-border-color: red;");
        else if (goldcr.getText().isEmpty())
            goldcr.setStyle("-fx-border-color: red;");
        else {
            titlecr.setStyle("");
            typecr.setStyle("");
            goldcr.setStyle("");
            Rewards re = null;
            if ((re = Rewards.postNewReward(titlecr.getText(), typecr.getText(), goldcr.getText(), desccr.getText())) != null) {
                re.setCreated_at();
                this.rewardsobs.add(re);
                Main.Rewards.put(re.get_id(), re);
            }

        }
    }

    @FXML
    private void handleUpdateClicked() {
        if (titleup.getText().isEmpty())
            titleup.setStyle("-fx-border-color: red;");
        else if (typeup.getText().isEmpty())
            titleup.setStyle("-fx-border-color: red;");
        else if (goldup.getText().isEmpty())
            goldup.setStyle("-fx-border-color: red;");
        else {
            titleup.setStyle("");
            typeup.setStyle("");
            goldup.setStyle("");
            if (Rewards.updateReward(Main.Rewards.get(currentRewardId), titleup.getText(), typeup.getText(), goldup.getText(), descup.getText())) {
                this.rewardTab.refresh();
            }
        }
    }

    @FXML
    private void handleDeleteClicked() {
        if (currentRewardId != null) {
            if (Rewards.deleteReward(currentRewardId)) {
                this.rewardsobs.remove(Main.Rewards.remove(currentRewardId));
            }

        }
    }
}
