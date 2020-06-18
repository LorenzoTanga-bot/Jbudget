package it.unicam.cs.pa.jbudget104953.FXController.Account;

import java.util.HashMap;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddManagement {

    @FXML
    TextField txName;

    @FXML
    TextArea taDescription;

    @FXML
    RadioButton rbShared;

    public void add() {
        if (!txName.getText().isEmpty()) {
            FXSetter.getInstance().getControllerAccount().addManagement(new HashMap<>() {
                private static final long serialVersionUID = 1L;
                {
                    put("Name", txName.getText());
                    put("Description", taDescription.getText());
                    if (rbShared.isSelected())
                        put("Shared", "Y");
                    else
                        put("Shared", "N");
                }
            });
            FXSetter.getInstance().getPopUp().close();
        }
    }

    public void cancel() {
        FXSetter.getInstance().getPopUp().close();
    }

}