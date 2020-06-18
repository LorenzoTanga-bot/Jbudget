package it.unicam.cs.pa.jbudget104953.FXController.Management;

import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import it.unicam.cs.pa.jbudget104953.model.EventListener;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.MovementInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ManagementFX implements Initializable, EventListener {

    @FXML
    ListView<MovementInterface> listManagement;

    @FXML
    LineChart<GregorianCalendar, Double> lcManagement;

    @FXML
    Label lBalance;

    @FXML
    Label lName;

    @FXML
    Label lDescription;

    @FXML
    Button btnAddMovement;

    @FXML
    Button btnRemoveMovement;

    @FXML
    Button btnViewMovement;

    private void updateView() {
        ObservableList<MovementInterface> movementList = FXCollections.observableArrayList();
        XYChart.Series<GregorianCalendar, Double> series = new XYChart.Series<>();
        for (Object objMovement : FXSetter.getInstance().getControllerManagement().getAllElement()) {
            MovementInterface movement = (MovementInterface) objMovement;
            movementList.add(movement);
            series.getData().add(new XYChart.Data<>(movement.getInitialTransaction().getDate(), movement.getBalance()));
        }

        lcManagement.getData().add(series);
        listManagement.setItems(movementList);
        // TODO sistemare la creazione della linechart e aggiungere i filtri
        lBalance.setText("Balance: " + FXSetter.getInstance().getControllerManagement().getBalance());
        lName.setText("Name: " + FXSetter.getInstance().getControllerManagement().getName());
        lDescription.setText("Description: " + FXSetter.getInstance().getControllerManagement().getDescription());
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Management/SelectElementFX.fxml"));
        try {
            FXSetter.getInstance().getPopUp().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXSetter.getInstance().getPopUp().setTitle("Add Element - Jbudget");
        FXSetter.getInstance().getPopUp().show();
    }

    public void removeElement() {
        if (listManagement.getSelectionModel().getSelectedItem() != null)
            FXSetter.getInstance().getControllerManagement()
                    .removeElement(((MovementInterface) listManagement.getSelectionModel().getSelectedItem()).getID());
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