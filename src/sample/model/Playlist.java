package sample.model;

import sample.Main;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Created by MADMAX on 21/06/2017.
 */
public class Playlist {
    private String _id;
    private String created_at;
    private String updated_at;
    private LocalDate builded_created_at;
    private LocalDate buildec_updated_at;
    private String title;
    private Boolean isPublic;
    private String Creator;
    private User creator;
    private String[] Songs;
    private HashMap<String, Song> songs;

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

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        creator = creator;
    }

    public void setCreated_at(String created_at) {
        this.builded_created_at = Main.mongoDateToLocalDate(created_at);
    }

    public void setUpdated_at(String updated_at) {
        this.buildec_updated_at = Main.mongoDateToLocalDate(updated_at);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "_id='" + _id + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", title='" + title + '\'' +
                ", isPublic=" + isPublic +
                ", Creator=" + Creator +
                ", Songs=" + Songs +
                '}';
    }
}
