package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import sample.Main;

/**
 * Created by MADMAX on 12/07/2017.
 */
public class LoaderController {

    @FXML
    private TextField labtot;

    @FXML
    private TextField labprog;

    @FXML
    private ProgressBar probar;

    @FXML
    private ProgressIndicator proind;

    @FXML
    private TextArea tarea;

    @FXML
    private void initialize() {
        Main.mainApp.loadCont = this;
        tarea.setText("");
        tarea.setEditable(false);
        labprog.setText("");
        labprog.setEditable(false);
        labtot.setText("");
        labtot.setEditable(false);
        labtot.setFocusTraversable(false);
        labprog.setFocusTraversable(false);
        labtot.setBorder(Border.EMPTY);
        labprog.setBorder(Border.EMPTY);
    }

    public void advProgBar(double u) {
        probar.setProgress(probar.getProgress()+u);
    }

    public void resetIn() {
        proind.setProgress(0.00);
    }

    public void advProgIn(double u) {
        proind.setProgress(proind.getProgress()+u);
    }

    public void setGlob(String s) {
        labtot.setText(s);
    }

    public void setCurrentLoading(String s) {
        labprog.setText(s);
    }

    public void pushTea(String s, String s2) {
        tarea.setText( s +"\n"+ tarea.getText() );
        setCurrentLoading(s2);
    }
}
