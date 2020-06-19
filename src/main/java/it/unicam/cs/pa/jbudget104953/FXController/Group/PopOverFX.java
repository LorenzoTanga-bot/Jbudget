package it.unicam.cs.pa.jbudget104953.FXController.Group;

import java.net.URL;
import java.util.ResourceBundle;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PopOverFX implements Initializable {

    @FXML
    Label lID;

    @FXML
    Label lName;

    @FXML
    Label lBalanceInside;

    @FXML
    Label lBalanceOutside;

    @FXML
    ListView<ManagementInterface<?>> lvManagement;

    private void initLabel() {
        lID.setText("ID: " + FXSetter.getInstance().getControllerAccount().getID());
        lName.setText("Name: " + FXSetter.getInstance().getControllerAccount().getName() + " "
                + FXSetter.getInstance().getControllerAccount().getSurname());
        lBalanceInside.setText("Balance Inside: " + FXSetter.getInstance().getControllerAccount().getBalanceInside());
        lBalanceOutside
                .setText("Balance Outside: " + FXSetter.getInstance().getControllerAccount().getBalanceOutside());

    }

    private void initListview() {
        ObservableList<ManagementInterface<?>> managementList = FXCollections.observableArrayList();
        for (ManagementInterface<?> management : FXSetter.getInstance().getControllerAccount().getAllInOneManagement())
            managementList.add(management);
        lvManagement.setItems(managementList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLabel();
        initListview();
    }

}