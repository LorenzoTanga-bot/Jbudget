package it.unicam.cs.pa.jbudget104953.FXController;

import java.io.IOException;

import it.unicam.cs.pa.jbudget104953.model.Group;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
import it.unicam.cs.pa.jbudget104953.model.Sync;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXView extends Application {

    private GroupInterface sync() {
        try {
            return new Sync().read("sync.json");
        } catch (Exception e) {
            return new Group();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXSetter.getInstance().setStage(primaryStage);

        FXSetter.getInstance().setControllerGroup(sync());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hello.fxml"));

        FXSetter.getInstance().getStage().setScene(new Scene(loader.load()));
        FXSetter.getInstance().getStage().setTitle("Jbudget");
        FXSetter.getInstance().getStage().show();

        FXSetter.getInstance().getStage().setOnCloseRequest(e -> {
            try {
                new Sync().write(FXSetter.getInstance().getControllerGroup().getGroup(), "sync.json");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        });

    }

}