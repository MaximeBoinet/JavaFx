package sample;

import java.net.Proxy;
import java.net.URL;
import java.util.*;
import com.google.gson.*;

import javafx.application.Platform;
import org.json.*;
import sample.model.*;

import static java.lang.Thread.sleep;

public class ObjectBuilder implements Runnable {
    private static final String BASEURL = "http://mocnodeserv.hopto.org:3000/";
    private Scanner scan;
    private String res;
    private double indicUnit;

    public void sendGetUsers() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"users").openStream());
        build(Main.Users, User.class);
    }

    public void sendGetSongs() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"song").openStream());
        build(Main.Songs, Song.class);
    }

    public void sendGetPlaylists() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"playlist").openStream());
        build(Main.Playlists, Playlist.class);
    }

    public void sendGetGames() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"game").openStream());
        Main.mainApp.loadCont.setCurrentLoading("Building games");
        build(Main.Games, Game.class);
        Main.mainApp.loadCont.pushTea("Games builded","");
    }

    public void sendGetRank() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"rank").openStream());
        build(Main.Ranks, Rank.class);

    }

    public void sendGetScore() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"score").openStream());
        Main.mainApp.loadCont.setCurrentLoading("Building scores");
        build(Main.Scores, Score.class);
        Main.mainApp.loadCont.pushTea("Scores builded","");
    }

    public void sendGetGenre() throws Exception {
        this.scan = new Scanner(new URL(BASEURL +"kind").openStream());
        build(Main.Scores, Score.class);
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
            System.out.println(T.toString() + " " +job);
            (mainHm).put(job.getString("_id"), gson.fromJson(job.toString(), T));
            Main.mainApp.loadCont.advProgIn(indicUnit);
        }
        Main.mainApp.loadCont.resetIn();
        Main.mainApp.loadCont.advProgBar(0.10);
    }

    public void buildStringToData() {

    }

    public void pause(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Main.mainApp.loadCont.setGlob("Creating object from raw data");
        try {
            Main.mainApp.loadCont.pushTea("","Building playlists");
            this.sendGetUsers();
            Main.mainApp.loadCont.pushTea("Users builded","Building playlists");
            //this.sendGetGames();
            this.sendGetPlaylists();
            Main.mainApp.loadCont.pushTea("Playlists builded","Building ranks");
            this.sendGetRank();
            Main.mainApp.loadCont.pushTea("Ranks builded","Building songs");
            this.sendGetSongs();
            Main.mainApp.loadCont.pushTea("Songs builded","Building genres");
            this.sendGetGenre();
            Main.mainApp.loadCont.pushTea("Genres builded","");
            //this.sendGetScore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}