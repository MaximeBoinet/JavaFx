package sample.model;

import sample.Main;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Created by MADMAX on 21/06/2017.
 */
public class Song {
    private String _id;
    private String created_at;
    private String updated_at;
    private LocalDate builded_created_at;
    private LocalDate builded_updated_at;
    private String title;
    private String url;
    private String uri;
    private int duration;
    private String[] Artists;

    public String get_id() {
        return _id;
    }

    public LocalDate getCreated_at() {
        return builded_created_at;
    }

    public LocalDate getUpdated_at() {
        return builded_created_at;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUri() {
        return uri;
    }

    public int getDuration() {
        return duration;
    }

    public String[] getArtist() {
        return Artists;
    }

    public void setCreated_at() {
        this.builded_created_at = Main.mongoDateToLocalDate(this.created_at);
    }

    public void setUpdated_at() {
        this.builded_updated_at = Main.mongoDateToLocalDate(this.updated_at);
    }

    @Override
    public String toString() {
        return "Song{" +
                "_id='" + _id + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", uri='" + uri + '\'' +
                ", duration=" + duration +
                ", Artist=" + Artists +
                '}';
    }
}
