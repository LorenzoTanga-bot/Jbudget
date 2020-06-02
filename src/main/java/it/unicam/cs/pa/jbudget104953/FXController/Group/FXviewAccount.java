package it.unicam.cs.pa.jbudget104953.FXController.Group;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.FXController.Account.FXAccount;
import it.unicam.cs.pa.jbudget104953.controller.ControllerAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class FXviewAccount extends FXGroupSetter {

    @FXML
    TextField idField;

    @FXML
    public void view() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Account/AccountFX.fxml"));
        Scene accountScene = new Scene(loader.load());
        FXAccount accountFX = loader.<FXAccount>getController();
        ControllerAccount account = new ControllerAccount();
        account.setAccount(group.getAccount(Integer.parseInt(idField.getText())));
        Map<String, Object> info = new HashMap<>() {

            private static final long serialVersionUID = 1L;

            {
                put("accountController", account);
                put("Stage", stage);
            }
        };

        accountFX.set(info);
        stage.setScene(accountScene);

    }

}