package it.unicam.cs.pa.jbudget104953.FXController.Account;

import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.EventListener;
import javafx.fxml.FXML;
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

    public void addManagement() {

    }

    public void removeManagement() {

    }

    public void viewManagement() {

    }

}