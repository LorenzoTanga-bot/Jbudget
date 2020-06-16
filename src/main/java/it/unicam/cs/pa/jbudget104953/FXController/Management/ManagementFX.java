package it.unicam.cs.pa.jbudget104953.FXController.Management;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import it.unicam.cs.pa.jbudget104953.model.EventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ManagementFX implements Initializable, EventListener {

    @FXML
    ListView<Object> listManagement;

    @FXML
    Button btnAddMovement;

    @FXML
    Button btnRemoveMovement;

    @FXML
    Button btnViewMovement;

    private void updateView() {
        ObservableList<Object> movementList = FXCollections.observableArrayList();
        for (Object movement : FXSetter.getInstance().getControllerManagement().getAllElement()) {
            movementList.add(movement);
        }
        listManagement.setItems(movementList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXSetter.getInstance().getControllerManagement().getManagement().subscribe(this);
        updateView();
        btnRemoveMovement.setDisable(true);
        btnViewMovement.setDisable(true);

    }

    public void selectItem() {
        btnRemoveMovement.setDisable(false);
        btnViewMovement.setDisable(false);
    }

    public void addElement() {
        FXSetter.getInstance().setPopUp(new Stage());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Management/AddElementFX.fxml"));
        try {
            FXSetter.getInstance().getPopUp().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXSetter.getInstance().getPopUp().setTitle("Add Element - Jbudget");
        FXSetter.getInstance().getPopUp().show();
    }

    public void removeElement() {

    }

    public void filterElement() {

    }

    public void goBack() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Account/AccountFX.fxml"));
        try {
            FXSetter.getInstance().getStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXSetter.getInstance().getStage().setTitle("Account - Jbudget");
    }

    @Override
    public void update(Object object) {
        if (FXSetter.getInstance().getControllerManagement().getManagement().equals(object))
            updateView();
    }

}