package it.unicam.cs.pa.jbudget104953.FXController.Management;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.controlsfx.control.CheckComboBox;

import it.unicam.cs.pa.jbudget104953.FXController.FXSetter;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagList;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeFinancial;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class filterPopFX implements Initializable {

    @FXML
    CheckComboBox<TagInterface> ccbTag;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TagInterface> tagList = FXCollections.observableArrayList();
        for (Entry<TypeFinancial, ArrayList<TagInterface>> tagMap : TagList.getInstance().getTag().entrySet())
            for (TagInterface tag : tagMap.getValue())
                tagList.add(tag);
        ccbTag.getItems().removeAll(ccbTag.getItems());
        ccbTag.getItems().addAll(tagList);
    }

    public void clear() {
        ccbTag.getCheckModel().clearChecks();
    }

    public void applay() {
        FXSetter.getInstance()
                .setTagForFilter(ccbTag.getItems().stream().collect(Collectors.toCollection(ArrayList::new)));

    }

}