package it.unicam.cs.pa.jbudget104953.controller;

import com.google.common.annotations.GwtIncompatible;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXView extends Application {

    Stage stage;
    Scene groupScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        groupScene = new Scene(FXMLLoader.load(getClass().getResource("/GroupFX.fxml")));

        stage.setScene(groupScene);
        stage.setTitle("Jbudget");
        stage.show();
    }

}