package sample.model;

import java.time.LocalDate;

/**
 * Created by MADMAX on 20/06/2017.
 */
public class Rank {
    private String _id;
    private String created_at;
    private String updated_at;
    private LocalDate builded_created_at;
    private LocalDate buildec_updated_at;
    private int nb;
    private String title;
    private int scoreToAcces;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public LocalDate getCreated_at() {
        return builded_created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.builded_created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return buildec_updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.buildec_updated_at = updated_at;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScoreToAcces() {
        return scoreToAcces;
    }

    public void setScoreToAcces(int scoreToAcces) {
        this.scoreToAcces = scoreToAcces;
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
