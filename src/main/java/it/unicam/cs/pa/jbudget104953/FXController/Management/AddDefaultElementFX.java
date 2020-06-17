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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.control.CheckComboBox;

public class AddDefaultElementFX implements Initializable, EventListener {

    @FXML
    Label lTitle;

    @FXML
    ChoiceBox<TypeFinancial> cbType;

    @FXML
    TextField tfDescription;

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
        btNext.setText("Next");
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
            case "Single":
                initSingle();
                break;
            case "Multi":
                initMulti();
                break;
            case "Repeated":
                initRepeated();
                break;
            case "Loan":
                initLoan();
        }
        TagList.getInstance().subscribe(this);
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