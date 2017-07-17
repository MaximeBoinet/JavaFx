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
        this.tarea.setText("");
        this.tarea.setEditable(false);
        this.labprog.setText("");
        this.labprog.setEditable(false);
        this.labtot.setText("");
        this.labtot.setEditable(false);
        this.labtot.setFocusTraversable(false);
        this.labprog.setFocusTraversable(false);
        this.labtot.setBorder(Border.EMPTY);
        this.labprog.setBorder(Border.EMPTY);
    }

    public void advProgBar(double u) {
        this.probar.setProgress(this.probar.getProgress()+u);
    }

    public void resetIn() {
        this.proind.setProgress(0.00);
    }

    public void advProgIn(double u) {
        this.proind.setProgress(this.proind.getProgress()+u);
    }

    public void setGlob(String s) {
        this.labtot.setText(s);
    }

    public void setCurrentLoading(String s) {
        this.labprog.setText(s);
    }

    public void pushTea(String s, String s2) {
        this.tarea.setText( s +"\n"+ this.tarea.getText() );
        setCurrentLoading(s2);
    }
}
