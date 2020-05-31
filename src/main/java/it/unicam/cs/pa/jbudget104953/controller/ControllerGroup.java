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

}