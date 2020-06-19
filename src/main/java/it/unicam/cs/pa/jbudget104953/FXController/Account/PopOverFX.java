package it.unicam.cs.pa.jbudget104953.FXController.Account;

import java.net.URL;
import java.util.ResourceBundle;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PopOverFX implements Initializable {

    @FXML
    Label lName;

    @FXML
    Label lID;

    @FXML
    Label lBalance;

    @FXML
    Label lDescription;

    @FXML
    ListView<Object> lvMovement;

    private void initLabel() {
        lName.setText("Name: " + FXSetter.getInstance().getControllerManagement().getName());
        lID.setText("ID: " + FXSetter.getInstance().getControllerManagement().getID());
        lBalance.setText("Balance: " + FXSetter.getInstance().getControllerManagement().getBalance());
        lDescription.setText("Description: " + FXSetter.getInstance().getControllerManagement().getDescription());

    }

    private void initListview() {
        ObservableList<Object> movementList = FXCollections.observableArrayList();
        for (Object financial : FXSetter.getInstance().getControllerManagement().getAllElement())
            movementList.add(financial);
        lvMovement.setItems(movementList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLabel();
        initListview();
    }

}