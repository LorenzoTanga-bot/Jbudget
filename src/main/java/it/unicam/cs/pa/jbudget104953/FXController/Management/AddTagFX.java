package it.unicam.cs.pa.jbudget104953.FXController.Management;

import java.net.URL;
import java.util.ResourceBundle;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import it.unicam.cs.pa.jbudget104953.model.EventListener;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Tag;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagList;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeFinancial;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddTagFX implements Initializable {

    @FXML
    ChoiceBox<TypeFinancial> cbType;

    @FXML
    TextField tfName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (TypeFinancial type : TypeFinancial.values()) {
            cbType.getItems().add(type);
        }
    }

    public void add() {
        if (!tfName.getText().isEmpty() && !cbType.getSelectionModel().isEmpty()) {
            TagList.getInstance().addTag(cbType.getSelectionModel().getSelectedItem(), new Tag(tfName.getText()));
            FXSetter.getInstance().getPopUpTag().close();
        }
    }

    public void goBack() {
        FXSetter.getInstance().getPopUpTag().close();
    }

}