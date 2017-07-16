package sample.model;

import sample.Main;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Created by MADMAX on 21/06/2017.
 */
public class Game {
    private String _id;
    private String created_at;
    private String updated_at;
    private LocalDate builded_created_at;
    private LocalDate buildec_updated_at;
    private int difficulty;
    private boolean isMultiplayer;
    private boolean isPublic;
    private boolean isFinished;
    private String[] Songs;
    private Object[] Scores;
    private HashMap<String, Song> songs;
    private HashMap<String, Score> scores;

    public String get_id() {
        return _id;
    }


    public LocalDate getCreated_at() {
        return builded_created_at;
    }

    public LocalDate getUpdated_at() {
        return buildec_updated_at;
    }

    public int getDifficulty() {
        return difficulty;
    }


    public boolean isMultiplayer() {
        return isMultiplayer;
    }


    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isFinished() {
        return isFinished;
    }


    public void setCreated_at() {
        this.builded_created_at = Main.mongoDateToLocalDate(this.created_at);
    }

    public void setUpdated_at() {
        this.buildec_updated_at = Main.mongoDateToLocalDate(this.updated_at);
    }

    @Override
    public String toString() {
        return "Game{" +
                "_id='" + _id + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", difficulty=" + difficulty +
                ", isMultiplayer=" + isMultiplayer +
                ", isPublic=" + isPublic +
                ", isFinished=" + isFinished +
                ", songs=" + songs +
                ", scores=" + scores +
                '}';
    }
}
