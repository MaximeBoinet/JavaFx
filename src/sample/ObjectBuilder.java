package sample;

import java.net.URL;
import java.util.*;
import com.google.gson.*;
import javafx.application.Platform;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.model.*;


public class ObjectBuilder implements Runnable {
    private static final String BASEURL = "http://mocnodeserv.hopto.org:80/";
    private Scanner scan;
    private String res;
    private double indicUnit;

    public void sendGetUsers() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"users").openStream());
        build(Main.Users, User.class);
        pause();
        Main.mainApp.loadCont.pushTea("Users builded","Parsing date user");
        parseDateUser();
    }

    public void sendGetSongs() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"song").openStream());
        build(Main.Songs, Song.class);
        pause();
        Main.mainApp.loadCont.pushTea("Songs builded","Parsing date songs");
        parseDateSong();
    }

    public void sendGetPlaylists() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"playlist").openStream());
        build(Main.Playlists, Playlist.class);
        pause();
        Main.mainApp.loadCont.pushTea("Playlists builded","Parsing date playlist");
        parseDatePlaylist();
    }

    public void sendGetGames() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"game").openStream());
        build(Main.Games, Game.class);
        pause();
        Main.mainApp.loadCont.pushTea("Games builded","Parsing date game");
        parseDateGame();
    }

    public void sendGetRank() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"rank").openStream());
        build(Main.Ranks, Rank.class);
        pause();
        Main.mainApp.loadCont.pushTea("Ranks builded","Parsing date rank");
        parseDateRank();
    }

    public void sendGetScore() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"game/score").openStream());
        build(Main.Scores, Score.class);
        pause();
        Main.mainApp.loadCont.pushTea("Scores builded","Parsing date score");
        parseDateScore();
    }

    public void sendGetGenre() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"kind").openStream());
        build(Main.Genres, Genre.class);
        pause();
        Main.mainApp.loadCont.pushTea("Kinds builded","Parsing date kind");
        parseDateGenre();
    }

    public void sendGetArtist() throws Exception {
        this.scan = new Scanner(new URL(BASEURL + "artist").openStream());
        build(Main.Artist, Artist.class);
        pause();
        Main.mainApp.loadCont.pushTea("Artists builded","Parsing date kind");
        parseDateArtist();
    }

    public void sendGetRewards() throws Exception {
        this.scan = new Scanner(new URL(BASEURL + "reward").openStream());
        build(Main.Rewards, Rewards.class);
        pause();
        Main.mainApp.loadCont.pushTea("Rewards builded","Parsing date reward");
        parseDateReward();
    }

    public void build(HashMap mainHm, Class T) {
        this.res = new String();
        while (this.scan.hasNext())
            this.res += scan.nextLine();
        scan.close();
        JSONArray arr = new JSONArray(res);
        indicUnit = 100.00/arr.length()/100.00;
        LinkedList<JSONObject> hs = new LinkedList<>();
        for (int i = 0; i < arr.length(); i++)
            hs.add(arr.getJSONObject(i));
        Iterator<JSONObject> it = hs.iterator();
        Gson gson = new Gson();
        while (it.hasNext()) {
            pause();
            JSONObject job = it.next();
            (mainHm).put(job.getString("_id"), gson.fromJson(job.toString(), T));
            Main.mainApp.loadCont.advProgIn(indicUnit);
        }
        pause();
        Main.mainApp.loadCont.resetIn();
        pause();
        Main.mainApp.loadCont.advProgBar((1.00/9.00));
    }

    public void parseDateUser() {
        Iterator it = Main.mainApp.Users.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            Main.Users.get(key).setCreated_at();
            Main.Users.get(key).setUpdated_at();
        }
    }

    public void parseDateGame() {
        Iterator it = Main.mainApp.Games.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            Main.Games.get(key).setCreated_at();
            Main.Games.get(key).setUpdated_at();
        }
    }

    public void parseDatePlaylist() {
        Iterator it = Main.mainApp.Playlists.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            Main.Playlists.get(key).setCreated_at();
            Main.Playlists.get(key).setUpdated_at();
        }
    }

    public void parseDateRank() {
        Iterator it = Main.mainApp.Ranks.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            Main.Ranks.get(key).setCreated_at();
            Main.Ranks.get(key).setUpdated_at();
        }
    }

    public void parseDateSong() {
        Iterator it = Main.mainApp.Songs.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            Main.Songs.get(key).setCreated_at();
            Main.Songs.get(key).setUpdated_at();
        }
    }

    public void parseDateGenre() {
        Iterator it = Main.mainApp.Genres.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            Main.Genres.get(key).setCreated_at();
            Main.Genres.get(key).setUpdated_at();
        }
    }

    public void parseDateArtist() {
        Iterator it = Main.mainApp.Artist.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            Main.Artist.get(key).setCreated_at();
            Main.Artist.get(key).setUpdated_at();
        }
    }

    public void parseDateScore() {
        Iterator it = Main.mainApp.Scores.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            Main.Scores.get(key).setCreated_at();
            Main.Scores.get(key).setUpdated_at();
        }
    }

    public void parseDateReward() {
        Iterator it = Main.mainApp.Rewards.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            Main.Rewards.get(key).setCreated_at();
            Main.Rewards.get(key).setUpdated_at();
        }
    }

    public void pause(){
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createObject() {
        Main.mainApp.loadCont.setGlob("Creating object from raw data");
        try {
            Main.mainApp.loadCont.pushTea("","Building users");
            this.sendGetUsers();
            pause();
            Main.mainApp.loadCont.pushTea("Users date parsed","Building games");
            this.sendGetGames();
            pause();
            Main.mainApp.loadCont.pushTea("Games date parsed","Building playlist");
            this.sendGetPlaylists();
            pause();
            Main.mainApp.loadCont.pushTea("Playlists date parsed","Building ranks");
            this.sendGetRank();
            pause();
            Main.mainApp.loadCont.pushTea("Ranks date parsed","Building songs");
            this.sendGetSongs();
            pause();
            Main.mainApp.loadCont.pushTea("Songs date parsed","Building kinds");
            this.sendGetGenre();
            pause();
            Main.mainApp.loadCont.pushTea("Kinds date parsed","Building scores");
            this.sendGetScore();
            pause();
            Main.mainApp.loadCont.pushTea("Scores date parsed","Building artists");
            this.sendGetArtist();
            pause();
            Main.mainApp.loadCont.pushTea("Artist date parsed","Building rewards");
            this.sendGetRewards();
            Main.mainApp.dowloaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        createObject();
        pause();
        Platform.runLater(() -> Main.mainApp.dialog.hide());
    }
}