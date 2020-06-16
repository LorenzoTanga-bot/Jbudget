package it.unicam.cs.pa.jbudget104953.FXController.Group;

import java.util.HashMap;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddAccount {

    @FXML
    TextField tfName;

    @FXML
    TextField tfSurname;

    @FXML
    TextArea taDescription;

    public void add() {
        if (!tfName.getText().isEmpty() && !tfSurname.getText().isEmpty()) {
            FXSetter.getInstance().getControllerGroup().addAccount(new HashMap<>() {
                private static final long serialVersionUID = 1L;
                {
                    put("Name", tfName.getText());
                    put("Surname", tfSurname.getText());
                    put("Description", taDescription.getText());
                }
            });
            FXSetter.getInstance().getPopUp().close();
        }
    }

    public void cancel() {
        FXSetter.getInstance().getPopUp().close();
    }

}