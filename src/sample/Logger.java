package sample;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.util.Pair;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

/**
 * Created by MADMAX on 20/07/2017.
 */
public class Logger {
    private static final String BASEURL = "http://mocnodeserv.hopto.org:3000/";

    public void initDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login");
        dialog.setHeaderText("Please enter your ID");
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(Main.mainApp.primaryStage);
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);
        username.textProperty().addListener((observable, oldValue, newValue) -> loginButton.setDisable(newValue.trim().isEmpty()));
        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> username.requestFocus());
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> requestLogin(usernamePassword));
    }

    private void requestLogin(Pair<String, String> usernamePassword) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(BASEURL+"auth/login/adminSys");
        StringEntity postingString = null;
        try {
            postingString = new StringEntity("{\"userName\":\""+usernamePassword.getKey()+"\",\"password\":\""+usernamePassword.getValue()+"\"}");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response;
        try {
            response = httpClient.execute(post);
            System.out.print(response.getStatusLine());
            if (response.getStatusLine().getStatusCode() == 200)
                Main.token = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
