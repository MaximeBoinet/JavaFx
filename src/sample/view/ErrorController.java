package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Main;

/**
 * Created by MADMAX on 18/07/2017.
 */
public class ErrorController {
    @FXML
    private Button okbut;


    @FXML
    public void initialize() {
    }

    @FXML
    private void handleOk() {
        Main.mainApp.dialogerror.close();
    }
}
