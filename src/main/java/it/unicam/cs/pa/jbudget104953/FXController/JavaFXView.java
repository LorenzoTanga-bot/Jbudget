package it.unicam.cs.pa.jbudget104953.FXController;

import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.controller.ControllerGroup;
import it.unicam.cs.pa.jbudget104953.model.Group;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXView extends Application {

    private ControllerGroup groupController = new ControllerGroup();

    private boolean sync = false;

    private Stage stage;
    private Scene groupScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Group/GroupFX.fxml"));
        groupScene = new Scene(loader.load());
        FXGroup groupFX = loader.<FXGroup>getController();

        if (!sync) {
            GroupInterface group = new Group();
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
        }

        stage.setScene(groupScene);
        stage.setTitle("Jbudget");
        stage.show();

    }

}