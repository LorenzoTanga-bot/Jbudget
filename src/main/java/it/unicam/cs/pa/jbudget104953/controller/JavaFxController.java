package it.unicam.cs.pa.jbudget104953.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ViewFX.fxml"));

        primaryStage.setTitle("Jbudget");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

}