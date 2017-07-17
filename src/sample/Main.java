package sample;

import com.sun.javafx.tk.Toolkit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import sample.model.*;
import sample.view.LoaderController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    public Stage dialog;
    public Stage primaryStage;
    private BorderPane root;
    public static Runnable ob;
    public LoaderController loadCont;
    public static Main mainApp;
    public static HashMap<String, User> Users;
    public static HashMap<String, Song> Songs;
    public static HashMap<String, Game> Games;
    public static HashMap<String, Playlist> Playlists;
    public static HashMap<String, Rank> Ranks;
    public static HashMap<String, Genre> Genres;
    public static HashMap<String, Score> Scores;
    public static HashMap<String, Artist> Artist;
    public boolean dowloaded;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.mainApp = this;
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("StatMaker");
        this.dowloaded = false;
        Users = new HashMap<>();
        Songs = new HashMap<>();
        Games = new HashMap<>();
        Playlists = new HashMap<>();
        Ranks = new HashMap<>();
        Genres = new HashMap<>();
        Scores = new HashMap<>();
        Artist = new HashMap<>();
        showLoader();
    }

    public void initRootLayout() {
        try {
            root = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        showMainView();
    }

    public void showLoader() {
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setOnHiding(we -> {
            if(dowloaded)Main.mainApp.initRootLayout();
            else try {
                this.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        try {
            dialog.setScene(new Scene(new FXMLLoader(Main.class.getResource("view/loader.fxml")).load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.show();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ob = new ObjectBuilder();
        Thread t = new Thread(ob);
        t.start();
    }

    public void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainView.fxml"));
            AnchorPane mainView = (AnchorPane) loader.load();
            root.setCenter(mainView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static LocalDate mongoDateToLocalDate(String mongodate) {
        SimpleDateFormat original = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        Date d;
        LocalDate date;
        try {
            d = original.parse(mongodate);
            date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
