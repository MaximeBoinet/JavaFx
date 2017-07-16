package sample.model;

import sample.Main;

import java.time.LocalDate;

/**
 * Created by MADMAX on 21/06/2017.
 */
public class Rewards {
    private String _id;
    private String created_at;
    private String updated_at;
    private LocalDate builded_created_at;
    private LocalDate buildec_updated_at;
    private String title;
    private String description;
    private String type;
    private int goldToAcces;

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
        return buildec_updated_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGoldToAcces() {
        return goldToAcces;
    }

    public void setGoldToAcces(int goldToAcces) {
        this.goldToAcces = goldToAcces;
    }

    public void setCreated_at(String created_at) {
        this.builded_created_at = Main.mongoDateToLocalDate(created_at);
    }

    public void setUpdated_at(String updated_at) {
        this.buildec_updated_at = Main.mongoDateToLocalDate(updated_at);
    }

    @Override
    public String toString() {
        return "Rewards{" +
                "_id='" + _id + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", goldToAcces=" + goldToAcces +
                '}';
    }
}