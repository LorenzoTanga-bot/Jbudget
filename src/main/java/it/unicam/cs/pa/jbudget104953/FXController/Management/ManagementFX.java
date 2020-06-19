package it.unicam.cs.pa.jbudget104953.FXController.Management;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ManagementFX implements Initializable, EventListener {

    @FXML
    StackPane pListView;

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

    private ListView<FinancialInterface> listMovement;

    private ArrayList<FinancialInterface> transaction;

    private void updateLineChart() {
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        double balance = 0;
        Collections.sort(transaction);
        for (FinancialInterface financial : transaction) {
            balance += financial.getAmount();
            if (financial.getScheduled() != null)
                series.getData()
                        .add(new XYChart.Data<>(financial.getScheduled().getDate().get(Calendar.DAY_OF_MONTH) + "/"
                                + (financial.getScheduled().getDate().get(Calendar.MONTH) + 1) + "/"
                                + financial.getScheduled().getDate().get(Calendar.YEAR), balance));
            else
                series.getData()
                        .add(new XYChart.Data<>(financial.getDate().get(Calendar.DAY_OF_MONTH) + "/"
                                + (financial.getDate().get(Calendar.MONTH) + 1) + "/"
                                + financial.getDate().get(Calendar.YEAR), balance));

        }
        lcManagement.getData().removeAll(lcManagement.getData());
        lcManagement.getData().add(series);
    }

    private void updateLabel() {
        lBalance.setText("Balance: " + FXSetter.getInstance().getControllerManagement().getBalance());
        lName.setText("Name: " + FXSetter.getInstance().getControllerManagement().getName());
        lDescription.setText("Description: " + FXSetter.getInstance().getControllerManagement().getDescription());
    }

    private ListView<MovementInterface> listViewMovement() {
        ObservableList<MovementInterface> movementList = FXCollections.observableArrayList();

        for (Object objMovement : FXSetter.getInstance().getControllerManagement().getAllElement()) {
            MovementInterface movement = (MovementInterface) objMovement;
            movementList.add(movement);
        }

        ListView<MovementInterface> listManagement = new ListView<>(movementList);
        listManagement.setCellFactory(new Callback<ListView<MovementInterface>, ListCell<MovementInterface>>() {

            @Override
            public ListCell<MovementInterface> call(ListView<MovementInterface> p) {

                ListCell<MovementInterface> cell = new ListCell<MovementInterface>() {

                    @Override
                    protected void updateItem(MovementInterface movement, boolean bln) {
                        super.updateItem(movement, bln);
                        if (movement != null) {
                            setText("ID: " + movement.getID() + "\tType: " + movement.getType() + "\tBalance: "
                                    + movement.getBalance() + "\nTag: "
                                    + movement.getInitialTransaction().getTag().toString());
                        }
                    }

                };

                return cell;
            }
        });

        listManagement.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (listManagement.getSelectionModel().getSelectedItem() != null) {
                    btnRemoveMovement.setDisable(false);
                    popOver();
                } else
                    btnRemoveMovement.setDisable(true);
            }
        });

        return listManagement;
    }

    private ListView<FinancialInterface> listViewFinancial() {
        ObservableList<FinancialInterface> financialList = FXCollections.observableArrayList();

        for (FinancialInterface financial : transaction) {
            financialList.add(financial);
        }
        ListView<FinancialInterface> listMovement = new ListView<>(financialList);
        listMovement.setCellFactory(new Callback<ListView<FinancialInterface>, ListCell<FinancialInterface>>() {

            @Override
            public ListCell<FinancialInterface> call(ListView<FinancialInterface> p) {

                ListCell<FinancialInterface> cell = new ListCell<FinancialInterface>() {

                    @Override
                    protected void updateItem(FinancialInterface financial, boolean bln) {
                        super.updateItem(financial, bln);
                        if (financial != null) {
                            if (financial.getScheduled() == null)
                                setText("ID: " + financial.getID() + "\tType: " + financial.getTypeFinancial()
                                        + "\tAmount: " + financial.getAmount() + "\tDate: "
                                        + (new SimpleDateFormat("dd-MM-yyyy").format(financial.getDate().getTime()))
                                        + "\nPayment: " + financial.getTypePayment() + "\tTag: "
                                        + financial.getTag().toString());
                            else
                                setText("ID: " + financial.getID() + "\tType: " + financial.getTypeFinancial()
                                        + "\tAmount: " + financial.getAmount() + "\tDate Scheduled : "
                                        + (new SimpleDateFormat("dd-MM-yyyy")
                                                .format(financial.getScheduled().getDate().getTime()))
                                        + "\nPayment: " + financial.getTypePayment() + "\tTag: "
                                        + financial.getTag().toString());

                        }
                    }

                };

                return cell;
            }
        });
        return listMovement;
    }

    private void updateView() {
        pListView.getChildren().removeIf(x -> x.isVisible());
        if (FXSetter.getInstance().getTagForFilter() == null) {
            transaction = FXSetter.getInstance().getControllerManagement().getAllTransaction();
            listManagement = listViewMovement();
            listManagement.setPrefSize(580, 200);
            pListView.getChildren().add(listManagement);
            pListView.setPrefSize(580, 200);
        } else {
            transaction = FXSetter.getInstance().getControllerManagement()
                    .getAllTransactionFilterByTag(FXSetter.getInstance().getTagForFilter());
            listMovement = listViewFinancial();
            listMovement.setPrefSize(580, 200);
            pListView.getChildren().add(listMovement);
        }
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
                PopOver popOver = new PopOver(popUp);
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

    public void addElement() {
        FXSetter.getInstance().setTagForFilter(null);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Management/filterPopFX.fxml"));
            VBox popUp = loader.load();
            FilterPopFX filterPop = loader.<FilterPopFX>getController();
            filterPop.subscribe(this);
            PopOver popOver = new PopOver(popUp);
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
        FXSetter.getInstance().setTagForFilter(null);
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
        if (object instanceof FilterPopFX
                || FXSetter.getInstance().getControllerManagement().getManagement().equals(object))
            updateView();
    }

}