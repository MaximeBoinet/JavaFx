package sample.model;

import sample.Main;

import java.time.LocalDate;

/**
 * Created by MADMAX on 20/06/2017.
 */
public class Rank {
    private String _id;
    private String created_at;
    private String updated_at;
    private LocalDate builded_created_at;
    private LocalDate builded_updated_at;
    private int nb;
    private String title;
    private int scoreToAcces;

    public String get_id() {
        return _id;
    }

    public LocalDate getCreated_at() {
        return builded_created_at;
    }

    public LocalDate getUpdated_at() {
        return builded_updated_at;
    }

    public void setCreated_at() {
        this.builded_created_at = Main.mongoDateToLocalDate(this.created_at);
    }

    public void setUpdated_at() {
        this.builded_updated_at = Main.mongoDateToLocalDate(this.updated_at);
    }

    public int getNb() {
        return nb;
    }

    public String getTitle() {
        return title;
    }

    public int getScoreToAcces() {
        return scoreToAcces;
    }

    public static boolean postNewRank() {
        //TODO
        return true;
    }

    public static boolean updateRank() {
        //TODO
        return true;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "_id='" + _id + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", nb=" + nb +
                ", title='" + title + '\'' +
                ", scoreToAcces=" + scoreToAcces +
                '}';
    }
}
