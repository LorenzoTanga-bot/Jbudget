package it.unicam.cs.pa.jbudget104953.FXController.Group;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXaddAccount extends FXGroupSetter {

    @FXML
    TextField name;

    @FXML
    TextField surname;

    @FXML
    TextArea description;

    @FXML
    public void add() throws IOException {
        if (!name.getText().isEmpty() && !surname.getText().isEmpty()) {
            Map<String, String> info = new HashMap<>() {
                private static final long serialVersionUID = 1L;

                {
                    put("Name", name.getText());
                    put("Surname", surname.getText());
                    put("Descriptio", description.getText());
                }
            };
            group.addAccount(info);
            returnMenuGroup();
        }

    }

}