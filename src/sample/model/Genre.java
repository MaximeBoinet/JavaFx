package sample.model;

import sample.Main;

import java.time.LocalDate;

/**
 * Created by MADMAX on 21/06/2017.
 */
public class Genre {
    private String _id;
    private String created_at;
    private String updated_at;
    private LocalDate builded_created_at;
    private LocalDate builded_updated_at;
    private String title;

    public String get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public void setCreated_at() {
        this.builded_created_at = Main.mongoDateToLocalDate(this.created_at);
    }

    public void setUpdated_at() {
        this.builded_updated_at = Main.mongoDateToLocalDate(this.updated_at);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "_id='" + _id + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", title='" + title + '\'' +
                '}';
    }
}
