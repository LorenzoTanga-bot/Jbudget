package it.unicam.cs.pa.jbudget104953.FXController;

import java.util.Map;

import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerAccountInterface;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerGroupInterface;
import it.unicam.cs.pa.jbudget104953.controller.modelController.ControllerManagementInterface;
import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import javafx.stage.Stage;

public interface FXSetterInterface {
    public Stage getStage();

    public boolean setStage(Stage stage);

    public ControllerGroupInterface getControllerGroup();

    public boolean setControllerGroup(GroupInterface group);

    public ControllerAccountInterface getControllerAccount();

    public boolean setControllerAccount(AccountInterface account);

    public ControllerManagementInterface getControllerManagement();

    public boolean setControllerManagement(ManagementInterface<?> management);

    public Stage getPopUp();

    public boolean setPopUp(Stage stage);

    public Map<String, String> getInfo();

    public boolean setInfo(Map<String, String> elementMode);

    public Stage getPopUpTag();

    public void setPopUpTag(Stage popUpTag);

}