package it.unicam.cs.pa.jbudget104953.FXController.Account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.FXController.Group.FXGroup;
import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.EventListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class FXAccount extends FXAccountSetter implements EventListener {

    @FXML
    Label console;

    public void set(Map<String, Object> info) {
        super.set(info);
        console.setText(account.toString());
    }

    @Override
    public void update(Object object) {
        if (object instanceof AccountInterface && account.getID() == ((AccountInterface) object).getID()) {
            console.setText(account.toString());
        }
    }

    @FXML
    public void addManagement() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Account/AddManagement.fxml"));
        Scene addManagementScene = new Scene(loader.load());
        FXaddManagement addManagementFX = loader.<FXaddManagement>getController();
        Map<String, Object> info = new HashMap<>() {

            private static final long serialVersionUID = 1L;

            {
                put("groupController", group);
                put("accountController", account);
                put("Stage", stage);
            }
        };
        addManagementFX.set(info);
        stage.setScene(addManagementScene);
    }

    @FXML
    public void removeManagement() {

    }

    @FXML
    public void viewManagement() {

    }

    @FXML
    public void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Group/GroupFX.fxml"));
        Scene groupScene = new Scene(loader.load());
        FXGroup groupFX = loader.<FXGroup>getController();
        Map<String, Object> info = new HashMap<>() {

            private static final long serialVersionUID = 1L;

            {
                put("groupController", group);
                put("Stage", stage);
            }
        };
        groupFX.set(info);
        stage.setScene(groupScene);
    }

}