package it.unicam.cs.pa.jbudget104953.FXController.Account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerAccount;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerAccountInterface;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerGroup;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerGroupInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXAccountSetter {
    protected Stage stage;
    protected ControllerAccountInterface account = null;
    protected ControllerGroupInterface group = null;

    public void set(Map<String, Object> info) {
        this.account = (ControllerAccount) info.get("accountController");
        this.group = (ControllerGroup) info.get("groupController");
        this.stage = (Stage) info.get("Stage");
    }

    public void returnMenuAccount() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Account/AccountFX.fxml"));
        Scene accountScene = new Scene(loader.load());
        FXAccount accountFX = loader.<FXAccount>getController();
        accountFX.set(new HashMap<>() {

            private static final long serialVersionUID = 1L;

            {
                put("accountController", account);
                put("groupController", group);
                put("Stage", stage);
            }
        });

        stage.setScene(accountScene);

    }

}