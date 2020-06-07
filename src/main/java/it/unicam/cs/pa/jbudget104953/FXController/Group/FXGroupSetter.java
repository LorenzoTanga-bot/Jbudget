package it.unicam.cs.pa.jbudget104953.FXController.Group;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerGroup;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerGroupInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXGroupSetter {

    protected Stage stage;
    protected ControllerGroupInterface group = null;

    public void set(Map<String, Object> info) {
        this.group = (ControllerGroup) info.get("groupController");
        this.stage = (Stage) info.get("Stage");
    }

    public void returnMenuGroup() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Group/GroupFX.fxml"));
        Scene groupScene = new Scene(loader.load());
        FXGroup groupFX = loader.<FXGroup>getController();
        groupFX.set(new HashMap<>() {

            private static final long serialVersionUID = 1L;

            {
                put("groupController", group);
                put("Stage", stage);
            }
        });

        stage.setScene(groupScene);
    }

}