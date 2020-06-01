package it.unicam.cs.pa.jbudget104953.controller;

import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.Account;
import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.Group;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;

public class ControllerGroup {

    private GroupInterface group = new Group();

    public boolean setGroup(GroupInterface group) {
        this.group = group;
        return true;
    }

    public GroupInterface getGroup() {
        return group;
    }

    public int getID() {
        return group.getID();
    }

    public double getBalance() {
        return group.getBalance();
    }

    public boolean addAccount(Map<String, String> info) {
        return group.addAccount(new Account(info.get("Name"), info.get("Surname"), info.get("Description")));
    }

    public boolean removeAccount(int ID) {
        return group.removeAccount(group.getAccount(ID));
    }

    public AccountInterface getAccount(int ID) {
        return group.getAccount(ID);
    }

    public String toString() {
        return group.toString();
    }

}
/**
 * <AnchorPane maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity"
 * minWidth="-Infinity" prefHeight="400.0" prefWidth="600.0" style=
 * "-fx-background-color: #383461;" xmlns="http://javafx.com/javafx/11.0.1"
 * xmlns:fx="http://javafx.com/fxml/1" fx:controller=
 * "it.unicam.cs.pa.jbudget104953.controller.FXaddAccount"> <children>
 * <TextField fx:id="name" layoutX="246.0" layoutY="50.0" promptText="&gt;"
 * style="-fx-background-color: #624370;" />
 * <TextField fx:id="surname" layoutX="246.0" layoutY="92.0" maxHeight=
 * "-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity"
 * promptText="&gt;" style="-fx-background-color: #624370;" />
 * <TextArea fx:id="description" layoutX="246.0" layoutY="132.0" maxHeight=
 * "-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity"
 * prefHeight="200.0" prefWidth="200.0" promptText="&gt;" style=
 * "-fx-background-color: #624370;" />
 * <Label layoutX="155.0" layoutY="55.0" text="Name" textFill="WHITE" />
 * <Label layoutX="155.0" layoutY="97.0" text="Surname" textFill="WHITE" />
 * <Label layoutX="155.0" layoutY="132.0" text="Description" textFill="WHITE" />
 * <Button layoutX="278.0" layoutY="359.0" mnemonicParsing="false" onAction=
 * "#add" style="-fx-background-color: #624370;" text="ADD" />
 * <Label layoutX="264.0" layoutY="14.0" text="Add Account" textFill="WHITE" />
 * </children> </AnchorPane>
 */