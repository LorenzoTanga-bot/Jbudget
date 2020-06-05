package it.unicam.cs.pa.jbudget104953.FXController.Account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FXaddManagement extends FXAccountSetter {

    @FXML
    ChoiceBox<String> type;

    @FXML
    CheckBox shared;

    @FXML
    TextArea description;

    public void set(Map<String, Object> info) {
        super.set(info);
        type.getItems().addAll("FINANCIAL", "LOAN");
    }

    @FXML
    public void add() throws IOException {
        if (!(type.getValue()).isEmpty()) {
            Map<String, String> info = new HashMap<>() {
                private static final long serialVersionUID = 1L;

                {
                    put("Type", type.getValue());
                    put("Description", description.getText());
                }
            };
            if (shared.isSelected())
                info.put("Shared", "Y");
            else
                info.put("Shared", "N");

            account.addManagement(info);
            returnMenuAccount();
        }
    }
}