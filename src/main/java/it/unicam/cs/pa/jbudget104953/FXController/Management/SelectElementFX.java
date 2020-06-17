package it.unicam.cs.pa.jbudget104953.FXController.Management;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class SelectElementFX implements Initializable {

    @FXML
    ChoiceBox<String> chType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chType.getItems().addAll("Single", "Multi", "Repeated", "Loan");
    }

    private FXMLLoader getLoader() {
        switch (chType.getSelectionModel().getSelectedItem()) {
            case "Single":
                FXSetter.getInstance().getInfo().put("TypeMovement", "Single");
                break;
            case "Multi":
                FXSetter.getInstance().getInfo().put("TypeMovement", "Multi");
                break;
            case "Repeated":
                FXSetter.getInstance().getInfo().put("TypeMovement", "Repeated");
                break;
            case "Loan":
                FXSetter.getInstance().getInfo().put("TypeMovement", "Loan");
        }
        return new FXMLLoader(getClass().getResource("/Management/AddDefaultElementFX.fxml"));

    }

    public void next() {
        if (!chType.getSelectionModel().isEmpty()) {
            FXSetter.getInstance().getPopUp().close();
            FXSetter.getInstance().setPopUp(new Stage());
            FXMLLoader loader = getLoader();
            try {
                FXSetter.getInstance().getPopUp().setScene(new Scene(loader.load()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            FXSetter.getInstance().getPopUp().setTitle("Add Element - Jbudget");
            FXSetter.getInstance().getPopUp().show();

        }
    }

    public void goBack() {
        FXSetter.getInstance().getPopUp().close();
    }

}