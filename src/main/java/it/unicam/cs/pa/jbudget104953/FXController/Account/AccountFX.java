package it.unicam.cs.pa.jbudget104953.FXController.Account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.EventListener;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AccountFX implements Initializable, EventListener {
    @FXML
    ListView<ManagementInterface<?>> listAccount;

    @FXML
    Button btnAddManagement;

    @FXML
    Button btnRemoveManagement;

    @FXML
    Button btnViewManagement;

    private void updateListview() {
        ObservableList<ManagementInterface<?>> managementList = FXCollections.observableArrayList();
        for (ManagementInterface<?> management : FXSetter.getInstance().getControllerAccount()
                .getAllInOneManagement()) {
            managementList.add(management);
        }
        listAccount.setItems(managementList);
        listAccount.setCellFactory(new Callback<ListView<ManagementInterface<?>>, ListCell<ManagementInterface<?>>>() {

            @Override
            public ListCell<ManagementInterface<?>> call(ListView<ManagementInterface<?>> p) {

                ListCell<ManagementInterface<?>> cell = new ListCell<ManagementInterface<?>>() {

                    @Override
                    protected void updateItem(ManagementInterface<?> management, boolean bln) {
                        super.updateItem(management, bln);
                        if (management != null) {
                            setText("ID: " + management.getID() + "\tName: " + management.getName() + "\tBalance: "
                                    + management.getBalance());
                        }
                    }

                };

                return cell;
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXSetter.getInstance().getControllerAccount().getAccount().subscribe(this);
        updateListview();
        btnRemoveManagement.setDisable(true);
        btnViewManagement.setDisable(true);

    }

    private void popOver() {
        if (listAccount.getSelectionModel().getSelectedItem() != null) {
            FXSetter.getInstance().setControllerManagement(listAccount.getSelectionModel().getSelectedItem());
            try {
                VBox popUp = FXMLLoader.load(getClass().getResource("/Account/popOver.fxml"));
                PopOver popOver = new PopOver(popUp);
                popOver.show(listAccount);
                popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);
                popOver.setAutoFix(true);
                popOver.setAutoHide(true);
                popOver.setHideOnEscape(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void selectItem() {
        btnRemoveManagement.setDisable(false);
        btnViewManagement.setDisable(false);
        popOver();
    }

    public void addManagement() {
        FXSetter.getInstance().setPopUp(new Stage());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Account/AddManagementFX.fxml"));
        try {
            FXSetter.getInstance().getPopUp().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXSetter.getInstance().getPopUp().setTitle("Add Management - Jbudget");
        FXSetter.getInstance().getPopUp().show();
    }

    public void removeManagement() {
        if (listAccount.getSelectionModel().getSelectedItem() != null)
            FXSetter.getInstance().getControllerAccount()
                    .removeManagement(listAccount.getSelectionModel().getSelectedItem().getID());
    }

    public void viewManagement() {
        if (listAccount.getSelectionModel().getSelectedItem() != null) {
            FXSetter.getInstance().setControllerManagement(listAccount.getSelectionModel().getSelectedItem());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Management/ManagementFX.fxml"));
            try {
                FXSetter.getInstance().getStage().setScene(new Scene(loader.load()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            FXSetter.getInstance().getStage().setTitle("Wallet - Jbudget");
        }

    }

    public void goBack() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Group/GroupFX.fxml"));
        try {
            FXSetter.getInstance().getStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXSetter.getInstance().getStage().setTitle("Group - Jbudget");
    }

    @Override
    public void update(Object object) {
        if (object instanceof AccountInterface)
            if (FXSetter.getInstance().getControllerAccount().getAccount().equals(object))
                updateListview();

    }

}