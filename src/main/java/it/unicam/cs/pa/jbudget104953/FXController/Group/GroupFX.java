package it.unicam.cs.pa.jbudget104953.FXController.Group;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.EventListener;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
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

public class GroupFX implements Initializable, EventListener {

    @FXML
    ListView<AccountInterface> listGroup;

    @FXML
    Button btnAddAccount;

    @FXML
    Button btnRemoveAccount;

    @FXML
    Button btnViewAccount;

    private void updateListview() {
        ObservableList<AccountInterface> accountList = FXCollections.observableArrayList();
        for (AccountInterface account : FXSetter.getInstance().getControllerGroup().getAccounts())
            accountList.add(account);

        listGroup.setItems(accountList);
        listGroup.setCellFactory(new Callback<ListView<AccountInterface>, ListCell<AccountInterface>>() {

            @Override
            public ListCell<AccountInterface> call(ListView<AccountInterface> p) {

                ListCell<AccountInterface> cell = new ListCell<AccountInterface>() {

                    @Override
                    protected void updateItem(AccountInterface account, boolean bln) {
                        super.updateItem(account, bln);
                        if (account != null) {
                            setText("ID: " + account.getID() + "\tName: " + account.getName() + " "
                                    + account.getSurname() + "\tBalance outside " + account.getBalanceOutside()
                                    + "\tBalance inside: " + account.getBalanceInside());
                        }
                    }

                };

                return cell;
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXSetter.getInstance().getStage().setTitle("Group - Jbudget");
        FXSetter.getInstance().getControllerGroup().getGroup().subscribe(this);
        updateListview();
        btnRemoveAccount.setDisable(true);
        btnViewAccount.setDisable(true);

    }

    private void popOver() {
        if (listGroup.getSelectionModel().getSelectedItem() != null) {
            FXSetter.getInstance().setControllerAccount(listGroup.getSelectionModel().getSelectedItem());
            try {
                VBox popUp = FXMLLoader.load(getClass().getResource("/Group/popOver.fxml"));
                PopOver popOver = new PopOver(popUp);
                popOver.show(listGroup);
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
        btnRemoveAccount.setDisable(false);
        btnViewAccount.setDisable(false);
        popOver();
    }

    public void addAccount() {
        FXSetter.getInstance().setPopUp(new Stage());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Group/AddAccountFX.fxml"));
        try {
            FXSetter.getInstance().getPopUp().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXSetter.getInstance().getPopUp().setTitle("Add Account - Jbudget");
        FXSetter.getInstance().getPopUp().show();
    }

    public void removeAccount() {
        if (listGroup.getSelectionModel().getSelectedItem() != null)
            FXSetter.getInstance().getControllerGroup()
                    .removeAccount(listGroup.getSelectionModel().getSelectedItem().getID());
    }

    public void viewAccount() {
        if (listGroup.getSelectionModel().getSelectedItem() != null) {
            FXSetter.getInstance().setControllerAccount(listGroup.getSelectionModel().getSelectedItem());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Account/AccountFX.fxml"));
            try {
                FXSetter.getInstance().getStage().setScene(new Scene(loader.load()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            FXSetter.getInstance().getStage().setTitle("Account - Jbudget");
        }

    }

    @Override
    public void update(Object object) {
        if (object instanceof GroupInterface)
            if (FXSetter.getInstance().getControllerGroup().getGroup().equals(object))
                updateListview();
        listGroup.refresh();

    }

}