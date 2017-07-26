package sample;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.model.*;
import sample.view.LoaderController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import static java.lang.System.in;

public class Main extends Application {
    public static String token;
    public Stage dialog;
    public Stage dialogerror;
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
    public static HashMap<String, Rewards> Rewards;
    public boolean dowloaded;

    @Override
    public void start(Stage primaryStage) throws Exception{
        token = null;
        this.mainApp = this;
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("StatMaker");
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("res/ico.ico")));
        this.dowloaded = false;
        Users = new HashMap<>();
        Songs = new HashMap<>();
        Games = new HashMap<>();
        Playlists = new HashMap<>();
        Ranks = new HashMap<>();
        Genres = new HashMap<>();
        Scores = new HashMap<>();
        Artist = new HashMap<>();
        Rewards = new HashMap<>();
        showLoader();
    }

    public void initRootLayout() {
        try {
            root = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root));
        Logger log = new Logger();
        primaryStage.show();
        log.initDialog();
        if (token == null || token == "")
            primaryStage.close();
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
                showErrorDialog();
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

        ob = new ObjectBuilder();
        Thread t = new Thread(ob);
        t.start();
    }

    public void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainView.fxml"));
            AnchorPane mainView = loader.load();
            root.setCenter(mainView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showErrorDialog() {
        dialogerror = new Stage();
        dialogerror.initModality(Modality.APPLICATION_MODAL);
        dialogerror.initOwner(primaryStage);
        dialogerror.initStyle(StageStyle.UTILITY);
        try {
            dialogerror.setScene(new Scene(new FXMLLoader(Main.class.getResource("view/Error.fxml")).load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogerror.showAndWait();
        this.primaryStage.close();
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
