package it.unicam.cs.pa.jbudget104953.FXController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerAccount;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerAccountInterface;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerGroup;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerGroupInterface;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerManagement;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerManagementInterface;
import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.MovementInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import javafx.stage.Stage;

public class FXSetter implements FXSetterInterface {

    private static FXSetterInterface setter = null;
    private Stage stage;
    private ControllerGroupInterface group;
    private ControllerAccountInterface account;
    private ControllerManagementInterface management;
    private Stage popUp;
    private Stage popUpTag;
    private Map<String, String> info;
    private MovementInterface movementSelected;
    private ArrayList<TagInterface> tagForFilter;

    private FXSetter() {
        group = new ControllerGroup();
        account = new ControllerAccount();
        management = new ControllerManagement();
        info = new HashMap<>();
    }

    public static FXSetterInterface getInstance() {
        if (setter == null)
            setter = new FXSetter();
        return setter;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public boolean setStage(Stage stage) {
        this.stage = stage;
        return true;
    }

    @Override
    public ControllerGroupInterface getControllerGroup() {
        return group;
    }

    @Override
    public boolean setControllerGroup(GroupInterface group) {
        this.group.setGroup(group);
        return true;
    }

    @Override
    public ControllerAccountInterface getControllerAccount() {
        return account;
    }

    @Override
    public boolean setControllerAccount(AccountInterface account) {
        this.account.setAccount(account);
        return true;
    }

    @Override
    public ControllerManagementInterface getControllerManagement() {
        return management;
    }

    @Override
    public boolean setControllerManagement(ManagementInterface<?> management) {
        this.management.setManagement(management);
        return true;
    }

    @Override
    public Stage getPopUp() {
        return popUp;
    }

    @Override
    public boolean setPopUp(Stage stage) {
        this.popUp = stage;
        return true;
    }

    @Override
    public Map<String, String> getInfo() {
        return info;
    }

    @Override
    public boolean setInfo(Map<String, String> info) {
        this.info = info;
        return true;
    }

    @Override
    public Stage getPopUpTag() {
        return popUpTag;
    }

    @Override
    public boolean setPopUpTag(Stage popUpTag) {
        this.popUpTag = popUpTag;
        return true;

    }

    @Override
    public MovementInterface getMovementSelected() {
        return movementSelected;
    }

    @Override
    public boolean setMovementSelected(MovementInterface movementSelected) {
        this.movementSelected = movementSelected;
        return true;

    }

    @Override
    public ArrayList<TagInterface> getTagForFilter() {
        return tagForFilter;
    }

    @Override
    public boolean setTagForFilter(ArrayList<TagInterface> tagForFilter) {
        this.tagForFilter = tagForFilter;
        return true;

    }

}