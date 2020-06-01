package it.unicam.cs.pa.jbudget104953.FXController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.EventListener;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class FXGroup extends FXGroupSetter implements EventListener {

    @FXML
    Label console;

    public void set(Map<String, Object> info) {
        super.set(info);
        console.setText(group.toString());
    }

    @Override
    public void update(Object object) {
        if (object instanceof GroupInterface && group.getID() == ((GroupInterface) object).getID()) {
            console.setText(group.toString());
        }

    }

    @FXML
    public void addAccount() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Group/AddAccount.fxml"));
        Scene addAccountScene = new Scene(loader.load());
        FXaddAccount addAccountFX = loader.<FXaddAccount>getController();
        Map<String, Object> info = new HashMap<>() {

            private static final long serialVersionUID = 1L;

            {
                put("groupController", group);
                put("Stage", stage);
            }
        };
        addAccountFX.set(info);
        stage.setScene(addAccountScene);
    }

    @FXML
    public void removeAccount() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Group/RemoveAccount.fxml"));
        Scene removeAccountScene = new Scene(loader.load());
        FXremoveAccount removeAccountFX = loader.<FXremoveAccount>getController();
        Map<String, Object> info = new HashMap<>() {

            private static final long serialVersionUID = 1L;

            {
                put("groupController", group);
                put("Stage", stage);
            }
        };
        removeAccountFX.set(info);
        stage.setScene(removeAccountScene);
    }

    @FXML
    public void viewAccount() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Group/ViewAccount.fxml"));
        Scene viewAccountScene = new Scene(loader.load());
        FXviewAccount removeAccountFX = loader.<FXviewAccount>getController();
        Map<String, Object> info = new HashMap<>() {

            private static final long serialVersionUID = 1L;

            {
                put("groupController", group);
                put("Stage", stage);
            }
        };
        removeAccountFX.set(info);
        stage.setScene(viewAccountScene);
    }
}
