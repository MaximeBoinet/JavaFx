package sample.model;

import sample.Main;

import java.time.LocalDate;

/**
 * Created by MADMAX on 14/07/2017.
 */
public class Score {
    private String _id;
    private String created_at;
    private String updated_at;
    private LocalDate builded_created_at;
    private LocalDate builded_updated_at;
    private int scoreInGame;
    private boolean isFinished;
    private String User;
    private String Game;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public LocalDate getCreated_at() {
        return builded_created_at;
    }

    public LocalDate getUpdated_at() {
        return builded_updated_at;
    }

    public int getScoreInGame() {
        return scoreInGame;
    }

    public void setScoreInGame(int scoreInGame) {
        this.scoreInGame = scoreInGame;
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public void setCreated_at() {
        this.builded_created_at = Main.mongoDateToLocalDate(created_at);
    }

    public void setUpdated_at() {
        this.builded_updated_at = Main.mongoDateToLocalDate(updated_at);
    }

    @Override
    public String toString() {
        return "Score{" +
                "_id='" + _id + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", scoreInGame=" + scoreInGame +
                ", isFinished=" + isFinished +
                ", user=" + User +
                ", game=" + Game +
                '}';
    }
}
