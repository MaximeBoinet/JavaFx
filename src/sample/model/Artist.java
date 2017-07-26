package sample.model;

import sample.Main;

import java.time.LocalDate;

/**
 * Created by MADMAX on 21/06/2017.
 */
public class Artist {
    private String _id;
    private String created_at;
    private String updated_at;
    private String title;
    private LocalDate builded_created_at;
    private LocalDate builded_updated_at;

    public String getTitle() {
        return title;
    }

    public String get_id() {
        return _id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public LocalDate getBuilded_created_at() {
        return builded_created_at;
    }

    public LocalDate getBuilded_updated_at() {
        return builded_updated_at;
    }

    public void setCreated_at() {
        this.builded_created_at = Main.mongoDateToLocalDate(this.created_at);
    }

    public void setUpdated_at() {
        this.builded_updated_at = Main.mongoDateToLocalDate(this.updated_at);
    }


}
