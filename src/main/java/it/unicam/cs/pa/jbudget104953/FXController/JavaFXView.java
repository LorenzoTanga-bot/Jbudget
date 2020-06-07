package it.unicam.cs.pa.jbudget104953.FXController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.FXController.Group.FXGroup;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerGroup;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerGroupInterface;
import it.unicam.cs.pa.jbudget104953.model.Group;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
import it.unicam.cs.pa.jbudget104953.model.Sync;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXView extends Application {

    private ControllerGroupInterface groupController = new ControllerGroup();

    private Stage stage;
    private Scene groupScene;

    private GroupInterface sync() {
        try {
            return new Sync().read("sync.json");
        } catch (Exception e) {
            return new Group();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        stage = primaryStage;

        GroupInterface group = sync();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Group/GroupFX.fxml"));
        groupScene = new Scene(loader.load());
        FXGroup groupFX = loader.<FXGroup>getController();

        group.subscribe(groupFX);
        groupController.setGroup(group);

        Map<String, Object> info = new HashMap<>() {
            private static final long serialVersionUID = 1L;
            {
                put("groupController", groupController);
                put("Stage", stage);
            }
        };

        groupFX.set(info);

        stage.setScene(groupScene);
        stage.setTitle("Jbudget");
        stage.show();

    }

}