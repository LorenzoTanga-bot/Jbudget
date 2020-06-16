package it.unicam.cs.pa.jbudget104953.FXController.SplashScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

public class SplashScreen implements Initializable {

    @FXML
    VBox vboxSplashScreen;

    @FXML
    ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new ShowSplashScreen().start();
    }

    class ShowSplashScreen extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);

                Platform.runLater(() -> {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Group/GroupFX.fxml"));
                    try {
                        FXSetter.getInstance().getStage().setScene(new Scene(loader.load()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }

}