package it.unicam.cs.pa.jbudget104953.FXController.Management;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import it.unicam.cs.pa.jbudget104953.model.EventListener;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ManagementFX implements Initializable, EventListener {

    @FXML
    Pane pListView;

    @FXML
    LineChart<String, Double> lcManagement;

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
    Button btnFilterElement;

    private ListView<MovementInterface> listManagement;

    private ListView<FinancialInterface> listFinancial;

    private ArrayList<FinancialInterface> transaction;

    private PopOver popOver;

    private void updateLineChart() {
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        double balance = 0;
        Collections.sort(transaction);
        for (FinancialInterface financial : transaction) {
            balance += financial.getAmount();
            if (financial.getScheduled() != null)
                series.getData().add(new XYChart.Data<>(financial.getScheduled().getDate().get(Calendar.DAY_OF_MONTH)
                        + "/" + (financial.getScheduled().getDate().get(Calendar.MONTH)), balance));
            else
                series.getData().add(new XYChart.Data<>(
                        financial.getDate().get(Calendar.DAY_OF_MONTH) + "/" + financial.getDate().get(Calendar.MONTH),
                        balance));

        }
        lcManagement.getData().removeAll(lcManagement.getData());
        lcManagement.getData().add(series);
    }

    private void updateLabel() {
        lBalance.setText("Balance: " + FXSetter.getInstance().getControllerManagement().getBalance());
        lName.setText("Name: " + FXSetter.getInstance().getControllerManagement().getName());
        lDescription.setText("Description: " + FXSetter.getInstance().getControllerManagement().getDescription());
    }

    private void updateListView() {
        ObservableList<MovementInterface> movementList = FXCollections.observableArrayList();

        for (Object objMovement : FXSetter.getInstance().getControllerManagement().getAllElement()) {
            MovementInterface movement = (MovementInterface) objMovement;
            movementList.add(movement);
        }

        listManagement.setItems(movementList);
        listManagement.setCellFactory(new Callback<ListView<MovementInterface>, ListCell<MovementInterface>>() {

            @Override
            public ListCell<MovementInterface> call(ListView<MovementInterface> p) {

                ListCell<MovementInterface> cell = new ListCell<MovementInterface>() {

                    @Override
                    protected void updateItem(MovementInterface movement, boolean bln) {
                        super.updateItem(movement, bln);
                        if (movement != null) {
                            setText("ID: " + movement.getID() + "\tType: " + movement.getType() + "\tBalance: "
                                    + movement.getBalance());
                        }
                    }

                };

                return cell;
            }
        });
    }

    private void updateView() {
        updateListView();
        updateLineChart();
        updateLabel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXSetter.getInstance().getControllerManagement().getManagement().subscribe(this);
        updateView();
        btnRemoveMovement.setDisable(true);

    }

    private void popOver() {
        if (listManagement.getSelectionModel().getSelectedItem() != null) {
            FXSetter.getInstance().setMovementSelected(listManagement.getSelectionModel().getSelectedItem());
            try {
                VBox popUp = FXMLLoader.load(getClass().getResource("/Management/popOver.fxml"));
                popOver = new PopOver(popUp);
                popOver.show(listManagement);
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
        btnRemoveMovement.setDisable(false);
        popOver();
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

        try {
            VBox popUp = FXMLLoader.load(getClass().getResource("/Management/filterPopFX.fxml"));
            popOver = new PopOver(popUp);
            popOver.show(btnFilterElement);
            popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);
            popOver.setAutoFix(true);
            popOver.setAutoHide(true);
            popOver.setHideOnEscape(true);
            popOver.setDetachable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        if (object instanceof filterPopFX)
            ;
        if (FXSetter.getInstance().getControllerManagement().getManagement().equals(object))
            updateView();
    }

}