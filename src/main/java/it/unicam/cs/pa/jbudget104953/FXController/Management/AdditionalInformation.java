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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdditionalInformation implements Initializable {

    @FXML
    Label lTitle;

    @FXML
    TextField tfRate;

    @FXML
    TextField tfDay;

    @FXML
    ChoiceBox<String> cbScope;

    @FXML
    TextField tfRatio;

    private void initInput() {
        switch (FXSetter.getInstance().getInfo().get("TypeMovement")) {
            case "REPEATED":
                lTitle.setText("Repeated");
                cbScope.setDisable(true);
                tfRatio.setDisable(true);
                break;
            case "LOAN":
                lTitle.setText("Loan");
                cbScope.getItems().addAll("LIQUID", "CONSUMER");
                break;
        }
    }

    private boolean getInput() {
        switch (FXSetter.getInstance().getInfo().get("TypeMovement")) {
            case "REPEATED":
                if (!tfRate.getText().isEmpty() && !tfDay.getText().isEmpty()) {
                    FXSetter.getInstance().getInfo().put("NumberMovement", tfRate.getText());
                    FXSetter.getInstance().getInfo().put("Day", tfDay.getText());
                    return true;
                }

            case "LOAN":
                if (!tfRate.getText().isEmpty() && !tfDay.getText().isEmpty() && !cbScope.getSelectionModel().isEmpty()
                        && !tfRatio.getText().isEmpty()) {
                    FXSetter.getInstance().getInfo().put("NumberMovement", tfRate.getText());
                    FXSetter.getInstance().getInfo().put("Day", tfDay.getText());
                    FXSetter.getInstance().getInfo().put("Scope",
                            cbScope.getSelectionModel().getSelectedItem().toString());
                    FXSetter.getInstance().getInfo().put("Ratio", tfRatio.getText());
                    return true;
                }
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initInput();
    }

    public void add() {
        if (getInput()) {
            FXSetter.getInstance().getControllerManagement().addElement(FXSetter.getInstance().getInfo());
            FXSetter.getInstance().getPopUp().close();
        }
    }

    public void goBack() {
        FXSetter.getInstance().getPopUp().close();
    }

    public void returnBack() {
        FXSetter.getInstance().getPopUp().close();
        FXSetter.getInstance().setPopUp(new Stage());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Management/AddDefaultElementFX.fxml"));
        try {
            FXSetter.getInstance().getPopUp().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXSetter.getInstance().getPopUp().setTitle("Add Element - Jbudget");
        FXSetter.getInstance().getPopUp().show();
    }

}