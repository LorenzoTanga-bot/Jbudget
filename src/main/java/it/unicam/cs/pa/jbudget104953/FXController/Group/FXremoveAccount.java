package it.unicam.cs.pa.jbudget104953.FXController.Group;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FXremoveAccount extends FXGroupSetter {

    @FXML
    TextField idField;

    public void remove() throws IOException {
        group.removeAccount(Integer.parseInt(idField.getText()));
        returnMenuGroup();

    }

}