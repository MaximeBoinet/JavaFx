package sample.model;

import sample.Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by MADMAX on 20/06/2017.
 */
public class User {


    private String _id;
    private String created_at;
    private String updated_at;
    private LocalDate builded_created_at;
    private LocalDate builded_updated_at;
    private String userName;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private String mail;
    private String Rank;
    private Rank rank;
    private Long globalScore;
    private Double gold;
    private String location;
    private LocalDate birthDay;
    private Rewards rewards;

    public String[] getGames() {
        return Games;
    }

    private String[] Games;
    private String[] Friends;
    private String[] Playlists;
    private String Preferences;
    private HashMap<String, Game> games;
    private HashMap<String, User> friends;
    private HashMap<String, Playlist> playlist;
    private Preferences preferences;

    public void setCreated_at() {
        this.builded_created_at = Main.mongoDateToLocalDate(this.created_at);
    }

    public void setUpdated_at() {
        this.builded_updated_at = Main.mongoDateToLocalDate(this.updated_at);
    }

    public String get_id() {
        return _id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getRank() {
        return Rank;
    }

    public Long getGlobalScore() {
        return globalScore;
    }

    public Double getGold() {
        return gold;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public LocalDate getCreated_at() {
        return builded_created_at;
    }

    public LocalDate getUpdated_at() {
        return builded_updated_at;
    }

    public Rewards getRewards() {
        return rewards;
    }

    public void setRewards(Rewards rewards) {
        this.rewards = rewards;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isAdmin=" + isAdmin +
                ", mail='" + mail + '\'' +
                ", rank=" + rank +
                ", globalScore=" + globalScore +
                ", gold=" + gold +
                ", games=" + (Games != null ? Games.toString(): "Null") +
                ", location='" + location + '\'' +
                ", birthDay=" + birthDay +
                ", rewards=" + rewards +
                ", friends=" + (Friends != null ?  Friends.toString() : "Null") +
                ", playlist=" + (Playlists != null ? Playlists.toString() : "Null") +
                ", preferences=" + (Preferences != null ? Preferences.toString() : "Null") +
                '}';
    }
}
