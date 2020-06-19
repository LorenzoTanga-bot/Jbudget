package it.unicam.cs.pa.jbudget104953.FXController.Management;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import it.unicam.cs.pa.jbudget104953.model.EventListener;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagList;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeFinancial;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.control.CheckComboBox;

public class AddDefaultElementFX implements Initializable, EventListener {

    private int numberMovement = 0;

    @FXML
    Label lTitle;

    @FXML
    ChoiceBox<TypeFinancial> cbType;

    @FXML
    TextField tfAmount;

    @FXML
    TextArea taDescription;

    @FXML
    RadioButton rbScheduled;

    @FXML
    Label lDate;

    @FXML
    DatePicker dateScheduled;

    @FXML
    CheckComboBox<TagInterface> ccbTag;

    @FXML
    Button btnTag;

    @FXML
    Button btOneMore;

    @FXML
    Button btNext;

    private void initSingle() {
        lTitle.setText("Single");
        btOneMore.setDisable(true);
        btNext.setText("ADD");

    }

    private void initMulti() {
        lTitle.setText("Multi");
        btNext.setText("ADD");
    }

    private void initRepeated() {
        lTitle.setText("Repeated");
        btOneMore.setDisable(true);
        btNext.setText("NEXT");
    }

    private void initLoan() {
        lTitle.setText("Loan");
        btOneMore.setDisable(true);
        btNext.setText("NEXT");
    }

    private boolean getInput() {
        if (!cbType.getSelectionModel().isEmpty() && !tfAmount.getText().isEmpty()
                && !taDescription.getText().isEmpty()) {
            FXSetter.getInstance().getInfo().put("TypeFinancial" + numberMovement,
                    cbType.getSelectionModel().getSelectedItem().toString());
            FXSetter.getInstance().getInfo().put("Amount" + numberMovement, tfAmount.getText());
            FXSetter.getInstance().getInfo().put("Description" + numberMovement, taDescription.getText());
            FXSetter.getInstance().getInfo().put("DateScheduled" + numberMovement, null);
            if (rbScheduled.isSelected())
                FXSetter.getInstance().getInfo().put("DateScheduled" + numberMovement,
                        dateScheduled.getValue().getDayOfMonth() + "/" + dateScheduled.getValue().getMonthValue() + "/"
                                + dateScheduled.getValue().getYear());

            String IDtag = "";
            for (TagInterface tag : ccbTag.getCheckModel().getCheckedItems()) {
                IDtag += tag.getID() + ",";
            }
            FXSetter.getInstance().getInfo().put("Tag" + numberMovement, IDtag);

            return true;
        }
        return false;

    }

    private void add() {
        if (getInput()) {
            FXSetter.getInstance().getControllerManagement().addElement(FXSetter.getInstance().getInfo());
            FXSetter.getInstance().getPopUp().close();
        }

    }

    private void next() {
        getInput();
        FXSetter.getInstance().getPopUp().close();
        FXSetter.getInstance().setPopUp(new Stage());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Management/AdditionalInformation.fxml"));
        try {
            FXSetter.getInstance().getPopUp().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXSetter.getInstance().getPopUp().setTitle("Add Element - Jbudget");
        FXSetter.getInstance().getPopUp().show();
    }

    private void initButton() {
        btNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                switch (btNext.getText()) {
                    case "ADD":
                        add();
                        break;
                    case "NEXT":
                        next();
                        break;
                }

            }
        });
    }

    private void initTagBox() {
        ObservableList<TagInterface> tagList = FXCollections.observableArrayList();

        if (!cbType.getSelectionModel().isEmpty())
            for (TagInterface tag : TagList.getInstance().getTag().get(cbType.getSelectionModel().getSelectedItem()))
                tagList.add(tag);

        ccbTag.getItems().removeAll(ccbTag.getItems());
        ccbTag.getItems().addAll(tagList);
    }

    private void initType() {
        for (TypeFinancial type : TypeFinancial.values()) {
            cbType.getItems().add(type);
        }
        cbType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TypeFinancial>() {
            @Override
            public void changed(ObservableValue<? extends TypeFinancial> observable, TypeFinancial oldValue,
                    TypeFinancial newValue) {
                initTagBox();
            }
        });
    }

    private void initScheduled() {
        lDate.setDisable(true);
        dateScheduled.setDisable(true);
        rbScheduled.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    lDate.setDisable(false);
                    dateScheduled.setDisable(false);
                } else {
                    lDate.setDisable(true);
                    dateScheduled.setDisable(true);
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        switch (FXSetter.getInstance().getInfo().get("TypeMovement")) {
            case "SINGLE":
                initSingle();
                break;
            case "MULTI":
                initMulti();
                break;
            case "REPEATED":
                initRepeated();
                break;
            case "LOAN":
                initLoan();
                break;
        }
        FXSetter.getInstance().getInfo().put("NumberMovement", "0");
        TagList.getInstance().subscribe(this);
        initButton();
        initType();
        initScheduled();
    }

    public void addTag() {
        FXSetter.getInstance().setPopUpTag(new Stage());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Management/AddTagFX.fxml"));
        try {
            FXSetter.getInstance().getPopUpTag().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXSetter.getInstance().getPopUpTag().setTitle("Add Tag - Jbudget");
        FXSetter.getInstance().getPopUpTag().show();
    }

    private void resetInput() {
        cbType.getSelectionModel().clearSelection();
        tfAmount.clear();
        taDescription.clear();
        rbScheduled.disarm();
        dateScheduled.disarm();
    }

    public void oneMore() {
        getInput();
        resetInput();
        numberMovement++;
    }

    public void goBack() {
        FXSetter.getInstance().getPopUp().close();
    }

    public void returnBack() {
        FXSetter.getInstance().getPopUp().close();
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

    @Override
    public void update(Object object) {
        initTagBox();
    }

}