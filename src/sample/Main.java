package sample;

import com.sun.javafx.tk.Toolkit;
import javafx.application.Application;
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



    @Override
    public void start(Stage primaryStage) throws Exception{
        this.mainApp = this;
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("StatMaker");

        Users = new HashMap<>();
        Songs = new HashMap<>();
        Games = new HashMap<>();
        Playlists = new HashMap<>();
        Ranks = new HashMap<>();
        Genres = new HashMap<>();
        Scores = new HashMap<>();
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
        dialog.setOnCloseRequest(we -> Main.mainApp.initRootLayout());
        dialog.setOnHiding(we -> Main.mainApp.initRootLayout());
        try {
            dialog.setScene(new Scene(new FXMLLoader(Main.class.getResource("view/loader.fxml")).load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.show();
        ob = new ObjectBuilder();
        Thread t = new Thread(ob);
        t.start();




    }

    public void showMainView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainView.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            root.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static LocalDate mongoDateToLocalDate(String mongodate) {
        SimpleDateFormat original = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        SimpleDateFormat output= new SimpleDateFormat("yyyy-MM-dd");
        //String isoFormat = original.format(mongodate);
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
