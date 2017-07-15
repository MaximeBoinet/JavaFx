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
    private LocalDate buildec_updated_at;
    private String userName;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private String mail;
    private Rank rank;
    private Long globalScore;
    private Long gold;
    private String location;
    private LocalDate birthDay;
    private Rewards rewards;
    private String[] Games;
    private String[] Friends;
    private String[] Playlists;
    private String Preferences;
    private HashMap<String, Game> games;
    private HashMap<String, User> friends;
    private HashMap<String, Playlist> playlist;
    private Preferences preferences;

    public User() {
        super();
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setGlobalScore(Long globalScore) {
        this.globalScore = globalScore;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    /*public void setCreated_at(String created_at) {
        this.created_at = Main.mongoDateToLocalDate(created_at);
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = Main.mongoDateToLocalDate(updated_at);
    }
*/
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

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Long getGlobalScore() {
        return globalScore;
    }

    public Long getGold() {
        return gold;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (isAdmin() != user.isAdmin()) return false;
        if (!get_id().equals(user.get_id())) return false;
        if (getCreated_at() != null ? !getCreated_at().equals(user.getCreated_at()) : user.getCreated_at() != null)
            return false;
        if (getUpdated_at() != null ? !getUpdated_at().equals(user.getUpdated_at()) : user.getUpdated_at() != null)
            return false;
        if (builded_created_at != null ? !builded_created_at.equals(user.builded_created_at) : user.builded_created_at != null)
            return false;
        if (buildec_updated_at != null ? !buildec_updated_at.equals(user.buildec_updated_at) : user.buildec_updated_at != null)
            return false;
        if (getUserName() != null ? !getUserName().equals(user.getUserName()) : user.getUserName() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getMail() != null ? !getMail().equals(user.getMail()) : user.getMail() != null) return false;
        if (getRank() != null ? !getRank().equals(user.getRank()) : user.getRank() != null) return false;
        if (getGlobalScore() != null ? !getGlobalScore().equals(user.getGlobalScore()) : user.getGlobalScore() != null)
            return false;
        if (getGold() != null ? !getGold().equals(user.getGold()) : user.getGold() != null) return false;
        if (getLocation() != null ? !getLocation().equals(user.getLocation()) : user.getLocation() != null)
            return false;
        if (getBirthDay() != null ? !getBirthDay().equals(user.getBirthDay()) : user.getBirthDay() != null)
            return false;
        if (getRewards() != null ? !getRewards().equals(user.getRewards()) : user.getRewards() != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(Games, user.Games)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(Friends, user.Friends)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(Playlists, user.Playlists)) return false;
        if (Preferences != null ? !Preferences.equals(user.Preferences) : user.Preferences != null) return false;
        if (games != null ? !games.equals(user.games) : user.games != null) return false;
        if (friends != null ? !friends.equals(user.friends) : user.friends != null) return false;
        if (playlist != null ? !playlist.equals(user.playlist) : user.playlist != null) return false;
        return preferences != null ? preferences.equals(user.preferences) : user.preferences == null;
    }

    @Override
    public int hashCode() {
        int result = get_id().hashCode();
        result = 31 * result + (getCreated_at() != null ? getCreated_at().hashCode() : 0);
        result = 31 * result + (getUpdated_at() != null ? getUpdated_at().hashCode() : 0);
        result = 31 * result + (builded_created_at != null ? builded_created_at.hashCode() : 0);
        result = 31 * result + (buildec_updated_at != null ? buildec_updated_at.hashCode() : 0);
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (isAdmin() ? 1 : 0);
        result = 31 * result + (getMail() != null ? getMail().hashCode() : 0);
        result = 31 * result + (getRank() != null ? getRank().hashCode() : 0);
        result = 31 * result + (getGlobalScore() != null ? getGlobalScore().hashCode() : 0);
        result = 31 * result + (getGold() != null ? getGold().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        result = 31 * result + (getBirthDay() != null ? getBirthDay().hashCode() : 0);
        result = 31 * result + (getRewards() != null ? getRewards().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(Games);
        result = 31 * result + Arrays.hashCode(Friends);
        result = 31 * result + Arrays.hashCode(Playlists);
        result = 31 * result + (Preferences != null ? Preferences.hashCode() : 0);
        result = 31 * result + (games != null ? games.hashCode() : 0);
        result = 31 * result + (friends != null ? friends.hashCode() : 0);
        result = 31 * result + (playlist != null ? playlist.hashCode() : 0);
        result = 31 * result + (preferences != null ? preferences.hashCode() : 0);
        return result;
    }
}
