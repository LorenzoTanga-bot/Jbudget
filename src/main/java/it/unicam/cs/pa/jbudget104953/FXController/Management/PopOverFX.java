package it.unicam.cs.pa.jbudget104953.FXController.Management;

import java.net.URL;
import java.util.ResourceBundle;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PopOverFX implements Initializable {

    @FXML
    Label lType;

    @FXML
    Label lID;

    @FXML
    Label lBalance;

    @FXML
    ListView<FinancialInterface> lvFinancial;

    private void initLabel() {
        lType.setText("Type: " + FXSetter.getInstance().getMovementSelected().getType());
        lID.setText("ID: " + FXSetter.getInstance().getMovementSelected().getID());
        lBalance.setText("Balance: " + FXSetter.getInstance().getMovementSelected().getBalance());
    }

    private void initListview() {
        ObservableList<FinancialInterface> financialList = FXCollections.observableArrayList();
        for (FinancialInterface financial : FXSetter.getInstance().getMovementSelected().getAllTransaction())
            financialList.add(financial);
        lvFinancial.setItems(financialList);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLabel();
        initListview();
    }

}